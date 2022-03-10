package com.example.toptracks.Fragment.login;

import com.example.toptracks.Model.Token;
import com.example.toptracks.Service.TokenAPI;
import com.example.toptracks.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentLoginPresenter extends BasePresenter<LoginIterator.LoginView>
        implements LoginIterator.LoginPresenter {
    @Override
    public void fetchLogin() {

        String url = "https://ws.audioscrobbler.com/2.0/";
        String api_key ="4bae3a6d607a824a4eb8dc9455402d76";

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TokenAPI tokenAPI = retrofit.create(TokenAPI.class);
        Call<Token> tokenCall =  tokenAPI.getToken(api_key);
        tokenCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (!response.isSuccessful()) {
                    getMvpView().onFailed(response.message());
                    return;
                }
                Token myToken = response.body();
                String token = myToken.getToken();
                if (token.isEmpty() || myToken == null){
                    getMvpView().onFailed("Ko có data");
                    return;
                }
                getMvpView().onFetchSuccess();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                getMvpView().onFailed(t.getLocalizedMessage());
            }
        });
    }
}
