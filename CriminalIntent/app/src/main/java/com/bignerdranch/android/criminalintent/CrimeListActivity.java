package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by dexunzhu on 2018-01-16.
 */

public class CrimeListActivity extends SingleFramentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
