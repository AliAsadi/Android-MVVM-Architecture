package com.aliasadi.mvvm.ui.details;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.data.model.MovieRemote;


/**
 * Created by Ali Asadi on 19/12/2018.
 */
public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private final Movie movie;

    public DetailsViewModelFactory(Movie movie) {
        this.movie = movie;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(movie);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
