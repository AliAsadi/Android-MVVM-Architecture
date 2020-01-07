package com.example.ali.androidmvvm.ui.details;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import com.example.ali.androidmvvm.data.network.model.Movie;


/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class DetailsViewModel extends ViewModel {

    MutableLiveData<Movie> movie;

    public DetailsViewModel() {
        this.movie = new MutableLiveData<>();
    }

    public void loadMovieData(Intent intent) {
        assert intent.getExtras() != null;
        Movie movieExtra = intent.getExtras().getParcelable("movie");

        movie.postValue(movieExtra);
    }

    public MutableLiveData<Movie> getMovie() {
        return movie;
    }
}
