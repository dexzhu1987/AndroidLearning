package com.example.dexter.raindrop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RainDropActivity extends AppCompatActivity {
    private  Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain_drop);
    }

    public void startAnimation(View view) {
        final RainDrop rd =  findViewById(R.id.myAnimation);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                rd.doAnimation();
            }
        });{
            thread.start();
        }
        rd.postInvalidate();
    }

    public void stopAnimation(View view) {
    }
}
