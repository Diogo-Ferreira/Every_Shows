package com.example.sarahgroell.artistview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by sarah.groell on 01.11.2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public RecyclerViewAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    private List<Artist> artists;

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.artistName.setText(artists.get(position).name);
        holder.imageCover.setImageURI(artists.get(position).imageCover);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView artistName;
        public SimpleDraweeView imageCover;

        public ViewHolder(View itemView) {
            super(itemView);
            artistName = (TextView) itemView.findViewById(R.id.textView);
            imageCover= (SimpleDraweeView) itemView.findViewById(R.id.image);
        }
    }
}
