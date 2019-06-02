package com.hbteam.menuapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hbteam.menuapplication.Activity.MainActivity;
import com.hbteam.menuapplication.R;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by Sanket Patel on 02-06-2019.
 */

public class cartadapter extends RecyclerView.Adapter<cartadapter.MyViewHolder> {

    private List<cart_adapter> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;
        public FButton fButton;
        Context context;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.price);
            fButton = (FButton) view.findViewById(R.id.clicker);
            Typeface custom_font = Typeface.createFromAsset(view.getContext().getAssets(),  "font/calibri.ttf");

            title.setTypeface(custom_font);
            year.setTypeface(custom_font);
            fButton.setTypeface(custom_font);

        }
    }


    public cartadapter(List<cart_adapter> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_lay, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final cart_adapter movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(""+movie.getPrice());
        holder.fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new FancyGifDialog.Builder((Activity) view.getContext())
                        .setTitle(movie.getTitle())
                        .setMessage("Add In Cart?")
                        .setNegativeBtnText("Cancel")
                        .setPositiveBtnBackground("#FF4081")
                        .setPositiveBtnText("Ok")
                        .setNegativeBtnBackground("#FFA9A7A8")
                        .setGifResource(R.drawable.gif7)   //Pass your Gif here
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(view.getContext(),"Ok",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(view.getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
