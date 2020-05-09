package com.mcy.define;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: AppModule
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 16:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 16:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    AppApi providerAppApi() {
        return new AppApi();
    }
}
