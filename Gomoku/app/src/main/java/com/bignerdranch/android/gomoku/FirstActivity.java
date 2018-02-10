package com.bignerdranch.android.gomoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void startMain(View view) {
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void startTwoPlayer(View view) {
        Intent intent = new Intent(FirstActivity.this, TwoPlayerActivity.class);
        startActivity(intent);
    }
}
