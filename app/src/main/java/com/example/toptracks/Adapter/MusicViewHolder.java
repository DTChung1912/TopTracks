package com.example.toptracks.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptracks.R;

public class MusicViewHolder extends RecyclerView.ViewHolder {

    ImageView musicImage;
    TextView songName, singerName, songRank, listener;
    ProgressBar loadmore;

    public MusicViewHolder(@NonNull View itemView) {
        super(itemView);
        musicImage = itemView.findViewById(R.id.musicImage);
        songName = itemView.findViewById(R.id.songName);
        singerName = itemView.findViewById(R.id.singerName);
        songRank = itemView.findViewById(R.id.songRank);
        listener = itemView.findViewById(R.id.listener);
        loadmore = itemView.findViewById(R.id.loadmore);
    }
}
