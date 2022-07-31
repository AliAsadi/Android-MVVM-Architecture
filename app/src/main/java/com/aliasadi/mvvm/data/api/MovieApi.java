package com.aliasadi.mvvm.data.api;

import com.aliasadi.mvvm.data.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("/movies")
    Call<MovieResponse> getMovies();
}
