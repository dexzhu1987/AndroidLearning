package com.bignerdranch.android.gomoku;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
    protected final static int MAXN=15;
    protected Context context;

    protected ImageView[][] ivCell = new ImageView[MAXN][MAXN];
    protected Drawable[] drawCell = new Drawable[4];

    protected Button btnPlay;
    protected TextView tvTurn;
    protected ImageView mImageView;
    
    protected int[][] valueCell =  new int [MAXN][MAXN];
    protected int winner_play;
    protected boolean firstMove;
    protected int xMove, yMove;
    protected int turnPlay;
    protected AlertDialog mAlertDialog;

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
        mImageView = findViewById(R.id.imgTurn);
        

        btnPlay.setText("Play Game");
        tvTurn.setText("Press button Play Game");
        mImageView.setImageDrawable(drawCell[0]);
        

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_game();
                play_game();
            }
        });
    }


    private void loadResouces() {
        drawCell[3] = context.getResources().getDrawable(R.drawable.cell_bg);
        drawCell[0] = null;
        drawCell[1] = context.getResources().getDrawable(R.drawable.circle); //player
        drawCell[2] = context.getResources().getDrawable(R.drawable.cross);
    }


    private void init_game() {
        firstMove = true;
        for (int i=0; i<MAXN; i++){
            for (int j=0; j<MAXN; j++){
                ivCell[i][j].setImageDrawable(drawCell[0]);
                valueCell[i][j]=0;
            }
        }
        mImageView.setImageDrawable(drawCell[0]);
    }
    
    protected void play_game() {
        Random r =  new Random();
        turnPlay = r.nextInt(2)+1;
        winner_play = 0;

        toggleCells(true);

        if (turnPlay==1){
            Toast.makeText(context, "Player play first", Toast.LENGTH_SHORT).show();
            playerTurn();
        }else {
            Toast.makeText(context, "Bot play first", Toast.LENGTH_SHORT).show();
            botTurn();
        }
    }

    private void playerTurn() {
        Log.i("Gomoku", "Player1 turn: " + turnPlay + " xMove: " + xMove + " yMove" + yMove );
        tvTurn.setText("Player");
        mImageView.setImageDrawable(drawCell[1]);
        firstMove=false;
        isClicked=false;
    }

    protected void botTurn() {
        Log.i("Gomoku", "Bot1 turn: " + turnPlay + " xMove: " + xMove + " yMove" + yMove );
        tvTurn.setText("Bot");
        mImageView.setImageDrawable(drawCell[2]);
        if (firstMove){
            firstMove=false;
            xMove=7; yMove=7;
            make_a_move();
        }else {
            findBotMove();
            make_a_move();
        }
    }

    private final int[] iRow={-1,-1,-1,0,1,1,1,0};
    private final int[] iCol={-1,0,1,1,1,0,-1,-1};

    private void findBotMove() {
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        final int RANGE =2 ;
        for (int i=0; i<MAXN; i++){
            for (int j=0; j<MAXN; j++)
                if (valueCell[i][j]!=0){
                    for (int t=1; t<=RANGE; t++){
                        for (int k=0; k<8; k++){
                            int x=i+iRow[k]*t;
                            int y=j+iCol[k]*t;
                            if (inBoard(x,y) && valueCell[x][y]==0) {
                                listX.add(x);
                                listY.add(y);
                            }
                        }
                    }
                }
        }
        int lx=listX.get(0);
        int ly=listY.get(0);

        int res = Integer.MAX_VALUE-10;
        for (int i=0;i<listX.size();i++){
            int x=listX.get(i);
            int y=listY.get(i);
            valueCell[x][y]=2;
            int rr=getValue_Position();
            if (rr<res){
                res=rr; lx=x; ly=y;
            }
            valueCell[x][y]=0;
        }
        xMove=lx; yMove=ly;
    }

    private int getValue_Position() {
        int rr=0;
        int p1=turnPlay;

        for (int i=0; i<MAXN; i++){
            rr+=CheckValue(MAXN-1,i,-1,0,p1);
        }
        for (int i=0; i<MAXN; i++){
            rr+=CheckValue(i,MAXN-1,0,-1,p1);
        }


        for (int i=MAXN-1; i>=0; i--) {
            rr+=CheckValue(i,MAXN-1,-1,-1,p1);
        }
        for (int i=MAXN-2; i>=0; i--) {
            rr+=CheckValue(MAXN-1,i,-1,-1,p1);
        }


        for (int i=MAXN-1; i>=0; i--) {
            rr+=CheckValue(i,0,-1,1,p1);
        }
        for (int i=MAXN-1; i>=1; i--) {
            rr+=CheckValue(MAXN-1,i,-1,1,p1);
        }
        return rr;
    }

    private int CheckValue(int xd, int yd, int vx, int vy, int p1) {
        int i,j;
        int rr=0;
        i=xd; j=yd;
        String st=String.valueOf(valueCell[i][j]);
        while (true){
            i+=vx; j+=vy;
            if(inBoard(i,j)){
                st=st+String.valueOf(valueCell[i][j]);
                if (st.length()==6){
                    rr+=Eval(st,p1);
                    st=st.substring(1,6);
                }
            } else break;
        }
        return rr;
    }

    protected void make_a_move() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Winner is: ");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mAlertDialog = builder.create();

        Log.i("Gomoku", "Make a move: " + turnPlay + " xMove: " + xMove + " yMove" + yMove );
        ivCell[xMove][yMove].setImageDrawable(drawCell[turnPlay]);
        valueCell[xMove][yMove] = turnPlay;
        if (noEmptyCell()) {
            Toast.makeText(context,"Draw!!!", Toast.LENGTH_SHORT).show();
            return;
        } else if (checkWinner()) {
            if (winner_play==1) {
                tvTurn.setText("Winner is player");
                mAlertDialog.setMessage("Player \n\n(Press Play Game for a new turn)");
            } else {
                tvTurn.setText("Winner is bot");
                mAlertDialog.setMessage("Bot \n\n(Press Play Game for a new turn)");
            }
            toggleCells(false);
            mAlertDialog.show();
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

    protected void toggleCells(boolean enabled) {
        for (ImageView[] imageViews : ivCell) {
            for (ImageView imageView : imageViews) {
                imageView.setEnabled(enabled);
            }
        }
    }

    protected boolean checkWinner() {
        if (winner_play!=0) return true;

        VectorEnd(xMove,0,0,1,xMove,yMove);
        VectorEnd(0,yMove,1,0,xMove,yMove);

        if (xMove+yMove>=MAXN-1){
            VectorEnd(MAXN-1,xMove+yMove-MAXN+1,-1,1,xMove,yMove);
        } else {
            VectorEnd(xMove+yMove,0,-1,1,xMove,yMove);
        }

        if(xMove<=yMove){
            VectorEnd(xMove-yMove+MAXN-1,MAXN-1,-1,-1,xMove,yMove);
        }else {
            VectorEnd(MAXN-1,MAXN-1-(xMove-yMove),-1,-1,xMove,yMove);
        }
        if (winner_play!=0) return true;
        return false;
    }

    private void VectorEnd(int xx, int yy, int vx, int vy, int rx, int ry) {
        if (winner_play!=0) return;
        final int RANGE=4;
        int i, j;
        int xbelow = rx-RANGE*vx;
        int ybelow = ry-RANGE*vy;
        int xabove = rx+RANGE*vx;
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

    protected boolean noEmptyCell() {
        for (int i=0; i<MAXN; i++){
            for (int j=0; j<MAXN; j++)
                if (valueCell[i][j]==0) return false;
        }
        return true;
    }


    protected boolean isClicked;

    protected void designBroadGame() {

        int sizeofCells = Math.round(ScreenWidth()/MAXN);
        LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(sizeofCells*MAXN, sizeofCells);
        LinearLayout.LayoutParams lpCell = new LinearLayout.LayoutParams(sizeofCells, sizeofCells);

        LinearLayout linBoardGame = findViewById(R.id.linBoordGame);

        for (int i=0; i<MAXN; i++){
            LinearLayout linRow =  new LinearLayout(this);
            for (int j=0; j<MAXN; j++){
                Log.i("Gomoku", "Before click: " + turnPlay + " xMove: " + xMove + " yMove" + yMove );
                ivCell[i][j] = new ImageView(this);
                ivCell[i][j].setBackground(drawCell[3]);
                final int x=i;
                final int y=j;
                ivCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Gomoku", "After click: " + turnPlay + " xMove: " + xMove + " yMove" + yMove );
                        if(valueCell[x][y] == 0) {
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
        if (p1 == 1){
            b1 = 2;
            b2 = 1;
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
