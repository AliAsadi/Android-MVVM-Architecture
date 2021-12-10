package com.aliasadi.mvvm.ui.details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.databinding.ActivityDetailsBinding;
import com.aliasadi.mvvm.ui.base.BaseActivity;
import com.bumptech.glide.Glide;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.loadMovieData();
        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                binding.title.setText(movie.getTitle());
                binding.desc.setText(movie.getDescription());
                Glide.with(getApplicationContext()).load(movie.getImage()).into(binding.image);
            }
        });
    }

    @NonNull
    @Override
    protected DetailsViewModel createViewModel() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        DetailsViewModelFactory factory = new DetailsViewModelFactory(movie);
        return ViewModelProviders.of(this, factory).get(DetailsViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityDetailsBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityDetailsBinding.inflate(layoutInflater);
    }

    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(starter);
    }
}

