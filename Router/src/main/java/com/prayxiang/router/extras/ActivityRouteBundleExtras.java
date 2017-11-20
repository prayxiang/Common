package com.prayxiang.router.extras;

import android.content.Intent;
import android.os.Parcel;

import com.prayxiang.router.interceptors.RouteInterceptor;


/**
 * An extra container associate with {@link com.prayxiang.router.route.ActivityRoute}
 *
 * @author haoge
 */
public class ActivityRouteBundleExtras extends RouteBundleExtras {
    /**
     * Associate with {@link android.app.Activity#startActivityForResult(Intent, int)}
     */
    private int requestCode = -1;
    private int inAnimation = -1;
    private int outAnimation = -1;
    private int flags;

    public ActivityRouteBundleExtras() {}

    private ActivityRouteBundleExtras(Parcel in) {
        super(in);
        requestCode = in.readInt();
        inAnimation = in.readInt();
        outAnimation = in.readInt();
        flags = in.readInt();
    }

    public static final Creator<ActivityRouteBundleExtras> CREATOR = new Creator<ActivityRouteBundleExtras>() {
        @Override
        public ActivityRouteBundleExtras createFromParcel(Parcel in) {
            return new ActivityRouteBundleExtras(in);
        }

        @Override
        public ActivityRouteBundleExtras[] newArray(int size) {
            return new ActivityRouteBundleExtras[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(requestCode);
        dest.writeInt(inAnimation);
        dest.writeInt(outAnimation);
        dest.writeInt(this.flags);
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getInAnimation() {
        return inAnimation;
    }

    public void setInAnimation(int inAnimation) {
        this.inAnimation = inAnimation;
    }

    public int getOutAnimation() {
        return outAnimation;
    }

    public void setOutAnimation(int outAnimation) {
        this.outAnimation = outAnimation;
    }

    public int getFlags() {
        return flags;
    }

    public void addFlags(int flags) {
        this.flags |= flags;
    }

    @Override
    public ActivityRouteBundleExtras addInterceptor(RouteInterceptor interceptor) {
        return (ActivityRouteBundleExtras) super.addInterceptor(interceptor);
    }

    @Override
    public ActivityRouteBundleExtras removeInterceptor(RouteInterceptor interceptor) {
        return (ActivityRouteBundleExtras) super.removeInterceptor(interceptor);
    }

    @Override
    public ActivityRouteBundleExtras removeAllInterceptors() {
        return (ActivityRouteBundleExtras) super.removeAllInterceptors();
    }

}
