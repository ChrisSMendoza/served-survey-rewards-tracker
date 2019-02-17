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

    private ImageView imageView = null;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionsIfNeeded(); // request permissions when app launches
        imageView = findViewById(R.id.image_view);
    }

    public void goToTakeSurvey(View view) {
        Log.d(LOG_TAG, "goToTakeSurvey button clicked");

        Intent goTakeSurveyIntent = new Intent(this, TakeSurveyActivity.class);
        startActivity(goTakeSurveyIntent);
    }

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private File createImageFile() throws IOException {

        // create a file name based on today's date
        String imageFileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // store image file in app's directory
        File appDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(imageFileName, ".jpg", appDirectory);
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
    private static final int REQUEST_GET_PHOTO = 2;
    private final int MY_PERMISSIONS_REQUEST = 3;

    public void displayPhoto(View view) {

        File appDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        String photoName = "20190216_2105491221145406.jpg";
        String photoPath = appDirectory.toString() + File.separator + photoName;

        File image = new File(photoPath); // to check it exists

        if(image.exists()) {
            Picasso.get().load(image).into(imageView);
            Log.d("displayPhoto", "Picasso loaded image");
        }else
            Log.d(LOG_TAG, "image didn't exist...");

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
