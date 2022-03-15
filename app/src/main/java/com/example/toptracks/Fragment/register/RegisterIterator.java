package com.example.toptracks.Fragment.register;

import com.example.toptracks.base.MVPView;

public interface RegisterIterator {
    interface RegisterView extends MVPView {
        void onFetchSuccess();
        void onFailed(String msg);
    }

    interface RegisterPresenter {
        void fetchRegister();
    }
}
