package com.bignerdranch.android.gomoku;

/**
 * Created by Dexter on 2018-02-09.
 */

public class TwoPlayerActivity extends MainActivity {

    @Override
    protected void botTurn() {
        tvTurn.setText("Player");
        firstMove=false;
        isClicked=false;
    }

    @Override
    protected void make_a_move() {
        super.make_a_move();
    }
}
