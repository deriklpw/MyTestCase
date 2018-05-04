package com.derik.demo.b_second.Async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by derik on 17-3-1.
 */

public class DownTask extends AsyncTask<URL, Integer, String> {

    private Context mContext;
    private int downloadSize;
    private ProgressDialog progressDialog;

    public DownTask(Context ctx){
        mContext = ctx;
    }

    @Override
    protected String doInBackground(URL... params) {
        try {
            URLConnection connection =  params[0].openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");

            int length = connection.getContentLength();
            Log.i("length", "" + length);
            InputStream in = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int hasRead;
            downloadSize = 0;
            File file = new File("/sdcard/Download/ES.zip");
            FileOutputStream fos = new FileOutputStream (file);

            while((hasRead = in.read(buffer))>0){
                fos.write(buffer,0, hasRead);
                downloadSize += hasRead;
                publishProgress(downloadSize);
            }

            in.close();
            fos.close();

            return "download success";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Task is running");
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(7409142);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result){

        AsyncTaskTestActivity.show.setText(result);
        progressDialog.dismiss();
    }

}
