package com.mcy.define;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: SQLiteManager
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/28 20:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/28 20:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SQLiteManager {
    private static volatile SQLiteManager mInstance;
    private SQLiteDatabase mWriteableDB;
    private SQLiteDatabase mReadableDB;
    private SQLiteHelper mSqLiteHelper;
    private volatile int mDBCount = 0;

    private SQLiteManager(Context context) {
        init(context);
    }

    public static synchronized SQLiteManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SQLiteManager(context);
        }
        return mInstance;
    }

    private void init(Context context) {
        mSqLiteHelper = new SQLiteHelper(context);
    }

    public synchronized SQLiteDatabase getWriteableDB() {
        if (mWriteableDB == null || !mWriteableDB.isOpen()) {
            mWriteableDB = mSqLiteHelper.getWritableDatabase();
        }
        mDBCount++;
        return mWriteableDB;
    }

    public synchronized SQLiteDatabase getReadableDB() {
        if (mReadableDB == null || !mReadableDB.isOpen()) {
            mReadableDB = mSqLiteHelper.getReadableDatabase();
        }
        mDBCount++;
        return mReadableDB;
    }

    public synchronized void releaseWriteableDB(){
        if(mWriteableDB != null){
            mWriteableDB.close();
            mWriteableDB = null;
        }
    }

    public synchronized void releaseReadableDB(){
        if(mReadableDB != null){
            mReadableDB.close();
            mReadableDB = null;
        }
    }

    public synchronized void release(){
        if(mSqLiteHelper != null){
            mSqLiteHelper.close();
            mSqLiteHelper = null;
        }
    }
}
