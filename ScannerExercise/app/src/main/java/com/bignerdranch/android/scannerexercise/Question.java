package com.bignerdranch.android.scannerexercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Created by dexunzhu on 2018-01-17.
 */

public class Question {
    private HashMap<String, String> mNames;
    private String mQuestion;
    private String mOption1;
    private String mOption2;
    private String mOption3;
    private String mOption4;
    private int mAnswers;

    public Question (HashMap<String, String> names){
            mNames = names;
            genrated();
    }

    private void genrated() {
        Set<String> firstnamesSet = mNames.keySet();
        ArrayList<String> firstnames = new ArrayList<>(firstnamesSet);

        Collection<String> lastnamesCollection = mNames.values();
        ArrayList<String> lastnames = new ArrayList<>(lastnamesCollection);

        Random random = new Random();
        int number = random.nextInt(firstnames.size());
        String pickedFirstname = firstnames.get(number);
        String pickedLastname = lastnames.get(number);
        lastnames.remove(number);


        mQuestion = pickedFirstname;

        mAnswers = random.nextInt(4);

        ArrayList<Integer> indexs = new ArrayList<>();
        for (int i=0; i<4; i++){
            indexs.add(i);
        }
        indexs.remove(mAnswers);

        setOption(mAnswers,pickedLastname);

        for (int index: indexs){
            int num = random.nextInt(lastnames.size());
            String option = lastnames.get(num);
            setOption(index, option);
            lastnames.remove(num);
        }




    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getOption1() {
        return mOption1;
    }

    public String getOption2() {
        return mOption2;
    }

    public String getOption3() {
        return mOption3;
    }

    public String getOption4() {
        return mOption4;
    }

    public int getAnswers() {
        return mAnswers;
    }

    private void setOption (int index, String option){
        switch (index){
            case 0:
                mOption1 = option;
                break;
            case 1:
                mOption2 = option;
                break;
            case 2:
                mOption3 = option;
                break;
            case 3:
                mOption4 = option;
                break;
        }
    }
}
