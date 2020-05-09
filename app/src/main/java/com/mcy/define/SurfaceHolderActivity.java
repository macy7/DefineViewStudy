package com.mcy.define;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.mcy.PictureContentObserver;

public class SurfaceHolderActivity extends AppCompatActivity implements LifecycleOwner {

    public SurfaceHolderActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_surface_holder);
        // 隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 把Activity的标题去掉
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置布局
        this.setContentView(new AndroidSurfaceView(this));
        getLifecycle().addObserver(new MyLifeCycleObserver());
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d("macy7", "" + msg.obj);
            }
        };
        Message message = new Message();
        message.obj = "1111";
        handler.dispatchMessage(message);
        HelloWordKt.helloWorld(new String[]{});
//        int max = HelloWordKt.max(1, 2);
        int max = HelloWordKt.maxA(1, 2);
        Log.d("macy7", "max " + max);

        startService(new Intent(this, PicService.class));

    }




}
