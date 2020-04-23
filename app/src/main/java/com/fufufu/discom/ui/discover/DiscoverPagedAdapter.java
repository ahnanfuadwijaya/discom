package com.fufufu.discom.ui.discover;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Movie;
import com.fufufu.discom.ui.detail.DetailActivity;
import com.fufufu.discom.viewmodelfactory.ViewModelFactory;

import java.util.Objects;

public class DiscoverPagedAdapter extends PagedListAdapter<Movie, DiscoverPagedAdapter.ViewHolder> {
    DiscoverPagedAdapter() {
        super(Movie.DIFF_CALLBACK);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_discover_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(getItem(position)!= null){
            Context context = holder.itemView.getContext();
            Glide.with(context)
                    .load(Objects.requireNonNull(getItem(position)).getPosterPath())
                    .thumbnail(0.1f)
                    .into(holder.ivPoster);
            if(Objects.requireNonNull(getItem(position)).getVoteAverage()<7){
                holder.ivScore.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_score_yellow_24dp, null));
            }
            else {
                holder.ivScore.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_score_green_24dp, null));
            }
            holder.tvScore.setText(String.valueOf(Objects.requireNonNull(getItem(position)).getVoteAverage()));
            holder.tvPopularity.setText(String.valueOf(Objects.requireNonNull(getItem(position)).getPopularity()));
            holder.tvTitle.setText(Objects.requireNonNull(getItem(position)).getTitle());
            holder.tvReleaseDate.setText(Objects.requireNonNull(getItem(position)).getReleaseDate());
            holder.tvOverview.setText(Objects.requireNonNull(getItem(position)).getOverview());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent moveWithDataIntent = new Intent(v.getContext(), DetailActivity.class);
                    moveWithDataIntent.putExtra("movie_id", Objects.requireNonNull(getItem(position)).getId());
                    moveWithDataIntent.putExtra("position", position);
                    v.getContext().startActivity(moveWithDataIntent);
                }
            });
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private ImageView ivScore;
        private TextView tvScore;
        private TextView tvPopularity;
        private TextView tvTitle;
        private TextView tvReleaseDate;
        private TextView tvOverview;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_movie_poster);
            ivScore = itemView.findViewById(R.id.iv_movie_score);
            tvScore = itemView.findViewById(R.id.tv_movie_score);
            tvPopularity = itemView.findViewById(R.id.tv_movie_popularity);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_movie_release_date);
            tvOverview = itemView.findViewById(R.id.tv_movie_overview);
        }
    }

}
