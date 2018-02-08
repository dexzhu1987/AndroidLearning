package com.bignerdranch.android.gomoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

public class MainActivity extends AppCompatActivity {
    private GridLayout mGridLayout;
    private ZoomControls mZoomControls;
    private ImageButton mPanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGridLayout = findViewById(R.id.main_grid);
        final int MARGIN = 5;

        int pWidth = mGridLayout.getWidth();
        int pHeight = mGridLayout.getHeight();
        int numOfCol = mGridLayout.getColumnCount();
        int numOfRow = mGridLayout.getRowCount();
        int w = pWidth/numOfCol;
        int h = pHeight/numOfRow;


        for (int i=0; i<numOfCol*numOfRow; i++){
            ImageButton button = new ImageButton(this);
            button.setLayoutParams(new ViewGroup.LayoutParams(w - 2*MARGIN, h - 2*MARGIN));
            
            mGridLayout.addView(button);
        }

//        for(int yPos=0; yPos<numOfRow; yPos++){
//            for(int xPos=0; xPos<numOfCol; xPos++){
//                ImageButton button = new ImageButton(this);
//                GridLayout.LayoutParams params =
//                        (GridLayout.LayoutParams)button.getLayoutParams();
//                params.width = w - 2*MARGIN;
//                params.height = h - 2*MARGIN;
//                params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
//                button.setLayoutParams(params);
//                mGridLayout.addView(button);
//            }
//        }

        mZoomControls = findViewById(R.id.main_zoom);
        mZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = mGridLayout.getScaleX();
                float y = mGridLayout.getScaleY();

                mGridLayout.setScaleX((int)(x+1));
                mGridLayout.setScaleY((int)(y+1));
            }
        });

        mZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = mGridLayout.getScaleX();
                float y = mGridLayout.getScaleY();

                mGridLayout.setScaleX((int)(x-1));
                mGridLayout.setScaleY((int)(y-1));
            }
        });

    }
}
