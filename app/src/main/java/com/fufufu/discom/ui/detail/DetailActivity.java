package com.fufufu.discom.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.fufufu.discom.R;
import com.fufufu.discom.data.model.DetailMovie;
import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.data.model.Video;
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
    private RecyclerView rvReview;
    private ImageView ivPoster;
    private TextView tvOriginalTitle;
    private TextView tvOverview;
    private TextView tvPopularity;
    private TextView tvScore;
    private ImageView ivScore;
    YouTubePlayerView ytPlayerView;
    private DetailGenreAdapter detailGenreAdapter;
    private ReviewPagedAdapter reviewPagedAdapter;
    private DetailViewModel detailMovieViewModel;
    private final String VIDEO_URL = "https://www.youtube.com/embed/";
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DiscoM - Detail Movie");
        setContentView(R.layout.activity_detail);
        detailMovieViewModel = obtainViewModel(this);
        final int movieID = getIntent().getIntExtra("movie_id", 0);
        Log.d("MovieID", String.valueOf(movieID));
        tvTitle = findViewById(R.id.tv_detail_title);
        tvStatus = findViewById(R.id.tv_detail_release_status_value);
        tvReleaseDate = findViewById(R.id.tv_detail_release_date_value);
        tvRuntime = findViewById(R.id.tv_detail_runtime_value);
        tvBudget = findViewById(R.id.tv_detail_budget_value);
        tvRevenue = findViewById(R.id.tv_detail_revenue_value);
        tvVoteCount = findViewById(R.id.tv_detail_vote_count_value);
        rvDetailGenre = findViewById(R.id.rv_detail_genre);
        rvReview = findViewById(R.id.rv_detail_review);
        ivPoster = findViewById(R.id.iv_detail_poster);
        tvOriginalTitle = findViewById(R.id.tv_detail_original_title_value);
        tvOverview = findViewById(R.id.tv_detail_overview_value);
        tvPopularity = findViewById(R.id.tv_detail_popularity_value);
        tvScore = findViewById(R.id.tv_detail_score_value);
        ivScore = findViewById(R.id.iv_detail_score);
        ytPlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(ytPlayerView);
        rvDetailGenre.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.HORIZONTAL, false));
        rvReview.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        detailGenreAdapter = new DetailGenreAdapter();
        reviewPagedAdapter = new ReviewPagedAdapter();

        detailMovieViewModel.getDetailMovie(movieID).observe(this, new Observer<DetailMovie>() {
            @Override
            public void onChanged(DetailMovie detailMovie) {
                if(detailMovie!=null){
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
                    Glide.with(getBaseContext())
                            .load(detailMovie.getPosterPath())
                            .override(250, 400)
                            .into(ivPoster);
                }
            }
        });
        detailMovieViewModel.getVideoList(movieID).observe(this, new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> videos) {
                Log.d("videoKey", videos.get(0).getKey());
                Log.d("videoSite", videos.get(0).getSite());
                if(videos.size()>0){
                    key = videos.get(0).getKey();
                    ytPlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            super.onReady(youTubePlayer);
                            youTubePlayer.loadVideo(key, 0f);
                        }
                    });
                    Log.d("videoURL", VIDEO_URL+key);
                }
            }
        });
        detailMovieViewModel.getReviewMoviePagedList(movieID).observe(this, new Observer<PagedList<Review>>() {
            @Override
                public void onChanged(PagedList<Review> reviews) {
                Log.d("ReviewPagedList", String.valueOf(movieID));
                reviewPagedAdapter.submitList(reviews);
                rvReview.setAdapter(reviewPagedAdapter);
            }
        });
    }
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(DetailViewModel.class);
    }
}
