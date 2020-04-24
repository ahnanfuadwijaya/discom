package com.fufufu.discom.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ReviewMovieDataSourceFactory extends DataSource.Factory {
    private int movieId;
    private MutableLiveData<ReviewMovieDataSource> mutableLiveData;

    public ReviewMovieDataSourceFactory(int movieId){
        mutableLiveData = new MutableLiveData<>();
        this.movieId = movieId;
    }

    @Override
    public DataSource create() {
        ReviewMovieDataSource reviewMovieDataSource = new ReviewMovieDataSource(movieId);
        mutableLiveData.postValue(reviewMovieDataSource);
        return reviewMovieDataSource;
    }
}
