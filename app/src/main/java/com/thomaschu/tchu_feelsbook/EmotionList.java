package com.thomaschu.tchu_feelsbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class EmotionList {
    private ArrayList<Emotion> emotions;

    public EmotionList() {
        emotions = new ArrayList<>();
    }

    public Collection<Emotion> getList() {
        // sort by date first then return the sorted list
        Collections.sort(emotions, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion a, Emotion b) {
                return a.getDate().compareTo(b.getDate());
            }
        });

        return emotions;
    }

    public boolean contains(Emotion e) {
        return emotions.contains(e);
    }

    public void add(Emotion e) {
        emotions.add(e);
    }

    public void remove(Emotion e) {
        emotions.remove(e);
    }

    public void edit(Emotion e, String comment, Date date) {
        remove(e);
        e.setEmotion(comment, date);
        add(e);
    }

    // used to test ListActivity
    public String[] toStringArray() {
        String[] result = new String[emotions.size()];
        for(int i = 0; i < emotions.size(); i++) {
            result[i] = emotions.get(i).getEmotionType();
        }
        return result;
    }
}
