package com.aliasadi.mvvm.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.aliasadi.mvvm.data.network.model.Movie;
import com.aliasadi.mvvm.data.network.model.MovieResponse;
import com.aliasadi.mvvm.data.network.services.MovieService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ali Esa Assadi on 18/12/2018.
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;
    private MutableLiveData<Boolean> isLoading;

    private MovieService movieService;

    MainViewModel(MovieService movieService) {
        this.movieService = movieService;
        movieList = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    MutableLiveData<List<Movie>> getMovies() {
        return movieList;
    }
    MutableLiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    void loadMoviesNetwork() {
        setIsLoading(true);

        Call<MovieResponse> movieCall = movieService.getMovieApi().getAllMovie();
        movieCall.enqueue(new MovieCallback());
    }
    void loadMovieLocal() {
        setIsLoading(true);

        String name = "Breaking Bad";
        String image = "https://coderwall-assets-0.s3.amazonaws.com/uploads/picture/file/622/breaking_bad_css3_svg_raw.png";

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(name,image,name));
        movies.add(new Movie(name,image,name));
        movies.add(new Movie(name,image,name));
        setMovies(movies);
    }
    void onEmptyClicked() { setMovies(Collections.<Movie>emptyList()); }

    private void setIsLoading(boolean loading) {
        isLoading.postValue(loading);
    }
    private void setMovies(List<Movie> movies) {
        setIsLoading(false);
        movieList.postValue(movies);
    }

    /**
     * Callback
     **/
    private class MovieCallback implements Callback<MovieResponse> {

        @Override
        public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
            MovieResponse movieResponse = response.body();

            if (movieResponse != null) {
                setMovies(movieResponse.getMovies());
            } else {
                setMovies(Collections.<Movie>emptyList());
            }
        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            setMovies(Collections.<Movie>emptyList());

        }
    }
}
