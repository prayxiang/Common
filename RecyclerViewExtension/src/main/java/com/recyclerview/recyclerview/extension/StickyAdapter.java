package com.recyclerview.recyclerview.extension;

import android.util.Log;
import android.view.View;

import com.recyclerview.recyclerview.extension.manager.StickyHeaders;

/**
 * Created by prayxiang on 2017/11/7.
 */

public class StickyAdapter extends MultiTypeAdapter implements StickyHeaders, StickyHeaders.ViewSetup {

    @Override
    public boolean isStickyHeader(int position) {
        return (getItem(position)+"").contains("3");
    }

    @Override
    public void setupStickyHeaderView(View stickyHeader) {

        Log.d("xgf","test setup");
    }

    @Override
    public void teardownStickyHeaderView(View stickyHeader) {
        Log.d("xgf","teardown");
    }
}
