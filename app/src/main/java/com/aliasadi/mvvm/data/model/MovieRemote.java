package com.aliasadi.mvvm.data.model;

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
public class MovieRemote {

    private int id;
    private String description;
    private String image;
    private String title;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
