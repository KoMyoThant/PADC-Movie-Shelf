package com.ps.movieshelf.deligates;

import android.view.View;

import com.ps.movieshelf.data.vo.MovieVO;

/**
 * Created by Dell on 12/15/2017.
 */

public interface MovieItemDeligate {
    void onItemTap(MovieVO movieVO);

    void onImageTap();

    void onMovieOverviewTap();
}
