package com.aliasadi.mvvm.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.data.model.MovieRemote;
import com.aliasadi.mvvm.data.repository.movie.MovieRepository;
import com.aliasadi.mvvm.ui.base.BaseViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ali Asadi on 18/12/2018.
 */
public class MainViewModel extends BaseViewModel {

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> showErrorMessageLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> showLoadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> hideLoadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Movie> navigateToDetailsLiveData = new MutableLiveData<>();

    private final MovieRepository moviesRepository;

    private final MovieCallback movieCallback = new MovieCallback();

    MainViewModel(MovieRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public void loadMovies() {
        setIsLoading(true);
        moviesRepository.getMovies(movieCallback);
    }

    public void onEmptyClicked() {
        setMoviesLiveData(Collections.<Movie>emptyList());
    }

    private void setIsLoading(boolean loading) {
        if (loading) {
            showLoadingLiveData.postValue(null);
        } else {
            hideLoadingLiveData.postValue(null);
        }
    }

    private void setMoviesLiveData(List<Movie> moviesLiveData) {
        setIsLoading(false);
        this.moviesLiveData.postValue(moviesLiveData);
    }

    public void onMovieClicked(Movie movie) {
        navigateToDetailsLiveData.postValue(movie);
    }

    /**
     * Callback
     **/
    private class MovieCallback implements MovieRepository.LoadMoviesCallback {
        @Override
        public void onMoviesLoaded(List<Movie> movies) {
            setMoviesLiveData(movies);
        }

        @Override
        public void onDataNotAvailable() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("There is not items!");
        }

        @Override
        public void onError() {
            setIsLoading(false);
            showErrorMessageLiveData.postValue("Something Went Wrong!");
        }
    }

    /**
     * LiveData
     **/
    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public LiveData<Void> getShowLoadingLiveData() {
        return showLoadingLiveData;
    }

    public LiveData<String> getShowErrorMessageLiveData() {
        return showErrorMessageLiveData;
    }

    public LiveData<Void> getHideLoadingLiveData() {
        return hideLoadingLiveData;
    }

    public LiveData<Movie> getNavigateToDetailsLiveData() {
        return navigateToDetailsLiveData;
    }
}
