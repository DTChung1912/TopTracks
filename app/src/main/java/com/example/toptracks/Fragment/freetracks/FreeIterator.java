package com.example.toptracks.Fragment.freetracks;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.MVPView;
import java.util.ArrayList;

public interface FreeIterator {
    interface FreeTrackView extends MVPView {
        void onFetchSuccess(ArrayList<Music> freeTracks);
        void onFailed(String msg);
    }

    interface FreeTrackPresenter {
        void fetchFreeTracks();
    }
}
