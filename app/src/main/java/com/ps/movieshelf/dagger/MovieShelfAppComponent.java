package com.ps.movieshelf.dagger;

import com.ps.movieshelf.MovieShelfApp;
import com.ps.movieshelf.data.models.MovieModel;
import com.ps.movieshelf.fragments.NowCinemaFragment;
import com.ps.movieshelf.mvp.presenters.MovieListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pyaesone on 1/14/18.
 */

@Singleton
@Component(modules = {MovieShelfAppModule.class,UtilModule.class})
public interface MovieShelfAppComponent {

    void inject(MovieShelfApp app);

    void inject(MovieModel movieModel);

    void inject(MovieListPresenter movieListPresenter);

    void inject(NowCinemaFragment nowCinemaFragment);
}
