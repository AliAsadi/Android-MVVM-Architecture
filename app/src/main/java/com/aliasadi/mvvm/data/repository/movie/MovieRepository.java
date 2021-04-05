package com.aliasadi.mvvm.data.repository.movie;

import com.aliasadi.mvvm.data.domain.Movie;

import java.util.List;

/**
 * Created by Ali Asadi on 05/04/2021
 */
public interface MovieRepository {

    interface LoadMoviesCallback {
        void onMoviesLoaded(List<Movie> movies);
        void onDataNotAvailable();
        void onError();
    }

    void getMovies(LoadMoviesCallback callback);
    void saveMovies(List<Movie> movies);
}
