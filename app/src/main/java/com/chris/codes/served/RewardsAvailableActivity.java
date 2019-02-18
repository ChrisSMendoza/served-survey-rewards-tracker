package com.chris.codes.served;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class RewardsAvailableActivity extends AppCompatActivity {

    static final String REWARD_BUNDLE = "REWARD_BUNDLE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_available);
    }
    public String getPhotoAbsPath(String photoName) {

        File appDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return appDirectory.toString() + File.separator + photoName;
    }

    public void viewDetails(View view) {

        Intent viewDetailsIntent = new Intent(this, RewardDetailActivity.class);

        String photoName = "20190216_2105491221145406.jpg";
        String imageAbsPath = getPhotoAbsPath(photoName);

        DevReward reward = new DevReward("2 Piece Chicken", imageAbsPath);

        Bundle rewardBundle = new Bundle();
        rewardBundle.putSerializable(REWARD_BUNDLE, reward);

        viewDetailsIntent.putExtras(rewardBundle);
        startActivity(viewDetailsIntent);
    }
}
