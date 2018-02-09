package com.bignerdranch.android.smileyface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dexunzhu on 2018-02-08.
 */

public class SmileyFace extends View {
    private Drawable mDrawable;


    public SmileyFace(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //face
        Paint yellow = new Paint();
        yellow.setARGB(255,255,255,0);
        yellow.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(100,100,100, yellow);

        //eyes
        Paint blue = new Paint();
        blue.setARGB(255,0,0,255);
        blue.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(60,70,15,blue);
        canvas.drawCircle(140,70,15,blue);

        //nose
        canvas.drawCircle(100,100,10,blue);

        //mouse
        Paint red = new Paint();
        red.setARGB(255,255,0,0);
        red.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(new Rect(60,140,140,160),red);

        Paint textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC));
        canvas.drawText("This is working!", 100,400,textPaint);

        mDrawable.draw(canvas);

    }
}
