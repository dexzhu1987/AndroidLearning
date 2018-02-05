package com.bignerdranch.android.todolist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import com.bignerdranch.android.todolist.ToDo;
import com.bignerdranch.android.todolist.database.ToDoDbSchema.ToDoTable;
/**
 * Created by dexunzhu on 2018-02-01.
 */

public class ToDoCursorWrapper extends CursorWrapper {
    public ToDoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ToDo getToDo(){
        String uuidString = getString(getColumnIndex(ToDoTable.Cols.UUID));
        String content  = getString(getColumnIndex(ToDoTable.Cols.CONTENT));
        String title = getString(getColumnIndex(ToDoTable.Cols.TITLE));

        ToDo toDo = new ToDo(UUID.fromString(uuidString));
        toDo.setContent(content);
        toDo.setTitle(title);

        return toDo;
    }
}
