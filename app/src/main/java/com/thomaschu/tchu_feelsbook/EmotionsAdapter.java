package com.thomaschu.tchu_feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class EmotionsAdapter extends ArrayAdapter implements EmotionConstants {

    private Dialog dialog;

    public EmotionsAdapter(Context context, int resource, EmotionList emotionList) {
        super(context, resource, emotionList.getList());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Emotion emote = (Emotion)getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emotion_item, parent, false);
        }

        // Lookup view to populate with data
        ImageView emoteImage = (ImageView) convertView.findViewById(R.id.emotionImg);
        TextView emoteType = (TextView) convertView.findViewById(R.id.emotionType);
        TextView emoteDate = (TextView) convertView.findViewById(R.id.emotionDate);
        TextView emoteComment = (TextView) convertView.findViewById(R.id.emotionComment);

        // our buttons to edit/delete emotions
        final Button editButton = (Button) convertView.findViewById(R.id.emotionEditButton);
        Button deleteButton = (Button) convertView.findViewById(R.id.emotionDeleteButton);

        // populate fields
        emoteImage.setImageDrawable(getEmotionDrawable(emote.getEmotionType()));
        emoteType.setText(emote.getEmotionType());
        emoteComment.setText(emote.getComment());
        emoteDate.setText(emote.getDate());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion editEmotion = (Emotion) getItem(position);
                createEditForm(editEmotion);
            }
        });

        // set deleteButton onClick to remove emotion
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(emote);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    /*
    * create our edit form for selected emotion
    * @param {Emotion e} target emotion
    * @return {} nothing
    * */
    private void createEditForm(final Emotion e) {
        // create a dialog and set layout to edit_form_popup
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.edit_form_popup);
        // set functionality to X button
        dialog.findViewById(R.id.ExitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // get fields to populate
        ImageView emoteImg = dialog.findViewById(R.id.EmotionImage);
        final TextView commentBox = dialog.findViewById(R.id.CommentBox);
        final TextView timeDateField = dialog.findViewById(R.id.TimeEdit);

        // populate fields with existing data
        emoteImg.setImageDrawable(getEmotionDrawable(e.getEmotionType()));
        commentBox.setText(e.getComment());
        timeDateField.setText(e.getDate());

        dialog.findViewById(R.id.SubmitEmotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DateConverter.isValidDate(timeDateField.getText().toString())) {
                    EmotionController.get().edit(e,
                            commentBox.getText().toString(),
                            DateConverter.getDateFromString(timeDateField.getText().toString()));

                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Invalid Date!", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
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
