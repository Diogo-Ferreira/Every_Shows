package com.example.sarahgroell.artistview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Listener.IArtistListener;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by sarah.groell on 06.12.2016.
 */

public class SimilarArtistAdapter extends RecyclerView.Adapter<SimilarArtistAdapter.ViewHolder> {
    private List<Artist> similarArtists;
    private IArtistListener listener;


    public SimilarArtistAdapter(List<Artist> similarArtists) {
        this.similarArtists = similarArtists;
    }

    @Override
    public SimilarArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar_artist_item, parent, false);
        SimilarArtistAdapter.ViewHolder vh = new SimilarArtistAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimilarArtistAdapter.ViewHolder holder, final int position) {
        holder.artistName.setText(similarArtists.get(position).name);
        holder.imageCover.setImageURI(similarArtists.get(position).imageCover);
        holder.imageCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClickArtist(similarArtists.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return similarArtists.size();
    }

    public void setListener(IArtistListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView artistName;
        public SimpleDraweeView imageCover;

        public ViewHolder(View itemView) {
            super(itemView);
            artistName = (TextView) itemView.findViewById(R.id.simArtistName);
            imageCover= (SimpleDraweeView) itemView.findViewById(R.id.simArtistImage);
        }
    }
}
