package com.thomaschu.tchu_feelsbook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EMOTION_ID = "com.thomaschu.tchu_feelsbook.EMOTION_ID";
    public static final String EMOTION_LIST = "com.thomaschu.tchu.feelsbook.EMOTION_LIST";

    private EmotionList emotionsHistory;

    Dialog dialog;
    Drawable emotionState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionsHistory = new EmotionList();

        dialog = new Dialog(this);
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
                // prepare a popup form for additional comments and finalizing emotion
                emotionState = view.getBackground();
                // send a new instance of the drawable to the popup image
                createPopupForm(emotionState.getConstantState().newDrawable(), view.getContentDescription());

                break;
            case R.id.HistoryButton:
            default:
                break;
        }
    }

    // switch to history of emotions activity
    public void historyLogClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra(EMOTION_LIST, emotionsHistory.toStringArray());
        startActivity(intent);
    }

    // submit emotion creating new emotion to file
    public void emotionDialogSubmit(View view) {
        View emotionImage = dialog.findViewById(R.id.EmotionImage);
        View comment = dialog.findViewById(R.id.CommentBox);
        Emotion feeling = new Emotion(
                emotionImage.getContentDescription().toString(),
                comment.toString(),
                new Date());
        emotionsHistory.add(feeling);

        dialog.dismiss();
        Toast.makeText(MainActivity.this, feeling.getEmotionType(), Toast.LENGTH_SHORT).show();
    }

    // set modal popup functionality
    private void createPopupForm(Drawable emotionDrawable, CharSequence emotionType) {
        // set dialog to popup
        dialog.setContentView(R.layout.emotion_form_popup);

        // set functionality of exit button
        TextView closeButton = dialog.findViewById(R.id.ExitButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // change emotion image to whats clicked
        ImageView EmotionImage = dialog.findViewById(R.id.EmotionImage);
        EmotionImage.setImageDrawable(emotionDrawable);
        EmotionImage.setContentDescription(emotionType);

        // prepare popup for display
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
