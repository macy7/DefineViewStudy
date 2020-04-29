package com.mcy.define;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: SqliteHelper
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/28 18:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/28 18:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "macy7";
    public static final String TABLE_NAME = "home";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "
                + "name VARCHAR, "
                + "age INT "
                + ")";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
