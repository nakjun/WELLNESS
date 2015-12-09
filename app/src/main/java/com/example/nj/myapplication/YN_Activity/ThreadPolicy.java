package com.example.nj.myapplication.YN_Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by NJ on 2015-11-30.
 */
public class ThreadPolicy {

    // For smooth networking
    //@TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public ThreadPolicy() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}