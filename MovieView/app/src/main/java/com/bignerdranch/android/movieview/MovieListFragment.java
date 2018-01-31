package com.bignerdranch.android.movieview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.security.auth.callback.Callback;

/**
 * Created by dexunzhu on 2018-01-30.
 */

public class MovieListFragment extends Fragment {
    private RecyclerView mMovieRececylerView;
    private MovieAdapter mAdapter;
    private Callbacks mCallbacks;

    private static final String MOVIEID = "movieid";
    private Movie mMovie;

    public interface Callbacks{
        void onMovieSelected(Movie movie);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }


    public static MovieListFragment newIntent(UUID movieId){
        Bundle args = new Bundle();
        args.putSerializable(MOVIEID, movieId);

        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            super.onCreate(savedInstanceState);
            UUID movieID = (UUID) getArguments().getSerializable(MOVIEID);
            mMovie = MovieLibrary.get(getActivity()).getMovie(movieID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.activity_movie_list, container, false);

        mMovieRececylerView = (RecyclerView) view.findViewById(R.id.movie_recycler_view);
        mMovieRececylerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public void updateUI() {
        MovieLibrary movieLibrary = MovieLibrary.get(getActivity());
        List<Movie> movies = movieLibrary.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        mAdapter = new MovieAdapter(movies);
        mMovieRececylerView.setAdapter(mAdapter);
    }

    private class MovieHolder extends RecyclerView.ViewHolder {
        private ImageButton mMovieImageButton;
        private ImageButton mMovieImageButton2;
        private ImageButton mMovieImageButton3;
        private ArrayList<Movie> mMovies;

        public MovieHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_movie, parent, false));
            mMovieImageButton = itemView.findViewById(R.id.movieImageButton);
            mMovieImageButton2 = itemView.findViewById(R.id.movieImageButton2);
            mMovieImageButton3 = itemView.findViewById(R.id.movieImageButton3);
        }


        public void bind(ArrayList<Movie> movies) {
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
                    updateUI();
                    mCallbacks.onMovieSelected(movie);
                }
            });
        }


    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
        private List<Movie> mAllMovies;

        public MovieAdapter(List<Movie> movies) {
            mAllMovies = movies;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MovieHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            position = position * 3;
            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(mAllMovies.get(position));
            movies.add(mAllMovies.get(position + 1));
            movies.add(mAllMovies.get(position + 2));
            holder.bind(movies);
        }

        @Override
        public int getItemCount() {
            return mAllMovies.size() / 3;
        }


    }
}
