package com.bignerdranch.android.gomoku;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final static int MAXN=15;
    private Context context;

    private ImageView[][] ivCell = new ImageView[MAXN][MAXN];
    private Drawable[] drawCell = new Drawable[4];

    private Button btnPlay;
    private TextView tvTurn;

    private int[][] valueCell =  new int [MAXN][MAXN];
    private  int winner_play;
    private boolean firstMove;
    private int xMove, yMove;
    private int turnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        setListen();
        loadResouces();
        designBroadGame();
    }

    private void setListen() {
        btnPlay = findViewById(R.id.btnPlay);
        tvTurn = findViewById(R.id.tvTurn);

        btnPlay.setText("Play Game");
        tvTurn.setText("Press button Play Game");

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_game();
                play_game();
            }
        });
    }

    private void play_game() {
        Random r =  new Random();
        turnPlay = r.nextInt(2)+1;

        if (turnPlay==1){
            Toast.makeText(context, "Player play first", Toast.LENGTH_SHORT).show();
            playerTurn();
        }else {
            Toast.makeText(context, "Bot play first", Toast.LENGTH_SHORT).show();
            botTurn();
        }
    }

    private void botTurn() {
        tvTurn.setText("Bot");
        if (firstMove){
            firstMove=false;
            xMove=7; yMove=7;
            make_a_move();
        }else {
            findBotMove();
        }
    }

    private void findBotMove() {
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        final int RANGE =2 ;
        for (int i=0; i<MAXN; i++){
            for (int j=0; j<MAXN; j++)
                if (valueCell[i][j]!=0){
                    for (int t=1; t<=RANGE; t++){
                        for (int k=0; k<8; k++){
                            int x=1+iRow[k]*t;
                            int y=j+iCol[k]*t;
                        }
                    }
                }
        }
    }


    private void playerTurn() {
        tvTurn.setText("Player");
        isClicked = false;
    }

    private void make_a_move() {
        ivCell[xMove][yMove].setImageDrawable(drawCell[turnPlay]);
        valueCell[xMove][yMove] = turnPlay;
        if (noEmptyCell()) {
            Toast.makeText(context,"Draw!!!", Toast.LENGTH_SHORT).show();
            return;
        } else if (checkWinner()) {
            if (winner_play==1) {
                Toast.makeText(context, "Winner is player",Toast.LENGTH_SHORT).show();
                tvTurn.setText("Winner is player");
            } else {
                Toast.makeText(context,"Winner is bot", Toast.LENGTH_SHORT).show();
                tvTurn.setText("Winner is bot");
            }

                return;
        }
        
        if (turnPlay==1){
            turnPlay=3-turnPlay;
            botTurn();
        } else {
            turnPlay=3-turnPlay;
            playerTurn();
        }
    }

    private boolean checkWinner() {
        if (winner_play!=0) return true;
        VectorEnd(xMove,0,0,1,xMove,yMove);
        return false;
    }

    private void VectorEnd(int xx, int yy, int vx, int vy, int rx, int ry) {

        if (winner_play!=0) return;
        final int RANGE=4;
        int i, j;
        int xbelow = rx-RANGE*vx;
        int ybelow = ry-RANGE*vy;
        int xabove = ry+RANGE*vy;
        int yabove = ry+RANGE*vy;
        String st="";
        i=xx; j=yy;
        while (!inside(i,xbelow,xabove)||!inside(j,ybelow,yabove)){
            i+=vx; j+=vy;
        }
        while (true){
            st=st+String.valueOf(valueCell[i][j]);
            if (st.length()==5){
                EvalEnd(st);
                st=st.substring(1,5);
            }
            i+=vx; j+=vy;
            if(!inBoard(i,j) || !inside(i,xbelow,xabove)|| !inside(j,yabove,ybelow) || winner_play !=0){
                break;
            }
        }

    }

    private void EvalEnd(String st) {
        switch (st){
            case "11111": winner_play=1;break;
            case "22222": winner_play=2;break;
            default:break;
        }
    }

    private boolean inBoard(int i, int j) {
        if (i<0||i>MAXN-1||j<0||j>MAXN-1) return false;
        return true;
    }

    private boolean inside(int i, int xbelow, int xabove) {
        return (i-xbelow)*(i-xabove)<=0;
    }

    private boolean noEmptyCell() {
        for (int i=0; i<MAXN; i++){
            for (int j=0; j<MAXN; j++)
                if (valueCell[i][j]==0) return false;
        }
        return true;
    }



    private void init_game() {
        firstMove = true;
        for (int i=0; i<MAXN; i++){
            for (int j=0; j<MAXN; j++){
                ivCell[i][j].setImageDrawable(drawCell[0]);
                valueCell[i][j]=0;
            }
        }
    }

    private void loadResouces() {
        drawCell[3] = context.getResources().getDrawable(R.drawable.cell_bg);
        drawCell[0] = null;
        drawCell[1] = context.getResources().getDrawable(R.drawable.circle); //player
        drawCell[2] = context.getResources().getDrawable(R.drawable.cross);
    }

    private boolean isClicked;

    private void designBroadGame() {

        int sizeofCells = Math.round(ScreenWidth()/MAXN);
        LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(sizeofCells*MAXN, sizeofCells);
        LinearLayout.LayoutParams lpCell = new LinearLayout.LayoutParams(sizeofCells, sizeofCells);

        LinearLayout linBoardGame = findViewById(R.id.linBoordGame);

        for (int i=0; i<MAXN; i++){
            LinearLayout linRow =  new LinearLayout(this);
            for (int j=0; j<MAXN; j++){
                ivCell[i][j] = new ImageView(this);
                ivCell[i][j].setBackground(drawCell[3]);
                final int x=i;
                final int y=j;
                ivCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(valueCell[xMove][yMove] == 0) {
                            if (turnPlay==1 || !isClicked){
                                isClicked=true;
                                xMove=x; yMove=y;
                                make_a_move();
                            }
                        }
                    }
                });
                linRow.addView(ivCell[i][j],lpCell);
            }
            linBoardGame.addView(linRow,lpRow);
        }
    }

    private float ScreenWidth() {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }


    private int Eval(String st, int p1){
        int b1 = 1, b2 =1;
        if (p1==1){
            b1= 2;
            b2= 1;
        } else {
            b1 = 1;
            b2 = 2;
        }
        switch (st){
            case "111110": return b1* 100000000;
            case "011111": return b1* 100000000;
            case "211111": return b1* 100000000;
            case "111112": return b1* 100000000;
            case "011110": return b1* 10000000;
            case "101110": return b1* 1002;
            case "011101": return b1* 1002;
            case "011112": return b1* 1000;
            case "011100": return b1* 102;
            case "001110": return b1* 102;
            case "210111": return b1* 100;
            case "211110": return b1* 100;
            case "211011": return b1* 100;
            case "211101": return b1* 100;
            case "010100": return b1* 10;
            case "011000": return b1* 10;
            case "001100": return b1* 10;
            case "000110": return b1* 10;
            case "211000": return b1* 1;
            case "201100": return b1* 1;
            case "200110": return b1* 1;
            case "200011": return b1* 1;
            case "222220": return b2* -100000000;
            case "022222": return b2* -100000000;
            case "122222": return b2* -100000000;
            case "222221": return b2* -100000000;
            case "022220": return b2* -10000000;
            case "202220": return b2* -1002;
            case "022202": return b2* -1002;
            case "022221": return b2* -1000;
            case "022200": return b2* -102;
            case "002220": return b2* -102;
            case "120222": return b2* -100;
            case "122220": return b2* -100;
            case "122022": return b2* -100;
            case "122202": return b2* -100;
            case "020200": return b2* -10;
            case "022000": return b2* -10;
            case "002200": return b2* -10;
            case "000220": return b2* -10;
            case "122000": return b2* -1;
            case "102200": return b2* -1;
            case "100220": return b2* -1;
            case "100022": return b2* -1;
            default:
                break;
        }
        return 0;
    }
}
