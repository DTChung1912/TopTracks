package com.example.toptracks.View;

import com.example.toptracks.Fragment.toptracks.TopTrackIterator;
import com.example.toptracks.View.viewpager.ViewPagerAdapter;
import com.example.toptracks.base.BasePresenter;

public class MainActivityPresenter extends BasePresenter<MainActivityIterator.MainView>
        implements MainActivityIterator.MainPresenter {
    String TAG = MainActivityPresenter.class.getSimpleName();
    @Override
    public void fetchMain() {
        getMvpView().onFetchSuccess(TAG);
    }
}
