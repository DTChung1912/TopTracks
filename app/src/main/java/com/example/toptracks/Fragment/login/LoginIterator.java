package com.example.toptracks.Fragment.login;


import android.content.Intent;

import com.example.toptracks.base.MVPView;

public interface LoginIterator {
    interface LoginView extends MVPView {
        void onFetchSuccess();
        void onFailed(String msg);
    }

    interface LoginPresenter {
        void fetchLogin();
    }
}
