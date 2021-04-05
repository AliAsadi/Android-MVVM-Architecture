package com.aliasadi.mvvm.data.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ali Asadi on 10/03/2018.
 */
@Entity(tableName = "movies")
public class Movie implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @Expose
    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;

    @Expose
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    public Movie(int id, String description, String image, String title) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    protected Movie(Parcel in) {
        description = in.readString();
        image = in.readString();
        title = in.readString();
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
