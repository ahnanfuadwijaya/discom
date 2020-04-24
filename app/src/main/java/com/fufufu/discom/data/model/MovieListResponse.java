package com.fufufu.discom.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {
    @Expose
    @SerializedName("page")
    private int page;
    @Expose
    @SerializedName("results")
    private List<Movie> movies;
    @Expose
    @SerializedName("total_results")
    private int totalResults;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;

    public List<Movie> getMovies() {
        return movies;
    }
}
