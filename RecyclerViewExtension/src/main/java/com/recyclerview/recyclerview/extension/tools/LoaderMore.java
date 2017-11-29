package com.recyclerview.recyclerview.extension.tools;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.recyclerview.extension.BR;


/**
 * Created by xianggaofeng on 2017/6/6.
 */
public class LoaderMore extends BaseObservable {
    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;
    @Bindable
    public int loadMoreStatus = STATUS_DEFAULT;
    public boolean loadMoreEndGone = false;
    public int currentPage = 1;
    private boolean active;


    public LoaderMore(){

    }

    public void setIncludeFirst(boolean includeFirst) {
        isIncludeFirst = includeFirst;
        currentPage = includeFirst?0:1;
    }

    public LoaderMore(boolean includeFirst){
        currentPage = includeFirst?0:1;
    }



    public int getLoadMoreStatus() {
        return loadMoreStatus;
    }

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.loadMoreStatus = loadMoreStatus;
        if (loadMoreStatus == STATUS_SUCCESS) {
            currentPage++;
        }

        notifyPropertyChanged(BR.loadMoreStatus);
    }

    public boolean isIncludeFirst;

    public void reset() {
        loadMoreStatus = STATUS_DEFAULT;
        if (isIncludeFirst) {
            currentPage = 0;
        } else {
            currentPage = 1;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
