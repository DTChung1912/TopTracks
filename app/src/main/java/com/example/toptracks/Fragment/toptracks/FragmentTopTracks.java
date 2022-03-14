package com.example.toptracks.Fragment.toptracks;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptracks.Adapter.MusicAdapter;
import com.example.toptracks.Model.Music;
import com.example.toptracks.R;
import com.example.toptracks.Scroll.RecyclerViewScrollListener;

import java.util.ArrayList;

public class FragmentTopTracks extends Fragment implements TopTrackIterator.TopTrackView {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private ArrayList<Music> musicList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private FragmentTopTracksPresenter presenter;

    private boolean isLoading;
    private boolean isLastPage;
    private boolean isLoadmore;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toptracks, container, false);

        recyclerView = view.findViewById(R.id.recylerView);
        linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        presenter = new FragmentTopTracksPresenter();
        presenter.attachView(this);
        musicAdapter = new MusicAdapter(this.getContext(), musicList);
        recyclerView.setAdapter(musicAdapter);
        presenter.fetchTopTracks();

        recyclerView.addOnScrollListener(new RecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void loadmoreItem() {
                isLoading = true;
                if (isLoading = true){
                    presenter.addProgessBar();
                }

                if (isLoadmore){
                    presenter.fetchTopTracks();
                    isLoadmore = false;
                }
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastpage() {
                return isLastPage;
            }
        });
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onFetchSuccess(ArrayList<Music> topTracks) {
        isLoading = false;
        isLastPage = false;
        musicList.clear();
        musicList.addAll(topTracks);
        musicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProgessbar() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                musicList.add(null);
                musicAdapter.notifyItemInserted(musicList.size()-1);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                musicList.remove(null);
                int listSize = musicList.size();
                musicAdapter.notifyItemRemoved(listSize);

                musicAdapter.notifyDataSetChanged();
                if (musicList.size() >= 50){
                    isLastPage = true;
                    Toast.makeText(getContext(), "out of data", Toast.LENGTH_SHORT).show();
                }

                isLoading = false;
                isLoadmore = true;
            }
        }, 2000);
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) { }
}