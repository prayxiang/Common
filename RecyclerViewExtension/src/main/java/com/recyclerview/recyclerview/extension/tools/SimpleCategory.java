package com.recyclerview.recyclerview.extension.tools;


import java.util.Collection;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class SimpleCategory extends BaseCategory {
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
        return items.size() - headOffset - footOffset;
    }

    public void addHead(Object o) {
        items.add(o);
        headOffset++;
        adapter.notifyDataSetChanged();
    }

    public void addFoot(Object o) {
        int position = items.size();
        items.add(position, o);
        footOffset++;
        onInserted(position, 1);
    }

    public void removeFoot(Object o) {
        items.remove(o);
        footOffset--;
        adapter.notifyDataSetChanged();
    }

    public void removeHead(Object o) {
        items.remove(o);
        headOffset--;
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public void clear() {
        for (int i = items.size() - footOffset - 1; i >= headOffset; i--) {
            items.remove(i);
        }
    }

    @Override
    public void display(Collection<?> collections) {
        loaderMore.reset();
        clear();
        items.addAll(headOffset, collections);
        if (getDataSize() >=limit) {
            if (!isShowFoot()) {
                int position = items.size();
                items.add(position, loaderMore);
                footOffset++;
            }
        } else if (isShowFoot()) {
            items.remove(loaderMore);
            footOffset--;
            adapter.notifyDataSetChanged();
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
            items.addAll(items.size() - footOffset, collections);
            adapter.notifyItemRangeInserted(items.size() - footOffset, collections.size());
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T getLastItem() {
        return (T) items.get(items.size() - 1 - footOffset);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
}
