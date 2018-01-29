package com.example.dexunzhu.rightcolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RightColor extends AppCompatActivity {
    private Question question;
    private int points;
    private String correctNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_color);
        points = 0;
        refreshed();
    }


    private void refreshed(){
        question = new Question();

        TextView questionview = (TextView) findViewById(R.id.question);
        questionview.setText(question.getQuestion());

        Button left = (Button)findViewById(R.id.left_button);
        left.setBackgroundColor(question.getLeftOption());

        Button right = (Button)findViewById(R.id.right_button);
        right.setBackgroundColor(question.getRightOption());

        TextView indicatorview = (TextView) findViewById(R.id.color_indicator);
        indicatorview.setText(question.getAnswer());

        correctNum = question.getAnswerInNum();
    }

    public void leftButtonClicked(View view){
        isCorrect("Left");
    }

    public void rightButtonClicked(View view){
        isCorrect("Right");
    }

    private void isCorrect(String leftRight){
        if (leftRight.equalsIgnoreCase(correctNum)){
            points++;
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        TextView pointView = (TextView) findViewById(R.id.point);
        pointView.setText("Score: " + points ) ;

        refreshed();
    }

}
