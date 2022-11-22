package com.example.quantnetwork;

import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static Drawable currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPicture(View view) {
        ImageButton picture = findViewById(view.getId());
        currentImage = picture.getDrawable();

        Intent intent = new Intent(MainActivity.this, OnePicture.class);

        startActivity(intent);
    }

}
