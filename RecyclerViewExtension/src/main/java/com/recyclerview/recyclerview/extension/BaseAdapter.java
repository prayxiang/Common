package com.recyclerview.recyclerview.extension;

import android.support.v7.widget.RecyclerView;

import com.recyclerview.listview.extension.tools.SimpleCategory;
import com.recyclerview.recyclerview.extension.tools.LoadMoreScrollListener;
import com.recyclerview.recyclerview.extension.tools.LoaderMore;
import com.recyclerview.recyclerview.extension.tools.LoaderMoreViewBinder;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class BaseAdapter extends MultiTypeAdapter {

    private LoadListener loadListener;
    private SimpleCategory simpleCategory = new SimpleCategory() {
        @Override
        public int getItemViewType(Object item) {
            return getItemViewTypeByObject(item);
        }
    };


    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public BaseAdapter() {
        setStrategy(simpleCategory);
    }

    public int getItemViewTypeByObject(Object item) {
        if (item instanceof Cell) {
            return ((Cell) item).getItemViewType();
        }
        return item.getClass().hashCode();
    }

    public interface LoadListener {
        void load(int offset);
    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        register(new LoaderMoreViewBinder(new LoadMoreScrollListener() {
            @Override
            public void loadMore() {
                if (loadListener != null) {
                    LoaderMore loaderMore = simpleCategory.getLoaderMore();
                    if (loaderMore.loadMoreStatus != LoaderMore.STATUS_END && loaderMore.loadMoreStatus != LoaderMore.STATUS_LOADING) {
                        loaderMore.setLoadMoreStatus(LoaderMore.STATUS_LOADING);
                        loadListener.load(loaderMore.currentPage);
                    }

                }
            }
        }));
    }

    public void addHead(Object o) {
        simpleCategory.addHead(o);
    }

    public void setLimit(int limit) {
        simpleCategory.setLimit(limit);
    }

}
