package com.example.toptracks.Listener;

import com.example.toptracks.Model.Music;

import java.util.List;

public interface MusicInterface {
    void onFetchSuccess(List<Music> musicList);
    void onFetchFault(Exception e);
}
