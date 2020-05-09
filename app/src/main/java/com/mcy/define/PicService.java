package com.mcy.define;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mcy.PictureContentObserver;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: PicService
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/6 15:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/6 15:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PicService extends Service {

    PictureContentObserver pictureContentObserver;

    Handler handler1 = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        pictureContentObserver = new PictureContentObserver(handler1);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerContentObserver();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void registerContentObserver(){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        getContentResolver().registerContentObserver(uri, false, pictureContentObserver);
    }

    private void unregisterContentObserver(){
        getContentResolver().unregisterContentObserver(pictureContentObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterContentObserver();
    }
}
