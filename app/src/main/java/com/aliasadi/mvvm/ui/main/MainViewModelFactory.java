package com.aliasadi.mvvm.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aliasadi.mvvm.data.repository.movie.MovieRepository;
import com.aliasadi.mvvm.data.repository.movie.MovieRepositoryImpl;

/**
 * Created by Ali Asadi on 19/12/2018.
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository moviesRepository;

    public MainViewModelFactory(MovieRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(moviesRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
