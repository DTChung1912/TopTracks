package com.example.toptracks.View.viewpager;

import androidx.fragment.app.Fragment;

import com.example.toptracks.Fragment.freetracks.FragmentFree;
import com.example.toptracks.Fragment.purchasestracks.FragmentPurchases;
import com.example.toptracks.Fragment.toptracks.FragmentTopTracks;
import com.example.toptracks.Fragment.toptracks.TopTrackIterator;
import com.example.toptracks.base.BasePresenter;

import java.util.ArrayList;

public class ViewPagerPresenter extends BasePresenter<ViewPagerIterator.ViewPagerView>
        implements ViewPagerIterator.ViewPagerPresenter{

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private Fragment fragment;
    private String title;


    @Override
    public void fetchViewPager() {

        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        fragments.add(new FragmentTopTracks());
        fragments.add(new FragmentPurchases());
        fragments.add(new FragmentFree());

        titles.add("All");
        titles.add("Purchases");
        titles.add("Free");

        getMvpView().onFetchSuccess(fragments,titles);
    }

}
