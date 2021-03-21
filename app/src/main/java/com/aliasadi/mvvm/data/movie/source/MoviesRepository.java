package com.aliasadi.mvvm.data.movie.source;

import com.aliasadi.mvvm.data.movie.Movie;
import com.aliasadi.mvvm.data.movie.source.local.MovieCacheDataSource;
import com.aliasadi.mvvm.data.movie.source.local.MovieLocalDataSource;
import com.aliasadi.mvvm.data.movie.source.remote.MovieRemoteDataSource;

import java.util.List;

/**
 * Created by Ali Asadi on 29/01/2019.
 */
public class MoviesRepository implements MovieDataSource {

    private final MovieDataSource movieRemote;
    private final MovieDataSource movieLocal;
    private final MovieDataSource movieCache;

    private static MoviesRepository instance;

    private MoviesRepository(MovieRemoteDataSource movieRemote,
                             MovieLocalDataSource movieLocal,
                             MovieCacheDataSource movieCache) {

        this.movieRemote = movieRemote;
        this.movieLocal = movieLocal;
        this.movieCache = movieCache;
    }

    public static MoviesRepository getInstance(MovieRemoteDataSource movieRemote,
                                               MovieLocalDataSource movieLocal,
                                               MovieCacheDataSource movieCache) {
        if (instance == null) {
            instance = new MoviesRepository(movieRemote, movieLocal, movieCache);
        }
        return instance;
    }

    @Override
    public void getMovies(final LoadMoviesCallback callback) {
        if (callback == null) return;

        movieCache.getMovies(new LoadMoviesCallback() {
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

    private void getMoviesFromLocalDataSource(final LoadMoviesCallback callback) {
        movieLocal.getMovies(new LoadMoviesCallback() {
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

    private void getMoviesFromRemoteDataSource(final LoadMoviesCallback callback) {
        movieRemote.getMovies(new LoadMoviesCallback() {
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

    public void destroyInstance() {
        instance = null;
    }
}
