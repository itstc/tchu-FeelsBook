package com.thomaschu.tchu_feelsbook;

import java.util.Date;

public class Emotion {
    private String emotionType;
    private String comment;
    private Date emotionDate;

    public Emotion(String type, String comment, Date created) {
        this.emotionType = type;
        this.comment = comment;
        this.emotionDate = created;
    }

    public Emotion(String type) {
        this.emotionType = type;
        this.comment = "";
        this.emotionDate = new Date();
    }

    public String getEmotionType() {
        return emotionType;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return emotionDate;
    }

    public void setEmotion(String comment, Date date) {
        this.comment = comment;
        this.emotionDate = date;
    }

    public String serialize() {
        return "";
    }

    public void deserialize(String line) {

    }
}
