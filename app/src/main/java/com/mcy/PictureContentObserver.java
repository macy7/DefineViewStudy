package com.mcy;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy
 * @ClassName: PictureContentObserver
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/6 14:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/6 14:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureContentObserver extends ContentObserver {

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public PictureContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        Log.d("macy7", "-----onChange-----" + selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        Log.d("macy7", "-----onChange-----" + selfChange + " " + uri.toString());
    }
}
