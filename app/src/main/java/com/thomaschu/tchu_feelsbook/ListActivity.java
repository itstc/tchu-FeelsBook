package com.thomaschu.tchu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String[] emotionList = intent.getStringArrayExtra(MainActivity.EMOTION_LIST);

        // append results from intent payload
        ViewGroup viewList = findViewById(R.id.EmotionList);
        for(String emotion: emotionList) {
            TextView row = new TextView(this);
            row.setText(emotion);
            viewList.addView(row);
        }

    }
}
