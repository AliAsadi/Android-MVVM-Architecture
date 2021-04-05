package com.aliasadi.mvvm.data.repository.movie;

import com.aliasadi.mvvm.data.api.MovieApi;
import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.data.mapper.MovieMapper;
import com.aliasadi.mvvm.data.model.MovieRemote;
import com.aliasadi.mvvm.data.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource.Remote {

    private static MovieRemoteDataSource instance;

    private final MovieApi movieApi;

    private MovieRemoteDataSource(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    public static MovieRemoteDataSource getInstance(MovieApi movieApi) {
        if (instance == null) {
            instance = new MovieRemoteDataSource(movieApi);
        }
        return instance;
    }

    @Override
    public void getMovies(final MovieRepository.LoadMoviesCallback callback) {
        movieApi.getMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieRemote> movies = response.body() != null ? response.body().getMovies() : null;
                if (movies != null && !movies.isEmpty()) {
                    final List<Movie> moviesDomain = new ArrayList<>();
                    for (MovieRemote movieRemote : movies) {
                        moviesDomain.add(MovieMapper.toDomain(movieRemote));
                    }
                    callback.onMoviesLoaded(moviesDomain);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
