package com.example.dexunzhu.thebiggerthebetter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int num1;
    private int num2;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //when app loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshedButtons();
    }

    private void refreshedButtons() {
        //create Random numbers
        Random gen = new Random();
        num1 = gen.nextInt(50);
        while (true){
            num2 = gen.nextInt(50);
            if (num1!=num2) break;
        }

        //set text with the ran numbers;
        Button left = (Button) findViewById(R.id.left_button);
        left.setText(Integer.toString(num1));


        Button right = (Button) findViewById(R.id.right_button);
        right.setText(Integer.toString(num2));
    }

    public void leftButtonClicked(View view) {
        rightorleft(num1, num2);
    }

    public void rightButtonClicked(View view) {
        rightorleft(num2, num1);
    }

    private void rightorleft(int num1, int num2){
        if (num1>num2){
            points++;
            Toast.makeText(this,"right",Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(this,"wrong",Toast.LENGTH_SHORT).show();
        }

        TextView pointView = (TextView) findViewById(R.id.points);
        pointView.setText("Score: " + points);

        refreshedButtons();
    }


}
