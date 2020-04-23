package com.fufufu.discom.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.fufufu.discom.data.model.DetailMovie;
import com.fufufu.discom.data.model.Genre;
import com.fufufu.discom.data.model.GenreListResponse;
import com.fufufu.discom.data.model.Movie;
import com.fufufu.discom.data.model.Review;
import com.fufufu.discom.data.model.Video;
import com.fufufu.discom.data.model.VideoResponse;
import com.fufufu.discom.data.retrofit.MovieService;
import com.fufufu.discom.data.retrofit.RetrofitClient;
import com.fufufu.discom.paging.DiscoverMovieDataSourceFactory;
import com.fufufu.discom.paging.ReviewMovieDataSourceFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository INSTANCE;
    private MovieService movieService = RetrofitClient.getMovieService();

    public static Repository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    public MutableLiveData<ArrayList<Genre>> getGenreList() {
        final MutableLiveData<ArrayList<Genre>> genreList = new MutableLiveData<>();
        Call<GenreListResponse> call = movieService.getGenreList();
        call.enqueue(new Callback<GenreListResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenreListResponse> call, @NonNull Response<GenreListResponse> response) {
                if(response.body()!=null){
                    genreList.setValue(response.body().getGenreList());
                }
            }

            @Override
            public void onFailure(Call<GenreListResponse> call, Throwable t) {
                Log.d("Repository-getGenreList", "onFailure");
                if(t.getMessage()!=null){
                    Log.d("Message", t.getMessage());
                }
            }
        });
        return genreList;
    }

    public LiveData<PagedList<Movie>> getDiscoverMoviePagedList(int genreId){
        LiveData<PagedList<Movie>> movies;
        DiscoverMovieDataSourceFactory discoverMovieDataSourceFactory = new DiscoverMovieDataSourceFactory(genreId);
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        MainThreadExecutor executor = new MainThreadExecutor();

        movies = (new LivePagedListBuilder<Long,Movie>(discoverMovieDataSourceFactory,config))
                .setBoundaryCallback(new PagedList.BoundaryCallback<Movie>() {
                    @Override
                    public void onZeroItemsLoaded() {
                        super.onZeroItemsLoaded();
                    }
                })
                .setFetchExecutor(executor)
                .build();
        return movies;
    }

    public MutableLiveData<DetailMovie> getDetailMovie(long movieID) {
        final MutableLiveData<DetailMovie> detailMovie = new MutableLiveData<>();
        MovieService movieService = RetrofitClient.getMovieService();
        Call<DetailMovie> call = movieService.getDetailMovie(movieID);
        call.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(@NonNull Call<DetailMovie> call,@NonNull Response<DetailMovie> response) {
                detailMovie.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<DetailMovie> call,@NonNull Throwable t) {
            }
        });
        return detailMovie;
    }

    public MutableLiveData<ArrayList<Video>> getVideoList(int id) {
        final MutableLiveData<ArrayList<Video>> videoList = new MutableLiveData<>();
        Call<VideoResponse> call = movieService.getVideoMovie(id);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if(response.body()!=null){
                    videoList.setValue(response.body().getVideoList());
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Log.d("Repository-getVideoList", "onFailure");
                if(t.getMessage()!=null){
                    Log.d("Message", t.getMessage());
                }
            }
        });
        return videoList;
    }

    public LiveData<PagedList<Review>> getReviewMoviePagedList(int movieId){
        LiveData<PagedList<Review>> reviewList;
        ReviewMovieDataSourceFactory reviewMovieDataSourceFactory = new ReviewMovieDataSourceFactory(movieId);
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        MainThreadExecutor executor = new MainThreadExecutor();

        reviewList = (new LivePagedListBuilder<Long,Review>(reviewMovieDataSourceFactory,config))
                .setBoundaryCallback(new PagedList.BoundaryCallback<Review>() {
                    @Override
                    public void onZeroItemsLoaded() {
                        super.onZeroItemsLoaded();
                    }
                })
                .setFetchExecutor(executor)
                .build();
        return reviewList;
    }
}
