package com.example.quantnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OnePicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_picture);
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setImageDrawable(MainActivity.currentImage);
    }



    public void goToPicturesList(View view) {
        onPause();
        finish();
    }
}