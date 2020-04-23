package com.fufufu.discom.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailMovie implements Parcelable {
    private final String BASE_POSTER = "https://image.tmdb.org/t/p/w500";
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    @SerializedName("runtime")
    private int runtime;
    @Expose
    @SerializedName("budget")
    private int budget;
    @Expose
    @SerializedName("revenue")
    private int revenue;
    @Expose
    @SerializedName("vote_count")
    private int voteCount;
    @Expose
    @SerializedName("genres")
    private ArrayList<Genre> genreList;
    @Expose
    @SerializedName("poster_path")
    private String posterPath;
    @Expose
    @SerializedName("original_title")
    private String originalTitle;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("popularity")
    private float popularity;
    @Expose
    @SerializedName("vote_average")
    private float voteAverage;

    public DetailMovie(){
    }

    DetailMovie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        status = in.readString();
        releaseDate = in.readString();
        runtime = in.readInt();
        budget = in.readInt();
        revenue = in.readInt();
        voteCount = in.readInt();
        genreList = in.createTypedArrayList(Genre.CREATOR);
        posterPath = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        popularity = in.readFloat();
        voteAverage = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeString(releaseDate);
        dest.writeInt(runtime);
        dest.writeInt(budget);
        dest.writeInt(revenue);
        dest.writeInt(voteCount);
        dest.writeTypedList(genreList);
        dest.writeString(posterPath);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeFloat(popularity);
        dest.writeFloat(voteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailMovie> CREATOR = new Creator<DetailMovie>() {
        @Override
        public DetailMovie createFromParcel(Parcel in) {
            return new DetailMovie(in);
        }

        @Override
        public DetailMovie[] newArray(int size) {
            return new DetailMovie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String releaseStatus) {
        this.status = releaseStatus;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public ArrayList<Genre> getGenres() {
        return genreList;
    }

    public void setGenres(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getPosterPath() {
        return BASE_POSTER+posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
}
