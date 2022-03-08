package com.example.toptracks.base;

public class BasePresenter<T extends MVPView> implements Presenter<T> {

    private T mvpView;

    @Override
    public void attachView(T view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    @Override
    public void unSubscribe() {

    }

    public T getMvpView() {
        return mvpView;
    }
}