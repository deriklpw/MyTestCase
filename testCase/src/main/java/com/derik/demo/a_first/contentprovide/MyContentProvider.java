package com.derik.demo.a_first.contentprovide;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.derik.demo.a_first.sqlite.MyDatabaseHelper;

public class MyContentProvider extends ContentProvider {
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private MyDatabaseHelper dbOpenHelper;

    static {
        matcher.addURI(Words.AUTHORITY, "words", WORDS);
        matcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }


    @Override
    public boolean onCreate() {
        System.out.println("=== onCreate 方法被调用 ===");
        dbOpenHelper = new MyDatabaseHelper(this.getContext(), "Mydict.db3", 1);
        //getContext().startActivity(new Intent(getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        System.out.println(uri + " === insert 方法被调用 ===");
        System.out.println("values 参数: " + values.get(Words.Word.WORD)+" Detail:"+values.getAsString(Words.Word.DETAIL));

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case WORDS:
                //事实是values实例没有键值对，照样插入空行
                //若values为null，第二个参数任何设置均无效
                long rowId = db.insert("dict", Words.Word.WORD, values);
                if (rowId > 0) {
                    Uri wordUri = ContentUris.withAppendedId(Words.Word.WORD_CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(wordUri, null);
                    return wordUri;
                }
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] whereArgs, String sortOrder) {
        System.out.println(uri + "=== query 方法被调用 ===");
        System.out.println("where 参数: " + where);

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case WORDS:

                return db.query("dict", projection, where, whereArgs, null, null, sortOrder);

            case WORD:
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                if (where != null && !where.equals("")) {
                    whereClause = whereClause + " and " + where;
                }
                System.out.println(whereClause);
                return db.query("dict", projection, whereClause, whereArgs, null, null, sortOrder);

            default:
                throw new IllegalArgumentException("未知Uri:" + uri);

        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        System.out.println(uri + " === update 方法被调用 ===");
        System.out.println("values 参数: " + values.get(Words.Word.WORD)+" Detail:"+values.getAsString(Words.Word.DETAIL) + ", where 参数: " + where);

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        int num = 0;
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.update("dict", values, where, whereArgs);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                if (where != null && !"".equals(where)) {
                    whereClause = whereClause + " and " + where;
                }
                num = db.update("dict", values, whereClause, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        System.out.println(uri + " === delete 方法被调用 ===");
        System.out.println("where 参数: " + where);

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        int num;
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.delete("dict", where, whereArgs);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                if (where != null && !"".equals(where)) {
                    whereClause = whereClause + " and " + where;
                }
                num = db.delete("dict", whereClause, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        switch (matcher.match(uri)) {
            case WORDS:
                return "vnd.android.cursor.dir/org.foxconn.dict";

            case WORD:
                return "vnd.android.cursor.item/org.foxconn.dict";

            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
    }
}
