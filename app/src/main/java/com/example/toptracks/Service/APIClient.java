package com.example.toptracks.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String url = "https://ws.audioscrobbler.com/2.0/";
    public static Retrofit retrofit = null;

    public static Retrofit getAPIClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
