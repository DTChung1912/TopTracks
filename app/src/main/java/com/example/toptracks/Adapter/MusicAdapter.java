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

    public MusicAdapter(Context myContext, List<Music> musicList) {
        this.myContext = myContext;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.all_item, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        final Music music = musicList.get(position);
        holder.songName.setText(music.getSongName());
        holder.singerName.setText(music.getSingerName());
        holder.songRank.setText("Rank: " + music.getSongRank());
        holder.listener.setText(music.getListener());
        Glide.with(myContext).load(music.getMusicImage()).into(holder.musicImage);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}
