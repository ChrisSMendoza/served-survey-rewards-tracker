package com.chris.codes.served;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RewardDetailActivity extends AppCompatActivity {

    TextView itemNameText = null;
    TextView imageNameText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_detail);

        itemNameText = findViewById(R.id.reward_item_label);
        imageNameText = findViewById(R.id.image_name_label);

        Intent intent = this.getIntent();
        Bundle rewardBundle = intent.getExtras();

        DevReward reward = (DevReward) rewardBundle.getSerializable(MainActivity.REWARD_BUNDLE);

        if(reward == null)
            Log.d("onCreate", "reward was not instantiated properly");
        else {
            itemNameText.setText(reward.itemName);
            imageNameText.setText(reward.imageName);
            Log.d("onCreate", "textViews were set");
        }

    }


}
