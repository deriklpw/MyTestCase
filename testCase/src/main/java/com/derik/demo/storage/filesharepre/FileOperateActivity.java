package com.derik.demo.storage.filesharepre;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.derik.demo.R;
import com.derik.library.view.MsgToast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileOperateActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "FileOperateActivity";
    private static final String SDCARD = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    private Bitmap bitmap;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_operate);
        initViews();
        // 获取bitmap
        bitmap = (Bitmap) getIntent().getParcelableExtra("bitmapScreenshot");
    }

    private void initViews() {
        Button internal_files = findViewById(R.id.internal_files);
        Button internal_cache = findViewById(R.id.internal_cache);
        Button internal_app_dir = findViewById(R.id.internal_app_dir);
        Button external_sdcard = findViewById(R.id.external_sdcard);
        Button external_public = findViewById(R.id.external_public);
        Button external_private = findViewById(R.id.external_private);
        content = findViewById(R.id.content);
        showDirs();
        internal_files.setOnClickListener(this);
        internal_cache.setOnClickListener(this);
        internal_app_dir.setOnClickListener(this);
        external_sdcard.setOnClickListener(this);
        external_public.setOnClickListener(this);
        external_private.setOnClickListener(this);
    }

    private void showDirs() {
        StringBuilder buff = new StringBuilder();

        buff.append("Environment.getRootDirectory(): " + Environment.getRootDirectory().getAbsolutePath() + "\n");
        buff.append("Environment.getDataDirectory(): " + Environment.getDataDirectory().getAbsolutePath() + "\n");
        buff.append("Environment.getDownloadCacheDirectory(): " + Environment.getDownloadCacheDirectory().getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStorageDirectory(): " + Environment.getExternalStorageDirectory().getAbsolutePath() + "\n\n");

        //internal
        buff.append("getFilesDir(): " + getFilesDir().getAbsolutePath() + "\n");
        buff.append("getCacheDir(): " + getCacheDir() + "\n");
        buff.append("getDir(\"getDir\", MODE_PRIVATE): " + getDir("getDir", MODE_PRIVATE).getAbsolutePath() + "\n");
        buff.append("getDatabasePath(\"database\"): " + getDatabasePath("db").getAbsolutePath() + "\n\n");

        //外部SD卡Android目录下，私有
        //external private
        buff.append("getExternalFilesDir(\"aa\"): " + getExternalFilesDir("aa") + "\n"); //private
        buff.append("getExternalFilesDir(\"\"): " + getExternalFilesDir("") + "\n"); //private
        buff.append("getExternalCacheDir(): " + getExternalCacheDir() + "\n"); //private
        buff.append("getExternalFilesDir(String type): " + getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "\n"); //private
        buff.append("getObbDir(): " + getObbDir() + "\n\n");

        //外部SD卡根目录下，共有
        //external public
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "\n");
        buff.append("Environment.getExternalStoragePublicDirectory(): "
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "\n");

        Log.d(TAG, "showDirs: " + buff.toString());
        content.setText(buff.toString());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String internalPath;
        switch (id) {
            case R.id.internal_files:
                // files目录
                internalPath = getFilesDir().getAbsolutePath();

                funcInternalFile(internalPath + "/internalDir/temp", "internal_files.txt",
                        "files目录,内部存储数据测试内容");

                MsgToast.show(this, "files目录,内部存储数据测试内容");

                break;
            case R.id.internal_cache:
                // cache目录
                internalPath = getCacheDir().getAbsolutePath();

                funcInternalFile(internalPath + "/internalDir/temp", "internal_cache.txt",
                        "cache目录,内部存储数据测试内容");

                MsgToast.show(this, "cache目录,内部存储数据测试内容");

                getTempFile(this, "/mnt/sdcard/test/test");

                break;
            case R.id.internal_app_dir:
                // app_dir目录
                internalPath = getDir("dir", MODE_PRIVATE).getAbsolutePath();

                funcInternalFile(internalPath, "internal_app_dir.txt",
                        "app_dir,内部存储数据测试内容");

                MsgToast.show(this, "app_dir,内部存储数据测试内容");

                break;
            case R.id.external_sdcard:
                funcExternalStorage(SDCARD + "/externalDir/temp", "external_file.txt",
                        "SDCARD,外部存储数据测试内容");

                MsgToast.show(this, "SDCARD,外部存储数据测试内容");

                if (bitmap == null) {
                    Toast.makeText(this, "null", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "获取到Bitmap信息，已存储", Toast.LENGTH_LONG).show();
                    funcSaveExternalBitmap(SDCARD + "/externalBitmap/temp", "picture.jpg", bitmap);
                }

                break;
            case R.id.external_public:
                //应用卸载，数据依然保留在Sd卡上
                getAlbumStorageDirPublic("Ladygaga");
                MsgToast.show(this, "SDCARD, public");
                break;
            case R.id.external_private:
                //应用卸载，数据将从Sd卡上删除
                getAlbumStorageDirPrivate(this, "Lionel");
                MsgToast.show(this, "SDCARD, private");
                break;
            default:
                break;
        }
    }


    private File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }

    private void funcInternalFile(String path, String fileName, String content) {

        // // openFileOutPut使用

        // try {
        // // 使用FileOutputStream输出至文件, openFileOutput 对应内部files目录
        // FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
        // fos.write(content.getBytes());
        // fos.close();
        // Toast.makeText(this, getFileStreamPath(fileName).toString(),
        // Toast.LENGTH_LONG).show();
        //
        // } catch (Exception e) {
        // // TODO: handle exception
        // e.printStackTrace();
        // }

        //可以建立多层目录
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Log.i("internalPath", dir.getAbsolutePath());

        // 建立目录后检测或建立文件
        File file;
        file = new File(dir.getAbsolutePath(), fileName);

        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    Log.i("File", "create success.");
                } else {
                    Log.e("File", "create failed.");
                }
            } else {
                Log.i("File", "is already exist");
            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        Log.i("internalFilePath", file.getAbsolutePath());

        BufferedWriter writer;
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            try {
                writer.write(content);
                writer.flush();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    private void funcExternalStorage(String path, String fileName,
                                     String content) {

        // 外部存储, 先判断外部存储是否可用
        if (isExternalStorageWritable()) {

            // 传入SD卡的路径
            File fileDir = new File(path);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            Log.i("externalPath", fileDir.getAbsolutePath());

            File targetFile = new File(fileDir.getAbsolutePath() + "/"
                    + fileName);
            try {

                if (!targetFile.exists()) {
                    if (targetFile.createNewFile()) {
                        Log.i("File", "create success");
                    } else {
                        Log.e("File", "create failed");
                    }
                } else {
                    Log.i("File", "is already exist");
                }

            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            Log.i("externalFilePath", targetFile.getAbsolutePath());

            try {

                // 使用OutputStream
                FileOutputStream out = new FileOutputStream(targetFile);
                try {
                    out.write(content.getBytes("utf-8"));
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 使用RandomAccessFile
//				RandomAccessFile rafAccessFile = new RandomAccessFile(
//						targetFile, "rw");
//				try {
//					rafAccessFile.seek(targetFile.length()); //实现文件尾追加内容
//					rafAccessFile.write(content.getBytes("utf-8"));
//					rafAccessFile.close();
//				} catch (IOException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}

            } catch (FileNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public String funcSaveExternalBitmap(String path, String name, Bitmap screenShot) {

        if (isExternalStorageWritable()) {

            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir.getAbsolutePath(), name);
            try {
                if (file.exists()) {
                    file.delete();
                    file.createNewFile();
                } else {
                    file.createNewFile();
                }

            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            FileOutputStream fout = null;

            try {
                fout = new FileOutputStream(file);

            } catch (FileNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            screenShot.compress(Bitmap.CompressFormat.JPEG, 100, fout);

            try {
                fout.flush();
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("path", file.getAbsolutePath());
            return file.getAbsolutePath();
        }
        return null;
    }

    /**
     * @param albumName
     * @return
     */
    public File getAlbumStorageDirPublic(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    public File getAlbumStorageDirPrivate(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

}
