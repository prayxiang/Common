/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prayxiang.recyclerview.extension;


import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prayxiang.recyclerview.extension.tools.OnItemClickListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> implements OnItemClickListener {


    static final Object DB_PAYLOAD = new Object();
    protected List<Object> mItems = Collections.emptyList();
    @Nullable
    private RecyclerView mRecyclerView;
    private MultiTypePool mPool = new MultiTypePool();
    private TypeStrategy mCategory = new TypeStrategy() {

        @Override
        public int getItemType(Object item) {
            return item.getClass().hashCode();
        }
    };

    public MultiTypeAdapter() {

    }

    /**
     * This is used to block items from updating themselves. RecyclerView wants to know when an
     * item is invalidated and it prefers to refresh it via onRebind. It also helps with performance
     * since data binding will not update views that are not changed.
     */
    final OnRebindCallback mOnRebindCallback = new OnRebindCallback() {
        @Override
        public boolean onPreBind(ViewDataBinding binding) {
            if (mRecyclerView == null || mRecyclerView.isComputingLayout()) {
                return true;
            }
            int childAdapterPosition = mRecyclerView.getChildAdapterPosition(binding.getRoot());
            if (childAdapterPosition == RecyclerView.NO_POSITION) {
                return true;
            }
            notifyItemChanged(childAdapterPosition, DB_PAYLOAD);
            return false;
        }
    };
    private LayoutInflater inflater;


    @Override
    @CallSuper
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewBinder binder = mPool.findViewBinder(viewType);
        binder.adapter = this;
        assert inflater != null;
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int position) {
        throw new IllegalArgumentException("just overridden to make final.");
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        ViewBinder binder = mPool.findViewBinder(holder.getItemViewType());
        holder.setItem(getItem(position));
        binder.onBindViewHolder(holder, payloads);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewBinder binder = (ViewBinder) mPool.findViewBinder(holder.getItemViewType());
        binder.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ViewBinder binder = (ViewBinder) mPool.findViewBinder(holder.getItemViewType());
        binder.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    @Override
    public int getItemViewType(int position) {
        return mCategory.getItemType(getItem(position));
    }


    public MultiTypeAdapter(Object... items) {
        mItems = Arrays.asList(items);
    }

    public void register(Class t, ViewBinder binder) {
        mPool.register(t.hashCode(), binder);
    }

    public void register(int type, ViewBinder binder) {
        mPool.register(type, binder);
    }

    public void register(ViewBinder binder) {
        Type types[] = getGenericParametersType(binder.getClass());
        mPool.register(types[0].hashCode(), binder);
    }

    public static Type[] getGenericParametersType(Class clz) {
        ParameterizedType paramType = (ParameterizedType) clz.getGenericSuperclass();
        return paramType.getActualTypeArguments();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }


    public void addItem(Object item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }


    public void addItem(int position, Object item) {
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void addItem(int position, List item) {
        int from = mItems.size() - 1;
        if (item == null) {
            return;
        }
        if (from < 0) {
            from = 0;
        }
        mItems.add(position, item);
        notifyItemRangeInserted(from, item.size());
    }

    public List getItems() {
        return mItems;
    }

    public void setItems(List items) {
        if (items == null) {
            items = Collections.emptyList();
        }
        this.mItems = items;
    }

    public void display(List items) {
        setItems(items);
        notifyDataSetChanged();
    }

    public void removed(int position) {
        mItems.remove(position);
    }

    private OnItemClickListener itemClickListener;

    @Override
    public void onItemClick(View view, Object item, int position) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(view, item, position);
        }
    }
}
