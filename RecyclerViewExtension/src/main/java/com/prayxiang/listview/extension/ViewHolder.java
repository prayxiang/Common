package com.prayxiang.listview.extension;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

/**
 * Created by prayxiang on 2017/10/17.
 *
 * @description TODO
 */
public class ViewHolder {
    private Object object;

    public View itemView;
    private int position;
    private int type;

    public final int getAdapterPosition() {

        return position;
    }

    @SuppressWarnings("unchecked")
    public <T> T getItem() {
        return (T) object;
    }

    public void setItem(Object object) {
        this.object = object;
    }

    @SuppressWarnings("all")
    public ViewHolder(View itemView) {
        this.itemView = itemView;
    }

    public Context getContext() {
        return itemView.getContext();
    }
}
