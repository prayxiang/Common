package com.prayxiang.flipper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.prayxiang.base.vo.Status;

/**
 * Created by xianggaofeng on 2017/11/22.
 */

public class StatusLayout extends FrameLayout {
    public Status status;
    public View errorView;
    public View emptyView;
    public View loadingView;
    public View contentView;
    private boolean needDefault;

    RecyclerDefaultObserver mObserver = new RecyclerDefaultObserver();
    private View currentView;

    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.StatusLayout, defStyleAttr, 0);
        int emptyLayoutId = a.getInt(R.styleable.StatusLayout_emptyLayout, 0);
        int errorLayoutId = a.getInt(R.styleable.StatusLayout_errorLayout, 0);
        int loadLayoutId = a.getInt(R.styleable.StatusLayout_loadingLayout, R.layout.flipper_status_layout_default_loading);

        needDefault = a.getBoolean(R.styleable.StatusLayout_needDefault, false);

        contentView = findViewById(R.id.status_content);

        if (contentView == null) {
            try {
                contentView = getChildAt(0);
                currentView = contentView;
            } catch (Exception e) {

            }

        }
        if (needDefault) {
            if (emptyLayoutId == 0) {
                emptyLayoutId = R.layout.flipper_status_layout_default_empty;
            }
            if (loadLayoutId == 0) {
                loadLayoutId = R.layout.flipper_status_layout_default_loading;
            }
            if (errorLayoutId == 0) {
                errorLayoutId = R.layout.flipper_status_layout_default_error;
            }
        }
        if (loadLayoutId != 0) {
            LayoutInflater.from(context).inflate(emptyLayoutId, this, true);
        }
        if (errorLayoutId != 0) {
            LayoutInflater.from(context).inflate(errorLayoutId, this, true);
        }

        if (emptyLayoutId != 0) {
            LayoutInflater.from(context).inflate(emptyLayoutId, this, true);
        }
        errorView = findViewById(R.id.status_error);
        if (errorView != null) {
            errorView.setVisibility(GONE);
        }
        emptyView = findViewById(R.id.status_empty);
        if (emptyView != null) {
            emptyView.setVisibility(GONE);
        }

        loadingView = findViewById(R.id.status_loading);
        if (loadingView != null) {
            loadingView.setVisibility(GONE);
        }

        a.recycle();
    }


    protected void replace(View outView, View inView, boolean animate) {
        mObserver.replace(outView, inView, animate);
    }


    private void checkStatusInternal() {

        if (currentView == null) {
            currentView = contentView;
        }
        if (status == Status.SUCCESS) {
            replace(currentView, contentView, true);
            currentView = contentView;
        } else if (status == Status.ERROR && errorView != null) {
            replace(currentView, errorView, true);
            currentView = errorView;
        } else if (status == Status.LOADING && loadingView != null) {
            replace(currentView, loadingView, true);
            currentView = loadingView;
        } else if (status == Status.EMPTY) {
            replace(currentView, emptyView, true);
            currentView = emptyView;
        }
        if (currentView != null) {
            bringChildToFront(contentView);
        }

    }


    public void setStatus(Status status) {
        if (this.status == status) {
            return;
        }
        this.status = status;
        checkStatusInternal();
    }


}
