package com.fufufu.discom.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Movie implements Parcelable {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    @SerializedName("poster_path")
    private String posterPath;
    @Expose
    @SerializedName("popularity")
    private float popularity;
    @Expose
    @SerializedName("vote_average")
    private float voteAverage;

    public Movie(){

    }

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        popularity = in.readFloat();
        voteAverage = in.readFloat();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
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


    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        String BASE_POSTER = "https://image.tmdb.org/t/p/w500";
        return BASE_POSTER +posterPath;
    }


    public float getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeFloat(popularity);
        parcel.writeFloat(voteAverage);
    }

    public static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(Movie oldMovie, Movie newMovie) {
                    return oldMovie.getId() == newMovie.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Movie oldMovie,
                                                  @NonNull Movie newMovie) {
                    return Objects.equals(oldMovie, newMovie);
                }
            };
}
