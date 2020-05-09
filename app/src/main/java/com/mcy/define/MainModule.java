package com.mcy.define;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: MainModule
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 11:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 11:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Module(includes = {SecondModule.class})
public class MainModule {

    @Provides
    A provideA(B b) {
        return new A(b);
    }
}
