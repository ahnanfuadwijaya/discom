package com.fufufu.discom.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fufufu.discom.data.model.DetailMovie;
import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.data.model.Video;
import com.fufufu.discom.data.source.Repository;

import java.util.ArrayList;


public class DetailViewModel extends ViewModel {
    private Repository repository;

    public DetailViewModel(Repository repository) {
        this.repository = repository;
    }


    LiveData<DetailMovie> getDetailMovie(long movieID) {
        return repository.getDetailMovie(movieID);
    }
    LiveData<ArrayList<Video>> getVideoList(int id){
        return repository.getVideoList(id);
    }

    LiveData<Review> getSampleReview(int movieId){
        return repository.getSampleReview(movieId);
    }
}
