package com.mcy.define;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: DataBaseUtil
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/28 21:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/28 21:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DataBaseUtil {
    private static DataBaseUtil mInstance;
    private SQLiteManager mSqLiteManager;

    private DataBaseUtil(Context context) {
        init(context);
    }

    public static synchronized DataBaseUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataBaseUtil(context);
        }
        return mInstance;
    }

    private void init(Context context) {
        if (mSqLiteManager == null) {
            mSqLiteManager = SQLiteManager.getInstance(context);
        }
    }

    public long insertDB(ContentValues contentValues) {
        SQLiteDatabase writeableDB = mSqLiteManager.getWriteableDB();
        long insert = writeableDB.insert(SQLiteHelper.TABLE_NAME, null, contentValues);
        mSqLiteManager.releaseWriteableDB();
        return insert;
    }

    public int deleteDB(String[] conditions) {
        SQLiteDatabase writeableDB = mSqLiteManager.getWriteableDB();
        int delete = writeableDB.delete(SQLiteHelper.TABLE_NAME, "name = ? AND age = ?", conditions);
        mSqLiteManager.releaseWriteableDB();
        return delete;
    }

    public int updateDB(ContentValues contentValues, String[] conditions) {
        SQLiteDatabase writeableDB = mSqLiteManager.getWriteableDB();
        int update = writeableDB.update(SQLiteHelper.TABLE_NAME, contentValues, "name = ? AND age = ?", conditions);
        mSqLiteManager.releaseWriteableDB();
        return update;
    }

    public List<Student> queryDB() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase readableDB = mSqLiteManager.getReadableDB();
        Cursor cursor = readableDB.query(SQLiteHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            Student student = new Student();
            student.setName(name);
            student.setAge(age);
            list.add(student);
        }
        cursor.close();
        mSqLiteManager.releaseReadableDB();
        return list;
    }
}
