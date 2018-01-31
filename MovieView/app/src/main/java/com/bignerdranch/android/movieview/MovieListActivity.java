package com.bignerdranch.android.movieview;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class MovieListActivity extends SingleFragmentActivity implements MovieListFragment.Callbacks{
    @Override
    protected Fragment createFragment() {
        return new MovieListFragment();
    }

    @Override
    protected int getLayoutResiD(){
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onMovieSelected(Movie movie) {
        if (findViewById(R.id.detailed_fragment_container) == null){
            Intent intent = MovieActivity.newIntent(this,movie.getUUID());
            startActivity(intent);
        } else {
            Fragment newDetail = MovieFragment.newIntent(movie.getUUID());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailed_fragment_container, newDetail)
                    .commit();
        }
    }


}

