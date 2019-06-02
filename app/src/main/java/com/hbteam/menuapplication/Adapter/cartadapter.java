package com.hbteam.menuapplication.Adapter;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hbteam.menuapplication.R;

import java.util.List;

/**
 * Created by Sanket Patel on 02-06-2019.
 */

public class cartadapter extends RecyclerView.Adapter<cartadapter.MyViewHolder> {

    private List<cart_adapter> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.price);
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        cart_adapter movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(""+movie.getPrice());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
