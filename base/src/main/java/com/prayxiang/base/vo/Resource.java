package com.prayxiang.base.vo;

import android.support.annotation.Nullable;

import static com.prayxiang.base.vo.Status.EMPTY;
import static com.prayxiang.base.vo.Status.ERROR;
import static com.prayxiang.base.vo.Status.LOADING;
import static com.prayxiang.base.vo.Status.SUCCESS;

/**
 * Created by xianggaofeng on 2017/11/21.
 */

public class Resource<T> {
    public final Status status;

    public final String message;

    public final T data;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }


    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public static <T> Resource<T> empty(@Nullable T data) {
        return new Resource<>(EMPTY, data, null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }


    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
