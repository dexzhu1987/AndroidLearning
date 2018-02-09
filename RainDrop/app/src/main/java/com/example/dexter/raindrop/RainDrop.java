package com.example.dexter.raindrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dexter on 2018-02-08.
 */

public class RainDrop extends View {

    private static final float BALL_SIZE = 10;
    private float velocityY = 5;
    private int i;
    private List<Integer> ballXs = new ArrayList<>();
    private List<Integer> ballYs = new ArrayList<>();


    public RainDrop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rainFall(canvas);
    }


    private void rainFall(Canvas canvas){
        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        i++;
        //25*20=500 millis secs = half a second
        //create a new drop
        if (i%25==0){
            //create new drop and add to a list
            int width= canvas.getWidth();
            Random r = new Random();
            int x = r.nextInt(width);
            canvas.drawCircle(x,5,BALL_SIZE,bluePaint);
            ballXs.add(x);
            ballYs.add(5);
        }

        //drawing these drops from the list + velocity(moving);
        //if more than height, remove from the list
        for (int q=0; q<ballXs.size(); q++){
            canvas.drawCircle(ballXs.get(q), ballYs.get(q) + velocityY, BALL_SIZE, bluePaint);
            if (ballYs.get(q)+velocityY>canvas.getHeight()){
                ballXs.remove(q);
                ballYs.remove(q);
            } else {
                ballYs.set(q,(int)(ballYs.get(q) + velocityY));
            }
        }

    }


    public void doAnimation(){
        while (true){
            //pause
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            postInvalidate(); // redraw
      // redraw
        }
    }
}
