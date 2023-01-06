package com.example.photogalleryunsplashapi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    static String url = "https://api.unsplash.com//photos//?client_id=ab3411e4ac868c2646c0ed488dfd919ef612b04c264f3374c97fff98ed253dc9";
    static private int viewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("RES", response.toString());
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject allImageInfo = response.getJSONObject(i);

                                String photoDescription = "Description: ";
                                if (!allImageInfo.getString("description").equals("null"))
                                    photoDescription += allImageInfo.getString("description");
                                else if (!allImageInfo.getString("alt_description").equals("null"))
                                    photoDescription += allImageInfo.getString("alt_description");
                                else photoDescription += "Without description";

                                String author = "Author: " + allImageInfo.getJSONObject("user").getString("name");
                                String icon = allImageInfo.getJSONObject("urls").getString("thumb");
                                String imageLink_regular = allImageInfo.getJSONObject("urls").getString("regular");
                                createRelative(icon, imageLink_regular, author, photoDescription);
                                viewCount++;


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


    public void openPicture(String imageLink) {
        OnePicture.currentImageLink = imageLink;
        Intent intent = new Intent(MainActivity.this, OnePicture.class);

        startActivity(intent);
    }

    private void createRelative(String icon, String imageLink, String author, String photoDescription) {

        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                this.getResources().getDisplayMetrics());


        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        relativeLayout.setLayoutParams(relativeLayoutParams);
        relativeLayout.setBackgroundColor(Color.WHITE);
        relativeLayout.setId(("relativeLayout" + viewCount).hashCode());


        ImageButton imageButton = new ImageButton(this);
        Picasso.get().load(icon).into(imageButton);
        imageButton.setBackgroundColor(Color.WHITE);
        RelativeLayout.LayoutParams imageButtonParams = new RelativeLayout.LayoutParams(dp * 100, dp * 100);
        imageButtonParams.setMargins(4 * dp, 4 * dp, 4*dp, 4 * dp);
        imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageButton.setOnClickListener(v -> openPicture(imageLink));
        imageButton.setId(("imageButton" + viewCount).hashCode());
        relativeLayout.addView(imageButton, imageButtonParams);


        TextView authorName = new TextView(this);
        authorName.setText(author);
        RelativeLayout.LayoutParams authorNameParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        authorNameParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        authorNameParams.setMargins(4*dp, 0, 0, 0);
        authorNameParams.addRule(RelativeLayout.RIGHT_OF, imageButton.getId());
        authorName.setTextColor(Color.BLACK);
        authorName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        authorName.setTypeface(Typeface.DEFAULT_BOLD);
        authorName.setId(("authorName" + viewCount).hashCode());
        relativeLayout.addView(authorName, authorNameParams);


        TextView description = new TextView(this);
        description.setText(photoDescription);
        RelativeLayout.LayoutParams descriptionParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        descriptionParams.setMargins(4*dp, 0, 8 * dp, 8 * dp);
        descriptionParams.addRule(RelativeLayout.RIGHT_OF, imageButton.getId());
        descriptionParams.addRule(RelativeLayout.BELOW, authorName.getId());
        description.setTextColor(Color.BLACK);
        description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        description.setTypeface(Typeface.DEFAULT);
        description.setId(("description" + viewCount).hashCode());
        relativeLayout.addView(description, descriptionParams);


        View horizontalLine = new View(this);
        RelativeLayout.LayoutParams horizontalLineParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                dp
        );
        horizontalLineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        horizontalLine.setBackgroundColor(Color.BLACK);
        horizontalLine.setId(("horizontalLine" + viewCount).hashCode());
        relativeLayout.addView(horizontalLine, horizontalLineParams);


        LinearLayout linearLayout = findViewById(R.id.mainGroup);
        linearLayout.addView(relativeLayout, relativeLayoutParams);
    }
}

