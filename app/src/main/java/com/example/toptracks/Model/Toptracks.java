
package com.example.toptracks.Model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Toptracks {

    @SerializedName("track")
    @Expose
    private List<Track> track = null;
    @SerializedName("@attr")
    @Expose
    private Attr__1 attr;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    public Attr__1 getAttr() {
        return attr;
    }

    public void setAttr(Attr__1 attr) {
        this.attr = attr;
    }

}
