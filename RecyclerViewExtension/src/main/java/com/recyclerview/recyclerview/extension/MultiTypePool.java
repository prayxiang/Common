package com.recyclerview.recyclerview.extension;

import android.util.SparseArray;

/**
 * Created by prayxiang on 2017/8/30.
 */

public class MultiTypePool {
    public final SparseArray<ViewBinder<?, ?>> mCaches = new SparseArray<>();

    public ViewBinder<?, ?> findViewBinder(Integer integer) {
        return mCaches.get(integer);
    }

    public void register(int integer, ViewBinder<?, ?> binder) {
        mCaches.put(integer, binder);
    }
}
