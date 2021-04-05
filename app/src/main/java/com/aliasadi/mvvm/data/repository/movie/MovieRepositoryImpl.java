package com.aliasadi.mvvm.data.repository.movie;

import com.aliasadi.mvvm.data.domain.Movie;

import java.util.List;

/**
 * Created by Ali Asadi on 29/01/2019.
 */
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieDataSource.Remote movieRemote;
    private final MovieDataSource.Local movieLocal;
    private final MovieDataSource.Local movieCache;

    private static MovieRepositoryImpl instance;

    private MovieRepositoryImpl(MovieRemoteDataSource movieRemote,
                                MovieLocalDataSource movieLocal,
                                MovieCacheDataSource movieCache) {

        this.movieRemote = movieRemote;
        this.movieLocal = movieLocal;
        this.movieCache = movieCache;
    }

    public static MovieRepositoryImpl getInstance(MovieRemoteDataSource movieRemote,
                                                  MovieLocalDataSource movieLocal,
                                                  MovieCacheDataSource movieCache) {
        if (instance == null) {
            instance = new MovieRepositoryImpl(movieRemote, movieLocal, movieCache);
        }
        return instance;
    }

    @Override
    public void getMovies(final MovieRepository.LoadMoviesCallback callback) {
        if (callback == null) return;

        movieCache.getMovies(new MovieRepository.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromLocalDataSource(callback);
            }

            @Override
            public void onError() {
                //not implemented in cache data source
            }
        });

    }

    @Override
    public void saveMovies(List<Movie> movies) {
        movieLocal.saveMovies(movies);
    }

    private void getMoviesFromLocalDataSource(final MovieRepository.LoadMoviesCallback callback) {
        movieLocal.getMovies(new MovieRepository.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
                refreshCache(movies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromRemoteDataSource(callback);
            }

            @Override
            public void onError() {
                //not implemented in local data source
            }
        });
    }

    private void getMoviesFromRemoteDataSource(final MovieRepository.LoadMoviesCallback callback) {
        movieRemote.getMovies(new MovieRepository.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
                saveMovies(movies);
                refreshCache(movies);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    private void refreshCache(List<Movie> movies) {
        movieCache.saveMovies(movies);
    }
}
