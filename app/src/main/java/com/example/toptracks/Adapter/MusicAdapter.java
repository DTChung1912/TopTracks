package com.example.toptracks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.toptracks.Model.Music;
import com.example.toptracks.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {
    private OnItemClick onItemClick;
    private Context myContext;
    private List<Music> musicList;
    private boolean isLoadmore;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public MusicAdapter(Context myContext, List<Music> musicList, OnItemClick onItemClick) {
        this.myContext = myContext;
        this.musicList = musicList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(myContext).inflate(R.layout.all_item, parent, false);
        } else {
            view = LayoutInflater.from(myContext).inflate(R.layout.load_view, parent, false);
        }
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            final Music music = musicList.get(position);
            holder.songName.setText(music.getSongName());
            holder.singerName.setText(music.getSingerName());
            holder.songRank.setText("Rank: " + music.getSongRank());
            holder.listener.setText(music.getListener());
            Glide.with(myContext).load(music.getMusicImage()).transform(new CircleCrop()).into(holder.musicImage);
            holder.musicImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClicked();
                }
            });
        } else {
            holder.loadmore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (musicList == null) {
            return 0;
        } else if (isLoadmore) {
            return musicList.size() + 1;
        } else {
            return musicList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (isLoadmore && position == musicList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void isLoadmore(boolean isLoadmore) {
        this.isLoadmore = isLoadmore;
    }
}
