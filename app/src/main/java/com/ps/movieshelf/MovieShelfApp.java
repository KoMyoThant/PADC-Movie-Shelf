package com.ps.movieshelf;

import android.app.Application;

import com.ps.movieshelf.utils.ConfigUtils;

/**
 * Created by pyaesone on 11/8/17.
 */

public class MovieShelfApp extends Application {
    public final static String LOG_TAG = "MovieShelfApp";

    @Override
    public void onCreate() {
        super.onCreate();

        ConfigUtils.initConfigUtils(getApplicationContext());
    }
}
