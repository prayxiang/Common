package com.prayxiang.flipper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class DefaultFlipperAnimator extends FlipperAnimator {
    private boolean mAnimating = false;

    @Override
    public void animateFlip(final View outView, final View inView) {
        if (mAnimating) {
            outView.animate().cancel();
            inView.animate().cancel();
        }

        if (outView != null) {
            outView.setVisibility(View.VISIBLE);
            outView.setAlpha(1f);
            outView.animate()
                    .alpha(0f)
                    .setDuration(getFlipDuration())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            outView.setVisibility(View.GONE);
                            outView.setAlpha(1f);
                        }
                    })
                    .withLayer()
                    .start();
        }

        if (inView != null) {
            inView.setVisibility(View.VISIBLE);
            inView.setAlpha(0f);
            inView.animate()
                    .alpha(1f)
                    .setDuration(getFlipDuration())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            inView.setAlpha(1f);
                            mAnimating = false;
                        }
                    })
                    .withLayer()
                    .start();
        }
        mAnimating = true;
    }

    @Override
    public boolean isAnimating() {
        return mAnimating;
    }
}
