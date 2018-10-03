package com.thomaschu.tchu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;


public class ListActivity extends AppCompatActivity implements TView<EmotionsModel> {
    private EmotionsAdapter emotionAdapter;
    private EmotionCountAdapter countAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
         * we inject activity to model and setup adapters in onStart because
         * onCreate only runs once when the app initially loads this activity
         */

        FeelsBookApplication.getEmotionsModel().addView(this);

        EmotionsController emotionsController = FeelsBookApplication.getEmotionsController();

        // hook up our EmotionsController counter to our custom ListView adapter to display counter
        countAdapter = new EmotionCountAdapter(this, emotionsController.getCounter());
        final RecyclerView countList = findViewById(R.id.CountLayout);
        countList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        countList.setAdapter(countAdapter);
        countList.setOnTouchListener(null);

        // hook up our EmotionsController list to our custom ListView adapter to display emotions
        emotionAdapter = new EmotionsAdapter(this, 0, emotionsController.getEmotions());
        ListView list = findViewById(R.id.EmotionList);
        list.setAdapter(emotionAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // remove this activity from our model so we don't update it when it's destroyed
        FeelsBookApplication.getEmotionsModel().removeView(this);
    }

    public void update(EmotionsModel model) {
        // update our adapters to re-render new data
        countAdapter.notifyDataSetChanged();
        emotionAdapter.notifyDataSetChanged();
    }
}
