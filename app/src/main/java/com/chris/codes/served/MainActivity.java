package com.chris.codes.served;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    static final String REWARD_BUNDLE = "REWARD_BUNDLE_KEY";

    private String getPhotoAbsPath(String photoName) {

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

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private File createImageFile() throws IOException {

        // create a file name based on today's date
        String imageFileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // store image file in app's directory
        File appDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // create the jpeg file and return it
        return File.createTempFile(imageFileName, ".jpg", appDirectory);
    }

    public void dispatchTakePictureIntent(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // there's a camera activity to handle the intent
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null; // will hold the photo
            try {
                photoFile = createImageFile();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

            if(photoFile != null) // file was created successfully
            {
                Uri photoURI = FileProvider.getUriForFile(
                    this,
                    "com.chris.codes.served.android.fileprovider",
                    photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
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
}
