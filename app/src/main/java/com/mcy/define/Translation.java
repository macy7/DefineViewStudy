package com.mcy.define;

import android.util.Log;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: Translation
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/30 18:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/30 18:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Translation {

    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.d("macy7", content.out );
    }
}
