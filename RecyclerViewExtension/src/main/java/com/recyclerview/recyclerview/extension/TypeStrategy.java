package com.recyclerview.recyclerview.extension;

import android.support.v7.util.ListUpdateCallback;

import java.util.Collection;
import java.util.List;

/**
 * Created by prayxiang on 2017/10/17.
 */

public abstract class TypeStrategy implements ListUpdateCallback {
    protected MultiTypeAdapter adapter;


    @Override
    public void onInserted(int position, int count) {
        adapter.notifyItemRangeInserted(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        adapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onChanged(int position, int count, Object payload) {
        adapter.notifyItemRangeChanged(position, count, payload);
    }


    public void attachAdapter(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract int getItemCount();

    public abstract int getItemViewType(Object item);

    public void display(Collection<?> collections) {

    }


    public void insert(Collection<?> collections) {
    }

    public abstract Object getItem(int position);

    public abstract void remove(int position);

    public void clear() {

    }

    public List<?> getItems() {
        return null;
    }
}
