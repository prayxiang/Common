package com.recyclerview.recyclerview.extension.tools;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class SimpleCategory extends DefaultCategory {
    protected List<Object> mItems = new ArrayList<>();
    protected int headOffset;
    protected int footOffset;
    public LoaderMore loaderMore = new LoaderMore();
    protected int limit;


    public LoaderMore getLoaderMore() {
        return loaderMore;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isShowFoot() {
        return footOffset > 0;
    }

    public int getDataSize() {
        return mItems.size() - headOffset - footOffset;
    }

    public void addHead(Object o) {
        mItems.add(o);
        headOffset++;
        adapter.notifyDataSetChanged();
    }

    public void addFoot(Object o) {
        int position = mItems.size();
        mItems.add(position, o);
        footOffset++;
        Log.d("xgf", getDataSize() + "   limit=" + limit + "  showFoot" + isShowFoot());
        onInserted(position,1);
    }

    public void removeFoot(Object o) {
        mItems.remove(o);
        footOffset--;
        adapter.notifyDataSetChanged();
    }

    public void removeHead(Object o) {
        mItems.remove(o);
        headOffset--;
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public void clear() {
        for (int i = mItems.size() - footOffset - 1; i >= headOffset; i--) {
            mItems.remove(i);
        }
    }

    @Override
    public void display(Collection<?> collections) {
        loaderMore.reset();
        clear();
        mItems.addAll(collections);
        Log.d("xgf", getDataSize() + "   limit=" + limit + "  showFoot" + isShowFoot());
        if (getDataSize() > limit) {
            if (!isShowFoot()) {
                addFoot(loaderMore);
            }
        } else if (isShowFoot()) {
            removeFoot(loaderMore);
        }
        adapter.notifyDataSetChanged();

    }


    @Override
    public void insert(Collection<?> collections) {
        if (collections == null || collections.size() < limit) {
            loaderMore.setLoadMoreStatus(LoaderMore.STATUS_END);
        } else {
            loaderMore.setLoadMoreStatus(LoaderMore.STATUS_SUCCESS);
        }
        if (collections != null) {
            mItems.addAll(mItems.size() - footOffset, collections);
            adapter.notifyItemRangeInserted(mItems.size() - footOffset, collections.size());
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T getLastItem() {
        return (T) mItems.get(mItems.size() - 1 - footOffset);
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }
}
