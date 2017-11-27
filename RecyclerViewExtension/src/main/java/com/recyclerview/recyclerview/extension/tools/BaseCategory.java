package com.recyclerview.recyclerview.extension.tools;

import com.recyclerview.recyclerview.extension.TypeStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class BaseCategory extends TypeStrategy {

    public BaseCategory() {
        items = new ArrayList();
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getClass().hashCode();
    }

    @Override
    public void display(Collection<?> collections) {
        clear();
        items.addAll(collections);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void insert(Collection<?> collections) {
        items.addAll(collections);
        onInserted(items.size(), collections.size());
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public void remove(int position) {
        items.remove(position);
        onRemoved(position, 1);
    }

    @Override
    public void clear() {
        items.clear();
    }

    public List<?> getData() {
        return items;
    }
}
