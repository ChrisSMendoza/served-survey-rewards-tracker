package com.chris.codes.served;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);
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
}
