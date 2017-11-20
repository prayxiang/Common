package com.prayxiang.router.module;

import android.app.Activity;

import com.prayxiang.router.route.ActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * An entity to contains some data for route
 * @author haoge
 */
public class RouteRule<R> {
    /** Associate with {@link android.os.Bundle#putString(String, String)}*/
    public static final int STRING = -1;
    /** Associate with {@link android.os.Bundle#putByte(String, byte)} */
    public static final int BYTE = 0;
    /** Associate with {@link android.os.Bundle#putShort(String, short)} */
    public static final int SHORT = 1;
    /** Associate with {@link android.os.Bundle#putInt(String, int)} */
    public static final int INT = 2;
    /** Associate with {@link android.os.Bundle#putLong(String, long)} */
    public static final int LONG = 3;
    /** Associate with {@link android.os.Bundle#putFloat(String, float)} */
    public static final int FLOAT = 4;
    /** Associate with {@link android.os.Bundle#putDouble(String, double)} */
    public static final int DOUBLE = 5;
    /** Associate with {@link android.os.Bundle#putBoolean(String, boolean)} */
    public static final int BOOLEAN = 6;
    /** Associate with {@link android.os.Bundle#putChar(String, char)} */
    public static final int CHAR = 7;

    /** Associate with {@link android.os.Bundle#putIntegerArrayList(String, ArrayList)} */
    public static final int INT_LIST = 8;
    /** Associate with {@link android.os.Bundle#putStringArrayList(String, ArrayList)} */
    public static final int STRING_LIST = 9;


    /**
     * @param clz clzName must be a {@link Activity} or {@link ActionSupport} total name
     */
    public RouteRule(Class clz) {
        this.clz = clz;
    }

    private Class clz;
    private Map<String,Integer> params = new HashMap<>();

    public Class getRuleClz() {
        return clz;
    }

    public Map<String,Integer> getParams() {
        return params;
    }

    /**
     * Specify required type when parsing the Url parameters
     * @param key the key in Url params.
     * @param type the required type. default is {@link RouteRule#STRING}
     * @return RouteRule
     */
    public R addParam (String key, int type) {
        params.put(key,type);
        //noinspection unchecked
        return (R) this;
    }


}
