package com.mcy.define;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: BaseApplication
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/29 9:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/29 9:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initGreenDao();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    private void initGreenDao()
    {
        DaoManager mManager = DaoManager.getInstance();
        mManager.init(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
