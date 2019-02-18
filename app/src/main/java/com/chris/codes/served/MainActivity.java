package com.chris.codes.served;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionsIfNeeded(); // request permissions when app launches
    }

    public void goToTakeSurvey(View view) {
        Log.d(LOG_TAG, "goToTakeSurvey button clicked");

        Intent goTakeSurveyIntent = new Intent(this, TakeSurveyActivity.class);
        startActivity(goTakeSurveyIntent);
    }


    private final int MY_PERMISSIONS_REQUEST = 3;

    // requesting access to all photos and files
    private void requestPermissionsIfNeeded() {

        int permissions =
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // we aren't allowed to access photos
        if(permissions != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST: {

                if(results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                    // we have permission to access photos

                }
                else {
                    // disable camera access features
                }
            }
        }
    }

    public void goToRewardsAvailable(View view) {

        Intent rewardsAvailableIntent =
                new Intent(this, RewardsAvailableActivity.class);

        startActivity(rewardsAvailableIntent);
    }
}
