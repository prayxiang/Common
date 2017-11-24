package com.recyclerview.recyclerview.extension.tools;

import android.view.View;

import com.recyclerview.extension.R;
import com.recyclerview.extension.databinding.MultiTpeViewBinderLoadMoreBinding;
import com.recyclerview.recyclerview.extension.DataBoundViewBinder;
import com.recyclerview.recyclerview.extension.DataBoundViewHolder;


/**
 * Created by xianggaofeng on 2017/6/6.
 */
public class LoaderMoreViewBinder extends DataBoundViewBinder<LoaderMore, MultiTpeViewBinderLoadMoreBinding> {

    private LoadMoreScrollListener listener;

    public LoaderMoreViewBinder(LoadMoreScrollListener listener) {
        super(R.layout.multi_tpe_view_binder_load_more);
        this.listener = listener;
    }

    @Override
    public void onDataBoundCreated(final DataBoundViewHolder<MultiTpeViewBinderLoadMoreBinding> vh) {
        super.onDataBoundCreated(vh);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.loadMore();
            }
        });
    }

    @Override
    public void bindItem(DataBoundViewHolder<MultiTpeViewBinderLoadMoreBinding> holder, LoaderMore item) {
        holder.binding.setData(item);
    }

    @Override
    public void onViewDetachedFromWindow(DataBoundViewHolder<MultiTpeViewBinderLoadMoreBinding> viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        LoaderMore more = viewHolder.getItem();
        if (more.loadMoreStatus == LoaderMore.STATUS_LOADING) {
        } else if (more.loadMoreStatus != LoaderMore.STATUS_END) {
            more.setLoadMoreStatus(LoaderMore.STATUS_DEFAULT);
        }
    }

    @Override
    public void onViewAttachedToWindow(DataBoundViewHolder<MultiTpeViewBinderLoadMoreBinding> viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        LoaderMore more = viewHolder.getItem();
        if (more.loadMoreStatus == LoaderMore.STATUS_LOADING) {
        }
    }
}
