package com.recyclerview.recyclerview.extension.tools;

import android.support.v7.widget.RecyclerView;

/**
 * Desc:用于RecyclerView加载更多的监听，实现滑动到底部自动加载更多
 */

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (recyclerView.computeVerticalScrollOffset() != 0 && !recyclerView.canScrollVertically(1) && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            loadMore();
        }

    }


    public abstract void loadMore();
}
