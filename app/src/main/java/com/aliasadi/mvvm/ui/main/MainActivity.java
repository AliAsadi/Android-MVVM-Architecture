package com.aliasadi.mvvm.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aliasadi.mvvm.R;
import com.aliasadi.mvvm.data.DataManager;
import com.aliasadi.mvvm.data.movie.Movie;
import com.aliasadi.mvvm.data.movie.source.MoviesRepository;
import com.aliasadi.mvvm.ui.base.BaseActivity;
import com.aliasadi.mvvm.ui.details.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ali Asadi on 12/03/2018.
 */

public class MainActivity extends BaseActivity<MainViewModel> implements MovieAdapter.MovieListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private MovieAdapter movieAdapter;

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        MoviesRepository movieRepository = DataManager.getInstance().getMovieRepository();
        MainViewModelFactory factory = new MainViewModelFactory(movieRepository);
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getShowLoadingLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getHideLoadingLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                progressBar.setVisibility(View.GONE);
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
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            }
        });
    }

    @OnClick(R.id.load)
    void onLoadMoviesButtonClick() {
        viewModel.loadMovies();
    }

    @OnClick(R.id.empty)
    void onEmptyButtonClick() {
        viewModel.onEmptyClicked();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        viewModel.onMovieClicked(movie);
    }
}
