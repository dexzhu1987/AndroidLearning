package com.bignerdranch.android.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.todolist.database.ToDoDbSchema.ToDoTable;

/**
 * Created by dexunzhu on 2018-02-01.
 */

public class ToDoBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "toDoBase.db";

    public ToDoBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ToDoTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ToDoTable.Cols.UUID + ", " +
                ToDoTable.Cols.CONTENT + ", " +
                ToDoTable.Cols.TITLE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
