package com.hbteam.menuapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.hbteam.menuapplication.Adapter.ItemsAdapter;
import com.hbteam.menuapplication.Class.ItemClass;
import com.hbteam.menuapplication.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    List<ItemClass> facultyList;
    private Intent intent;
    private android.support.v7.widget.SearchView searchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facultyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Adding Data to th list:
        addList();

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

    private void addList() {

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Aniruddhkumar G. Fataniya",
                        "Assistant Professor"
                )
        );
        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Arpita Shah",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Ashwin Makwana",
                        "Assistant Professor"
                )
        );
        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Chintan Bhatt",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Devyani Panchal",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Dippal Israni",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Dipsi Dave",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Hardik Mandora",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Khushboo Patel",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Kruti Dhyani",
                        "Assistant Professor"
                )
        );


        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Martin Parmar",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Minal Maniar",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Mrugendrasinh Rahevar",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Padmavathi Bindulal",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Ritesh Patel",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Ronak Patel",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new ItemClass(
                        R.drawable.sample,
                        "Vaishali Mewada",
                        "Assistant Professor"
                )
        );

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
