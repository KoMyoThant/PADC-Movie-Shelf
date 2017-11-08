package com.ps.movieshelf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.movieshelf.R;
import com.ps.movieshelf.viewholders.MoviesViewHolder;

/**
 * Created by pyaesone on 11/9/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;

    public MoviesAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieItemView = mInflater.inflate(R.layout.view_item_movie, parent, false);

        return new MoviesViewHolder(movieItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
