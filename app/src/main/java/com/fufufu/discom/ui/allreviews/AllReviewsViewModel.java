package com.fufufu.discom.ui.allreviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.data.source.Repository;


public class AllReviewsViewModel extends ViewModel {
    private Repository repository;

    public AllReviewsViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<PagedList<Review>> getReviewMoviePagedList(int movieId) {
        return repository.getReviewMoviePagedList(movieId);
    }
}
