package com.ps.movieshelf.network;

import android.content.Context;

/**
 * Created by Dell on 12/6/2017.
 */

public interface MovieDataAgent {
    void loadPopularMovies(int page, String accessToken, Context context);
}
