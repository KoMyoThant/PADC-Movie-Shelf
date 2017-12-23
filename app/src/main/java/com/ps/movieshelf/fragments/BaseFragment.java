package com.ps.movieshelf.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import com.ps.movieshelf.deligates.MovieItemDeligate;

/**
 * Created by pyaesone on 12/22/17.
 */

public class BaseFragment extends Fragment implements MovieItemDeligate {
    @Override
    public void onItemTap(View view) {

    }

    @Override
    public void onImageTap() {

    }

    @Override
    public void onMovieOverviewTap() {

    }
}
