package com.ps.movieshelf.dagger;

import android.content.Context;

import com.ps.movieshelf.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pyaesone on 1/14/18.
 */

@Module
public class UtilModule {

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context){
        return new ConfigUtils(context);
    }
}
