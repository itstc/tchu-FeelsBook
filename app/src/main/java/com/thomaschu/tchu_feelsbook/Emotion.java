package com.thomaschu.tchu_feelsbook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emotion {
    private String emotionType;
    private String comment;
    private Date emotionDate;

    /*
    * Emotion constructor to set our member attributes
    * @params {String type, String comment, Date created} each corresponds with our attributes
    * */

    public Emotion(String type, String comment, Date created) {
        this.emotionType = type;
        this.comment = comment;
        this.emotionDate = created;
    }

    // overloaded constructor if user does not add comment and date
    public Emotion(String type) {
        this.emotionType = type;
        this.comment = "";
        this.emotionDate = new Date();
    }

    /*
    * getters for emotion data
    * @params {}
    * @returns {String emotionType, String comment, SimpleDateFormat emotionDate}
    * */
    public String getEmotionType() {
        return emotionType;
    }
    public String getComment() {
        return comment;
    }
    public Date getDate() {
        return emotionDate;
    }
    public String getDateAsString() {
        return DateConverter.getISO8601FromDate(emotionDate);
    }


    /*
    * a setter to change the comment/date of our emotion
    * @params {String comment, Date date} new comment and date
    * @return {}
    * */
    public void setEmotion(String comment, Date date) {
        this.comment = comment;
        this.emotionDate = date;
    }

}
