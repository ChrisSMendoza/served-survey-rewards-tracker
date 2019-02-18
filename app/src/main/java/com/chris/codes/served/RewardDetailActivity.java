package com.chris.codes.served;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class RewardDetailActivity extends AppCompatActivity {

    TextView itemNameText = null;
    ImageView receiptImageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_detail);

        itemNameText = findViewById(R.id.reward_item_label);
        receiptImageView = findViewById(R.id.receipt_image_view);

        Intent intent = this.getIntent();
        Bundle rewardBundle = intent.getExtras();

        if(rewardBundle != null) {

            DevReward reward = (DevReward) rewardBundle.getSerializable(RewardsAvailableActivity.REWARD_BUNDLE);
            File image = new File(reward.imageName);

            itemNameText.setText(reward.itemName);

            if(image.exists()) {
                Picasso.get().load(image).into(receiptImageView);
                Log.d("onCreate", "itemNameText and imageView set");
            }
            else
                Log.d("onCreate", "the image did not exist..");
        }
    }
}
