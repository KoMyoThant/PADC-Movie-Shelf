package com.ps.movieshelf.mvp.views;

import android.content.Context;

import com.ps.movieshelf.data.vo.MovieVO;

import java.util.List;

/**
 * Created by pyaesone on 1/7/18.
 */

public interface MovieListView {

    void displayMovieList(List<MovieVO> movieList);

    void showLoading();

    void refreshMovieList();

    void nevigateToMovieDetail(MovieVO movieVO);

    Context getViewContext();
}
