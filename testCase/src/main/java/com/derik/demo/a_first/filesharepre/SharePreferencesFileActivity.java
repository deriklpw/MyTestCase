package com.derik.demo.a_first.filesharepre;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.derik.demo.R;

public class SharePreferencesFileActivity extends Activity {

	private static final String SDCARD = Environment
			.getExternalStorageDirectory().getAbsolutePath();
	private String internalPath;
	private File internalPathFile;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		funcRawFileRead(R.raw.test1);

		internalPath = getFilesDir().getAbsolutePath(); // files目录
		//internalPath = getCacheDir().getAbsolutePath(); // cache目录
		//internalPathFile = getDir("getDIR", MODE_PRIVATE); // 包名目录下新建app_getDIR目录, 返回File类型

		funcInternalFile(internalPath + "/internalDir/temp", "internal.txt",
				"内部存储数据测试内容");

		funcExternalStorage(SDCARD + "/externalDir1/temp", "external_file.txt",
				"外部存储数据测试内容");
		funcSharedPreferences("SharePreferences测试内容");

		// 获取bitmap
		bitmap = (Bitmap)getIntent().getParcelableExtra("bitmapScreenshot");
		if(bitmap == null) {
			Toast.makeText(this, "null", Toast.LENGTH_LONG).show();
		}

		funcSaveExternalBitmap(SDCARD + "/externalDir2/temp","picture.jpg", bitmap);
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
				}else{
					Log.e("File", "create failed.");
				}
			}else {
				Log.i("File","is already exist");
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
				out.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void funcExternalStorage(String path, String fileName,
			String content) {

		// 外部存储, 先判断外部存储是否可用
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

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
						Log.i("File","create success");
					}else{
						Log.e("File","create failed");
					}
				}else{
					Log.i("File","is already exist");
				}

			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Log.i("externalFilePath", targetFile.getAbsolutePath());

			try {

				// 使用OutputStream
				FileOutputStream out = new FileOutputStream(targetFile);
				try{
					out.write(content.getBytes("utf-8"));
					out.close();
				}catch (IOException e){
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

	public String funcSaveExternalBitmap(String path, String name, Bitmap screenShot){

		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File file = new File(dir.getAbsolutePath(), name);
			try {
				if (file.exists()) {
					file.delete();
					file.createNewFile();
				}else {
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
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Log.i("path", file.getAbsolutePath());
			return file.getAbsolutePath();
		}

		return null;
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
		edit.putBoolean("on", true);
		edit.putString("string", content);
		edit.commit();
	}

	private void read() {
		SharedPreferences sharedPreferences = getSharedPreferences("ShareXML1",
				MODE_PRIVATE);
		Toast.makeText(this, sharedPreferences.getBoolean("on", false) + "",
				Toast.LENGTH_SHORT).show();
		Toast.makeText(this, sharedPreferences.getString("string", "nothing"),
				Toast.LENGTH_SHORT).show();
	}
}
