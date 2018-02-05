package com.bignerdranch.android.todolist;

import java.util.UUID;

/**
 * Created by dexunzhu on 2018-02-01.
 */

public class ToDo {
    private UUID mUUID;
    private String mTitle;
    private String mContent;

    public ToDo(){
        this(UUID.randomUUID());
    }

    public ToDo(UUID uuid){
        mUUID = uuid;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String content) {
        mTitle = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
