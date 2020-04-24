package com.fufufu.discom.ui.allreviews;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.viewmodelfactory.ViewModelFactory;

public class AllReviewsActivity extends AppCompatActivity {
    private RecyclerView rvAllReviews;
    private ReviewPagedAdapter reviewPagedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DiscoM - All Reviews");
        setContentView(R.layout.activity_all_reviews);
        rvAllReviews = findViewById(R.id.rv_all_reviews);
        final int movieID = getIntent().getIntExtra("movie_id", 0);
        final String movieTitle = getIntent().getStringExtra("movie_title");
        TextView tvMovieTitle = findViewById(R.id.tv_all_reviews_movie_title);
        tvMovieTitle.setText(movieTitle);
        rvAllReviews = findViewById(R.id.rv_all_reviews);
        rvAllReviews.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        reviewPagedAdapter = new ReviewPagedAdapter();
        AllReviewsViewModel allReviewsViewModel = obtainViewModel(this);
        allReviewsViewModel.getReviewMoviePagedList(movieID).observe(this, new Observer<PagedList<Review>>() {
            @Override
            public void onChanged(PagedList<Review> reviews) {
                reviewPagedAdapter.submitList(reviews);
                rvAllReviews.setAdapter(reviewPagedAdapter);
            }
        });
    }

    private static AllReviewsViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(activity, factory).get(AllReviewsViewModel.class);
    }
}
