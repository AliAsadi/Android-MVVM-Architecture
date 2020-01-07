package com.example.ali.androidmvvm.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.ali.androidmvvm.data.network.services.MovieService;

/**
 * Created by Ali Esa Assadi on 19/12/2018.
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final MovieService movieService;

    public MainViewModelFactory(MovieService movieService) {
        this.movieService = movieService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(movieService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
