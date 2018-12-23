package com.example.ali.androidmvvm.ui.activity.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.ali.androidmvvm.data.network.model.Movie;
import com.example.ali.androidmvvm.data.network.model.MovieResponse;
import com.example.ali.androidmvvm.data.network.services.MovieService;

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

    private MovieService.MovieApi movieRepository;

    public MainViewModel(MovieService.MovieApi movieApi) {
        this.movieRepository = movieApi;
        movieList = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movieList;
    }
    public MutableLiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    public void loadMoviesNetwork() {
        setIsLoading(true);

        Call<MovieResponse> movieCall = movieRepository.getAllMovie();
        movieCall.enqueue(new MovieCallback());
    }
    public void loadMovieLocal() {
        setIsLoading(true);

        String name = "Breaking Bad";
        String image = "https://coderwall-assets-0.s3.amazonaws.com/uploads/picture/file/622/breaking_bad_css3_svg_raw.png";

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(name,image,name));
        movies.add(new Movie(name,image,name));
        movies.add(new Movie(name,image,name));
        setMovies(movies);
    }

    public void setIsLoading(boolean loading) {
        isLoading.postValue(loading);
    }
    public void setMovies(List<Movie> movies) {
        setIsLoading(false);
        movieList.postValue(movies);
    }

    public void onLocalButtonClick() {
        loadMovieLocal();
    }
    public void onNetworkButtonClick() {
        loadMoviesNetwork();
    }
    public void onEmptyButtonClick() {
        setMovies(Collections.<Movie>emptyList());
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
