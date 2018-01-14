package com.ps.movieshelf.mvp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.ps.movieshelf.MovieShelfApp;
import com.ps.movieshelf.dagger.MovieShelfAppComponent;
import com.ps.movieshelf.data.models.MovieModel;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.deligates.MovieItemDeligate;
import com.ps.movieshelf.mvp.views.MovieListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pyaesone on 1/13/18.
 */

public class MovieListPresenter extends BasePresenter<MovieListView> implements MovieItemDeligate {

//    private MovieListView movieListView;

    @Inject
    MovieModel movieModel;

    public MovieListPresenter() {
    }

    @Override
    public void onItemTap(MovieVO movieVO) {
        mView.nevigateToMovieDetail(movieVO);
    }

    @Override
    public void onImageTap() {

    }

    @Override
    public void onMovieOverviewTap() {

    }

    @Override
    public void onCreate(MovieListView mView) {
        super.onCreate(mView);
        MovieShelfApp movieShelfApp = (MovieShelfApp) mView.getViewContext();
        movieShelfApp.getMovieShelfAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        List<MovieVO> movieList = movieModel.getMovies();
        if (!movieList.isEmpty()) {
            mView.displayMovieList(movieList);
        } else {
            mView.showLoading();
        }
    }

    @Override
    public void onStop() {

    }

    public void onMovieListEndReach(Context context) {
        movieModel.loadMoreMovies(context);

    }

    public void onForceRefreshMovies(Context context) {
        movieModel.forceRefreshMovies(context);
    }

    public void onMovieLoadedFinished(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<MovieVO> movieList = new ArrayList<>();
            do {
                MovieVO newsVO = MovieVO.parseFromCursor(data);
                movieList.add(newsVO);
            } while (data.moveToNext());

            mView.displayMovieList(movieList);
        }
    }
}
