package com.example.toptracks.View.viewpager;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter implements ViewPagerIterator.ViewPagerView{
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    ViewPagerPresenter presenter;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
        this.titles = new ArrayList<>();

        presenter = new ViewPagerPresenter();
        presenter.attachView(this);
        presenter.fetchViewPager();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public void onFetchSuccess(ArrayList<Fragment> fragments, ArrayList<String> titles) {
        this.fragments.addAll(fragments);
        this.titles.addAll(titles);
    }

    @Override
    public void onFailed(String msg) {}

    @Override
    public void onError(String msg) {}

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}



