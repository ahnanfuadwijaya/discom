package com.fufufu.discom.ui.detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fufufu.discom.R;
import com.fufufu.discom.data.model.DetailMovie;
import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.data.model.Video;
import com.fufufu.discom.ui.allreviews.AllReviewsActivity;
import com.fufufu.discom.viewmodelfactory.ViewModelFactory;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvStatus;
    private TextView tvReleaseDate;
    private TextView tvRuntime;
    private TextView tvBudget;
    private TextView tvRevenue;
    private TextView tvVoteCount;
    private RecyclerView rvDetailGenre;
    private ImageView ivPoster;
    private TextView tvOriginalTitle;
    private TextView tvOverview;
    private TextView tvPopularity;
    private TextView tvScore;
    private ImageView ivScore;
    private ImageView ivPerson;
    private TextView tvAuthor;
    private TextView tvContent;
    private TextView tvNoData;
    private Button btnShowAllReviews;
    private YouTubePlayerView ytPlayerView;
    private DetailGenreAdapter detailGenreAdapter;
    private String key;
    private String movieTitle="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DiscoM - Detail Movie");
        setContentView(R.layout.activity_detail);
        DetailViewModel detailMovieViewModel = obtainViewModel(this);
        final int movieID = getIntent().getIntExtra("movie_id", 0);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvStatus = findViewById(R.id.tv_detail_release_status_value);
        tvReleaseDate = findViewById(R.id.tv_detail_release_date_value);
        tvRuntime = findViewById(R.id.tv_detail_runtime_value);
        tvBudget = findViewById(R.id.tv_detail_budget_value);
        tvRevenue = findViewById(R.id.tv_detail_revenue_value);
        tvVoteCount = findViewById(R.id.tv_detail_vote_count_value);
        tvNoData = findViewById(R.id.tv_review_sample_no_data);
        rvDetailGenre = findViewById(R.id.rv_detail_genre);
        ivPoster = findViewById(R.id.iv_detail_poster);
        ivPerson = findViewById(R.id.iv_detail_person);
        tvOriginalTitle = findViewById(R.id.tv_detail_original_title_value);
        tvOverview = findViewById(R.id.tv_detail_overview_value);
        tvPopularity = findViewById(R.id.tv_detail_popularity_value);
        tvScore = findViewById(R.id.tv_detail_score_value);
        tvAuthor = findViewById(R.id.tv_review_author_value);
        tvContent = findViewById(R.id.tv_review_content_value);
        ivScore = findViewById(R.id.iv_detail_score);
        ytPlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(ytPlayerView);
        rvDetailGenre.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.HORIZONTAL, false));
        detailGenreAdapter = new DetailGenreAdapter();
        btnShowAllReviews = findViewById(R.id.btn_show_all_reviews);
        btnShowAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithIntent = new Intent(v.getContext(), AllReviewsActivity.class);
                moveWithIntent.putExtra("movie_id", movieID);
                moveWithIntent.putExtra("movie_title", movieTitle);
                v.getContext().startActivity(moveWithIntent);
            }
        });
        detailMovieViewModel.getDetailMovie(movieID).observe(this, new Observer<DetailMovie>() {
            @Override
            public void onChanged(DetailMovie detailMovie) {
                if(detailMovie!=null){
                    movieTitle = detailMovie.getTitle();
                    tvTitle.setText(detailMovie.getTitle());
                    tvStatus.setText(detailMovie.getStatus());
                    tvReleaseDate.setText(detailMovie.getReleaseDate());
                    tvRuntime.setText(String.valueOf(detailMovie.getRuntime()));
                    tvBudget.setText(String.valueOf(detailMovie.getBudget()));
                    tvRevenue.setText(String.valueOf(detailMovie.getRevenue()));
                    tvVoteCount.setText(String.valueOf(detailMovie.getVoteCount()));
                    detailGenreAdapter.setGenreList(detailMovie.getGenres());
                    rvDetailGenre.setAdapter(detailGenreAdapter);
                    tvOriginalTitle.setText(detailMovie.getOriginalTitle());
                    tvOverview.setText(detailMovie.getOverview());
                    tvPopularity.setText(String.valueOf(detailMovie.getPopularity()));
                    tvScore.setText(String.valueOf(detailMovie.getVoteAverage()));
                    if(detailMovie.getVoteAverage()<7){
                        ivScore.setImageDrawable(getResources().getDrawable(R.drawable.ic_score_yellow_24dp, null));
                    }
                    else {
                        ivScore.setImageDrawable(getResources().getDrawable(R.drawable.ic_score_green_24dp, null));
                    }
                    Drawable brokenImage = getResources().getDrawable(R.drawable.ic_broken_image_black_24dp);
                    Glide.with(getBaseContext())
                            .load(detailMovie.getPosterPath())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                            .override(250, 400)
                            .error(brokenImage)
                            .into(ivPoster);
                }
            }
        });
        detailMovieViewModel.getVideoList(movieID).observe(this, new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> videos) {
                if(videos.size()>0){
                    key = videos.get(0).getKey();
                    ytPlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            super.onReady(youTubePlayer);
                            youTubePlayer.loadVideo(key, 0f);
                        }
                    });
                }
            }
        });
        detailMovieViewModel.getSampleReview(movieID).observe(this, new Observer<Review>() {
            @Override
                public void onChanged(Review review) {
                tvNoData.setVisibility(View.GONE);
                ivPerson.setVisibility(View.VISIBLE);
                btnShowAllReviews.setVisibility(View.VISIBLE);
                tvAuthor.setText(review.getAuthor());
                tvContent.setText(review.getContent());

            }
        });
    }
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(activity, factory).get(DetailViewModel.class);
    }
}
