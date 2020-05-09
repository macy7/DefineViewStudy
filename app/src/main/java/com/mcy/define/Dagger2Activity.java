package com.mcy.define;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Scope;

import dagger.Lazy;

public class Dagger2Activity extends AppCompatActivity {

    @Inject
    A a;
    @Inject
    B b1;
    @Inject
    B b2;
    @Named("dev")
    @Inject
    Lazy<B> devB;
    @Named("release")
    @Inject
    Provider<B> releaseB;
    @Inject
    AppApi appApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
//        DaggerMainComponent.create().inject(this);
        DaggerMainComponent
                .builder()
                .appComponent(BaseApplication.getAppComponent())
                .build()
                .inject(this);
//        a.eat();
//        b.show();
//        devB.show();
//        releaseB.show();
        Log.d("macy7", "---->" + devB);
        Log.d("macy7", "---->" + releaseB);
        Log.d("macy7", "---->" + appApi);
        RxJavaUtils.startActivity(this);
//        startActivity(new Intent(this, RxJavaActivity.class));

    }
}
