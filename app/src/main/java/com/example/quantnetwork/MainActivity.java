package com.example.quantnetwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    static Drawable currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.unsplash.com//photos//?client_id=ab3411e4ac868c2646c0ed488dfd919ef612b04c264f3374c97fff98ed253dc9";

        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("RES", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            String author = "author" + i;
                            TextView currentView = findViewById(R.id.author1);


                            try {
                                JSONObject allImageInfo = response.getJSONObject(i);
                                String authorUserName = allImageInfo.getJSONObject("user").getString("username");
//                                JSONObject linksToImage = allImageInfo.getJSONObject("urls");
//                                String imageAuthor = linksToImage.getString("small");

//                                URL url = new URL("YourUrl");
//                                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                                currentView.setText(authorUserName);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Log.e("ERROR", anError.toString());
                    }
                });
    }

    public void openPicture(View view) {
        ImageButton picture = findViewById(view.getId());
        currentImage = picture.getDrawable();

        Intent intent = new Intent(MainActivity.this, OnePicture.class);

        startActivity(intent);
    }

//    private void create


}
