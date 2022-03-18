package com.example.toptracks.View;

import com.example.toptracks.base.BasePresenter;

public class MainActivityPresenter extends BasePresenter<MainActivityIterator.MainView>
        implements MainActivityIterator.MainPresenter {

    @Override
    public void fetchMain() {
        getMvpView().onFetchSuccess();
    }
}
