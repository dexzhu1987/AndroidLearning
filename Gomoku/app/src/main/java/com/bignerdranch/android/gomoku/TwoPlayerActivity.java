package com.bignerdranch.android.gomoku;

import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Dexter on 2018-02-09.
 */

public class TwoPlayerActivity extends MainActivity {

    @Override
    protected void botTurn() {
        tvTurn.setText("Player 2");
        mImageView.setImageDrawable(drawCell[2]);
        firstMove=false;
        isClicked=false;
    }

    @Override
    protected void make_a_move() {
        super.make_a_move();
        if (noEmptyCell()) {
            Toast.makeText(context,"Draw!!!", Toast.LENGTH_SHORT).show();
            return;
        } else if (checkWinner()) {
            if (winner_play==1) {
                tvTurn.setText("Winner is player");
                mAlertDialog.setMessage("Player \n\n(Press Play Game for a new turn)");
            } else {
                tvTurn.setText("Winner is player 2");
                mAlertDialog.setMessage("Player 2 \n\n(Press Play Game for a new turn)");
            }
            mAlertDialog.show();
            toggleCells(false);
            return;
        }
    }
}
