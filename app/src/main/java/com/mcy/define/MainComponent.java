package com.mcy.define;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: MainComponent
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 11:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 11:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ActivityScoped
@Component(modules = {MainModule.class, SecondModule.class}, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(Dagger2Activity activity);
    void inject(RxJavaActivity activity);
}
