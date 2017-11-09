package com.recyclerview.listview.extension.tools;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.recyclerview.listview.extension.DataBoundViewBinder;
import com.recyclerview.listview.extension.DataBoundViewHolder;

/**
 * Created by prayxiang on 2017/10/17.
 */

public class SimpleViewBound<T> extends DataBoundViewBinder<T, ViewDataBinding> implements View.OnClickListener {
    private int br;

    public SimpleViewBound(int layoutId, int br) {
        super(layoutId);
        this.br = br;
    }

    @Override
    public void onDataBoundCreated(DataBoundViewHolder<ViewDataBinding> vh) {
        vh.binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void bindItem(DataBoundViewHolder<ViewDataBinding> holder, T item) {
        holder.binding.setVariable(br, item);
    }


    @Override
    public void onClick(View v) {

    }
}
