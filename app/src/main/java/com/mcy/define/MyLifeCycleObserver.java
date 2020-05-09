package com.mcy.define;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: MyLifeCycleOwner
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/3 11:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/3 11:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyLifeCycleObserver implements LifecycleObserver {

    private static final String TAG = "MyLifeCycleObserver";

    public MyLifeCycleObserver() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onActivityCreate() {
        Log.d(TAG, "onActivityCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onActivityResume() {
        Log.d(TAG, "onActivityResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onActivityPause() {
        Log.d(TAG, "onActivityPause");
    }
}
