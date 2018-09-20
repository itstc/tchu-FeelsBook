package com.thomaschu.tchu_feelsbook;

import java.util.Date;

public class Emotions {
    private String comment;
    private Date created;

    public Emotions(String comment, Date created) {
        this.comment = comment;
        this.created = created;
    }

    public Emotions() {
        this.comment = "";
        this.created = new Date();
    }

    public String serialize() {
        return "";
    }

    public void deserialize(String line) {

    }
}
