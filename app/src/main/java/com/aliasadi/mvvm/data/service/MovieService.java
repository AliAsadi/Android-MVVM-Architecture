package com.aliasadi.mvvm.data.service;

import com.aliasadi.mvvm.data.api.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ali Asadi on 26/03/2018.
 */
public class MovieService {

    private static final String URL = "https://demo7812962.mockable.io/";

    private final MovieApi movieApi;

    private static MovieService singleton;

    private MovieService() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL).build();

        movieApi = mRetrofit.create(MovieApi.class);
    }

    public static MovieService getInstance() {
        if (singleton == null) {
            synchronized (MovieService.class) {
                if (singleton == null) {
                    singleton = new MovieService();
                }
            }
        }
        return singleton;
    }

    public MovieApi getMovieApi() {
        return movieApi;
    }

}
