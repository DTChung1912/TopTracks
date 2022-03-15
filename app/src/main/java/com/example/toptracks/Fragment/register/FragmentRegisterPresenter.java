package com.example.toptracks.Fragment.register;

import android.content.SharedPreferences;

import com.example.toptracks.base.BasePresenter;

public class FragmentRegisterPresenter extends BasePresenter<RegisterIterator.RegisterView>
    implements RegisterIterator.RegisterPresenter{
    @Override
    public void fetchRegister() {
        getMvpView().onFetchSuccess();
    }
}
