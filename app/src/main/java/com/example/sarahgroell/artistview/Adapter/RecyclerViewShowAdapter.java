package com.example.sarahgroell.artistview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by aurelie.debrot on 08.11.2016.
 */

public class RecyclerViewShowAdapter extends RecyclerView.Adapter<RecyclerViewShowAdapter.ViewHolder> {

    private List<Show> listShow;

    public RecyclerViewShowAdapter(List<Show> listShow) {
        this.listShow = listShow;
    }

    @Override
    public RecyclerViewShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_show_list, parent, false);

        RecyclerViewShowAdapter.ViewHolder vh = new RecyclerViewShowAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewShowAdapter.ViewHolder holder, int position) {
        holder.showPlace.setText(listShow.get(position).place);
        holder.imageCover.setImageURI(listShow.get(position).imageCover);
        holder.showDate.setText(listShow.get(position).date);
       // holder.imageArtist.setImageURI(listShow.get(position).artist.imageCover);
    }

    @Override
    public int getItemCount() {
        return listShow.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView showPlace;
        public SimpleDraweeView imageCover;
        public TextView showDate;
        //public SimpleDraweeView imageArtist;

        public ViewHolder(View itemView){
            super(itemView);
            showPlace = (TextView) itemView.findViewById(R.id.textView);
            imageCover= (SimpleDraweeView) itemView.findViewById(R.id.imageHall);
            showDate = (TextView) itemView.findViewById(R.id.date);
            //imageArtist = (SimpleDraweeView) itemView.findViewById(R.id.artist);
        }

    }
}
