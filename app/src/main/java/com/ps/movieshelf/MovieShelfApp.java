package com.ps.movieshelf;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.ps.movieshelf.dagger.DaggerMovieShelfAppComponent;
import com.ps.movieshelf.dagger.MovieShelfAppComponent;
import com.ps.movieshelf.dagger.MovieShelfAppModule;
import com.ps.movieshelf.utils.ConfigUtils;

import javax.inject.Inject;

/**
 * Created by pyaesone on 11/8/17.
 */

public class MovieShelfApp extends Application {
    public final static String LOG_TAG = "MovieShelfApp";

    private MovieShelfAppComponent mMovieShelfAppComponent;

    @Inject
    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mMovieShelfAppComponent = initDagger(this);

        mMovieShelfAppComponent.inject(this);   // register consumer

        Log.d(LOG_TAG,"mContext : " + mContext);
    }

    private MovieShelfAppComponent initDagger(MovieShelfApp app){
        return DaggerMovieShelfAppComponent.builder()
                .movieShelfAppModule(new MovieShelfAppModule(this))
                .build();
    }

    public MovieShelfAppComponent getMovieShelfAppComponent(){
        return mMovieShelfAppComponent;
    }
}
