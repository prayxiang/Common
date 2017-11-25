package com.recyclerview.recyclerview.extension.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recyclerview.extension.R;
import com.recyclerview.recyclerview.extension.ItemViewBinder;
import com.recyclerview.recyclerview.extension.ItemViewHolder;


/**
 * Created by xianggaofeng on 2017/6/6.
 */
public class LoaderMoreViewBinder extends ItemViewBinder<LoaderMore, ItemViewHolder> {

    private LoadMoreScrollListener listener;

    public LoaderMoreViewBinder(LoadMoreScrollListener listener) {
        this.listener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {

        ItemViewHolder holder = new ItemViewHolder(inflater.inflate(R.layout.multi_type_view_binder_load_more, parent, false));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.loadMore();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, LoaderMore item) {
        checkStatus(holder, item);
    }

    private void checkStatus(ItemViewHolder holder, LoaderMore item) {
        holder.findViewById(R.id.load_default).setVisibility(View.GONE);
        holder.findViewById(R.id.load_loading).setVisibility(View.GONE);
        holder.findViewById(R.id.load_fail).setVisibility(View.GONE);
        holder.findViewById(R.id.load_finish).setVisibility(View.GONE);
        if (item.loadMoreStatus == LoaderMore.STATUS_LOADING) {
            holder.findViewById(R.id.load_loading).setVisibility(View.VISIBLE);
        } else if (item.loadMoreStatus == LoaderMore.STATUS_SUCCESS) {
            holder.findViewById(R.id.load_finish).setVisibility(View.VISIBLE);
        } else if (item.loadMoreStatus == LoaderMore.STATUS_DEFAULT) {
            holder.findViewById(R.id.load_default).setVisibility(View.VISIBLE);
        } else if (item.loadMoreStatus == LoaderMore.STATUS_FAIL) {
            holder.findViewById(R.id.load_fail).setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onViewDetachedFromWindow(ItemViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        LoaderMore more = viewHolder.getItem();
        if (more.loadMoreStatus == LoaderMore.STATUS_LOADING) {
        } else if (more.loadMoreStatus != LoaderMore.STATUS_END) {
            more.setLoadMoreStatus(LoaderMore.STATUS_DEFAULT);
        }
    }

    @Override
    public void onViewAttachedToWindow(ItemViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
    }
}
