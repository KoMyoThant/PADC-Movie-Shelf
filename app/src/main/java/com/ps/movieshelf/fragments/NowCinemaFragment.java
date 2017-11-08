package com.ps.movieshelf.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.movieshelf.R;
import com.ps.movieshelf.adapters.MoviesAdapter;

/**
 * Created by pyaesone on 11/8/17.
 */

public class NowCinemaFragment extends Fragment {
    private RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;

    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_now_cinema, container, false);

        findViewsByID();

        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        moviesAdapter = new MoviesAdapter(getContext());
        rvMovies.setAdapter(moviesAdapter);


        return view;
    }

    private void findViewsByID() {
        rvMovies = view.findViewById(R.id.rv_now_cinema_movies);
    }
}
