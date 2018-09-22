package com.thomaschu.tchu_feelsbook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EmotionsAdapter extends ArrayAdapter implements EmotionConstants {

    public EmotionsAdapter(Context context, int resource, EmotionList emotionList) {
        super(context, resource, emotionList.getList());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Emotion emote = (Emotion)getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emotion_item, parent, false);
        }

        // Lookup view to populate with data
        ImageView emoteImage = (ImageView) convertView.findViewById(R.id.emotionImg);
        TextView emoteType = (TextView) convertView.findViewById(R.id.emotionType);
        TextView emoteDate = (TextView) convertView.findViewById(R.id.emotionDate);
        TextView emoteComment = (TextView) convertView.findViewById(R.id.emotionComment);
        // Populate the data into the template view using the data object
        Drawable imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.joy);

        switch(emote.getEmotionType()) {
            case JOY:
                imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.joy);
                break;
            case LOVE:
                imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.love);
                break;
            case SURPRISE:
                imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.surprise);
                break;
            case SAD:
                imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.sad);
                break;
            case ANGRY:
                imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.angry);
                break;
            case FEAR:
                imgDrawable = ContextCompat.getDrawable(getContext(), R.drawable.fear);
                break;
        }
        emoteImage.setImageDrawable(imgDrawable);
        emoteType.setText(emote.getEmotionType());
        emoteDate.setText(emote.getDateAsString());
        // Return the completed view to render on screen
        return convertView;
    }
}
