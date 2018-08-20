package com.derik.demo.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = MyDatabaseHelper.class.getName();
    private String table = "dict";
    private String linShiTable = "linShi";
    final String CREATE_TABLE_SQL = "create table " + table + "(_id integer primary key autoincrement, word varchar(50), detail varchar(255), date varchar(50))";

    public MyDatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "--- onUpgrade Called ---" + oldVersion + " ---> " + newVersion);

        db.execSQL("create table " + linShiTable + " as select * from " + table + ";");
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
        db.execSQL("insert into " + table + "(_id, word, detail) select _id, word, detail from " + linShiTable + ";");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        Log.i(TAG, "--- onDowngrade Called ---" + oldVersion + " ---> " + newVersion);
    }
}
