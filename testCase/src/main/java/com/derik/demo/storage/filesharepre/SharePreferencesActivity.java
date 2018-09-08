package com.derik.demo.storage.filesharepre;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.derik.demo.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SharePreferencesActivity extends Activity {
    private static final String TAG = "SharePreferencesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        funcRawFileRead(R.raw.test1);
        funcSharedPreferences("SharePreferences测试内容");
    }

    private void funcRawFileRead(int resId) {
        InputStream in = getResources().openRawResource(resId);
        // InputStreamReader 可将字节流构造成字符流
        InputStreamReader inReader = new InputStreamReader(in);
        StringBuilder data = new StringBuilder("");
        int hasRead;
        char[] buff = new char[2048];
        try {
            while ((hasRead = inReader.read(buff)) > 0) {
                data.append(new String(buff, 0, hasRead));
            }
            Log.i("funcRawFileRead", data.toString());
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private void funcSharedPreferences(String content) {
        write(content);
        read();
    }

    private void write(String content) {
        // 定义SharedPreference 以map形式存储在xml文件中
        SharedPreferences sharedPreferences = getSharedPreferences("ShareXML1",
                MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("on_config", true);
        edit.putString("content_text", content);
        edit.commit();
    }

    private void read() {
        SharedPreferences sharedPreferences = getSharedPreferences("ShareXML1",
                MODE_PRIVATE);
        Toast.makeText(this, "读取xml信息：on=" + sharedPreferences.getBoolean("on_config", false), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "读取xml信息：content=" + sharedPreferences.getString("content_text", "nothing"),
                Toast.LENGTH_SHORT).show();
    }
}
