package com.thomaschu.tchu_feelsbook;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/*
* EmotionsController handles all the modifications to the EmotionsModel
* either by eventListeners within activities or dialog form submissions. Acts
* like a middleman between the View and Model
* */
public class EmotionsController {
    private static final String FILENAME = "feelsbook.db";
    private EmotionsModel model;

    public EmotionsController(EmotionsModel model) {
        this.model = model;
    }

    /*
     * deserialized function creates an emotion from a line from file
     * @param {String input} our line to parse
     * @return {Emotion} a new Emotion instance from input
     * @exception {EmotionParseException} happens if line can't be parsed
     * */
    private Emotion deserialize(String input) throws EmotionParseException {
        String[] args = input.split(Pattern.quote("|"));
        // unable to parse correctly
        if(args.length != 3) throw new EmotionParseException();

        // create emotion from our input string
        return new Emotion(args[0], args[1], DateConverter.getDateFromString(args[2]));
    }

    /*
     * serialize function will convert our list to string, which can be saved to file
     * @param {} takes from instance list
     * @return {String} returns a string on all emotions from our emotionLog attribute
     * */
    private String serialize() {
        StringBuilder results = new StringBuilder();
        for(Emotion em: this.model.getEmotions().getList()) {
            results.append(String.format("%s|%s|%s\n",
                    em.getEmotionType(),
                    em.getComment(),
                    em.getDate()
            ));
        }
        return results.toString();
    }

    public void importEmotions(Context context) {
        FileInputStream fis = null;
        try {
            // open database file and parse every line
            fis = context.openFileInput(FILENAME);
            BufferedReader bis = new BufferedReader(new InputStreamReader(fis));

            // iterate lines
            String line = bis.readLine();
            while(line != null) {
                Emotion currentEmote = deserialize(line);
                this.model.addEmotion(currentEmote);
                line = bis.readLine();
            }
        } catch (FileNotFoundException e) {
            // no database file found, make a empty one
            createDatabaseFile(context);
        } catch(IOException e) {
            // Not parsing error, close app
            e.printStackTrace();
        } catch(EmotionParseException e) {
        } finally {
            closeInputStream(fis);
        }

    }

    public void saveEmotions(Context context) {
        // write serialized emotions to database
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(serialize().getBytes());
            fos.close();
        } catch (IOException e) {
            createDatabaseFile(context);
            e.printStackTrace();
        }
    }

    private void createDatabaseFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeInputStream(FileInputStream fis) {
        // close file input if we can't parse any further
        if(fis == null) return;
        try {
            fis.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addEmotion(String type, String comment, Date date) {
        this.model.addEmotion(new Emotion(type, comment, date));
    }

    public void removeEmotion(Emotion e) {
        this.model.removeEmotion(e);
    }

    public void editEmotion(Emotion e, String comment, Date date) {
        this.model.editEmotion(e, comment, date);
    }

    public EmotionList getEmotions() {
        return this.model.getEmotions();
    }

    public LinkedHashMap<String, Integer> getCounter() {
        return this.model.getEmotionCount();
    }

}
