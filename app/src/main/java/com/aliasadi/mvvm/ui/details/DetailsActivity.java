package com.aliasadi.mvvm.ui.details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import com.aliasadi.mvvm.ui.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.aliasadi.mvvm.R;
import com.aliasadi.mvvm.data.network.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<DetailsViewModel> {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @BindView(R.id.image) AppCompatImageView image;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.desc) TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        viewModel.loadMovieData();
        viewModel.getMovie().observe(this, new MovieObserver());
    }

    @NonNull
    @Override
    protected DetailsViewModel createViewModel() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        DetailsViewModelFactory factory = new DetailsViewModelFactory(movie);
        return ViewModelProviders.of(this,factory).get(DetailsViewModel.class);
    }

    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(starter);
    }

    private class MovieObserver implements Observer<Movie> {
        @Override
        public void onChanged(@Nullable Movie movie) {
            if (movie == null) return;

            title.setText(movie.getTitle());
            desc.setText(movie.getDescription());
            Glide.with(getApplicationContext()).load(movie.getImage()).into(image);
        }
    }

}

