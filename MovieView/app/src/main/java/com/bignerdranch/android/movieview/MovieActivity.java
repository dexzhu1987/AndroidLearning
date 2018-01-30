package com.bignerdranch.android.movieview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class MovieActivity extends AppCompatActivity {
    private static final String MOVIEID = "movieid";

    private Movie mMovie;

    private ImageView mImageView;
    private TextView mTextView;


    public static Intent newIntent(Context context, UUID movieId){
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(MOVIEID,movieId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        UUID movieID = (UUID) getIntent().getSerializableExtra(MOVIEID);
        mMovie = MovieLibrary.get(MovieActivity.this).getMovie(movieID);

        mImageView = findViewById(R.id.imageView_singlemovie);
        mImageView.setImageResource(mMovie.getImage());

        mTextView = findViewById(R.id.textview_singlemovie);
        mTextView.setText(mMovie.getDescription());

    }



}
