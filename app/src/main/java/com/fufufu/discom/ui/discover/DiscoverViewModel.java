package com.fufufu.discom.ui.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.fufufu.discom.data.model.Movie;
import com.fufufu.discom.data.source.Repository;


public class DiscoverViewModel extends ViewModel {
    private Repository repository;

    public DiscoverViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<PagedList<Movie>> getDiscoverMoviePagedList(int genreId) {
        return repository.getDiscoverMoviePagedList(genreId);
    }
}
