package com.aliasadi.mvvm.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ali Asadi on 10/03/2018.
 */
public class Movie implements Parcelable{

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("image")
    private String image;

    @Expose
    @SerializedName("title")
    private String title;

    protected Movie(Parcel in) {
        description = in.readString();
        image = in.readString();
        title = in.readString();
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

    public Movie(String description, String image, String title) {
        this.description = description;
        this.image = image;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(title);
    }
}
