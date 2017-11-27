package com.recyclerview.recyclerview.extension;

import android.support.v7.widget.RecyclerView;

import com.recyclerview.recyclerview.extension.tools.LoadMoreScrollListener;
import com.recyclerview.recyclerview.extension.tools.LoaderMore;
import com.recyclerview.recyclerview.extension.tools.LoaderMoreViewBinder;
import com.recyclerview.recyclerview.extension.tools.SimpleCategory;

/**
 * Created by prayxiang on 2017/11/24.
 */

public class BaseAdapter extends MultiTypeAdapter {

    private LoadListener loadListener;

    protected LoadMoreScrollListener loadMoreScrollListener;

    private SimpleCategory simpleCategory = new SimpleCategory() {
        @Override
        public int getItemViewType(int position) {
            return getItemViewTypeByObject(getItem(position));
        }
    };


    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
        loadMoreScrollListener.setLoadListener(loadListener);
    }

    public BaseAdapter() {
        setStrategy(simpleCategory);
        loadMoreScrollListener = new LoadMoreScrollListener(simpleCategory.getLoaderMore());
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

    public LoadMoreScrollListener getLoadMoreScrollListener() {
        return loadMoreScrollListener;
    }

    public static MultiTypeAdapter create() {
        return new BaseAdapter();
    }

    private LoaderMoreViewBinder defaultLoadMoreViewBinder;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(loadMoreScrollListener);
        if (defaultLoadMoreViewBinder == null) {
            LoaderMoreViewBinder binder = new LoaderMoreViewBinder();
            binder.attachToAdapter(this);
            register(LoaderMore.class, binder);
        }
    }

    public void setDefaultLoadMoreViewBinder(LoaderMoreViewBinder defaultLoadMoreViewBinder) {
        this.defaultLoadMoreViewBinder = defaultLoadMoreViewBinder;
        defaultLoadMoreViewBinder.attachToAdapter(this);
        register(LoaderMore.class, defaultLoadMoreViewBinder);
    }

    public void setDefaultLoadMoreViewBinder(int type, LoaderMoreViewBinder defaultLoadMoreViewBinder) {
        this.defaultLoadMoreViewBinder = defaultLoadMoreViewBinder;
        defaultLoadMoreViewBinder.attachToAdapter(this);
        register(type, defaultLoadMoreViewBinder);
    }

    public void addHead(Object o) {
        simpleCategory.addHead(o);
    }

    public void setLimit(int limit) {
        simpleCategory.setLimit(limit);
    }
    public void loadFail(){
        simpleCategory.getLoaderMore().setLoadMoreStatus(LoaderMore.STATUS_FAIL);
    }
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    public <T> T getLastItem() {
        return simpleCategory.getLastItem();
    }

}
