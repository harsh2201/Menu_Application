package com.hbteam.menuapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbteam.menuapplication.Adapter.ItemsAdapter;
import com.hbteam.menuapplication.Class.ItemClass;
import com.hbteam.menuapplication.R;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    List<ItemClass> facultyList;
    private Intent intent;
    private android.support.v7.widget.SearchView searchview;


    //FireBase Content
    DatabaseReference rootRef,demoRef,old_demoRef;


    //Arrays for the data : retreived from firebase

    String new_name[]=new String[500];
    String new_packing[]=new String[500];
    Integer new_mrp[]=new Integer[500];
    Integer new_quantity[]=new Integer[500];


    String old_name[]=new String[500];
    String old_packing[]=new String[500];
    Integer old_mrp[]=new Integer[500];
    Integer old_quantity[]=new Integer[500];

    int new_counter=0;
    int old_counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("New Products");
        old_demoRef = rootRef.child("Old Products");


        facultyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);


        //Removed as it was working only in debuggin mode.
        //recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Loading the data from firebase
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length= (int) dataSnapshot.getChildrenCount();
                for(int i=1;i<=length;i++){
                    String name = dataSnapshot.child(""+i).child("Drug Name").getValue(String.class);
                    Integer mrp = dataSnapshot.child(""+i).child("MRP").getValue(Integer.class);
                    String packing = dataSnapshot.child(""+i).child("Packing").getValue(String.class);
                    Integer quantity = dataSnapshot.child(""+i).child("Shipper Quantity").getValue(Integer.class);
                    //Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_SHORT).show();



                    new_name[new_counter]=name;
                    new_mrp[new_counter]=mrp;
                    new_packing[new_counter]=packing;
                    new_quantity[new_counter]=quantity;


                    facultyList.add(
                            new ItemClass(
                                    R.drawable.sample,
                                    name,
                                    packing
                            )
                    );

                    new_counter++;

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }




        });


        old_demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length= (int) dataSnapshot.getChildrenCount();
                for(int i=1;i<=length;i++){
                    String name = dataSnapshot.child(""+i).child("Drug Name").getValue(String.class);
                    Integer mrp = dataSnapshot.child(""+i).child("MRP").getValue(Integer.class);
                    String packing = dataSnapshot.child(""+i).child("Packing").getValue(String.class);
                    Integer quantity = dataSnapshot.child(""+i).child("Shipper Quantity").getValue(Integer.class);
                    //Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_SHORT).show();
                    old_name[old_counter]=name;
                    old_mrp[old_counter]=mrp;
                    old_packing[old_counter]=packing;
                    old_quantity[old_counter]=quantity;

                    facultyList.add(
                            new ItemClass(
                                    R.drawable.sample,
                                    name,
                                    packing
                            )
                    );

                    old_counter++;




                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

            
            
        });



        //Adding Data to th list:
       // addList();

        Context con=recyclerView.getContext();
        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(con,R.anim.lay_fall_down);
        adapter=new ItemsAdapter(this,facultyList);

//        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(adapter);
        AlphaInAnimationAdapter alphaAdapter2 = new AlphaInAnimationAdapter(adapter);
        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(alphaAdapter2);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        alphaAdapter.setFirstOnly(false);
        recyclerView.setAdapter(alphaAdapter);
//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
        onClickList();
    }

   
    private void onClickList(){
//        final ItemsAdapter adaptNew=(ItemsAdapter)recyclerView.getAdapter();
//        assert adaptNew != null;
        adapter.setOnItemClickListener(new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                facultyList=adapter.currentList();
                Toast.makeText(MainActivity.this, ""+facultyList.get(position).gName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
