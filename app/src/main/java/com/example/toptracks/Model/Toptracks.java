
package com.example.toptracks.Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Toptracks {

    @SerializedName("track")
    @Expose
    private ArrayList<Track> track = null;
    @SerializedName("@attr")
    @Expose
    private Attr__1 attr;

    public ArrayList<Track> getTrack() {
        return track;
    }

    public void setTrack(ArrayList<Track> track) {
        this.track = track;
    }

    public Attr__1 getAttr() {
        return attr;
    }

    public void setAttr(Attr__1 attr) {
        this.attr = attr;
    }

}
