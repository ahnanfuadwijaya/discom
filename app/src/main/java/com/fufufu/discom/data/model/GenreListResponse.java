package com.fufufu.discom.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GenreListResponse {
    @Expose
    @SerializedName("genres")
    private ArrayList<Genre> genreList;
    public ArrayList<Genre> getGenreList() {
        return genreList;
    }
}
