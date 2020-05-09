package com.mcy.define;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: AppComponent
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 16:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 16:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    AppApi getAppApi();
}
