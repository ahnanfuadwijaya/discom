package com.fufufu.discom.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.data.model.ReviewResponse;
import com.fufufu.discom.data.retrofit.MovieService;
import com.fufufu.discom.data.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewMovieDataSource extends PageKeyedDataSource<Long, Review> {
    private MovieService movieService = RetrofitClient.getMovieService();


    private int movieId;


    ReviewMovieDataSource(int movieId){
        this.movieId = movieId;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Review> callback) {
        Call<ReviewResponse> call = movieService.getReviews(movieId, 1);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                assert response.body() != null;
                List<Review> reviewList = response.body().getReviewList();
                callback.onResult(reviewList, null, (long)2);
            }

            @Override
            public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Review> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Review> callback) {
        Call<ReviewResponse> call = movieService.getReviews(movieId, params.key.intValue());
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                assert response.body() != null;
                List<Review> reviewList = response.body().getReviewList();
                callback.onResult(reviewList, params.key+1);
            }

            @Override
            public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
