package com.example.toptracks.View.viewpager;

import androidx.fragment.app.Fragment;

import com.example.toptracks.Fragment.freetracks.FragmentFree;
import com.example.toptracks.Fragment.purchasestracks.FragmentPurchases;
import com.example.toptracks.Fragment.toptracks.FragmentTopTracks;
import com.example.toptracks.base.BasePresenter;

import java.util.ArrayList;

public class ViewPagerPresenter extends BasePresenter<ViewPagerIterator.ViewPagerView>
        implements ViewPagerIterator.ViewPagerPresenter{

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    public void fetchViewPager() {

        fragments.add(new FragmentTopTracks());
        fragments.add(new FragmentPurchases());
        fragments.add(new FragmentFree());
        
        titles.add("TopTracks");
        titles.add("Purchases");
        titles.add("Free");

        getMvpView().onFetchSuccess(fragments,titles);
    }
}
