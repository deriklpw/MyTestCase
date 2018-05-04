package com.example.screenapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class IntentServiceTest extends IntentService {

	
	private static final String TAG = IntentServiceTest.class.getSimpleName();
	
	// 必须有构造函数
	public IntentServiceTest(){
		super("IntentServiceTest");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "onHandleIntent");
		Toast.makeText(IntentServiceTest.this, "IntentService onHandleIntent 处理数据", Toast.LENGTH_SHORT).show();
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		super.onBind(intent);
		Log.i(TAG, "is bind");
		return null;
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "is created");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.i(TAG, "is started");
		return START_STICKY;
		
	}
	
	@Override
    public boolean onUnbind(Intent intent) {
    	super.onUnbind(intent);
    	Log.i(TAG, "onUnbind");
  
        return true;
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "is destroyed");

	}

}
