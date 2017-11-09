package com.prayxiang.listview.extension.tools;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prayxiang.listview.extension.ItemViewBinder;
import com.prayxiang.listview.extension.ItemViewHolder;

/**
 * Created by prayxiang on 2017/10/17.
 */

public class SimpleViewBinder<T> extends ItemViewBinder<T, ItemViewHolder> implements View.OnClickListener {
    private int mLayoutId;

    public SimpleViewBinder(int layoutId) {
        this.mLayoutId = layoutId;
    }

    @Override
    final public ItemViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(mLayoutId, parent, false);
        view.setOnClickListener(this);
        ItemViewHolder holder = new ItemViewHolder(view);
        onViewCreated(holder);
        return holder;
    }

    public void onViewCreated(ItemViewHolder holder) {
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, T item) {
        super.onBindViewHolder(holder, item);
    }

    @Override
    public void onClick(View v) {

    }
}
