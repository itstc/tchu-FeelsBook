package com.thomaschu.tchu_feelsbook;

import android.util.Log;

import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class EmotionController implements EmotionConstants {
    private EmotionList emotionLog;

    /* a static class to hold our single instance of the controller */
    private static class EmotionHolder {
        private static final EmotionController INSTANCE = new EmotionController();
    }

    // EmotionController constructor creates a new EmotionList and will populate it from file
    private EmotionController() {
        this.emotionLog = new EmotionList();
    }

    /*
    * getter for our emotionLog attribute
    * */
    public static EmotionList get() {
        return EmotionHolder.INSTANCE.emotionLog;
    }

    /*
    * deserialized function creates an emotion from a line from file
    * @param {String input} our line to parse
    * @return {Emotion} a new Emotion instance from input
    * @exception {EmotionParseException} happens if line can't be parsed
    * */
    public static Emotion deserialize(String input) throws EmotionParseException {
        String[] args = input.split(Pattern.quote("|"));
        // unable to parse correctly
        if(args.length != 3) throw new EmotionParseException();

        // create emotion from our input string
        Log.d("READ_DATE", args[2]);
        return new Emotion(args[0], args[1], DateConverter.getDateFromString(args[2]));
    }

    /*
    * serialize function will convert our list to string, which can be saved to file
    * @param {} takes from instance list
    * @return {String} returns a string on all emotions from our emotionLog attribute
    * */
    public static String serialize() {
        StringBuilder results = new StringBuilder();
        for(Emotion em: EmotionHolder.INSTANCE.emotionLog.getList()) {
            results.append(String.format("%s|%s|%s\n",
                    em.getEmotionType(),
                    em.getComment(),
                    em.getDate()
            ));
        }
        return results.toString();
    }

    public static void importEmotions(EmotionList newList) {
        EmotionHolder.INSTANCE.emotionLog = newList;
    }


}
