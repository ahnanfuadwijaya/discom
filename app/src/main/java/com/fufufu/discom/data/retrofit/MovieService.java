package com.fufufu.discom.data.retrofit;

import com.fufufu.discom.data.model.DetailMovie;
import com.fufufu.discom.data.model.GenreListResponse;
import com.fufufu.discom.data.model.MovieListResponse;
import com.fufufu.discom.data.model.ReviewResponse;
import com.fufufu.discom.data.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    String API_KEY = "f240487696509310687e5998a34a405f";
    @GET("3/genre/movie/list?api_key="+API_KEY)
    Call<GenreListResponse> getGenreList();

    @GET("3/discover/movie?api_key="+API_KEY)
    Call<MovieListResponse> getMovies(@Query("sort_by") String sortBy,
                                      @Query("with_genres") int withGenres,
                                      @Query("page") int page);

    @GET("3/movie/{movie_id}?api_key="+API_KEY)
    Call<DetailMovie> getDetailMovie(@Path("movie_id") long movieID);

    @GET("3/movie/{movie_id}/videos?api_key="+API_KEY)
    Call<VideoResponse> getVideoMovie(@Path("movie_id") long movieID);

    @GET("3/movie/{movie_id}/reviews?api_key="+API_KEY)
    Call<ReviewResponse> getReviews(@Path("movie_id") long movieID,
                                    @Query("page") int page);

    @GET("3/movie/{movie_id}/reviews?api_key="+API_KEY)
    Call<ReviewResponse> getSampleReview(@Path("movie_id") long movieID);
}
