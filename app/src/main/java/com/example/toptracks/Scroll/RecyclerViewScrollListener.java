package com.example.toptracks.Scroll;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;

    public RecyclerViewScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if (isLoading() || isLastpage()){
            return;
        }
        if (firstVisibleItemPosition >= 0
                && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount){
            loadmoreItem();
        }
    }

    public abstract void loadmoreItem();
    public abstract boolean isLoading();
    public abstract boolean isLastpage();
}
