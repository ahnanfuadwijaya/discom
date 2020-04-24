package com.fufufu.discom.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.fufufu.discom.data.model.Movie;
import com.fufufu.discom.data.model.MovieListResponse;
import com.fufufu.discom.data.retrofit.MovieService;
import com.fufufu.discom.data.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverMovieDataSource extends PageKeyedDataSource<Long, Movie> {
    private MovieService movieService = RetrofitClient.getMovieService();


    private int withGenres;


    DiscoverMovieDataSource(int genreId){
        withGenres = genreId;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        Call<MovieListResponse> call = movieService.getMovies("popularity.desc", withGenres, 1);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                assert response.body() != null;
                List<Movie> movies = response.body().getMovies();
                callback.onResult(movies, null, (long)2);
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        Call<MovieListResponse> call = movieService.getMovies("popularity.desc", withGenres, params.key.intValue());
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                assert response.body() != null;
                List<Movie> movies = response.body().getMovies();
                callback.onResult(movies, params.key+1);
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
            }
        });
    }
}
