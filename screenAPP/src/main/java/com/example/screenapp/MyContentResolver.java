package com.example.screenapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyContentResolver extends Activity {

    ContentResolver contentResolver;

    EditText wordEditText;
    EditText detailEditText;
    EditText inputId;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contentResolver = getContentResolver();

        //代码定义布局界面
        initView();
    }


    private void initView() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        wordEditText = new EditText(this);
        detailEditText = new EditText(this);
        inputId = new EditText(this);
        wordEditText.setHint("word");
        detailEditText.setHint("detail");
        inputId.setHint("operate id");
        inputId.setInputType(InputType.TYPE_CLASS_NUMBER);

        listView = new ListView(this);
        listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        Button reset = new Button(this);
        reset.setText("reset");
        reset.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        Button query = new Button(this);
        query.setText("query");
        query.setGravity(Gravity.CENTER);
        query.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        final Button insert = new Button(this);
        insert.setText("insert");
        insert.setGravity(Gravity.CENTER);
        insert.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        Button update = new Button(this);
        update.setText("update");
        update.setGravity(Gravity.CENTER);
        update.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        Button delete = new Button(this);
        delete.setText("delete");
        delete.setGravity(Gravity.CENTER);
        delete.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        layout.addView(wordEditText);
        layout.addView(detailEditText);
        layout.addView(inputId);
        layout.addView(reset);
        layout.addView(insert);
        layout.addView(query);
        layout.addView(update);
        layout.addView(delete);
        layout.addView(listView);
        setContentView(layout);


        reset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                wordEditText.setText("");
                detailEditText.setText("");
                inputId.setText("");
            }
        });

        query.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    query();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    update();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        insert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    insert();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void insert() throws IllegalAccessException {
        String word = wordEditText.getText().toString();
        String detail = detailEditText.getText().toString();

        ContentValues values = new ContentValues();

        if (!word.toString().trim().equals("") && !detail.toString().trim().equals("")) {
            values.put(Words.Word.WORD, word);
            values.put(Words.Word.DETAIL, detail);
        }


        // Uri：一般针对words，即整个数据库而言
        // 键值对：对应于要插入的字段和值
        Uri newUri = contentResolver.insert(Words.Word.DICT_CONTENT_URI, values);

        Toast.makeText(this, "远程ContentProvider新记录数的uri为: " + newUri, Toast.LENGTH_SHORT).show();


        String s = newUri != null ? ContentUris.parseId(newUri) + "" : "";
        Toast.makeText(this, "添加生词成功! ID:" + s, Toast.LENGTH_SHORT).show();
    }

    public void query() throws IllegalAccessException {
        String key = wordEditText.getText().toString().trim();
        String detailString = detailEditText.getText().toString().trim();
        String id = inputId.getText().toString().trim();
        //操作整个记录，具体执行的业务逻辑，Provider实现
//	    Cursor cursor = contentResolver.query(Words.Word.DICT_CONTENT_URI, null, "word like ? or detail like ?", new String[]{"%"+key+"%", "%"+detailString+"%"}, null);
        //操作指定Id的记录，具体的业务逻辑，Provider实现
        Cursor cursor = contentResolver.query(!id.equals("") ? ContentUris.withAppendedId(Words.Word.WORD_CONTENT_URI, Integer.parseInt(id)) : Words.Word.DICT_CONTENT_URI, null, null, null, null);
        inflateList(cursor);

    }

    public void update() throws IllegalAccessException {
        ContentValues values = new ContentValues();
        values.put(Words.Word.DETAIL, "Hello World");
        String id = inputId.getText().toString().trim();

        int count = contentResolver.update(!id.equals("") ? ContentUris.withAppendedId(Words.Word.WORD_CONTENT_URI, Integer.parseInt(id)) : Words.Word.DICT_CONTENT_URI, values, null, null);
        Toast.makeText(this, "远程ContentProvider更新记录数为: " + count, Toast.LENGTH_SHORT).show();
    }

    public void delete() throws IllegalAccessException {

        String id = inputId.getText().toString().trim();

        int count = contentResolver.delete(!id.equals("") ? ContentUris.withAppendedId(Words.Word.WORD_CONTENT_URI, Integer.parseInt(id)) : Words.Word.DICT_CONTENT_URI, null, null);
        Toast.makeText(this, "远程ContentProvider删除记录数为: " + count, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(com.example.screenapp.Words.Word.WORD, cursor.getString(1));
            map.put(com.example.screenapp.Words.Word.DETAIL, cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private void inflateList(Cursor cursor) {

        while (cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                System.out.println("name:" + cursor.getColumnName(i) + ",value:" + cursor.getString(i));
            }
        }

        listView.setAdapter(null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.line, cursor,
                new String[]{Words.Word._ID, Words.Word.WORD, Words.Word.DETAIL}, new int[]{R.id.my_id, R.id.my_title, R.id.my_content},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);

    }

}
