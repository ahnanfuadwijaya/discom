package com.fufufu.discom.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse implements Parcelable {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("page")
    private int page;
    @Expose
    @SerializedName("results")
    private List<Review> reviewList;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    @SerializedName("total_results")
    private int totalResults;

    private ReviewResponse(Parcel in) {
        id = in.readInt();
        page = in.readInt();
        reviewList = in.createTypedArrayList(Review.CREATOR);
        totalPages = in.readInt();
        totalResults = in.readInt();
    }

    public static final Creator<ReviewResponse> CREATOR = new Creator<ReviewResponse>() {
        @Override
        public ReviewResponse createFromParcel(Parcel in) {
            return new ReviewResponse(in);
        }

        @Override
        public ReviewResponse[] newArray(int size) {
            return new ReviewResponse[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(page);
        dest.writeTypedList(reviewList);
        dest.writeInt(totalPages);
        dest.writeInt(totalResults);
    }
}
