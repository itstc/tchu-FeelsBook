package com.thomaschu.tchu_feelsbook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "emotiondb.sav";
    public static final String EMOTION_ID = "com.thomaschu.tchu_feelsbook.EMOTION_ID";
    public static final String EMOTION_LIST = "com.thomaschu.tchu.feelsbook.EMOTION_LIST";

    Dialog dialog;
    Drawable emotionState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importEmotionToController();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DESTROY MAIN", "destroying main activity and saving...");
        saveToFile();
    }

    /**
     onClick function handles all buttons pressed on main activity layout
     @params view View (The Button)
     @return void
     **/
    public void onClick(View view) {
        // for emotion clicks we redirect to a modal to add comment
        switch(view.getId()) {
            case R.id.JoyButton:
            case R.id.LoveButton:
            case R.id.SurpriseButton:
            case R.id.SadButton:
            case R.id.AngryButton:
            case R.id.FearButton:
                // notify that emote is created
                EmotionController.setCount(view.getContentDescription().toString(), 1);
                Toast.makeText(MainActivity.this, view.getContentDescription().toString() + " added!", Toast.LENGTH_SHORT).show();
                EmotionController.get().add(new Emotion(view.getContentDescription().toString(), "", new Date()));
                break;
            case R.id.HistoryButton:
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    private void importEmotionToController() {
        EmotionList importedEmotions = new EmotionList();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            // append emotion line by line with format type|comment|date
            String currentLine = br.readLine();
            while(currentLine != null) {
                importedEmotions.add(EmotionController.deserialize(currentLine));
                currentLine = br.readLine();
            }
        }catch(FileNotFoundException e) {
            // file not found, create one
            createNewFile();
        }catch(IOException e) {
        }catch(EmotionParseException e) {
        }
        EmotionController.importEmotions(importedEmotions);
    }

    private void createNewFile() {
        // create a new file since we didn't find one
        try {
            FileOutputStream op = openFileOutput(FILENAME, MODE_PRIVATE);
            op.flush();
            op.close();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }


    private void saveToFile() {
        try {
            // write the serialized list to file
            FileOutputStream op = openFileOutput(FILENAME, MODE_PRIVATE);
            op.write(EmotionController.serialize().getBytes());
        } catch (FileNotFoundException e) {
            createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
