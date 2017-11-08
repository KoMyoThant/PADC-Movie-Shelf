package com.ps.movieshelf.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ps.movieshelf.fragments.MostPopularFragment;
import com.ps.movieshelf.fragments.NowCinemaFragment;
import com.ps.movieshelf.fragments.UpcomingFragment;

/**
 * Created by pyaesone on 11/8/17.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 3;
    private final String[] tabTitle = new String[]{"Now on Cinema", "Upcoming", "Most Popular"};

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NowCinemaFragment();
            case 1:
                return new UpcomingFragment();
            case 2:
                return new MostPopularFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }
}
