package com.example.toptracks.View;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.MVPView;

import java.util.ArrayList;

public interface MainActivityIterator {
    interface MainView extends MVPView {
        void onFetchSuccess(String msg);
        void onFailed(String msg);
    }

    interface MainPresenter {
        void fetchMain();
    }
}
