package com.example.sarahgroell.artistview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.vision.text.Text;

import java.util.List;

/**
 * Created by sarah.groell on 10.01.2017.
 */

public class NextShowArtistAdapter extends RecyclerView.Adapter<NextShowArtistAdapter.ViewHolder> {
    private List<Show> nextShows;

    public NextShowArtistAdapter(List<Show> nextShows) {
        this.nextShows = nextShows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.next_show_artist_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nextShowPlace.setText(nextShows.get(position).place);
        holder.nextShowDate.setText(nextShows.get(position).date);
        holder.imageCover.setImageURI(nextShows.get(position).imageCover);
    }

    @Override
    public int getItemCount() {
        return nextShows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nextShowPlace;
        public TextView nextShowDate;

        public SimpleDraweeView imageCover;

        public ViewHolder(View itemView) {
            super(itemView);
            nextShowPlace = (TextView) itemView.findViewById(R.id.nextShowPlace);
            nextShowDate = (TextView) itemView.findViewById(R.id.nextShowDate);
            imageCover = (SimpleDraweeView) itemView.findViewById(R.id.nextShowImage);



        }
    }
}
