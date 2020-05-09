package com.mcy.define;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: SecondModule
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/1 15:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/1 15:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Module
public class SecondModule {
    @Provides
    B provideB() {
        return new B();
    }

//    @Provides
//    B provideStrB(String tag){
//        return new B(tag);
//    }

    @Named("dev")
    @Provides
    B provideDevB() {
        return new B();
    }

    @Named("release")
    @Provides
    B provideReleaseB() {
        return new B();
    }
}
