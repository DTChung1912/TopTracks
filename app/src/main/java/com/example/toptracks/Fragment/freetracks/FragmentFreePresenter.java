package com.example.toptracks.Fragment.freetracks;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.BasePresenter;

import java.util.ArrayList;

public class FragmentFreePresenter extends BasePresenter<FreeIterator.FreeTrackView>
        implements FreeIterator.FreeTrackPresenter {

    @Override
    public void fetchFreeTracks() {
        ArrayList<Music> musicList = new ArrayList<>();
        getMvpView().onFetchSuccess(musicList);
    }
}
