//package com.prayxiang.flipper;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//
//
///**
// * Created by xianggaofeng on 2017/11/21.
// */
//
//public abstract class DefaultFlipper extends Flipper {
//    private View mProgressView;
//    private View mEmptyView;
//    private View mLoadingView;
//    private View mErrorView;
//    private View mCurrentView;
//    private View mContenView;
//
//    private Status mStatus;
//
//
//
//    private void checkStatusInternal(){
//        if(mCurrentView==null){
//            mCurrentView = mContenView;
//        }
//        if(mStatus==Status.SUCCESS && mContenView!=null){
//            replace(mCurrentView,mContenView);
//        }else if (mStatus==Status.ERROR&&mErrorView!=null){
//            replace(mCurrentView,mErrorView);
//        }else if(mStatus==Status.LOADING&&mLoadingView!=null){
//            replace(mCurrentView,mLoadingView);
//        }/*else if(mStatus==Status.EMPTY){
//
//        }*/
//    }
//
//    public void setStatus(Status status) {
//        if(status==mStatus){
//            return;
//        }
//        this.mStatus = mStatus;
//        checkStatusInternal();
//    }
//}
