package com.derik.demo.net.url;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.derik.demo.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTestActivity extends Activity {

    private EditText input;
    private Button getBtn;
    private ImageView image;
    private String urlStr = "http://pic59.nipic.com/file/20150201/20303008_132951239245_2.jpg";

    private URL url;
    private Bitmap bitmap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                image.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urltest);
        init();
    }

    private void init() {

        input = (EditText) findViewById(R.id.url_test_input);
        getBtn = (Button) findViewById(R.id.url_test_get);
        image = (ImageView) findViewById(R.id.url_test_image);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = input.getText().toString().trim();
                urlStr = (str.equals("") || str == null) ? urlStr : str;

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(urlStr);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        // 方法一
                        // 使用 Url 的openStream方法，仅可读取
//                        try {
//                            InputStream is = url.openStream();
//                            bitmap = BitmapFactory.decodeStream(is);
//                            handler.sendEmptyMessage(0x123);
//                            is.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        // 方法二
                        // 使用 Url 的openConnection方法建立通信链接，可读取，发送
                        PrintWriter out = null;
                        try{
                            URLConnection urlConnection = url.openConnection();
                            urlConnection.connect();

                            Map<String, List<String>> headers = urlConnection.getHeaderFields();
                            Log.i("headers Size",""+headers.size());
                            for(String key: headers.keySet()){
                                System.out.println("key:"+ key);
                                System.out.println("value:"+ headers.get(key));
                            }

                            Log.i("content", urlConnection.getContent().toString());

                            try{
                                InputStream is = urlConnection.getInputStream();
                                bitmap = BitmapFactory.decodeStream(is);
                                handler.sendEmptyMessage(0x123);
                                is.close();

                                // 保存到文件
//                                OutputStream out = openFileOutput("download.png", MODE_PRIVATE);
//                                byte[] buffer = new byte[1024];
//                                is = url.openStream();
//                                int hasRead;
//                                while((hasRead = is.read(buffer)) > 0){
//                                    out.write(buffer, 0 ,hasRead);
//                                }


                            } catch (IOException e){
                                e.printStackTrace();
                            }

                            // 单独取出每个Head 的Values，以String方式
//                            Collection<List<String>> headerValues = headers.values();
//                            for (List<String> list: headerValues) {
//                                for (String a : list){
//                                    Log.i("OK values", a);
//                                }
//                            }

                        }catch (IOException e){
                            e.printStackTrace();
                        }


                    }
                }.start();

            }
        });

    }
}
