package com.cybereye_community.udacitymovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by edikurniawan on 7/10/17.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{

    List<Result> movieList;

    public GridViewAdapter(List<Result> movieList) {
        this.movieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_movie, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // Get the data model based on position
        Glide.with(holder.itemView.getContext())
                .load(GlideImg.BASE_IMG+ movieList.get(position).getPosterPath())
                //.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(holder.imgView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result data = movieList.get(position);
                Intent i = new Intent(holder.itemView.getContext(),detail_movie.class);
                i.putExtra("movie",new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });

    }

    public void setData(List<Result> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();

    }



    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access

        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public ImageView imgView;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                imgView = (ImageView) itemView.findViewById(R.id.thumbImage);
            }
        }
}
