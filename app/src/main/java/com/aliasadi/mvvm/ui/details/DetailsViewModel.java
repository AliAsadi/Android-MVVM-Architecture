package com.aliasadi.mvvm.ui.details;

import android.arch.lifecycle.MutableLiveData;

import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.data.model.MovieRemote;
import com.aliasadi.mvvm.ui.base.BaseViewModel;


/**
 * Created by Ali Asadi on 12/03/2018.
 */
class DetailsViewModel extends BaseViewModel {

    private final MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();
    private final Movie movie;

    DetailsViewModel(Movie movie) {
        this.movie = movie;
    }

    public void loadMovieData() {
        movieLiveData.postValue(movie);
    }

    MutableLiveData<Movie> getMovie() {
        return movieLiveData;
    }
}
