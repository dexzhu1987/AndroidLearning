package com.bignerdranch.android.movieview;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class MovieActivity extends SingleFragmentActivity implements MovieListFragment.Callbacks{
    private static final String MOVIEID = "movieid";

    public static Intent newIntent(Context context, UUID movieId){
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(MOVIEID,movieId);
        return intent;
    }

    @Override
    protected int getLayoutResiD(){
        return R.layout.activity_masterdetail2;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(MOVIEID);
        return MovieFragment.newIntent(crimeId);
    }

    private void updateContent(){
        if (findViewById(R.id.detailed_fragment_container) !=null){
            Fragment newDetail = new MovieListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailed_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onMovieSelected(Movie movie) {
        if (findViewById(R.id.detailed_fragment_container) == null){
            Intent intent = MovieActivity.newIntent(this,movie.getUUID());
            startActivity(intent);
        } else {
            Fragment newDetail = MovieFragment.newIntent(movie.getUUID());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_contaner, newDetail)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateContent();
    }
}
