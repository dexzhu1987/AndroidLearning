package com.bignerdranch.android.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private AlertDialog mAlertDialog;
   private ListView mListView;
   private ArrayAdapter<String> mArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = findViewById(R.id.to_do_list);

        updateUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDo();
            }
        });
    }

    private void updateUI() {
        ToDoList toDoList = ToDoList.get(this);
        final List<ToDo> ToDoList = toDoList.getToDos();
        ArrayList<String> mToDoList = new ArrayList<>();
        for (ToDo todo: ToDoList){
            mToDoList.add(todo.getTitle());
        }



        mArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mToDoList);
        mListView.setAdapter(mArrayAdapter);

        if (toDoList.get(MainActivity.this)!=null){
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = ToDoActivity.newIntent(MainActivity.this, ToDoList.get(position).getUUID());
                    startActivity(intent);
                }
            });
        }

    }



    private void addToDo(){
        final EditText input = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
        );
        input.setLayoutParams(params);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to Do");
        builder.setMessage("What do you wanna do?");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String todoString = input.getText().toString();
                ToDo toDo = new ToDo();
                toDo.setTitle(todoString);
                toDo.setContent("");
                ToDoList.get(MainActivity.this).addToDo(toDo);
                updateUI();
                Snackbar.make(findViewById(R.id.fab), todoString + " is added", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        mAlertDialog = builder.create();
        mAlertDialog.setView(input);
        mAlertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
}
