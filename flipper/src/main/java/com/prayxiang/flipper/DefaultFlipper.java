package com.prayxiang.flipper;

import android.view.View;

import com.prayxiang.base.vo.Status;


/**
 * Created by xianggaofeng on 2017/11/21.
 */

public abstract class DefaultFlipper extends Flipper {
    private View progressView;
    private View emptyView;
    private View loadingView;
    private View errorView;
    private View mCurrentView;
    private View contenView;
    private Status mStatus;

    private void checkStatusInternal(){
        if(mCurrentView==null){
            mCurrentView = contenView;
        }
        if(mStatus== Status.SUCCESS && contenView !=null){
            replace(mCurrentView, contenView);
            mCurrentView = contenView;
        }else if (mStatus==Status.ERROR&& errorView !=null){
            replace(mCurrentView, errorView);
            mCurrentView = errorView;
        }else if(mStatus==Status.LOADING&& loadingView !=null){
            replace(mCurrentView, loadingView);
            mCurrentView = loadingView;
        }else if(mStatus==Status.EMPTY){
            replace(mCurrentView, emptyView);
            mCurrentView = emptyView;
        }
    }


    public void setStatus(Status status) {
        if(status==mStatus){
            return;
        }
        this.mStatus = mStatus;
        checkStatusInternal();
    }
}
