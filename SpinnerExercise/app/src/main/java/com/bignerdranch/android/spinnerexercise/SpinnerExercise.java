package com.bignerdranch.android.spinnerexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SpinnerExercise extends AppCompatActivity {
    private Spinner mSpinner;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_exercise);

//        mSpinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, R.layout.support_simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        mSpinner.setAdapter(adapter);
//
//        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                int position = mSpinner.getSelectedItemPosition();
//                mImageView = (ImageView) findViewById(R.id.imageView);
//                switch (position){
//                    case 0:
//                        mImageView.setImageResource(R.drawable.pikachu);
//                        break;
//                    case 1:
//                        mImageView.setImageResource(R.drawable.charmander);
//                        break;
//                    case 2:
//                        mImageView.setImageResource(R.drawable.squirtle);
//                        break;
//                    case 3:
//                        mImageView.setImageResource(R.drawable.bulbasaur);
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });





    }

    public void radioClick(View view) {
        mImageView = (ImageView) findViewById(R.id.imageView);
        if (view.getId()==R.id.Pikachu){
            mImageView.setImageResource(R.drawable.pikachu);
        } else if (view.getId()==R.id.Charmander) {
            mImageView.setImageResource(R.drawable.charmander);
        } else if (view.getId()==R.id.Squirtle){
            mImageView.setImageResource(R.drawable.squirtle);
        } else if (view.getId()==R.id.Bulbasaur){
            mImageView.setImageResource(R.drawable.bulbasaur);
        }

    }
}
