package com.dev.sachin.sachinkumardemo.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.dev.sachin.sachinkumardemo.Activities.LoginActivity.PREF_FILE_NAME;

/**
 * Created by sachin on 27-01-2018.
 */

public class UserDetailsPrefs {
    public static SharedPreferences mPrefs=null;
    public static String PREF_FILE_NAME="userPref";


public static UserProfile getuserDetails(Context context){
    if (mPrefs==null) {
       mPrefs = context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
    }
        Gson gson1 = new Gson();
        String json1 = mPrefs.getString("userDetails", "");
        UserProfile obj = gson1.fromJson(json1, UserProfile.class);

        return obj;
    }

}

