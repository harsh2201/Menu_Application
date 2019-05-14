package com.hbteam.menuapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbteam.menuapplication.Adapter.ItemsAdapter;
import com.hbteam.menuapplication.Class.ImageModel;
import com.hbteam.menuapplication.Class.ItemClass;
import com.hbteam.menuapplication.Class.SlidingImage_Adapter;
import com.hbteam.menuapplication.DrugInfo;
import com.hbteam.menuapplication.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ItemsAdapter adapter;
    List<ItemClass> facultyList;
    public Intent intent;
    public SearchView searchView;

   //Auto SLider
    private ArrayList<ImageModel> imageModelArrayList;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private int[] myImageList = new int[]{R.drawable.one, R.drawable.two,
           R.drawable.three,R.drawable.four};



    //FireBase Content
    DatabaseReference rootRef,demoRef,old_demoRef;


    //Arrays for the data : retreived from firebase

    String new_name[]=new String[500];
    String new_image[]=new String[500];
    String new_packing[]=new String[500];
    String new_desc[]=new String[500];
    Integer new_mrp[]=new Integer[500];
    Integer new_quantity[]=new Integer[500];


    String old_name[]=new String[500];
    String old_image[]=new String[500];
    String old_packing[]=new String[500];
    String old_desc[]=new String[500];
    Integer old_mrp[]=new Integer[500];
    Integer old_quantity[]=new Integer[500];

    int new_counter=0;
    int old_counter=0;


    //Static array for maintaining Image counter
    static String my_image[]=new String[500];
    int total_counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SearchView Initialized
        searchView = findViewById(R.id.searchView);
        search(searchView);


        //Auto Slider
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("New Products");
        old_demoRef = rootRef.child("Old Products");



        facultyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);



       recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Context context=this;
        get_newproducts(context);
        get_oldproducts(context);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.aboutus:
                Intent i = new Intent(MainActivity.this,About_Us.class);
                startActivity(i);
                break;

            case R.id.locate:
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 23.6182861, 72.9610794);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addList(){
        for(int i=0;i<new_counter;i++){
            facultyList.add(
                    new ItemClass(
                            new_image[i],
                            new_name[i],
                            new_packing[i],
                            new_desc[i]
                    )
            );
        }

        for(int i=0;i<old_counter;i++){
            facultyList.add(
                    new ItemClass(
                            old_image[i],
                            old_name[i],
                            old_packing[i],
                            old_desc[i]
                    )
            );
        }


        Context con=recyclerView.getContext();
        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(con,R.anim.lay_fall_down);
        adapter=new ItemsAdapter(this,facultyList);

//        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(adapter);
//        AlphaInAnimationAdapter alphaAdapter2 = new AlphaInAnimationAdapter(adapter);
//        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(alphaAdapter2);
//        alphaAdapter.setDuration(1500);
//        alphaAdapter.setInterpolator(new OvershootInterpolator());
//        alphaAdapter.setFirstOnly(false);
//        recyclerView.setAdapter(alphaAdapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
        onClickList();

    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ItemsAdapter adapt=(ItemsAdapter) recyclerView.getAdapter();
                assert adapt != null;
                adapt.getFilter().filter(newText);
                onClickList();
                return true;
            }
        });
    }

   //Called when click is encountered on the cards
    private void onClickList(){
        adapter.setOnItemClickListener(new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                facultyList=adapter.currentList();
                Intent intent = new Intent(MainActivity.this, DrugInfo.class);
                intent.putExtra("name", facultyList.get(position).gName());
                intent.putExtra("image", facultyList.get(position).gImage());
                intent.putExtra("desc", facultyList.get(position).gDesc());

                startActivity(intent);
//                Toast.makeText(MainActivity.this, ""+facultyList.get(position).gImage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void get_newproducts(Context context){

        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length= (int) dataSnapshot.getChildrenCount();
                for(int i=1;i<=length;i++){
                    String name = dataSnapshot.child(""+i).child("Drug Name").getValue(String.class);
                    Integer mrp = dataSnapshot.child(""+i).child("MRP").getValue(Integer.class);
                    String packing = dataSnapshot.child(""+i).child("Packing").getValue(String.class);
                    Integer quantity = dataSnapshot.child(""+i).child("Shipper Quantity").getValue(Integer.class);
                    String url = dataSnapshot.child(""+i).child("image").getValue(String.class);
                    String desc = dataSnapshot.child(""+i).child("description").getValue(String.class);

                    //Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_SHORT).show();


                    new_name[new_counter]=name;
                    new_mrp[new_counter]=mrp;
                    new_packing[new_counter]=packing;
                    new_quantity[new_counter]=quantity;
                    new_image[new_counter]=url;
                    new_desc[new_counter]=desc;


                    /*facultyList.add(
                            new ItemClass(
                                    R.drawable.sample,
                                    name,
                                    packing
                            )
                    );*/

                    new_counter++;

                }
                addList();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }




        });



    }

    public void get_oldproducts(final Context context){

        old_demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length= (int) dataSnapshot.getChildrenCount();
                for(int i=1;i<=length;i++){
                    String name = dataSnapshot.child(""+i).child("Drug Name").getValue(String.class);
                    Integer mrp = dataSnapshot.child(""+i).child("MRP").getValue(Integer.class);
                    String packing = dataSnapshot.child(""+i).child("Packing").getValue(String.class);
                    Integer quantity = dataSnapshot.child(""+i).child("Shipper Quantity").getValue(Integer.class);
                    String url = dataSnapshot.child(""+i).child("image").getValue(String.class);
                    String desc = dataSnapshot.child(""+i).child("description").getValue(String.class);

                    //Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_SHORT).show();
                    old_name[old_counter]=name;
                    old_mrp[old_counter]=mrp;
                    old_packing[old_counter]=packing;
                    old_quantity[old_counter]=quantity;
                    old_image[old_counter]=url;
                    old_desc[old_counter]=desc;

                    /*facultyList.add(
                            new ItemClass(
                                    R.drawable.sample,
                                    name,
                                    packing
                            )
                    );*/

                    old_counter++;




                }
               // addList();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }



        });




    }


    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

}
