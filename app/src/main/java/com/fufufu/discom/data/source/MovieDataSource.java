package com.fufufu.discom.data.source;


import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.fufufu.discom.data.model.GenreListResponse;
import com.fufufu.discom.data.model.Movie;

import retrofit2.Call;

public interface MovieDataSource {
    Call<GenreListResponse> getGenreList();
    LiveData<PagedList<Movie>> getDiscoverMoviePagedList();
    /*Call<MovieResponse> getMoviesAPI(String language, String sortBy, int page);
    LiveData<PagedList<Movie>> getMoviesPagedList();
    LiveData<DetailMovie> getDetailMovie(int movieID, String language);*/
}
