package com.mcy.define;

import android.webkit.JavascriptInterface;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: AndroidtoJs
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/6 18:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/6 18:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AndroidtoJs {
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        System.out.println("JS调用了Android的hello方法");
    }
}
