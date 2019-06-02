package com.hbteam.menuapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbteam.menuapplication.Activity.Cart;
import com.hbteam.menuapplication.Activity.Fullscreen;
import com.squareup.picasso.Picasso;

public class DrugInfo extends AppCompatActivity {

    private Menu menu;
    private ImageView imageView;
    private TextView textView;
    String imagename;
    Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.carty);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DrugInfo.this , Cart.class);
                i.putExtra("imagename",imagename);
                i.putExtra("pos",position);
                startActivity(i);

            }
        });

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });

        Bundle extras = getIntent().getExtras();

        setTitle(extras.getString("name"));
        imagename = extras.getString("image");
        position = extras.getInt("pos");

        String description = extras.getString("desc");

//        Toast.makeText(this, "" + imagename, Toast.LENGTH_SHORT).show();

        imageView = (ImageView) findViewById(R.id.expandedImage);
        textView=(TextView)findViewById(R.id.desc);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font/calibri.ttf");

        textView.setTypeface(custom_font);

        textView.setText(description);

        Picasso.with(DrugInfo.this).load(imagename).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DrugInfo.this, Fullscreen.class);
                i.putExtra("image",imagename);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            Intent i = new Intent(DrugInfo.this , Cart.class);
            i.putExtra("imagename",imagename);

            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}