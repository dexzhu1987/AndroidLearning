package com.bignerdranch.android.todolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.UUID;

public class ToDoActivity extends AppCompatActivity {
    private static final String EXTRA_TODO_ID = "todoid";

    private ToDo mToDo;

    private EditText mTitleTextView;
    private EditText mContentView;
    private ImageButton mDeleteButton;


    public static Intent newIntent(Context context, UUID todoId){
        Intent intent =  new Intent(context, ToDoActivity.class);
        intent.putExtra(EXTRA_TODO_ID, todoId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        UUID toDoId = (UUID) getIntent().getSerializableExtra(EXTRA_TODO_ID);
        mToDo = ToDoList.get(this).getToDo(toDoId);

        mTitleTextView = findViewById(R.id.to_do_title);
        mTitleTextView.setText(mToDo.getTitle());
        mTitleTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mToDo.setTitle(s.toString());
                updateToDo();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mContentView = findViewById(R.id.to_do_content);
        if (mToDo.getContent().equalsIgnoreCase("")){
            mContentView.setHint("Input here for details");
        } else {
            mContentView.setText(mToDo.getContent());
        }
        mContentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mToDo.setContent(s.toString());
                updateToDo();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        mDeleteButton =findViewById(R.id.delete_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoList.get(ToDoActivity.this).deleteToDo(mToDo);
                finish();
            }
        });




        final View activityRootView = findViewById(R.id.to_do_activity);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();

                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 150) {
                  enableDeleteButton();
                }else{
                  disableDeleteButton();
                }
            }
        });
    }



    private void disableDeleteButton() {
        mDeleteButton.setVisibility(View.INVISIBLE);
        mDeleteButton.setEnabled(false);
    }

    private void enableDeleteButton(){
        mDeleteButton.setVisibility(View.VISIBLE);
        mDeleteButton.setEnabled(true);
    }

    private void updateToDo() {
        ToDoList.get(this).updateToDo(mToDo);
    }


}
