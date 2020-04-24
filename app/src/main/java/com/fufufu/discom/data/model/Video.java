package com.fufufu.discom.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @Expose
    @SerializedName("iso_3166_1")
    private String iso_3166_1;
    @Expose
    @SerializedName("key")
    private String key;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("site")
    private String site;
    @Expose
    @SerializedName("size")
    private int size;
    @Expose
    @SerializedName("type")
    private String type;

    private Video(Parcel in) {
        id = in.readString();
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        size = in.readInt();
        type = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(iso_639_1);
        dest.writeString(iso_3166_1);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeInt(size);
        dest.writeString(type);
    }
}
