package com.bignerdranch.android.todolist.database;

/**
 * Created by dexunzhu on 2018-02-01.
 */

public class ToDoDbSchema {
    public static final class ToDoTable{
        public static final String NAME = "todolist";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String CONTENT = "content";
            public static final String TITLE = "title";
        }
    }
}
