package com.thomaschu.tchu_feelsbook;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EmotionTest extends TestCase{

    public void testEmotion() {

        // test member attributes of emotion on create
        Date testDate = new Date();
        Emotion emotion = new Emotion("Happy", "hello", testDate);
        assertEquals(emotion.getClass(), Emotion.class);
        assertEquals(emotion.getEmotionType(), "Happy");
        assertEquals(emotion.getComment(), "hello");
        assertEquals(emotion.getDate().toString(), testDate.toString());

        // test empty comment/date creation
        emotion = new Emotion("Sad");
        assertEquals(emotion.getClass(), Emotion.class);
        assertEquals(emotion.getEmotionType(), "Sad");
        assertEquals(emotion.getComment(), "");



    }
}
