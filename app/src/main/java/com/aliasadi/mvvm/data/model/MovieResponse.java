package com.aliasadi.mvvm.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ali Asadi on 24/03/2018.
 */

public class MovieResponse {

    @Expose
    @SerializedName("movies")
    private List<MovieRemote> movies;

    public List<MovieRemote> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieRemote> movies) {
        this.movies = movies;
    }
}
