package com.ps.movieshelf.mvp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.ps.movieshelf.data.models.MovieModel;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.deligates.MovieItemDeligate;
import com.ps.movieshelf.mvp.views.MovieListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyaesone on 1/13/18.
 */

public class MovieListPresenter extends BasePresenter implements MovieItemDeligate {

    private MovieListView movieListView;

    public MovieListPresenter(MovieListView view) {
        movieListView = view;
    }

    @Override
    public void onItemTap(MovieVO movieVO) {
        movieListView.nevigateToMovieDetail(movieVO);
    }

    @Override
    public void onImageTap() {

    }

    @Override
    public void onMovieOverviewTap() {

    }

    @Override
    public void onStart() {
        List<MovieVO> movieList = MovieModel.getInstance().getMovies();
        if (!movieList.isEmpty()) {
            movieListView.displayMovieList(movieList);
        } else {
            movieListView.showLoading();
        }
    }

    @Override
    public void onStop() {

    }

    public void onMovieListEndReach(Context context) {
        MovieModel.getInstance().loadMoreMovies(context);

    }

    public void onForceRefreshMovies(Context context) {
        MovieModel.getInstance().forceRefreshMovies(context);
    }

    public void onMovieLoadedFinished(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<MovieVO> movieList = new ArrayList<>();
            do {
                MovieVO newsVO = MovieVO.parseFromCursor(data);
                movieList.add(newsVO);
            } while (data.moveToNext());

            movieListView.displayMovieList(movieList);
        }
    }
}
