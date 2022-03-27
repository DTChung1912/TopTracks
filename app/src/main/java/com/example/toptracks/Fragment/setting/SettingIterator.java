package com.example.toptracks.Fragment.setting;

import com.example.toptracks.base.MVPView;

public interface SettingIterator {
    interface SettingView extends MVPView {
        void onFetchSuccess();

        void onFailed(String msg);
    }

    interface SettingPresenter {
        void fetchSetting();
    }
}
