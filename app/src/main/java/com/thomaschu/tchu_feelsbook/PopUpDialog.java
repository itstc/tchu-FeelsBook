package com.thomaschu.tchu_feelsbook;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class PopUpDialog extends Dialog implements EmotionConstants {

    // default dialog without image, comment, or date
    public PopUpDialog(Context context) {
        super(context);
        setContentView(R.layout.edit_form_popup);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // set X button to dismiss dialog on click
        findViewById(R.id.ExitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public PopUpDialog(@NonNull Context context, String emotionType) {
        this(context);
        ImageView image = findViewById(R.id.EmotionImage);
        image.setImageDrawable(getEmotionDrawable(emotionType));
    }

    public PopUpDialog(Context context, Emotion e) {
        this(context, e.getEmotionType());

        TextView comment = findViewById(R.id.CommentBox);
        comment.setText(e.getComment());

        TextView dateInput = findViewById(R.id.DateEdit);
        TextView timeInput = findViewById(R.id.TimeEdit);
        Date originalDate = DateConverter.getDateFromString(e.getDate());
        String dateString = DateConverter.getDateFormatString(originalDate);
        String timeString = DateConverter.getTimeFormatString(originalDate);
        dateInput.setText(dateString);
        timeInput.setText(timeString);

    }

    public void setSubmitButton(String text, View.OnClickListener listener) {
        Button submit = findViewById(R.id.SubmitEmotion);
        submit.setText(text);
        submit.setOnClickListener(listener);
    }

    private Drawable getEmotionDrawable(String emotionType) {
        switch(emotionType) {
            case JOY:
                return ContextCompat.getDrawable(getContext(), R.drawable.joy);
            case LOVE:
                return ContextCompat.getDrawable(getContext(), R.drawable.love);
            case SURPRISE:
                return ContextCompat.getDrawable(getContext(), R.drawable.surprise);
            case SAD:
                return ContextCompat.getDrawable(getContext(), R.drawable.sad);
            case ANGRY:
                return ContextCompat.getDrawable(getContext(), R.drawable.angry);
            case FEAR:
                return ContextCompat.getDrawable(getContext(), R.drawable.fear);
        }
        return null;
    }
}
