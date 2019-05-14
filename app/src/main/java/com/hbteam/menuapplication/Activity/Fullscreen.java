package com.hbteam.menuapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hbteam.menuapplication.DrugInfo;
import com.squareup.picasso.Picasso;

import com.hbteam.menuapplication.R;

public class Fullscreen extends AppCompatActivity {
        ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        imageView=(ImageView)findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        final String imagename = extras.getString("image");

        Picasso.with(Fullscreen.this).load(imagename).into(imageView);


    }
}
