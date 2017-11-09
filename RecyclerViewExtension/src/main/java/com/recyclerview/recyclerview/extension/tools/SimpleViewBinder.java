package com.recyclerview.recyclerview.extension.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recyclerview.recyclerview.extension.ItemViewBinder;
import com.recyclerview.recyclerview.extension.ItemViewHolder;

/**
 * Created by prayxiang on 2017/10/17.
 */

public class SimpleViewBinder<T> extends ItemViewBinder<T, ItemViewHolder>  {
    private int mLayoutId;
    private OnItemClickListener<T> onClickListener;

    public SimpleViewBinder(int layoutId) {
        this.mLayoutId = layoutId;
    }

    @Override
    final public ItemViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(mLayoutId, parent, false);

        ItemViewHolder holder = new ItemViewHolder(view);

        onViewCreated(holder);
        view.setOnClickListener((v) -> onItemClick(v,holder.<T>getItem(), holder.getAdapterPosition()));
        return holder;
    }

    public void onViewCreated(ItemViewHolder holder) {
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, T item) {
        super.onBindViewHolder(holder, item);
    }

    public void onItemClick(View v, T item, int position) {
        if (onClickListener != null) {
            onClickListener.onItemClick(v, item, position);
        } else {
            getAdapter().onItemClick(v,item,position);
        }
    }
    public SimpleViewBinder<T> addOnClickListener(OnItemClickListener<T> onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }
}
