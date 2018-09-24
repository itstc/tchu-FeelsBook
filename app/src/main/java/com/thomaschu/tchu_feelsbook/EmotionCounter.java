package com.thomaschu.tchu_feelsbook;

public class EmotionCounter {
    private int count;
    public EmotionCounter(int value) {
        this.count = value;
    }

    public EmotionCounter() {
        this.count = 0;
    }

    public void increment(int amount) {
        this.count += amount;
    }

    public String getCount() {
        return String.valueOf(this.count);
    }
}
