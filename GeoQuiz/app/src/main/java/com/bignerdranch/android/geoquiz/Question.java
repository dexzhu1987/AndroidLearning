package com.bignerdranch.android.geoquiz;

/**
 * Created by dexunzhu on 2018-01-11.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;


    public Question(int TextResId, boolean AnswerTrue) {
        this.mTextResId = TextResId;
        this.mAnswerTrue = AnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
