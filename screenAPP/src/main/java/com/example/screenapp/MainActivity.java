package com.example.screenapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.HashMap;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;
	private Intent intent;
	private ShareDialog shareDialog;
	MainToServiceBroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(this);
		button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(this);
		button7 = (Button) findViewById(R.id.button7);
		button7.setOnClickListener(this);
		button8 = (Button) findViewById(R.id.button8);
		button8.setOnClickListener(this);
		button9 = (Button) findViewById(R.id.button9);
		button9.setOnClickListener(this);
		button10 = (Button) findViewById(R.id.button10);
		button10.setOnClickListener(this);
		
		IntentFilter filter = new IntentFilter("android.toMain");
		receiver = new MainToServiceBroadcastReceiver();
		registerReceiver(receiver, filter);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			intent = new Intent(this, MonitorService.class);
			Toast.makeText(this, "即将启动 MonitorService", Toast.LENGTH_LONG).show();
			startService(intent);
			break;
			
		case R.id.button2:
			intent = new Intent(this, SharedPreferencesConfActivity.class);
			Toast.makeText(this, "即将启动 SharedPreferencesConfActivity", Toast.LENGTH_LONG).show();
			startActivity(intent);
			break;
			
		case R.id.button3:
			intent = new Intent(this, MonitorService.class);
			Toast.makeText(this, "将停止 MonitorService", Toast.LENGTH_LONG).show();
			stopService(intent);
			break;
			
		case R.id.button4:
			intent = new Intent(this, ViewFlipperActivity.class);
			Toast.makeText(this, "即将启动 ViewFlipperActivity", Toast.LENGTH_LONG).show();
			startActivity(intent);
			break;
			
		case R.id.button5:
			intent = new Intent();
			intent.setAction("android.all");
			intent.putExtra("msg", "来自Main的第一条消息");
			Toast.makeText(this, "将发送广播 android.all", Toast.LENGTH_LONG).show();
			sendBroadcast(intent);	
			break;
			
		case R.id.button6:
			intent = new Intent(this, TestCaseActivity.class);
			Toast.makeText(this, "即将启动 TestCaseActivity", Toast.LENGTH_LONG).show();
			startActivity(intent);
			break;
			
		case R.id.button7:
			shareDialog = new ShareDialog(this);
			shareDialog.setCancelButtonOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					shareDialog.dismiss();

				}
			});
			shareDialog.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					 HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);

					switch (arg2) {
					case 0:
						sharePage();
						break;

					case 1:
						Toast.makeText(MainActivity.this, "" + arg2, Toast.LENGTH_LONG).show();
						break;
					case 2:
						Toast.makeText(MainActivity.this, "" + arg2, Toast.LENGTH_LONG).show();
						break;
					case 3:
						Toast.makeText(MainActivity.this, "" + arg2, Toast.LENGTH_LONG).show();
						break;
					case 4:
						Toast.makeText(MainActivity.this, "" + arg2, Toast.LENGTH_LONG).show();
						break;
						
					case 5:
						Toast.makeText(MainActivity.this, "" + arg2, Toast.LENGTH_LONG).show();
						break;
					default:
						break;
					}
					shareDialog.dismiss();
				}
			});
			break;
			
		case R.id.button8:
			intent = new Intent(this, MyBindActivity.class);
			Toast.makeText(this, "即将启动测试BindService的Activity", Toast.LENGTH_LONG).show();
			startActivity(intent);
			break;
		case R.id.button9:
			intent = new Intent(this, IntentServiceTest.class);
			Toast.makeText(this, "即将启动测试IntentService", Toast.LENGTH_LONG).show();
			startService(intent);
			break;
		case R.id.button10:
			intent = new Intent(MainActivity.this, IntentServiceTest.class);
			Toast.makeText(this, "即将停止IntentServiceTest", Toast.LENGTH_LONG).show();
			stopService(intent);
			
		default:
			break;

		}
	}
	
    void sharePage() {
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("text/plain");
        try {
            startActivity(Intent.createChooser(send, "Share"));
        } catch(android.content.ActivityNotFoundException ex) {
            // if no app handles it, do nothing
        }
    }

	public class MainToServiceBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(
					context,
					"MainActivity中接受的Action为：" + intent.getAction()
							+ "\n消息内容是：" + intent.getStringExtra("msg"),
					Toast.LENGTH_SHORT).show();
			// Bundle bundle = new Bundle();
			// bundle.putString("Main", "MainActivity中BroadcastReceiver存入的消息.");
			// setResultExtras(bundle);

		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}
