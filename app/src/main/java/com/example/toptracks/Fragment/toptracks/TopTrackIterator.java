package com.example.toptracks.Fragment.toptracks;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.BasePresenter;
import com.example.toptracks.base.MVPView;

import java.util.ArrayList;

public interface TopTrackIterator {

    interface TopTrackView extends MVPView {
        void onFetchSuccess(ArrayList<Music> topTracks);
        void onFailed(String msg);
    }

    interface TopTrackPresenter {
        void fetchTopTracks();
    }
}
