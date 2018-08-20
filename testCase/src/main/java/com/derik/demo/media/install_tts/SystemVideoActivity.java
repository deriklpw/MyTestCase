package com.derik.demo.media.install_tts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.derik.demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class SystemVideoActivity extends Activity {

    private LayoutInflater inflater;
    private Thread thread;
    private TextToSpeech tts;
    private EditText editText;
    private String apkUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        //使用代码的方式添加控件
        inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_system_video, null);

        Button videoBtn = new Button(this);
        Button ttsBtn = new Button(this);
        Button autoInstallBtn = new Button(this);
        Button unInstallBtn = new Button(this);
        editText = new EditText(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(videoBtn, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(ttsBtn, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(autoInstallBtn, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(unInstallBtn, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(layout);

        videoBtn.setText("PlayVideo");
        ttsBtn.setText("TTS");
        autoInstallBtn.setText("AutoInstall");
        unInstallBtn.setText("UnInstall");

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_VIEW);
                String str = editText.getText().toString().trim();
                String uri =  "android.resource://" + getPackageName() + "/" + R.raw.video;
                it.setDataAndType(Uri.parse(uri), "video/mp4");
                startActivity(it);
            }
        });

        ttsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString().trim();
                tts.speak((str.equals("") || str == null ? "please input your words." : str), TextToSpeech.QUEUE_FLUSH, null, "utteranceld");
            }
        });

        autoInstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开启新线程进行安装
                thread.start();
            }
        });

        unInstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString().trim();
                unInstall(str.equals("") || str == null ? "com.media.mediaapp" : str);
                //tts.speak("uninstall finished.", TextToSpeech.QUEUE_FLUSH, null, "utteranceld");
            }
        });

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result != TextToSpeech.LANG_AVAILABLE && result != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                        Toast.makeText(SystemVideoActivity.this, "No support", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                downAndInstall();
            }
        });
    }


    public void downAndInstall() {
        // 下载apk，传入链接
        //downLoadFile("http://gdown.baidu.com/data/wisegame/dc429998555b7d4d/QQ_398.apk");
        // 自动安装

        AutoInstall autoInstall = new AutoInstall();
        String apkStr = editText.getText().toString().trim();
        apkUri = "/sdcard/" + (apkStr.equals("") || apkStr == null ? "mediaAPP-release.apk" : apkStr);
        if (!apkUri.endsWith(".apk")) {
            apkUri = apkUri + ".apk";
        }
        autoInstall.setUrl(apkUri);
        //tts.speak("installing now, please wait.", TextToSpeech.QUEUE_FLUSH, null, "utteranceld");
        autoInstall.install(SystemVideoActivity.this);
    }

    public void unInstall(String packageName) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DELETE");
//        <action android:name="android.intent.action.DELETE" />
//        <action android:name="android.intent.action.UNINSTALL_PACKAGE" />
//        <category android:name="android.intent.category.DEFAULT" />
//        <data android:scheme="package" />
        Uri uri = Uri.parse("package:" + packageName);
        intent.setData(uri);
        startActivity(intent);
    }

    protected File downLoadFile(String httpUrl) {
        final String fileName = "QQ_398.apk";
        // 创建文件路径
        File temFile = new File("/sdcard/update");
        // 如果文件不存在。就创建
        if (!temFile.exists()) {
            temFile.mkdir();
        }
        // 得到文件路径
        final File file = new File("/sdcard/update/" + fileName);
        try {
            // 实例化URL对象,指定文件下载的地址
            URL url = new URL(httpUrl);
            // 获取HttpURLConnection 对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 得到字节读取流对象
            InputStream is = conn.getInputStream();
            // 实例化文件写入流，将下载的文件写入sd卡
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[256];
            conn.connect();
            double count = 0;
            // 网络请求失败（getsponseCode==200时，请求成功）
            if (conn.getResponseCode() >= 400) {
                Toast.makeText(SystemVideoActivity.this, "连接超时",
                        Toast.LENGTH_SHORT).show();
            } else {
                while (count <= 100) {
                    if (is != null) {
                        int numRead = is.read(buf);
                        if (numRead <= 0) {
                            break;
                        } else {
                            Log.i("downloading:", count++ + "%");
                            fos.write(buf, 0, numRead);
                        }
                    } else {
                        break;
                    }
                }
            }
            conn.disconnect();
            // 关闭流
            fos.close();
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }

    public class AutoInstall {

        private String mUrl;
        private Context mContext;

        /**
         * 外部传进来的url以便定位需要安装的apk
         *
         * @param url
         */

        public void setUrl(String url) {
            mUrl = url;
        }

        /**
         * 安装
         *
         * @param context 接收外部传进来的context
         */
        public void install(Context context) {
            mContext = context;
            // 核心是下面几句代码
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(mUrl)),
                    "application/vnd.android.package-archive");
            mContext.startActivity(intent);
            Log.i("installed:", "OK");
        }
    }
}
