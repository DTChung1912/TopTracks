package com.example.toptracks.Service;

import com.example.toptracks.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicAPI {
    @GET("?method=artist.gettoptracks&artist=cher&format=json")
    Call<Example> getTrack(
                           @Query("api_key") String apikey ,
                           @Query("limit") int limit);
}
