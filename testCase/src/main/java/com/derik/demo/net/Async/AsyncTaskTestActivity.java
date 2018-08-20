package com.derik.demo.net.Async;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.derik.demo.R;
import com.derik.library.view.NoDoubleClickListener;

import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskTestActivity extends Activity {
    public static final String url1 = "http://192.168.0.100:8080/file_server/download?filename=@ES.zip";
    private static final String url2 = "http://gdown.baidu.com/data/wisegame/fc328fa3a33efe57/QQ_482.apk";
    public static TextView show;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        show = (TextView) findViewById(android.R.id.title);
        click = (Button) findViewById(android.R.id.button1);
        click.setOnClickListener(new NoDoubleClickListener(){
            @Override
            public void OnNoDoubleClick(View view){
                download();
            }
        });
    }

    public void download(){

        DownTask downTask = new DownTask(this);
        try {
            downTask.execute(new URL(url2));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
