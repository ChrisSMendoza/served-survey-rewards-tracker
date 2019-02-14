package com.chris.codes.served;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RewardListAdapter adapter = new RewardListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void goToTakeSurvey(View view) {
        Log.d(LOG_TAG, "goToTakeSurvey button clicked");

        Intent goTakeSurveyIntent = new Intent(this, TakeSurveyActivity.class);
        startActivity(goTakeSurveyIntent);
    }
}