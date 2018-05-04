package com.example.screenapp;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MyBindActivity extends Activity implements OnClickListener{
	
	private Intent intent;
	private Button bindButton;
	private Button unbindButton;
	private Button getCount;
	private static final String TAG = MyBindActivity.class.getName();
	
	private BindServiceTest.Mybinder binder;
	
	private ServiceConnection conn = new ServiceConnection(){
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service){
			System.out.println("--Service Connected--");
			binder = (BindServiceTest.Mybinder) service;
		}
		@Override
		public void onServiceDisconnected(ComponentName name){
			System.out.println("--Service Disconnected--");
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bindservice);
		
		bindButton = (Button)findViewById(R.id.button1);
		bindButton.setOnClickListener(this);
		unbindButton = (Button)findViewById(R.id.button2);
		unbindButton.setOnClickListener(this);
		getCount = (Button)findViewById(R.id.button3);
		getCount.setOnClickListener(this);
		
		intent = new Intent(MyBindActivity.this, BindServiceTest.class);


	}
	
	
	public void onPause(){
		super.onPause();

	}
	
	@Override
	public void onClick(View v){
		
		switch (v.getId()) {
		case R.id.button1:

			// 显示的绑定服务
			bindService(intent, conn, Service.BIND_AUTO_CREATE);
			break;
			
		case R.id.button2:
			if (binder!=null) {
				unbindService(conn);
			}
			break;
			
		case R.id.button3:
			// 不能放在onCreate中,会引发空指针错误
			if (binder!=null) {
				Toast.makeText(this, "Service count值为" + binder.getCount(), Toast.LENGTH_SHORT).show();
				binder.getStartAgin().test();
			}else {
				Log.e(TAG, "connected failed. binder is null.");
			}

			break;

		default:
			break;
		}
		
	}
	
	
}
