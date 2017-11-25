package com.prayxiang.flipper;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by prayxiang on 2017/11/25.
 */

public class RecyclerDefaultObserver extends RecyclerView.AdapterDataObserver {
    protected RecyclerView mRecyclerView;
    protected View mEmptyView;
    private FlipperAnimator mFlipperAnimator = new DefaultFlipperAnimator();
    private RecyclerView.Adapter mAdapter;
    private int mCount;

    public boolean monitor(RecyclerView.Adapter adapter) {
        // Start monitoring this adapter by registering an observer and ensuring the current visibility state is valid.
        if (adapter != mAdapter) {
            if (mAdapter != null) {
                mAdapter.unregisterAdapterDataObserver(this);
            }
            mAdapter = adapter;
            if (mAdapter != null) {
                mAdapter.registerAdapterDataObserver(this);
                mCount = mAdapter.getItemCount();
                if (mCount > 0) {
                    replace(mEmptyView, mRecyclerView, false);
                } else {
                    replace(mRecyclerView, mEmptyView, true);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onChanged() {
        checkCount(mAdapter.getItemCount());
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        checkCount(mCount + itemCount);
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        checkCount(mCount - itemCount);
    }

    private void checkCount(int count) {
        if (count == 0 && mCount > 0) {
            replace(mRecyclerView, mEmptyView, true);
        } else if (count > 0 && mCount == 0) {
            replace(mEmptyView, mRecyclerView, true);
        }
        mCount = count;
    }


    private void disableItemAnimatorForRecyclerInAnimation() {
        final RecyclerView.ItemAnimator itemAnimator = mRecyclerView.getItemAnimator();
        FlipperAnimator flipperAnimator = getFlipperAnimator();
        if (itemAnimator != null && flipperAnimator != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.postDelayed(() -> mRecyclerView.setItemAnimator(itemAnimator), flipperAnimator.getFlipDuration());
        }
    }


    public FlipperAnimator getFlipperAnimator() {
        return mFlipperAnimator;
    }

    protected void replace(View outView, View inView, boolean animate) {
        if (outView == inView) {
            return;
        }
        if (animate && inView == mRecyclerView) {
            disableItemAnimatorForRecyclerInAnimation();
        }
        if (animate && mFlipperAnimator != null && ViewCompat.isLaidOut(outView)) {
            mFlipperAnimator.animateFlip(outView, inView);
        } else {
            if (mFlipperAnimator != null && mFlipperAnimator.isAnimating()) {
                outView.animate().cancel();
                inView.animate().cancel();
            }
            outView.setVisibility(View.GONE);
            inView.setVisibility(View.VISIBLE);
        }
    }

}
