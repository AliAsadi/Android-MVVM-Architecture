package com.aliasadi.mvvm.ui.main;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.aliasadi.mvvm.R;
import com.aliasadi.mvvm.data.network.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Asadi on 24/03/2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public interface MovieListener {
        void onMovieClicked(Movie movie);
    }

    private List<Movie> items;
    private MovieListener listener;

    public MovieAdapter(MovieListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<Movie> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Movie getItem(int position) {
        return items.get(position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image) AppCompatImageView image;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.desc) TextView desc;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            Movie movie = getItem(position);

            setClickListener(movie);
            setTitle(movie.getTitle());
            setImage(movie.getImage());
            setDescription(movie.getDescription());
        }

        private void setTitle(String title) {
            this.title.setText(title);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).into(image);
        }

        private void setDescription(String description) {
            desc.setText(description);
        }

        private void setClickListener(Movie movie) {
            itemView.setTag(movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onMovieClicked((Movie) view.getTag());
        }
    }
}