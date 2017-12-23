package com.ps.movieshelf.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ps.movieshelf.R;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.viewholders.MovieTrailerViewHolder;

/**
 * Created by pyaesone on 12/22/17.
 */

public class MovieTrailerAdapter extends BaseRecyclerAdapter<MovieTrailerViewHolder, MovieVO> {
    public MovieTrailerAdapter(Context context) {
        super(context);
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_movie_trailer, parent, false);
        return new MovieTrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
