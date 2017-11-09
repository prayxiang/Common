package com.prayxiang.recyclerview.extension.tools;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.prayxiang.recyclerview.extension.DataBoundViewBinder;
import com.prayxiang.recyclerview.extension.DataBoundViewHolder;

/**
 * Created by prayxiang on 2017/10/17.
 */

public class SimpleViewBound<T> extends DataBoundViewBinder<T, ViewDataBinding> implements OnItemClickListener<T> {


    private int br;
    private OnItemClickListener<T> onClickListener;

    public SimpleViewBound(int layoutId, int br) {
        super(layoutId);
        this.br = br;
    }

    @Override
    public void onDataBoundCreated(DataBoundViewHolder<ViewDataBinding> vh) {
        vh.binding.getRoot().setOnClickListener((view) -> onItemClick(view, vh.<T>getItem(), vh.getAdapterPosition()));
    }

    @Override
    public void bindItem(DataBoundViewHolder<ViewDataBinding> holder, T item) {
        holder.binding.setVariable(br, item);
    }

    public void onItemClick(View v, T item, int position) {
        if (onClickListener != null) {
            onClickListener.onItemClick(v, item, position);
        } else {
            getAdapter().onItemClick(v,item,position);
        }
    }

    public SimpleViewBound<T> addOnClickListener(OnItemClickListener<T> onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }
}
