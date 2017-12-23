package com.ps.movieshelf.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.movieshelf.R;
import com.ps.movieshelf.activities.MovieDetailsActivity;
import com.ps.movieshelf.adapters.MoviesAdapter;
import com.ps.movieshelf.components.EmptyViewPod;
import com.ps.movieshelf.components.SmartRecyclerView;
import com.ps.movieshelf.components.SmartVerticalScrollListener;
import com.ps.movieshelf.data.models.MovieModel;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pyaesone on 11/8/17.
 */

public class UpcomingFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_upcoming_movies)
    SmartRecyclerView rvUpcoming;

    private MoviesAdapter adapter;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod vpEmptyMovie;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_upcoming, container, false);
        ButterKnife.bind(this, view);
        rvUpcoming.setHasFixedSize(true);
        adapter = new MoviesAdapter(getContext(), this);
        rvUpcoming.setEmptyView(vpEmptyMovie);
        rvUpcoming.setAdapter(adapter);
        rvUpcoming.setLayoutManager(new LinearLayoutManager(container.getContext()));

        SmartVerticalScrollListener scrollListener = new SmartVerticalScrollListener(new SmartVerticalScrollListener.OnSmartVerticalScrollListener() {
            @Override
            public void onListEndReached() {
                MovieModel.getInstance().loadMoreMovies(getContext());
            }
        });

        rvUpcoming.addOnScrollListener(scrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MovieModel.getInstance().forceRefreshMovies(getContext());
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        List<MovieVO> newsList = MovieModel.getInstance().getMovies();
        if (!newsList.isEmpty()) {
            adapter.setNewData(newsList);
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMovieDataLoaded(RestApiEvents.MoviesDataLoadedEvent event) {
/*        adapter.appendNewData(event.getLoadedMovies());
        swipeRefreshLayout.setRefreshing(false);*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvUpcoming, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
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
        //   MovieModel.getInstance().startLoadingPopularMovies(getContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemTap(View view) {
        super.onItemTap(view);

        Intent intent = MovieDetailsActivity.newIntent(getActivity().getApplicationContext());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                // the context of the activity
                getActivity(),
                new Pair<View, String>(view.findViewById(R.id.iv_movie),
                        getString(R.string.transition_name_movie_logo)),
                new Pair<View, String>(view.findViewById(R.id.tv_movie_name),
                        getString(R.string.transition_name_movie_name)),
                new Pair<View, String>(view.findViewById(R.id.rb_movie),
                        getString(R.string.transition_name_movie_rating_bar)),
                new Pair<View, String>(view.findViewById(R.id.tv_rate),
                        getString(R.string.transition_name_movie_rate_view)),
                new Pair<View, String>(view.findViewById(R.id.iv_barcode_scanner),
                        getString(R.string.transition_name_movie_view_logo))
        );
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}
