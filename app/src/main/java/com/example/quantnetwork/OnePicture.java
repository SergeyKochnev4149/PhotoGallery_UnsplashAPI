package com.example.quantnetwork;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

public class OnePicture extends AppCompatActivity {
    static String currentImageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_picture);
        ImageButton imageButton = findViewById(R.id.imageButton);
        Picasso.get().load(currentImageLink).into(imageButton);
    }



    public void goToPicturesList(View view) {
        onPause();
        finish();
    }
}