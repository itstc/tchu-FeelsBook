package com.thomaschu.tchu_feelsbook;

import android.app.Dialog;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TView<EmotionsModel> {

    private PopUpDialog dialog;
    private static final String saveButtonText = "Save Button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FeelsBookApplication.getEmotionsModel().addView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FeelsBookApplication.getEmotionsModel().removeView(this);
    }

    public void update(EmotionsModel m) {

    }

    /**
     onClick function handles all buttons pressed on main activity layout
     @params view View (The Button)
     @return void
     **/
    public void onClick(View view) {
        // for emotion clicks we redirect to a modal to add comment
        view.setEnabled(false);
        switch(view.getId()) {
            case R.id.JoyButton:
            case R.id.LoveButton:
            case R.id.SurpriseButton:
            case R.id.SadButton:
            case R.id.AngryButton:
            case R.id.FearButton:
                createFormPopUp(view.getContentDescription().toString(), (Button)view);
                break;
            case R.id.HistoryButton:
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    private void createFormPopUp(final String type, final Button formButton) {
        dialog = new PopUpDialog(this, type);
        dialog.findViewById(R.id.DateTimeField).setVisibility(View.GONE);
        // set our submit to add emotions
        dialog.setSubmitButton(saveButtonText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set debounce to avoid multiple clicks
                v.setEnabled(false);

                // add and save emotions then re-enable button
                TextView comment = dialog.findViewById(R.id.CommentBox);
                FeelsBookApplication.getEmotionsController().addEmotion(type, comment.getText().toString(), new Date());
                FeelsBookApplication.getEmotionsController().saveEmotions(getApplicationContext());
                Toast.makeText(MainActivity.this, type + " added!", Toast.LENGTH_SHORT).show();

                // re-enable after operations
                v.setEnabled(true);
                formButton.setEnabled(true);
                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
