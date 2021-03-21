package com.aliasadi.mvvm.data.movie.source.remote.services;

import com.aliasadi.mvvm.data.movie.source.remote.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movies/")
    Call<MovieResponse> getMovies();
}
