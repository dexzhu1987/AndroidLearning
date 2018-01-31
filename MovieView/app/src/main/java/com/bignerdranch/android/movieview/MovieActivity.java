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

public class MovieActivity extends SingleFragmentActivity {
    private static final String MOVIEID = "movieid";

    private Movie mMovie;


    public static Intent newIntent(Context context, UUID movieId){
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(MOVIEID,movieId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(MOVIEID);
        return MovieFragment.newIntent(crimeId);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        updateUI();
    }

    private void updateUI() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(MOVIEID);

        if (findViewById(R.id.detailed_fragment_container) != null){
            Fragment fragment = MovieListFragment.newIntent(crimeId);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailed_fragment_container, fragment)
                    .commit();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        updateUI();
    }


    @Override
    protected int getLayoutResiD(){
        return R.layout.activity_masterdetail2;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
}
