package com.example.toptracks.Model;

public class Music {
    private String songName;
    private String singerName;
    private String songRank;
    private String listener;
    private String musicImage;

    public Music(String songName, String singerName, String songRank, String listener, String musicImage) {
        this.songName = songName;
        this.singerName = singerName;
        this.songRank = songRank;
        this.listener = listener;
        this.musicImage = musicImage;
    }

    public Music() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongRank() {
        return songRank;
    }

    public void setSongRank(String songRank) {
        this.songRank = songRank;
    }

    public String getListener() {
        return listener;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }

    public String getMusicImage() {
        return musicImage;
    }

    public void setMusicImage(String musicImage) {
        this.musicImage = musicImage;
    }
}
