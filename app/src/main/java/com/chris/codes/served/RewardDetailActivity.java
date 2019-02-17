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

        DevReward reward = (DevReward) rewardBundle.getSerializable(MainActivity.REWARD_BUNDLE);

        if(reward == null)
            Log.d("onCreate", "reward was not instantiated properly");
        else {
            itemNameText.setText(reward.itemName);

            Picasso.get().load(new File(reward.imageName)).into(receiptImageView);

            Log.d("onCreate", "textView and imageView were set");
        }

    }


}
