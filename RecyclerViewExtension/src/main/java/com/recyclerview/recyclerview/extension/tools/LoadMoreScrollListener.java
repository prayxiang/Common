package com.recyclerview.recyclerview.extension.tools;

import android.support.v7.widget.RecyclerView;

import com.recyclerview.recyclerview.extension.BaseAdapter;

/**
 * Desc:用于RecyclerView加载更多的监听，实现滑动到底部自动加载更多
 */

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private LoaderMore loaderMore;

    private BaseAdapter.LoadListener loadListener;


    public void setLoadListener(BaseAdapter.LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public LoadMoreScrollListener(LoaderMore more) {
        this.loaderMore = more;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (recyclerView.computeVerticalScrollOffset() != 0 && !recyclerView.canScrollVertically(1) && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            if (loaderMore.loadMoreStatus != LoaderMore.STATUS_END && loaderMore.loadMoreStatus != LoaderMore.STATUS_LOADING&&loaderMore.isActive()) {
                loaderMore.setLoadMoreStatus(LoaderMore.STATUS_LOADING);
                if(loadListener!=null){
                    loadListener.load(loaderMore.currentPage);
                }
            }
        }

    }

    public void load(){
        if(loaderMore.loadMoreStatus != LoaderMore.STATUS_END && loaderMore.loadMoreStatus != LoaderMore.STATUS_LOADING&&loaderMore.isActive()){
            loadListener.load(loaderMore.currentPage);
        }
    }
}
