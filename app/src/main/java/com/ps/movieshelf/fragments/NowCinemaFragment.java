package com.ps.movieshelf.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.movieshelf.MovieShelfApp;
import com.ps.movieshelf.R;
import com.ps.movieshelf.activities.MovieDetailsActivity;
import com.ps.movieshelf.adapters.MoviesAdapter;
import com.ps.movieshelf.components.EmptyViewPod;
import com.ps.movieshelf.components.SmartRecyclerView;
import com.ps.movieshelf.components.SmartVerticalScrollListener;
import com.ps.movieshelf.data.models.MovieModel;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.events.RestApiEvents;
import com.ps.movieshelf.mvp.presenters.MovieListPresenter;
import com.ps.movieshelf.mvp.views.MovieListView;
import com.ps.movieshelf.persistence.MovieShelfContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pyaesone on 11/8/17.
 */

public class NowCinemaFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, MovieListView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int MOVIE_LOADER_ID = 1001;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_now_cinema_movies)
    SmartRecyclerView rvNowOnCinema;

    private MoviesAdapter adapter;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod vpEmptyMovie;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    MovieListPresenter mPresenter;

    public NowCinemaFragment() {
        // Required empty public constructor
    }

    public static NowCinemaFragment newInstance(String param1, String param2) {
        NowCinemaFragment fragment = new NowCinemaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        MovieShelfApp movieShelfApp = (MovieShelfApp) getActivity().getApplicationContext();
        movieShelfApp.getMovieShelfAppComponent().inject(this);

        mPresenter.onCreate(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_now_cinema, container, false);
        ButterKnife.bind(this, view);

        mPresenter.onCreateView();

        rvNowOnCinema.setHasFixedSize(true);
        adapter = new MoviesAdapter(getContext(), mPresenter);
        rvNowOnCinema.setEmptyView(vpEmptyMovie);
        rvNowOnCinema.setAdapter(adapter);
        rvNowOnCinema.setLayoutManager(new LinearLayoutManager(container.getContext()));

        SmartVerticalScrollListener scrollListener = new SmartVerticalScrollListener(new SmartVerticalScrollListener.OnSmartVerticalScrollListener() {
            @Override
            public void onListEndReached() {
                mPresenter.onMovieListEndReach(getContext());
            }
        });

        rvNowOnCinema.addOnScrollListener(scrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onForceRefreshMovies(getContext());
            }
        });

        getActivity().getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mPresenter.onStart();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMovieDataLoaded(RestApiEvents.MoviesDataLoadedEvent event) {
//        adapter.appendNewData(event.getLoadedMovies());
//        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvNowOnCinema, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        MovieModel.getInstance(context).startLoadingPopularMovies(getContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                MovieShelfContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mPresenter.onMovieLoadedFinished(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayMovieList(List<MovieVO> movieList) {
        adapter.setNewData(movieList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshMovieList() {

    }

    @Override
    public void nevigateToMovieDetail(MovieVO movieVO) {
        Intent intent = MovieDetailsActivity.newIntent(getActivity().getApplicationContext());
        startActivity(intent);
    }

    @Override
    public Context getViewContext() {
        return getActivity().getApplicationContext();
    }
}
