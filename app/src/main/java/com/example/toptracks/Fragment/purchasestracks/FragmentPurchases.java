package com.example.toptracks.Fragment.purchasestracks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.toptracks.Adapter.MusicAdapter;
import com.example.toptracks.Model.Music;
import com.example.toptracks.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentPurchases extends Fragment implements PurchasesIterator.PurchasesTrackView {
    private RecyclerView recyclerView;
    private FragmentPurchasesPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchases, container, false);
        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter = new FragmentPurchasesPresenter();
        presenter.attachView(this);
        presenter.fetchPurchasesTracks();
        return view;
    }

    @Override
    public void onFetchSuccess(ArrayList<Music> purchasesTracks) {

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) {
    }
}