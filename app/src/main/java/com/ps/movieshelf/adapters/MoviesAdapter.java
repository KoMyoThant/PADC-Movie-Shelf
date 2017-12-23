package com.ps.movieshelf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.movieshelf.R;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.deligates.MovieItemDeligate;
import com.ps.movieshelf.viewholders.MoviesViewHolder;

/**
 * Created by pyaesone on 11/9/17.
 */

public class MoviesAdapter extends BaseRecyclerAdapter<MoviesViewHolder, MovieVO> {
    private MovieItemDeligate movieItemDeligate;

    public MoviesAdapter(Context context, MovieItemDeligate movieItemDeligate) {
        super(context);
        this.movieItemDeligate = movieItemDeligate;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie, parent, false);
        return new MoviesViewHolder(view,movieItemDeligate);
    }
}
