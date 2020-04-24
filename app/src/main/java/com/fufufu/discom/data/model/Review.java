package com.fufufu.discom.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Review implements Parcelable {
    @Expose
    @SerializedName("author")
    private String author;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("url")
    private String url;

    private Review(Parcel in) {
        author = in.readString();
        content = in.readString();
        id = in.readString();
        url = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(id);
        dest.writeString(url);
    }
    public static DiffUtil.ItemCallback<Review> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Review>() {
                @Override
                public boolean areItemsTheSame(Review oldReview, Review newReview) {
                    return oldReview.getId().equals(newReview.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Review oldReview,
                                                  @NonNull Review newReview) {
                    return Objects.equals(oldReview, newReview);
                }
            };
}