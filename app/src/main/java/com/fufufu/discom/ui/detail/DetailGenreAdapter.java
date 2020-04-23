package com.fufufu.discom.ui.detail;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Genre;
import com.fufufu.discom.data.model.Video;
import com.fufufu.discom.ui.discover.DiscoverActivity;
import com.fufufu.discom.ui.home.GenreAdapter;

import java.util.ArrayList;

public class DetailGenreAdapter extends RecyclerView.Adapter<DetailGenreAdapter.Holder> {
    private ArrayList<Genre> genreList;
    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public DetailGenreAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detail_genre, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailGenreAdapter.Holder holder, final int position) {
        holder.tvGenre.setText(genreList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(genreList!=null){
            return genreList.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvGenre;
        Holder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }
    }
}
