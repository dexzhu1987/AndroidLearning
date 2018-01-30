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
        for (int i=0; i<movies.size(); i++){
            System.out.println(movies.get(i));
        }
        mAdapter = new MovieAdapter(movies);
        mMovieRececylerView.setAdapter(mAdapter);
    }

    private class MovieHolder extends RecyclerView.ViewHolder{
        private ImageButton mMovieImageButton;
        private ImageButton mMovieImageButton2;
        private ImageButton mMovieImageButton3;
        private  ArrayList<Movie>  mMovies;

        public MovieHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_movie, parent, false));
            mMovieImageButton = itemView.findViewById(R.id.movieImageButton);
            mMovieImageButton2 = itemView.findViewById(R.id.movieImageButton2);
            mMovieImageButton3 = itemView.findViewById(R.id.movieImageButton3);
        }


        public void bind(ArrayList<Movie> movies){
            mMovies = movies;
            mMovieImageButton.setImageResource(movies.get(0).getImage());
            startMovie(mMovieImageButton, movies.get(0));
            mMovieImageButton2.setImageResource(movies.get(1).getImage());
            startMovie(mMovieImageButton2, movies.get(1));
            mMovieImageButton3.setImageResource(movies.get(2).getImage());
            startMovie(mMovieImageButton3, movies.get(2));
        }

        public void startMovie(ImageButton button, final Movie movie) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = MovieActivity.newIntent(MovieListActivity.this,movie.getUUID());
                    startActivity(intent);
                }
            });
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
            position = position*3;
            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(mAllMovies.get(position));
            movies.add(mAllMovies.get(position+1));
            movies.add(mAllMovies.get(position+2));
            holder.bind(movies);
        }

        @Override
        public int getItemCount() {
            return mAllMovies.size()/3;
        }

    }
}
