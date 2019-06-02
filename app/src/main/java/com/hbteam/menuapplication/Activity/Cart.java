package com.hbteam.menuapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbteam.menuapplication.Adapter.cart_adapter;
import com.hbteam.menuapplication.Adapter.cartadapter;
import com.hbteam.menuapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    ImageView imageView;
    String imagename;
    Integer position;


    DatabaseReference rootRef,demoRef,old_demoRef;
    String new_name[]=new String[500];
    Integer new_mrp[]=new Integer[500];

    int new_counter=0;
    int old_counter=0;



    private List<cart_adapter> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private cartadapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        imageView=(ImageView)findViewById(R.id.preview);
        Bundle extras = getIntent().getExtras();

        //setTitle(extras.getString("name"));
        imagename = extras.getString("imagename");
        position = extras.getInt("pos");
        Picasso.with(Cart.this).load(imagename).into(imageView);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Context context = this;
        mAdapter = new cartadapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        prepareMovieData();







    }

    private void prepareMovieData() {
      

        rootRef = FirebaseDatabase.getInstance().getReference();

        position=position+1;
        demoRef = rootRef.child("New Products").child(""+position).child("Cart");
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length= (int) dataSnapshot.getChildrenCount();
                for(int i=1;i<=length;i++){
                    String name = dataSnapshot.child(""+i).getValue(String.class);
                    new_name[new_counter]=name;
                    new_counter++;
                    


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        demoRef = rootRef.child("New Products").child(""+position).child("Cart_Price");
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length= (int) dataSnapshot.getChildrenCount();
                for(int i=1;i<=length;i++){
                    Integer mrp = dataSnapshot.child(""+i).getValue(Integer.class);
                    new_mrp[old_counter]=mrp;
                    old_counter++;

                }
                add_content();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  void add_content()
    {


        for(int i=0;i<new_counter;i++)
        {
            cart_adapter movie = new cart_adapter(new_name[i],new_mrp[i]);
            movieList.add(movie);
        }

        mAdapter.notifyDataSetChanged();
    }

    
}
