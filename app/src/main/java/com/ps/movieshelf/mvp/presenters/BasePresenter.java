package com.ps.movieshelf.mvp.presenters;

/**
 * Created by pyaesone on 1/7/18.
 */

public abstract class BasePresenter<T> {

    protected T mView;

    public void onCreate(T mView) {
        this.mView = mView;
    }

    public void onCreateView(){}

    public abstract void onStart();

    public void onResume() {
    }

    ;

    public void onPause() {
    }

    ;

    public abstract void onStop();

    public void onDestroy() {
    }

    ;

}
