package com.example.ali.androidmvvm.ui.activity.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.ali.androidmvvm.data.DataManager;

/**
 * Created by Ali Esa Assadi on 19/12/2018.
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final DataManager dataManager;

    public MainViewModelFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataManager.getMovieService().getMovieApi());
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
