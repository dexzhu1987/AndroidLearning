package com.bignerdranch.android.smileyface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dexunzhu on 2018-02-08.
 */

public class Target extends View {

    public Target(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint red = new Paint();
        red.setARGB(255,255,0,0);
        red.setStyle(Paint.Style.FILL_AND_STROKE);

        Paint white = new Paint();
        white.setARGB(255,255,255,255);
        white.setStyle(Paint.Style.FILL_AND_STROKE);


        int w = canvas.getWidth();
        for (int i=0; i<5; i++){
            canvas.drawCircle(w/2,w/2,w/2-(i*50),i%2==1?red:white);
        }


    }
}
