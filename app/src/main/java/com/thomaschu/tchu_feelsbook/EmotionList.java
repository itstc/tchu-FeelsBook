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

    /*
    * getList() sorts and returns a list by most recent emotions based on date
    * @param {} none
    * @return {ArrayList<Emotion>} A sorted ArrayList of Emotion objects
    * */
    public ArrayList<Emotion> getList() {
        // sort by date first then return the sorted list
        Collections.sort(emotions, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion a, Emotion b) {
                return -DateConverter.compareEmotionDates(a,b);
            }
        });

        return emotions;
    }

    /*
    * contains is a getter to check if a emotion instance is in our list
    * @param {Emotion e} our emotion target
    * @return {Boolean} true if contains else false
    * */
    public boolean contains(Emotion e) {
        return emotions.contains(e);
    }

    /* add/remove setters
        @param {Emotion e} emotion we target
        @return {void} nothing
    */
    public void add(Emotion e) {
        emotions.add(e);
    }
    public void remove(Emotion e) {
        emotions.remove(e);
    }

    // using edit form data, we can update the emotion from the list
    public void edit(Emotion e, String comment, Date date) {
        e.setEmotion(comment, date);
        emotions = getList();
    }

}
