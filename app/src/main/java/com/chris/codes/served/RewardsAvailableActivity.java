package com.chris.codes.served;

import android.content.Intent;
import android.os.Environment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.File;
import java.util.List;

public class RewardsAvailableActivity extends AppCompatActivity {

    static final String REWARD_BUNDLE = "REWARD_BUNDLE_KEY";
    private RewardViewModel mRewardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_available);

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
    public String getPhotoAbsPath(String photoName) {
        return "";
    }

    public void viewDetails(View view) {

    }
}
