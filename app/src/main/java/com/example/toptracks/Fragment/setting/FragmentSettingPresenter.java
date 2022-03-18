package com.example.toptracks.Fragment.setting;

import com.example.toptracks.base.BasePresenter;

public class FragmentSettingPresenter extends BasePresenter<SettingIterator.SettingView> implements SettingIterator.SettingPresenter {
    @Override
    public void fetchSetting() {
        getMvpView().onFetchSuccess();
    }
}
