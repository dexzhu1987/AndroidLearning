package com.example.dexunzhu.rightcolor;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dexunzhu on 2018-01-10.
 */

public class Question {
    private List<String> questions;
    private List<Integer> rightoptions;
    private List<Integer> leftoptions;
    private List<String> answers;
    private List<String> answersInNums;
    private int number;

    public Question() {
        questions = new ArrayList<>();
        questions.add("Select the told color");
        questions.add("Select the brighter color");
        questions.add("Select the opposite color");
        questions.add("Select the darker color");
        questions.add("Selcet the left color");
        questions.add("Select the right color");
        questions.add("Select the primary color");
        questions.add("Select the secondary color");
        questions.add("Select the rainbow color");
        questions.add("Select the rainbow source");


        rightoptions = new ArrayList<>();
        rightoptions.add(Color.rgb(75, 0, 130)); //indigo
        rightoptions.add(Color.LTGRAY);
        rightoptions.add(Color.rgb(128,255,0)); //lightgreen
        rightoptions.add(Color.YELLOW);
        rightoptions.add(Color.RED);
        rightoptions.add(Color.rgb(255,128,0)); //Orange
        rightoptions.add(Color.GREEN);
        rightoptions.add(Color.rgb(204,0,204)); //Purple
        rightoptions.add(Color.rgb(255,204,255)); //Pink
        rightoptions.add(Color.WHITE);

        leftoptions = new ArrayList<>();
        leftoptions.add(Color.MAGENTA);
        leftoptions.add(Color.DKGRAY);
        leftoptions.add(Color.CYAN);
        leftoptions.add(Color.rgb(153,153,0)); //darkyellow
        leftoptions.add(Color.BLUE);
        leftoptions.add(Color.rgb(204,255,255)); //lightblue
        leftoptions.add(Color.BLUE);
        leftoptions.add(Color.RED);
        leftoptions.add(Color.rgb(238,130,238)); //Violet
        leftoptions.add(Color.BLACK);
      


        answers = new ArrayList<>();
        answers.add("Magenta");
        answers.add("Light Gray");
        answers.add("Cyan");
        answers.add("Dark Yellow");
        answers.add("Blue");
        answers.add("Orange");
        answers.add("Blue");
        answers.add("Purple");
        answers.add("Violet");
        answers.add("White");

        answersInNums = new ArrayList<>();
        answersInNums.add("Left");
        answersInNums.add("Right");
        answersInNums.add("Right");
        answersInNums.add("Left");
        answersInNums.add("Left");
        answersInNums.add("Right");
        answersInNums.add("Left");
        answersInNums.add("Right");
        answersInNums.add("Left");
        answersInNums.add("Right");

        Random random = new Random();
        number = random.nextInt(10);

    }

    public String getQuestion (){
        return questions.get(number);
    }

    public int getRightOption(){
        return rightoptions.get(number);
    }

    public int getLeftOption(){
        return leftoptions.get(number);
    }

    public String getAnswer(){
        return answers.get(number);
    }

    public String getAnswerInNum(){
        return answersInNums.get(number);
    }





}
