package com.fufufu.discom.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Genre;
import com.fufufu.discom.ui.discover.DiscoverActivity;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.Holder> {
    private ArrayList<Genre> genreList;

    void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_genre, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.tvGenre.setText(genreList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithDataIntent = new Intent(v.getContext(), DiscoverActivity.class);
                moveWithDataIntent.putExtra("genre_id", genreList.get(position).getId());
                moveWithDataIntent.putExtra("genre_name", genreList.get(position).getName());
                v.getContext().startActivity(moveWithDataIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(genreList!=null){
            return genreList.size();
        }
        return 0;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvGenre;
        Holder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }
    }
}
