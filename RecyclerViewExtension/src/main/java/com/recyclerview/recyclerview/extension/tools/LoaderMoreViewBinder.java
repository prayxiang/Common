package com.recyclerview.recyclerview.extension.tools;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.recyclerview.extension.BR;
import com.recyclerview.extension.R;
import com.recyclerview.recyclerview.extension.BaseAdapter;
import com.recyclerview.recyclerview.extension.DataBoundViewHolder;


/**
 * Created by xianggaofeng on 2017/6/6.
 */
public class LoaderMoreViewBinder extends SimpleViewBound<LoaderMore> {

    private LoadMoreScrollListener loadListener;

    public LoaderMoreViewBinder(int layoutId, int br) {
        super(layoutId, br);
    }

    public LoaderMoreViewBinder() {
        super(R.layout.multi_type_view_binder_load_more, BR.data);
    }

    public void attachToAdapter(BaseAdapter adapter) {
        loadListener = adapter.getLoadMoreScrollListener();
    }

    @Override
    public void onItemClick(View v, LoaderMore item, int position) {
        loadListener.load();
    }


//    private void checkStatus(ItemViewHolder holder, LoaderMore item) {
//        holder.findViewById(R.id.load_default).setVisibility(View.GONE);
//        holder.findViewById(R.id.load_loading).setVisibility(View.GONE);
//        holder.findViewById(R.id.load_fail).setVisibility(View.GONE);
//        holder.findViewById(R.id.load_finish).setVisibility(View.GONE);
//        holder.findViewById(R.id.load_end).setVisibility(View.GONE);
//        if (item.loadMoreStatus == LoaderMore.STATUS_LOADING) {
//            holder.findViewById(R.id.load_loading).setVisibility(View.VISIBLE);
//        } else if (item.loadMoreStatus == LoaderMore.STATUS_SUCCESS) {
//            holder.findViewById(R.id.load_finish).setVisibility(View.VISIBLE);
//        } else if (item.loadMoreStatus == LoaderMore.STATUS_DEFAULT) {
//            holder.findViewById(R.id.load_default).setVisibility(View.VISIBLE);
//        } else if (item.loadMoreStatus == LoaderMore.STATUS_FAIL) {
//            holder.findViewById(R.id.load_fail).setVisibility(View.VISIBLE);
//        }else if(item.loadMoreStatus==LoaderMore.S)
//    }


    @Override
    public void onViewAttachedToWindow(DataBoundViewHolder<ViewDataBinding> viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
    }

    @Override
    public void onViewDetachedFromWindow(DataBoundViewHolder<ViewDataBinding> viewHolder) {
        LoaderMore more = viewHolder.getItem();
        if (more.loadMoreStatus == LoaderMore.STATUS_LOADING) {
        } else if (more.loadMoreStatus != LoaderMore.STATUS_END) {
            more.setLoadMoreStatus(LoaderMore.STATUS_DEFAULT);
        }
    }

}
