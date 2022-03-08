package com.example.toptracks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toptracks.Model.Music;
import com.example.toptracks.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{

    private Context mycontext;
    private List<Music> musicList;


    public MusicAdapter(Context mycontext, List<Music> musicList){
        this.mycontext = mycontext;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mycontext).inflate(R.layout.all_item, parent, false);
        return new MusicAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Music music = musicList.get(position);
        holder.songName.setText(music.getSongName());
        holder.singerName.setText(music.getSingerName());
        holder.songRank.setText("Rank: " + music.getSongRank());
        holder.listener.setText(music.getListener());
        Glide.with(mycontext).load(music.getMusicImage()).into(holder.musicImage);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView musicImage;
        private TextView songName,singerName,songRank,listener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            musicImage = itemView.findViewById(R.id.musicImage);
            songName = itemView.findViewById(R.id.songName);
            singerName = itemView.findViewById(R.id.singerName);
            songRank = itemView.findViewById(R.id.songRank);
            listener = itemView.findViewById(R.id.listener);
        }
    }
}
