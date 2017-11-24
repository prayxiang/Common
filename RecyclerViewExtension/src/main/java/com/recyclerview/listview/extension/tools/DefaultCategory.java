package com.recyclerview.listview.extension.tools;

import com.recyclerview.recyclerview.extension.TypeStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class DefaultCategory extends TypeStrategy {
    protected List<Object> mItems = new ArrayList<>(30);

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(Object item) {
        return item.getClass().hashCode();
    }

    @Override
    public void display(Collection<?> collections) {
        clear();
        mItems.addAll(collections);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void insert(Collection<?> collections) {
        mItems.addAll(collections);
        onInserted(mItems.size(), collections.size());
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public void remove(int position) {
        mItems.remove(position);
        onRemoved(position, 1);
    }

    @Override
    public void clear() {
        mItems.clear();
    }
}
