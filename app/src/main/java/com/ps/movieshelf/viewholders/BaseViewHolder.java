package com.ps.movieshelf.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pyaesone on 12/22/17.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W mData);

}
