package com.thomaschu.tchu_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ListActivity extends AppCompatActivity implements EmotionConstants {
    private EmotionsAdapter emotionAdapter;
    private EmotionCountAdapter countAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();

        countAdapter = new EmotionCountAdapter(this, EmotionController.getCounter());
        final RecyclerView countList = findViewById(R.id.CountLayout);
        countList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        countList.setAdapter(countAdapter);

        // hook up our EmotionController list to our custom ListView adapter to display emotions
        emotionAdapter = new EmotionsAdapter(this, 0, EmotionController.get(), countAdapter);
        ListView list = findViewById(R.id.EmotionList);
        list.setAdapter(emotionAdapter);
    }
}
