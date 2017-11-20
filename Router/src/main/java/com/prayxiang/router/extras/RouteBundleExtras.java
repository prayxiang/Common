package com.prayxiang.router.extras;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;


import com.prayxiang.router.interceptors.RouteInterceptor;
import com.prayxiang.router.interceptors.RouteInterceptorAction;
import com.prayxiang.router.route.IBaseRoute;
import com.prayxiang.router.route.RouteCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An extra container contains {@link RouteBundleExtras#extras} and {@link RouteBundleExtras#interceptors}
 * <p>
 *      <i>extras: the extra bundle data set by {@link IBaseRoute#addExtras(Bundle)}</i>
 *      <i>interceptors: the extra RouteInterceptor set by {@link IBaseRoute#addInterceptor(RouteInterceptor)}</i>
 * </p>
 *
 * @author haoge
 * @see com.prayxiang.router.route.IBaseRoute
 */
public class RouteBundleExtras implements Parcelable, RouteInterceptorAction<RouteBundleExtras> {
    private Bundle extras = new Bundle();
    private ArrayList<RouteInterceptor> interceptors = new ArrayList<>();
    private RouteCallback callback;
    protected RouteBundleExtras() {}

    protected RouteBundleExtras(Parcel in) {
        extras = in.readBundle(getClass().getClassLoader());
        int parcelableInterceptorSize = in.readInt();
        // restore interceptors
        for (int i = 0; i < parcelableInterceptorSize; i++) {
            int type = in.readInt();
            RouteInterceptor interceptor;
            if (type == 0) {
                interceptor = in.readParcelable(getClass().getClassLoader());
            } else {
                interceptor = (RouteInterceptor) in.readSerializable();
            }
            addInterceptor(interceptor);
        }
        int type = in.readInt();
        switch (type) {
            case 0:
                callback = in.readParcelable(getClass().getClassLoader());
                break;
            case 1:
                callback = (RouteCallback) in.readSerializable();
                break;
        }
    }

    public static final Creator<RouteBundleExtras> CREATOR = new Creator<RouteBundleExtras>() {
        @Override
        public RouteBundleExtras createFromParcel(Parcel in) {
            return new RouteBundleExtras(in);
        }

        @Override
        public RouteBundleExtras[] newArray(int size) {
            return new RouteBundleExtras[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(extras);

        List<RouteInterceptor> parcelInterceptors = new ArrayList<>();
        for (RouteInterceptor interceptor : interceptors) {
            if (interceptor instanceof Parcelable
                    || interceptor instanceof Serializable) {
                parcelInterceptors.add(interceptor);
            }
        }
        dest.writeInt(parcelInterceptors.size());
        for (RouteInterceptor interceptor : parcelInterceptors) {
            if (interceptor instanceof Parcelable) {
                Parcelable parcelable = (Parcelable) interceptor;
                dest.writeInt(0);
                dest.writeParcelable(parcelable,flags);
            } else {
                Serializable serializable = (Serializable) interceptor;
                dest.writeInt(1);
                dest.writeSerializable(serializable);
            }
        }

        if (callback != null && callback instanceof Parcelable) {
            dest.writeInt(0);
            dest.writeParcelable((Parcelable) callback, flags);
        } else if (callback != null && callback instanceof Serializable){
            dest.writeInt(1);
            dest.writeSerializable((Serializable) callback);
        } else {
            dest.writeInt(-1);
        }
    }

    public Bundle getExtras() {
        return extras;
    }

    public void addExtras(Bundle extras) {
        if (extras != null) {
            this.extras.putAll(extras);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public RouteBundleExtras addInterceptor(RouteInterceptor interceptor) {
        if (interceptor != null && !interceptors.contains(interceptor)) {
            interceptors.add(interceptor);
        }
        return this;
    }

    @Override
    public RouteBundleExtras removeInterceptor(RouteInterceptor interceptor) {
        if (interceptor != null) {
            interceptors.remove(interceptor);
        }
        return this;
    }

    @Override
    public RouteBundleExtras removeAllInterceptors() {
        interceptors.clear();
        return this;
    }

    @Override
    public List<RouteInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setCallback(RouteCallback callback) {
        this.callback = callback;
    }

    public RouteCallback getCallback() {
        return callback;
    }
}
