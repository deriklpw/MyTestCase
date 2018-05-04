package com.example.screenapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class SharedPreferencesConfActivity extends Activity implements OnClickListener {

	private static final String TAG = SharedPreferencesConfActivity.class.getSimpleName();
	private Button button1;
	private Button button2;
	private Button button3;
	private Button write;
	private Button read;
	private SimpleDateFormat sdf;
	
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
		write = (Button) findViewById(R.id.button4);
		write.setOnClickListener(this);
		read = (Button) findViewById(R.id.button5);
		read.setOnClickListener(this);

		preferences = getSharedPreferences("lionel", Activity.MODE_PRIVATE);
		editor = preferences.edit();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent();
			intent.putExtra("msg", "second Activity msg");
			intent.setAction("android.toService");
			sendBroadcast(intent);
			break;
		case R.id.button2:
			finish();
			break;
		case R.id.button3:
			Configuration config = getResources().getConfiguration();
			if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
			if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
			break;
		case R.id.button4:

			sdf = new SimpleDateFormat("yyyy年MM月dd日"
					+ "hh:mm:ss");
			editor.putString("time", sdf.format(new Date()));
			editor.putInt("ranNum", (int) (Math.random() * 100));
			editor.commit();

			break;
		case R.id.button5:
			String time = preferences.getString("time", null);
			int ranNum = preferences.getInt("ranNum", 0);
			String result = time == null ? "您暂时未输入数据！" : "写入时间为：" + time
					+ "\n上次生成随机数为：" + ranNum;
			Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			break;
		default:
			break;

		}
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onPause() {

		Intent intent = new Intent();
		intent.putExtra("msg", "second Acivity paused");
		intent.setAction("android.toService");
		sendBroadcast(intent);
		super.onPause();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		String screenString = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向"
				: "竖向";
		Toast.makeText(this, "系统屏幕方向改变" + "\n修改后的屏幕方向为：" + screenString, Toast.LENGTH_LONG)
				.show();
	}
}
