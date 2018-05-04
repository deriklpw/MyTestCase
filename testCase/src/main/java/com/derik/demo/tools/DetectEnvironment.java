package com.derik.demo.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by derik on 17-2-15.
 */

public class DetectEnvironment {

    /**
     * 功能：检测url是否接收请求
     * 描述：先检测网络是否可用，再和指定的url建立连接，依据不同结果发送消息给主线程处理
     * @param ctx 上下文
     * @param url 指定url
     * @param handler 主线程handler
     */
    public static void detect(Context ctx, String url, Handler handler) {

        if (isNetworkAvailable(ctx)) {
            connect(handler, url);

        } else {
            MsgToast.show(ctx, "请检查网络是否连接");

        }

    }

    //判断网络是否可用
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    //连接url
    private synchronized static void connect(final Handler handler, final String urlStr) {

        if (handler == null || urlStr == null || urlStr.equals("")) {
            return;
        }

        new Thread() {
            int state = -1;
            int counts = 0;
            HttpURLConnection httpUrlConnection;

            @Override
            public void run() {

                while (counts < 3) {
                    try {
                        URL url = new URL(urlStr);
                        httpUrlConnection = (HttpURLConnection) url.openConnection();
                        httpUrlConnection.setConnectTimeout(3 * 1000);
                        state = httpUrlConnection.getResponseCode();

                        if (state == 200) {
                            Message msg = Message.obtain();
                            msg.what = 0x111;
                            msg.obj = urlStr;
                            handler.sendMessage(msg);
                            break;
                        }

                    } catch (Exception ex) {
                        counts++;
                        Log.e("urlTest", "URL不可用, 连接第" + counts + "次");

                    } finally {
                        Log.i("state", "" + state);
                        httpUrlConnection.disconnect();
                    }

                    if (state != 200) {
                        handler.sendEmptyMessage(0x222);
                    }

                }
            }

        }.start();
    }

}
