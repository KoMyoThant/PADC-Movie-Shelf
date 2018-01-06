package com.ps.movieshelf.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.ps.movieshelf.MovieShelfApp;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.events.RestApiEvents;
import com.ps.movieshelf.network.MovieDataAgentImpl;
import com.ps.movieshelf.persistence.MovieShelfContract;
import com.ps.movieshelf.utils.AppConstants;
import com.ps.movieshelf.utils.ConfigUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 12/6/2017.
 */

public class MovieModel {
    private static MovieModel objectInstance;
//    private int moviePageIndex = 1;

    private List<MovieVO> mMovies;

    private MovieModel() {
        EventBus.getDefault().register(this);
        mMovies = new ArrayList<>();
    }

    public static MovieModel getInstance() {
        if (objectInstance == null) {
            objectInstance = new MovieModel();
        }
        return objectInstance;
    }

    public void startLoadingPopularMovies(Context context) {
        MovieDataAgentImpl.getObjectInstance().loadPopularMovies(ConfigUtils.getObjInstance().loadPageIndex(), AppConstants.ACCESS_TOKEN, context);
    }

    public void loadMoreMovies(Context context) {
        MovieDataAgentImpl.getObjectInstance().loadPopularMovies(ConfigUtils.getObjInstance().loadPageIndex(), AppConstants.ACCESS_TOKEN, context);
    }


    public void forceRefreshMovies(Context context) {
        mMovies = new ArrayList<>();
//        moviePageIndex = 1;
        ConfigUtils.getObjInstance().savePageIndex(1);
        startLoadingPopularMovies(context);
    }

    public List<MovieVO> getMovies() {
        return mMovies;
    }

    @Subscribe
    public void onMoviesDataLoaded(RestApiEvents.MoviesDataLoadedEvent event) {
        mMovies.addAll(event.getLoadedMovies());
//        moviePageIndex = event.getLoadedPageIndex() + 1;
        ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex());

        //TODO Logic to save the data in Persistence Layer
        ContentValues[] movieCVS = new ContentValues[event.getLoadedMovies().size()];
        for (int index = 0; index < movieCVS.length; index++) {
            movieCVS[index] = event.getLoadedMovies().get(index).parseToContentValues();
        }

        int insertedRowCount = event.getContext().getContentResolver().bulkInsert(MovieShelfContract.MovieEntry.CONTENT_URI, movieCVS);
        Log.d(MovieShelfApp.LOG_TAG, "Inserted row : " + insertedRowCount);
    }
}
