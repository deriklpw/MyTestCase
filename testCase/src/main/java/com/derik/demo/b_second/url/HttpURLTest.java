package com.derik.demo.b_second.url;

import java.util.Timer;
import java.util.TimerTask;

import com.derik.demo.R;
import com.derik.demo.b_second.url.util.DownUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HttpURLTest extends Activity {

    private String url;
    private static String urlDefault = "http://gdown.baidu.com/data/wisegame/fc328fa3a33efe57/QQ_482.apk";
    private EditText urlEdit;
    private String path;
    private static String pathDefault = "sdcard/Download/default.apk";
    private EditText targetPathEdit;
    private Button downBn;
    private ProgressBar bar;
    private DownUtil downUtil;
    private int mDownStatus;
    private TextView showPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //机台屏幕常亮
        setContentView(R.layout.activity_httpurlconnection_test);
        urlEdit = (EditText) findViewById(R.id.url);
        targetPathEdit = (EditText) findViewById(R.id.target);
        downBn = (Button) findViewById(R.id.down);
        bar = (ProgressBar) findViewById(R.id.bar);

        showPro = (TextView) findViewById(android.R.id.text1);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    bar.setProgress(mDownStatus);
                    showPro.setText(mDownStatus+"%");
                }
            }
        };

        downBn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                url = urlEdit.getText().toString().trim();
                if(url.equals("") || url == null){
                    url = urlDefault;
                }

                path = targetPathEdit.getText().toString().trim();
                if(path.equals("") || path == null){
                    path = pathDefault;
                }
                downUtil = new DownUtil(url, path, 3);

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("out");
                            downUtil.download();
                            System.out.println("out2");
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }

                        // 更新进度/秒
                        final Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                double completeRate = downUtil.getCompleteRate();
                                System.out.println("out3: " + completeRate);
                                mDownStatus = (int) (completeRate * 100);
                                handler.sendEmptyMessage(0x123);
                                if (mDownStatus > 100) {
                                    timer.cancel();
                                    downBn.setEnabled(true);
                                }
                            }
                        }, 0, 100);

                    }
                }.start();
                downBn.setEnabled(false);
            }
        });

    }

}
