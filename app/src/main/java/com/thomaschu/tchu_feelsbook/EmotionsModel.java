package com.thomaschu.tchu_feelsbook;

import java.util.Date;
import java.util.LinkedHashMap;

public class EmotionsModel extends Model implements EmotionConstants {

    private EmotionList emotionList;
    private LinkedHashMap<String, Integer> emotionCount;

    public EmotionsModel() {
        super();
        // initialize empty EmotionList
        this.emotionList = new EmotionList();

        // initialize new count hash map
        LinkedHashMap<String, Integer> newMap = new LinkedHashMap<>();
        newMap.put(JOY, 0);
        newMap.put(LOVE, 0);
        newMap.put(SURPRISE, 0);
        newMap.put(SAD, 0);
        newMap.put(ANGRY, 0);
        newMap.put(FEAR, 0);
        this.emotionCount = newMap;
    }

    /* our getter functions to retrieve information about our current instance of the model */
    public EmotionList getEmotions() {
        return this.emotionList;
    }
    public LinkedHashMap<String, Integer> getEmotionCount() {
        return this.emotionCount;
    }
    private Integer getEmotionTypeCount(String type) {
        return this.emotionCount.get(type);
    }

    /*
    * Modification functions to be called from the EmotionsController,
    * allows model to update the views its connect to with new/updated data
    * */

    /*
    * addEmotion takes in an emotion and adds it to the list of emotions
    * also updates the counter.
    * @params {Emotion e} target emotion to add
    * @return {void}
    * */
    public void addEmotion(Emotion e) {
        this.emotionList.add(e);
        String emotionType = e.getEmotionType();
        this.emotionCount.put(emotionType, getEmotionTypeCount(emotionType) + 1);
        updateViews();
    }

    /*
     * removeEmotion takes in an emotion instance from the list and removes it
     * also updates the counter.
     * @params {Emotion e} target emotion within list
     * @return {void}
     * */
    public void removeEmotion(Emotion e) {
        this.emotionList.remove(e);
        String emotionType = e.getEmotionType();
        this.emotionCount.put(emotionType, getEmotionTypeCount(emotionType) - 1);
        updateViews();
    }

    /*
     * editEmotion takes in an emotion instance from list and also new
     * information to update the instance.
     * @params {Emotion e, String comment, Date date} e is our instance to update,
     * comment is the new comment to set to e, date is new date to set to e
     * @return {void}
     * */
    public void editEmotion(Emotion e, String comment, Date date) {
        this.emotionList.edit(e, comment, date);
        updateViews();
    }
}
