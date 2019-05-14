package com.hbteam.menuapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbteam.menuapplication.Activity.MainActivity;
import com.hbteam.menuapplication.Class.ItemClass;
import com.hbteam.menuapplication.DrugInfo;
import com.hbteam.menuapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.FacultyViewHolder> implements Filterable {

    private Context mCtx;
    private List<ItemClass> facultyList;
    private List<ItemClass> newfacultyList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public ItemsAdapter(Context mCtx, List<ItemClass> facultyList) {
        this.mCtx = mCtx;
        this.facultyList = facultyList;
        newfacultyList = facultyList;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.items_lay, null);
        return new FacultyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder facultyViewHolder, int i) {
        ItemClass fac = facultyList.get(i);
        facultyViewHolder.textName.setText(fac.gName());
        facultyViewHolder.textDesignation.setText(fac.gPrice());
        //Glide.with(mCtx).load(fac.gImage()).into(facultyViewHolder.imageView);
        Picasso.with(mCtx)
                .load(fac.gImage()).into(facultyViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    class FacultyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textName;
        TextView textDesignation;
        boolean isImageFitToScreen;

        public FacultyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textviewLab);
            textDesignation = itemView.findViewById(R.id.textViewDesignation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(mCtx, DrugInfo.class);
                            intent.putExtra("name", facultyList.get(position).gName());
                            intent.putExtra("image", facultyList.get(position).gImage());
                            intent.putExtra("desc", facultyList.get(position).gDesc());

                            mCtx.startActivity(intent);


                        }
                    }
                }
            });
        }
    }

    public List<ItemClass> currentList() {
        return facultyList;
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    facultyList = newfacultyList;
                } else {

                    ArrayList<ItemClass> filteredList = new ArrayList<>();

                    for (ItemClass fac : newfacultyList) {

                        if (fac.gName().toLowerCase().contains(charString)) {

                            filteredList.add(fac);
                        }
                    }

                    facultyList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = facultyList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                facultyList = (ArrayList<ItemClass>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
