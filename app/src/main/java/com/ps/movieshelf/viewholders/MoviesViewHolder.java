package com.ps.movieshelf.viewholders;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ps.movieshelf.R;
import com.ps.movieshelf.data.vo.MovieVO;
import com.ps.movieshelf.deligates.MovieItemDeligate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pyaesone on 11/9/17.
 */

public class MoviesViewHolder extends BaseViewHolder<MovieVO> {

    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;

    @BindView(R.id.tv_rating_count)
    TextView tvRate;

    @BindView(R.id.iv_movie_cover)
    ImageView ivMovie;

    @BindView(R.id.rb_current_popularity)
    AppCompatRatingBar rbMovie;

    private MovieItemDeligate movieItemDeligate;

    private MovieVO mData;

    public MoviesViewHolder(View itemView, MovieItemDeligate movieItemDeligate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.movieItemDeligate = movieItemDeligate;
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(MovieVO movieVO) {
        this.mData = movieVO;
        tvMovieName.setText(mData.getTitle());
        tvRate.setText(mData.getVoteAverage() + "");
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_movie_viewholder_black_24dp)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load("https://image.tmdb.org/t/p/original" + mData.getPosterPath()).apply(requestOptions).into(ivMovie);
        Log.e("path", mData.getPosterPath());
        float popularity = mData.getPopularity() / 200;
        rbMovie.setRating(popularity);
    }

    @Override
    public void onClick(View view) {
        movieItemDeligate.onItemTap(mData);
    }
}
