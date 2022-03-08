package com.example.toptracks.base;

public interface Presenter<V extends MVPView> {
    void attachView(V view);
    void detachView();
    void unSubscribe();
}
