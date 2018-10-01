package com.thomaschu.tchu_feelsbook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/*
* EmotionsAdapter links with a ListView to display emotions within EmotionList
* providing an image, name, date and option buttons for the emotion.
* */
public class EmotionsAdapter extends ArrayAdapter implements EmotionConstants {

    private PopUpDialog dialog;
    private static final String submitButtonText = "Edit Emotion";

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

        // set editButton onClick to show edit form
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
                FeelsBookApplication.getEmotionsController().removeEmotion(emote);
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
        dialog = new PopUpDialog(getContext(), e);

        dialog.setSubmitButton(submitButtonText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView commentBox = dialog.findViewById(R.id.CommentBox);
                TextView dateField = dialog.findViewById(R.id.DateEdit);
                TextView timeField = dialog.findViewById(R.id.TimeEdit);

                String formattedDateTime = DateConverter.formatDateToISO(dateField.getText().toString()) + "T" + timeField.getText();

                if(DateConverter.isValidDate(formattedDateTime)) {

                    // submit edit data
                    FeelsBookApplication.getEmotionsController().editEmotion(e,
                            commentBox.getText().toString(),
                            DateConverter.getDateFromString(formattedDateTime));
                    // close dialog
                    dialog.dismiss();
                } else {
                    // invalid form data
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
