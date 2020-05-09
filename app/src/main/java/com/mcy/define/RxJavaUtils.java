package com.mcy.define;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: RxJavaUtils
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/29 18:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/29 18:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RxJavaUtils {

    public static void subscribe() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws InterruptedException {
                emitter.onNext("1");
                emitter.onNext("2");
                Thread.sleep(8000);
                emitter.onNext("3");
//                emitter.onError(new Throwable("error"));
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("macy7", "onSubscribe-->" + d.isDisposed());
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d("macy7", "onNext-->" + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("macy7", "onError-->" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("macy7", "onComplete-->onComplete");
                    }
                });

//        new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.d("macy7", "onNext-->" + s);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Throwable {
//                Log.d("macy7", "onError-->" + throwable);
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Throwable {
//                Log.d("macy7", "onComplete-->complete");
//            }
//        }
        Observable.just("2").subscribe(new Subject<String>() {
            @Override
            public boolean hasObservers() {
                return false;
            }

            @Override
            public boolean hasThrowable() {
                return false;
            }

            @Override
            public boolean hasComplete() {
                return false;
            }

            @Override
            public @Nullable
            Throwable getThrowable() {
                return null;
            }

            @Override
            protected void subscribeActual(@NonNull Observer<? super String> observer) {

            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        return retrofit;
    }

    public static void startActivity(Activity activity) {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        activity.startActivity(new Intent(activity, RxJavaActivity.class));
                    }
                });
    }
}
