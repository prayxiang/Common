package com.recyclerview.listview.extension.tools;

import com.recyclerview.recyclerview.extension.tools.LoaderMore;

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
        mItems.add(mItems.size(), o);
        footOffset++;
        adapter.notifyDataSetChanged();
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
    public int getItemViewType(Object item) {
        return item.getClass().hashCode();
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
