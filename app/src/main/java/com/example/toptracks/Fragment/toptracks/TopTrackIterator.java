package com.example.toptracks.Fragment.toptracks;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.MVPView;

import java.util.ArrayList;

public interface TopTrackIterator {

    interface TopTrackView extends MVPView {
        void onFetchSuccess(ArrayList<Music> topTracks);
        void onProgessbar();
        void onSwipeRefesh();
        void onFailed(String msg);
    }

    interface TopTrackPresenter {
        void fetchTopTracks(boolean isSwipeRefesh);
        void addProgessBar();
        void addSwipeRefesh();
    }
}
