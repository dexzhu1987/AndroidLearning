package com.bignerdranch.android.movieview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by dexunzhu on 2018-01-30.
 */

public class MovieFragment extends Fragment {
    private static final String MOVIEID = "movieid";
    private Movie mMovie;
    private ImageView mImageView;
    private TextView mTextView;

    public static MovieFragment newIntent(UUID movieId){
        Bundle args = new Bundle();
        args.putSerializable(MOVIEID, movieId);

        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID movieID = (UUID) getArguments().getSerializable(MOVIEID);
        mMovie = MovieLibrary.get(getActivity()).getMovie(movieID);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_movie, container, false);

        mImageView = v.findViewById(R.id.imageView_singlemovie);
        mImageView.setImageResource(mMovie.getImage());

        mTextView = v.findViewById(R.id.textview_singlemovie);
        mTextView.setText(mMovie.getDescription());

        return v;
    }



}
