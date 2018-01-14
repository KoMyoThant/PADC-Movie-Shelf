package com.ps.movieshelf.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.ps.movieshelf.MovieShelfApp;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.events.RestApiEvents;
import com.ps.movieshelf.network.MovieDataAgent;
import com.ps.movieshelf.network.MovieDataAgentImpl;
import com.ps.movieshelf.persistence.MovieShelfContract;
import com.ps.movieshelf.utils.AppConstants;
import com.ps.movieshelf.utils.ConfigUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dell on 12/6/2017.
 */

public class MovieModel {
    private static MovieModel objectInstance;
//    private int moviePageIndex = 1;

    private List<MovieVO> mMovies;

    @Inject
    MovieDataAgent mDataAgent;

    @Inject
    ConfigUtils mConfigUtil;

    public MovieModel(Context context) {
        EventBus.getDefault().register(this);
        mMovies = new ArrayList<>();

        MovieShelfApp movieShelfApp = (MovieShelfApp) context.getApplicationContext();
        movieShelfApp.getMovieShelfAppComponent().inject(this);

    }

//    public static MovieModel getInstance(Context context) {
//        if (objectInstance == null) {
//            objectInstance = new MovieModel(context);
//        }
//        return objectInstance;
//    }

    public void startLoadingPopularMovies(Context context) {
        mDataAgent.loadPopularMovies(mConfigUtil.loadPageIndex(), AppConstants.ACCESS_TOKEN, context);
    }

    public void loadMoreMovies(Context context) {
        mDataAgent.loadPopularMovies(mConfigUtil.loadPageIndex(), AppConstants.ACCESS_TOKEN, context);
    }


    public void forceRefreshMovies(Context context) {
        mMovies = new ArrayList<>();
//        moviePageIndex = 1;
        mConfigUtil.savePageIndex(1);
        startLoadingPopularMovies(context);
    }

    public List<MovieVO> getMovies() {
        return mMovies;
    }

    @Subscribe
    public void onMoviesDataLoaded(RestApiEvents.MoviesDataLoadedEvent event) {
        mMovies.addAll(event.getLoadedMovies());
//        moviePageIndex = event.getLoadedPageIndex() + 1;
        mConfigUtil.savePageIndex(event.getLoadedPageIndex());

        //TODO Logic to save the data in Persistence Layer
        ContentValues[] movieCVS = new ContentValues[event.getLoadedMovies().size()];
        for (int index = 0; index < movieCVS.length; index++) {
            movieCVS[index] = event.getLoadedMovies().get(index).parseToContentValues();
        }

        int insertedRowCount = event.getContext().getContentResolver().bulkInsert(MovieShelfContract.MovieEntry.CONTENT_URI, movieCVS);
        Log.d(MovieShelfApp.LOG_TAG, "Inserted row : " + insertedRowCount);
    }
}
