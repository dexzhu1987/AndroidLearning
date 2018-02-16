package com.bignerdranch.android.photogallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by dexunzhu on 2018-02-15.
 */

public class PhotoPageActiviy extends SingleFragmentActivity {

    public static Intent newIntent (Context context, Uri photoPageUri){
        Intent i = new Intent(context, PhotoPageActiviy.class);
        i.setData(photoPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return PhotoPageFragment.newInstance(getIntent().getData());
    }
}
