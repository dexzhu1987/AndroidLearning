package com.bignerdranch.android.scannerexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Scanner;

public class ScannerExercise extends AppCompatActivity {
    private static final String ADDEDFIRSTNAME ="addedfirstname";
    private static final String ADDEDLASTNAME ="addedlastname";

    private String mAddedFirstName;
    private String mAddedLastName;

    private TextView mQustionView;
    private Button mOption1;
    private Button mOption2;
    private Button mOption3;
    private Button mOption4;
    public static int mAnswer;
    private Question mQuestion;
    private Button mBack;


    public static Intent newIntent(Context context, String firstname, String lastname ){
        Intent intent = new Intent(context, ScannerExercise.class);
        intent.putExtra(ADDEDFIRSTNAME,firstname);
        intent.putExtra(ADDEDLASTNAME,lastname);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_exercise);

        newQuestion();

    }

    private void newQuestion() {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.names));
        HashMap<String,String> names = new HashMap<>();

        while (scanner.hasNext()){
            names.put(scanner.next(),scanner.next());
        }

        try {
            Scanner scanner1 = new Scanner(openFileInput("out.txt"));
            while (scanner1.hasNext()){
                names.put(scanner1.next(),scanner1.next());
            }
        } catch (Exception e){

        }




        mAddedFirstName = getIntent().getStringExtra(ADDEDFIRSTNAME);
        mAddedLastName = getIntent().getStringExtra(ADDEDLASTNAME);

        Toast.makeText(ScannerExercise.this, mAddedFirstName +", " +mAddedLastName + " has been added.",
                Toast.LENGTH_SHORT );


        mQuestion = new Question(names);

        mQustionView= findViewById(R.id.firstname);
        mQustionView.setText(mQuestion.getQuestion());


        mOption1 = findViewById(R.id.options1);
        mOption1.setText(mQuestion.getOption1());
        mOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

        mOption2 = findViewById(R.id.options2);
        mOption2.setText(mQuestion.getOption2());
        mOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        mOption3 = findViewById(R.id.options3);
        mOption3.setText(mQuestion.getOption3());
        mOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        mOption4 = findViewById(R.id.options4);
        mOption4.setText(mQuestion.getOption4());
        mOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });

        mAnswer = mQuestion.getAnswers();


        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerExercise.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void checkAnswer(int answer){
        if (answer==mAnswer){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
        }

        newQuestion();
    }


}
