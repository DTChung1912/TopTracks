package com.example.toptracks.View;

import com.example.toptracks.base.MVPView;

public interface MainActivityIterator {
    interface MainView extends MVPView {
        void onFetchSuccess();
        void onFailed(String msg);
    }

    interface MainPresenter {
        void fetchMain();
    }
}
