package com.bignerdranch.android.scannerexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.PrintStream;

public class AddNameActivity extends AppCompatActivity {
    EditText mFirstNameAdded;
    EditText mLastNameAdded;
    Button mButton;
    private String mFirstNameAdded1;
    private String mLastNameAdded2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        mFirstNameAdded = findViewById(R.id.first_name_added);
        mFirstNameAdded.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    File outDir = getExternalFilesDir("/Users/dexunzhu/AndroidStudioProjects/ScannerExercise/app/src/main/res/raw");
                    File outFile = new File(outDir, "out.txt");
                    PrintStream output = new PrintStream(outFile);
                    output.print(s.toString());
                    output.close();
                    mFirstNameAdded1 = s.toString();
                } catch (Exception e){

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLastNameAdded = findViewById(R.id.last_name_added);
        mLastNameAdded.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    File outDir = getExternalFilesDir("/Users/dexunzhu/AndroidStudioProjects/ScannerExercise/app/src/main/res/raw");
                    File outFile = new File(outDir, "out.txt");
                    PrintStream output = new PrintStream(outFile);
                    output.print(s.toString());
                    output.close();
                    mLastNameAdded2 = s.toString();
                } catch (Exception e){

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mButton = findViewById(R.id.submit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ScannerExercise.newIntent(AddNameActivity.this, mFirstNameAdded1,mLastNameAdded2 );
                startActivity(intent);
            }
        });
    }
}
