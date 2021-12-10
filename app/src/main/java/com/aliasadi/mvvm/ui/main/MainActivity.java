package com.aliasadi.mvvm.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.aliasadi.mvvm.data.DataManager;
import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.data.repository.movie.MovieRepository;
import com.aliasadi.mvvm.databinding.ActivityMainBinding;
import com.aliasadi.mvvm.ui.base.BaseActivity;
import com.aliasadi.mvvm.ui.details.DetailsActivity;

import java.util.List;

/**
 * Created by Ali Asadi on 12/03/2018.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MovieAdapter.MovieListener {

    private MovieAdapter movieAdapter;

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        MovieRepository movieRepository = DataManager.getInstance().getMovieRepository();
        MainViewModelFactory factory = new MainViewModelFactory(movieRepository);
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieAdapter = new MovieAdapter(this);
        binding.recyclerView.setAdapter(movieAdapter);

        setListeners();
        observeViewModel();
    }

    private void setListeners() {
        binding.load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.loadMovies();
            }
        });

        binding.empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onEmptyClicked();
            }
        });
    }

    private void observeViewModel() {
        viewModel.getShowLoadingLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.progressBar.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getHideLoadingLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getMoviesLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                movieAdapter.setItems(movies);
            }
        });

        viewModel.getNavigateToDetailsLiveData().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                DetailsActivity.start(MainActivity.this, movie);
            }
        });

        viewModel.getShowErrorMessageLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMovieClicked(Movie movie) {
        viewModel.onMovieClicked(movie);
    }
}
