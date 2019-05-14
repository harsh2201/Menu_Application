package com.hbteam.menuapplication.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hbteam.menuapplication.R;

public class SplashScreen extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView=(TextView)findViewById(R.id.fidulis);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/roboto.ttf");

        textView.setTypeface(custom_font);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
}
