package com.mcy.define;

import android.util.Log;

import dagger.Module;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: B
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 15:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 15:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class B {
    private String mTag = "";

    public B() {
    }

    public B(String tag) {
        this.mTag = tag;
    }

    public void show() {
        Log.d("macy7", "show: " + mTag);
    }
}
