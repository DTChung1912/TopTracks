package com.example.toptracks.Fragment.toptracks;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class FragmentTopTracks extends Fragment implements TopTrackIterator.TopTrackView {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList = new ArrayList<>();

    private FragmentTopTracksPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new FragmentTopTracksPresenter();
        presenter.attachView(this);
        musicAdapter = new MusicAdapter(this.getContext(), musicList);
        recyclerView.setAdapter(musicAdapter);
        presenter.fetchTopTracks();
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onFetchSuccess(ArrayList<Music> topTracks) {
        musicList.addAll(topTracks);
        musicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) {

    }
}