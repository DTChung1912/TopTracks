package com.example.toptracks.Fragment.purchasestracks;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.BasePresenter;

import java.util.ArrayList;

public class FragmentPurchasesPresenter extends BasePresenter<PurchasesIterator.PurchasesTrackView>
        implements PurchasesIterator.PurchasesTrackPresenter  {



    @Override
    public void fetchPurchasesTracks() {
        ArrayList<Music> musicList = new ArrayList<>();
        if (musicList.isEmpty()) {
            getMvpView().onFailed("Ko có data");
            return;
        }
        getMvpView().onFetchSuccess(musicList);
    }
}
