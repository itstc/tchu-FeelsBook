package com.thomaschu.tchu_feelsbook;

import junit.framework.TestCase;

public class EmotionListTest extends TestCase {
    public void testEmotionList() {
        Emotion testEmotion = new Emotion("Happy");
        EmotionList list = new EmotionList();

        list.add(testEmotion);
        assertTrue(list.contains(testEmotion));
        list.remove(testEmotion);
        assertFalse(list.contains(testEmotion));
    }
}
