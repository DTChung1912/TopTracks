package com.example.toptracks.Service;

import com.example.toptracks.Model.Example;
import com.example.toptracks.Model.Token;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TokenAPI {
    @GET("?method=auth.gettoken&format=json")
    Call<Token> getToken(
            @Query("api_key") String apikey);
}
