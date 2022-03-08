package com.example.toptracks.Service;

import com.example.toptracks.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicAPI {
    @GET("?method=artist.gettoptracks&artist=cher&format=json")
    Call<Example> getTrack(
                           @Query("api_key") String apikey);
//https://ws.audioscrobbler.com/2.0/&api_key=4bae3a6d607a824a4eb8dc9455402d76&format=json
}
