package com.derik.demo.storage.sqlite;

import com.derik.demo.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SQLiteActivity extends Activity {

	private SQLiteDatabase dbDatabase;
	private Button writeButton = null;
	private Button clearButton = null;
	private Button queryButton = null;
	private ListView listView;
	private SQLiteDatabase dbDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contentsql);

		writeButton = (Button) findViewById(R.id.write);
		clearButton = (Button) findViewById(R.id.clear_db);
		queryButton = (Button) findViewById(R.id.query);
		listView = (ListView) findViewById(R.id.listview2);

		//方式一,使用异常机制,处理数据库
		//funcSQL();
		//方式二,使用SQLiteHelper,处理数据库
		funcSQLHelper();

	}

	public void funcSQL() {
		
		dbDatabase = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().getAbsolutePath() + "/My.db3", null);
		writeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String title = ((EditText) findViewById(R.id.title)).getText().toString();
				String content = ((EditText) findViewById(R.id.content)).getText().toString();

				try {
					insertData(dbDatabase, title, content);
					Cursor cursor = dbDatabase.rawQuery("select * from news_info", null);
					inflateList(cursor);
				} catch (SQLiteException e) {
					// TODO: handle exception
					dbDatabase.execSQL(
							"create table news_info(_id integer primary key autoincrement, news_title varchar(50), news_content varchar(255))");
					insertData(dbDatabase, title, content);
					Cursor cursor = dbDatabase.rawQuery("select * from news_info", null);
					inflateList(cursor);
				}

			}
		});

		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				try{
					if (dbDatabase != null) {
						dbDatabase.execSQL("delete from news_info");
						System.out.println("SQLiteActivity.onClick:  delete from news_info");
					}

				}catch (SQLiteException e){
					e.printStackTrace();
				}


			}
		});

		queryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Cursor cursor = dbDatabase.rawQuery("select * from news_info", null);
				inflateList(cursor);
			}
		});
	}
	
	
	private void insertData(SQLiteDatabase db, String title, String content) {
		db.execSQL("insert into news_info values(null, ?, ?)", new String[] { title, content });
	}

	private void inflateList(Cursor cursor) {

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(SQLiteActivity.this, R.layout.line, cursor,
				new String[] { "news_title", "news_content" }, new int[] { R.id.my_title, R.id.my_content },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);

	}

	//方式二,使用SQLiteOpenHelper,操作数据库
	public void funcSQLHelper() {
		MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this, getDir("test", MODE_PRIVATE).getAbsolutePath()+"/dict.db3", 1);
		dbDatabaseHelper = databaseHelper.getReadableDatabase();
		writeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String title = ((EditText) findViewById(R.id.title)).getText().toString();
				String content = ((EditText) findViewById(R.id.content)).getText().toString();
				insertDataTransactionHelper(dbDatabaseHelper, title, content);
				Cursor cursor = dbDatabaseHelper.rawQuery("select * from dict", null);
				inflateListHelper(cursor);
			}
		});

		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				try{
					if (dbDatabaseHelper != null) {
						dbDatabaseHelper.execSQL("delete from dict");
						System.out.println("SQLiteActivity.onClick:  delete from news_info");
					}

				}catch (SQLiteException e){
					e.printStackTrace();
				}


			}
		});

		queryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Cursor cursor = dbDatabaseHelper.rawQuery("select * from dict", null);
				inflateListHelper(cursor);
			}
		});
		
	}
	
	private void inflateListHelper(Cursor cursor) {

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(SQLiteActivity.this, R.layout.line, cursor,
				new String[] { "word", "detail" }, new int[] { R.id.my_title, R.id.my_content },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);

	}


	private void insertDataTransactionHelper(SQLiteDatabase db, String title, String content){
		// 使用SQLiteOpenHelper类的子类管理数据库文件
		try {
			db.beginTransaction();
			db.execSQL("insert into dict(_id, word, detail) values(null, ?, ?)", new String[] {title, content});
			db.setTransactionSuccessful();


		} finally {
			// TODO: handle finally clause
			db.endTransaction();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dbDatabase != null && dbDatabase.isOpen()) {
			dbDatabase.close();
		}
		
		if (dbDatabaseHelper != null && dbDatabaseHelper.isOpen()) {
			dbDatabaseHelper.close();
		}
	}

}
