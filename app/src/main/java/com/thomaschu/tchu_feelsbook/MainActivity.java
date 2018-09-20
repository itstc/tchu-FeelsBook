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

public class MainActivity extends AppCompatActivity {

    public static final String EMOTION_ID = "com.thomaschu.tchu_feelsbook.EMOTION_ID";

    Dialog dialog;
    Drawable emotionState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new Dialog(this);
    }

    /**
     onClick function handles all buttons pressed on main activity layout
     @params view View (The Button)
     @return void
     **/
    public void onClick(View view) {
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
                createPopupForm(emotionState.getConstantState().newDrawable());
                break;
            case R.id.HistoryButton:
            default:
                break;
        }
    }

    private void createPopupForm(Drawable emotionDrawable) {
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

        // prepare popup for display
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
