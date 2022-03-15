package com.example.toptracks.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("toptracks")
    @Expose
    private Toptracks toptracks;

    public Toptracks getToptracks() {
        return toptracks;
    }

    public void setToptracks(Toptracks toptracks) {
        this.toptracks = toptracks;
    }

}
