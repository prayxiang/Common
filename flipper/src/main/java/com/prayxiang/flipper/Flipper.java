package com.prayxiang.flipper;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Flips between views with a common ancestor using a {@link FlipperAnimator}.
 */
public class Flipper {
    private FlipperAnimator mFlipperAnimator = new DefaultFlipperAnimator();

    public FlipperAnimator getFlipperAnimator() {
        return mFlipperAnimator;
    }

    public void setFlipperAnimator(FlipperAnimator flipperAnimator) {
        mFlipperAnimator = flipperAnimator;
    }

    public void replace(View outView, View inView) {
        replaceInternal(outView, inView, true);
    }

    public void replaceNoAnimation(View outView, View inView) {
        replaceInternal(outView, inView, false);
    }

    protected void replaceInternal(View outView, View inView, boolean animate) {
        if(outView==inView){
            return;
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
