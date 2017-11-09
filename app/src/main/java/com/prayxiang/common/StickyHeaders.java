package com.prayxiang.common;

/**
 * Created by prayxiang on 2017/10/17.
 */

public class StickyHeaders implements com.prayxiang.recyclerview.extension.manager.StickyHeaders {
    @Override
    public boolean isStickyHeader(int position) {
        return false;
    }
}
