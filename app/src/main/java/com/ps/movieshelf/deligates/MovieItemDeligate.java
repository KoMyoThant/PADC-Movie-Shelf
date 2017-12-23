package com.ps.movieshelf.deligates;

import android.view.View;

/**
 * Created by Dell on 12/15/2017.
 */

public interface MovieItemDeligate {
    void onItemTap(View view);

    void onImageTap();

    void onMovieOverviewTap();
}
