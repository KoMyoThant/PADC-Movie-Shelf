package com.ps.movieshelf.dagger;

import android.content.Context;

import com.ps.movieshelf.MovieShelfApp;
import com.ps.movieshelf.data.models.MovieModel;
import com.ps.movieshelf.mvp.presenters.MovieListPresenter;
import com.ps.movieshelf.network.MovieDataAgent;
import com.ps.movieshelf.network.MovieDataAgentImpl;
import com.ps.movieshelf.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pyaesone on 1/14/18.
 */

@Module
public class MovieShelfAppModule {

    private MovieShelfApp mApp;

    public MovieShelfAppModule(MovieShelfApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public MovieDataAgent provideMovieShelfDataAgent() {
        return new MovieDataAgentImpl();
    }

    @Provides
    @Singleton
    public MovieModel provideMovieModel(Context context) {
        return new MovieModel(context);
    }


    @Provides
    public MovieListPresenter provideMovieListPresenter(){
        return new MovieListPresenter();
    }
}
