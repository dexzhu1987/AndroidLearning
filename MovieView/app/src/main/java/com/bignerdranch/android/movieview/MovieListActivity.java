package com.bignerdranch.android.movieview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieListActivity extends AppCompatActivity {
    private RecyclerView mMovieRececylerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        mMovieRececylerView = findViewById(R.id.movie_recycler_view);
        mMovieRececylerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));

        updateUI();
    }


    private void updateUI() {
        MovieLibrary movieLibrary = MovieLibrary.get(MovieListActivity.this);
        List<Movie> movies = movieLibrary.getMovies();
        System.out.println(movies.size());
        mAdapter = new MovieAdapter(movies);
        mMovieRececylerView.setAdapter(mAdapter);
    }

    private class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageButton mMovieImageButton;
        private Movie mMovie;

        public MovieHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_movie, parent, false));
            itemView.setOnClickListener(this);
            mMovieImageButton = itemView.findViewById(R.id.movieImageButton);
        }


        public void bind(Movie movie){
            mMovie = movie;
            mMovieImageButton.setImageResource(movie.getImage());
        }

        public void onClick(View view) {
            Intent intent = MovieActivity.newIntent(MovieListActivity.this, mMovie.getUUID());
            startActivity(intent);
        }


    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
        private List<Movie> mAllMovies;

        public MovieAdapter(List<Movie> movies){
            mAllMovies = movies;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(MovieListActivity.this);
            return new MovieHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            Movie movie = mAllMovies.get(position);
            holder.bind(movie);
        }

        @Override
        public int getItemCount() {
            return mAllMovies.size();
        }

    }
}
