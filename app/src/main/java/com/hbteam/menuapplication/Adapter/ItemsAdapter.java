package com.hbteam.menuapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbteam.menuapplication.Class.ItemClass;
import com.hbteam.menuapplication.R;

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
        Glide.with(mCtx).load(fac.gImage()).into(facultyViewHolder.imageView);
        ViewCompat.setTransitionName(facultyViewHolder.imageView,fac.gName());
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    class FacultyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textName;
        TextView textDesignation;

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