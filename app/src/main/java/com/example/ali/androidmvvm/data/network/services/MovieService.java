package com.example.ali.androidmvvm.data.network.services;

import com.example.ali.androidmvvm.data.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Ali Esa Assadi on 26/03/2018.
 */
public class MovieService {

    private static final String URL = "http://demo6483760.mockable.io/";

    private MovieApi mMovieApi;

    public MovieService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mMovieApi = mRetrofit.create(MovieApi.class);
    }

    public MovieApi getMovieApi() {
        return mMovieApi;
    }

    public interface MovieApi {
        @GET("movies/") Call<MovieResponse> getAllMovie();
    }

}


