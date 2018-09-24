package com.thomaschu.tchu_feelsbook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedHashMap;

public class EmotionCountAdapter extends RecyclerView.Adapter<EmotionCountAdapter.ViewHolder> implements EmotionConstants{
    private Context context;
    private LinkedHashMap<String, EmotionCounter> data;
    private String[] keys;

    public EmotionCountAdapter(Context context, LinkedHashMap<String, EmotionCounter> data) {
        // our context to inflate items to
        this.context = context;

        // we use a linked hash map in order to preserve the order of key:values
        this.data = data;
        // create a string array of keys within our linked hash map
        this.keys = data.keySet().toArray(new String[data.size()]);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emotion_count_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {
        view.image.setImageDrawable(getDrawableFromKey(keys[position]));
        view.counter.setText(getItem(position));
    }

    public String getItem(int position) {
        return data.get(keys[position]).getCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView counter;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.EmotionCountImage);
            counter = itemView.findViewById(R.id.EmotionCountValue);
        }
    }

    private Drawable getDrawableFromKey(String key) {
        switch(key) {
        case JOY:
            return ContextCompat.getDrawable(context, R.drawable.joy);
        case LOVE:
            return ContextCompat.getDrawable(context, R.drawable.love);
        case SURPRISE:
            return ContextCompat.getDrawable(context, R.drawable.surprise);
        case SAD:
            return ContextCompat.getDrawable(context, R.drawable.sad);
        case ANGRY:
            return ContextCompat.getDrawable(context, R.drawable.angry);
        case FEAR:
            return ContextCompat.getDrawable(context, R.drawable.fear);
        }
        return null;
    }
}
