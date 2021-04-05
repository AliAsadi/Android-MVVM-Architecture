package com.aliasadi.mvvm.ui.details;

import android.arch.lifecycle.MutableLiveData;

import com.aliasadi.mvvm.data.model.MovieRemote;
import com.aliasadi.mvvm.ui.base.BaseViewModel;


/**
 * Created by Ali Asadi on 12/03/2018.
 */
class DetailsViewModel extends BaseViewModel {

    private final MutableLiveData<MovieRemote> movieLiveData = new MutableLiveData<>();
    private final MovieRemote movie;

    DetailsViewModel(MovieRemote movie) {
        this.movie = movie;
    }

    public void loadMovieData() {
        movieLiveData.postValue(movie);
    }

    MutableLiveData<MovieRemote> getMovie() {
        return movieLiveData;
    }
}
