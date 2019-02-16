package com.chris.codes.served;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    }

    public void goToTakeSurvey(View view) {
        Log.d(LOG_TAG, "goToTakeSurvey button clicked");

        Intent goTakeSurveyIntent = new Intent(this, TakeSurveyActivity.class);
        startActivity(goTakeSurveyIntent);
    }



    private static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;

    private File createImageFile() throws IOException {

        // create an image file name based on today's date
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";

        // store image file in public directory
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath(); // path for use in ACTION_VIEW intent
        return image;
    }

    public void dispatchTakePictureIntent(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // there's a camera activity to handle the intent
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null; // will hold the photo
            try {
                photoFile = createImageFile();
            }catch (IOException ex) {
                Log.d(LOG_TAG, "error occurred while creating image file");
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
}
