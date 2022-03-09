package com.example.toptracks.Fragment.purchasestracks;

import com.example.toptracks.Model.Music;
import com.example.toptracks.base.MVPView;
import java.util.ArrayList;

public interface PurchasesIterator {
    interface PurchasesTrackView extends MVPView {
        void onFetchSuccess(ArrayList<Music> purchasesTracks);
        void onFailed(String msg);
    }

    interface PurchasesTrackPresenter {
        void fetchPurchasesTracks();
    }
}
