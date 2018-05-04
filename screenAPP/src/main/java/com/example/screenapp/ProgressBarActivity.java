package com.example.screenapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class ProgressBarActivity extends Activity implements OnClickListener {

	private static final String TAG = ProgressBarActivity.class.getSimpleName();
	private Button button1;
	private Button button2;
	private ProgressBar bar1;
	private ProgressBar bar2;
	private ProgressBar bar3;
	private ProgressBar bar4;
	private ImageView image;
	private ClipDrawable clipDrawable;
	private int status;
	private int hasData;
	private int[] data = new int[100];
	private myHandler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//状态栏进度条，必须在设置View之前
		requestWindowFeature(Window.FEATURE_PROGRESS);
		Log.i(TAG, "created");
		setContentView(R.layout.activity_progressbar);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		bar1 = (ProgressBar) findViewById(R.id.progressbar1);
		bar2 = (ProgressBar) findViewById(R.id.progressbar2);
		bar3 = (ProgressBar) findViewById(R.id.progressbar3);
		bar4 = (ProgressBar) findViewById(R.id.progressbar4);

		//setProgressBarVisibility(true);


		image = (ImageView) findViewById(R.id.image);
		clipDrawable = (ClipDrawable) image.getDrawable();
		new myThread().start();
		mHandler = new myHandler();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1: // finish Button
			finish();
			break;
		case R.id.button2:
			
			break;
		default:
			break;
		}
	}
	
	@SuppressLint("HandlerLeak")
	public class myHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x11) {
				bar1.setProgress(status);
				bar2.setProgress(status);
				bar3.setProgress(status);
				bar4.setProgress(status);
				setProgress(90 * status);
				clipDrawable.setLevel(status*100);

			}
		}
	}

	public class myThread extends Thread {
		@Override
		public void run() {
			while (status < 100) {
				status = doWork();
				mHandler.sendEmptyMessage(0x11);
			}
		}
	}

	public int doWork() {
		data[hasData++] = (int) (Math.random() * 100);
		new Random().nextInt(10);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hasData;
	}
}
