package com.bignerdranch.android.photogallery;

import android.content.Context;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by dexunzhu on 2018-02-13.
 */

public class QueryPreferences {
    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String PREF_LAST_RESULT_ID = "lastResultID";
    private static final String PRE_IS_ALARM_ON ="isAlarmOn";

    public static boolean isAlarmOn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PRE_IS_ALARM_ON,false);
    }

    public static void setAlarmOn(Context context, Boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PRE_IS_ALARM_ON, isOn)
                .apply();
    }


    public static String getStoreQuery(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context context, String query){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }

    public static String getLastResultId(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setLastResultId(Context context, String query){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_LAST_RESULT_ID, query)
                .apply();
    }

}