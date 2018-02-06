package com.bignerdranch.android.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.todolist.database.ToDoBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bignerdranch.android.todolist.database.ToDoCursorWrapper;
import com.bignerdranch.android.todolist.database.ToDoDbSchema.ToDoTable;

/**
 * Created by dexunzhu on 2018-02-01.
 */

public class ToDoList {
    private static ToDoList sToDoList;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public static ToDoList get(Context context){
        if (sToDoList == null){
            sToDoList =  new ToDoList(context);
        }
        return sToDoList;
    }

    private ToDoList(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ToDoBaseHelper(mContext).getWritableDatabase();
    }

    public void addToDo(ToDo toDo){
        ContentValues values = getContentValues(toDo);
        mDatabase.insert(ToDoTable.NAME, null, values);
    }

    public void deleteToDo(ToDo toDo){
        ContentValues values = getContentValues(toDo);
        UUID id = toDo.getUUID();
        mDatabase.delete(ToDoTable.NAME, ToDoTable.Cols.UUID + " =? ",
                new String[]{id.toString()});
    }

    public List<ToDo> getToDos() {
        List<ToDo> crimes = new ArrayList<>();

        ToDoCursorWrapper cursor = queryToDos(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getToDo());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public ToDo getToDo(UUID id){
        ToDoCursorWrapper cursor = queryToDos(ToDoTable.Cols.UUID + " =? ",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getToDo();
        } finally {
            cursor.close();
        }
    }


    public void updateToDo(ToDo toDo){
        String uuidString = toDo.getUUID().toString();
        ContentValues values = getContentValues(toDo);

        mDatabase.update(ToDoTable.NAME, values, ToDoTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(ToDo toDo){
        ContentValues values = new ContentValues();
        values.put(ToDoTable.Cols.UUID, toDo.getUUID().toString());
        values.put(ToDoTable.Cols.CONTENT,toDo.getContent());
        values.put(ToDoTable.Cols.TITLE, toDo.getTitle());

        return values;
    }

    private ToDoCursorWrapper queryToDos(String whereClasue, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ToDoTable.NAME,
                null,
                whereClasue,
                whereArgs,
                null,
                null,
                null
        );
        return new ToDoCursorWrapper(cursor);
    }

}
