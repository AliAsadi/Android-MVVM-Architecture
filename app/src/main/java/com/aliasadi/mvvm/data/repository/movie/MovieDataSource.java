package com.aliasadi.mvvm.data.repository.movie;

import com.aliasadi.mvvm.data.domain.Movie;

import java.util.List;

/**
 * Created by Ali Asadi on 30/01/2019.
 */
public interface MovieDataSource {

    interface Remote {
        void getMovies(MovieRepository.LoadMoviesCallback callback);
    }

    interface Local extends Remote {
        void saveMovies(List<Movie> movies);
    }
}
