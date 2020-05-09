package com.mcy.define;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import io.reactivex.schedulers.Schedulers;


public class RxJavaActivity extends AppCompatActivity {

    @Inject
    B b1;
    @Inject
    B b2;
    @Inject
    AppApi appApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        Lifecycle lifecycle = getLifecycle();
        lifecycle.getCurrentState();
        RxJavaUtils.subscribe();
        GetRequestInterface getRequestInterface = RxJavaUtils.getRetrofit().create(GetRequestInterface.class);
        Observable<String> call = getRequestInterface.getCall();
        Observable.interval(2,1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        call.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<String>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull String s) {
                                        Log.d("macy7", "result: " +s);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.d("macy7", "error" + e);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).subscribe();
        DaggerMainComponent
                .builder()
                .appComponent(BaseApplication.getAppComponent())
                .build()
                .inject(this);
        Log.d("macy7", "---->" + b1);
        Log.d("macy7", "---->" + b2);
        Log.d("macy7", "---->" + appApi);
    }
}
