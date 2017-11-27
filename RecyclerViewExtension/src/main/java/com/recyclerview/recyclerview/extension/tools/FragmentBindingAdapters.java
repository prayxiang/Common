/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.recyclerview.recyclerview.extension.tools;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Binding adapters that work with a fragment instance.
 */
public class FragmentBindingAdapters {
    final Fragment fragment;


    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }

    @BindingAdapter(value = {"imageUrl", "placeHolder", "errorImage"}, requireAll = false)
    public void bindImage(ImageView imageView, String url, Drawable drawable, Drawable error) {
        Glide.with(fragment).load(url).placeholder(drawable).error(error).into(imageView);
    }
}
