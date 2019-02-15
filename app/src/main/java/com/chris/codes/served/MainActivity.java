package com.chris.codes.served;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private RewardViewModel mRewardViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RewardListAdapter adapter = new RewardListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRewardViewModel = ViewModelProviders.of(this).get(RewardViewModel.class);

        mRewardViewModel.getAllRewards().observe(this, new Observer<List<Reward>>() {
            @Override
            public void onChanged(@Nullable final List<Reward> rewards) {

                adapter.setRewards(rewards); // update the cached copy of the rewards in the adapter
            }
        });
    }

    public void goToTakeSurvey(View view) {
        Log.d(LOG_TAG, "goToTakeSurvey button clicked");

        Intent goTakeSurveyIntent = new Intent(this, TakeSurveyActivity.class);
        startActivity(goTakeSurveyIntent);
    }

    public static final int NEW_REWARD_ACTIVITY_REQUEST_CODE = 1;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_REWARD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Reward reward = new Reward(data.getStringExtra(NewRewardActivity.EXTRA_REPLY), "fake-code");
            mRewardViewModel.insert(reward);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void addNewReward(View view) {
        Intent intent = new Intent(MainActivity.this, NewRewardActivity.class);
        startActivityForResult(intent, NEW_REWARD_ACTIVITY_REQUEST_CODE);
    }
}