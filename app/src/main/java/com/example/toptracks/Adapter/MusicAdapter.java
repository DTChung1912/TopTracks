package com.example.toptracks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toptracks.Model.Music;
import com.example.toptracks.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {

    private Context myContext;
    private List<Music> musicList;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public MusicAdapter(Context myContext, List<Music> musicList) {
        this.myContext = myContext;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(myContext).inflate(R.layout.all_item, parent, false);
            return new MusicViewHolder(view);
        }else {
            View view = LayoutInflater.from(myContext).inflate(R.layout.load_view, parent, false);
            return new MusicViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM){
            final Music music = musicList.get(position);
            holder.songName.setText(music.getSongName());
            holder.singerName.setText(music.getSingerName());
            holder.songRank.setText("Rank: " + music.getSongRank());
            holder.listener.setText(music.getListener());
            Glide.with(myContext).load(music.getMusicImage()).into(holder.musicImage);
        }else {
            holder.loadmore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return musicList == null ? 0 : musicList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return musicList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
}
