package com.example.toptracks.View.viewpager;

import androidx.fragment.app.Fragment;
import com.example.toptracks.base.MVPView;

import java.util.ArrayList;

public interface ViewPagerIterator {
    interface ViewPagerView extends MVPView {
        void onFetchSuccess(ArrayList<Fragment> fragments,ArrayList<String> titles);
        void onFailed(String msg);
    }

    interface ViewPagerPresenter {
        void fetchViewPager();
    }
}
