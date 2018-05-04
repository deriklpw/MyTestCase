package com.example.screenapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MonitorService extends Service {

	
	private static final String TAG = MonitorService.class.getSimpleName();
	private IntentFilter filter;
	ServiceToMainBroadcastReceiver receiver;

	// 创建非绑定的Service,无需实现,返回null即可
	@Override
	public IBinder onBind(Intent intent) {

		Log.i(TAG, "is binded");
		return null;
	}
	
	@Override
	public void onCreate() {
		
		super.onCreate();
		Log.i(TAG, "is created");
		filter = new IntentFilter("android.toService");
		receiver = new ServiceToMainBroadcastReceiver();
		registerReceiver(receiver, filter);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Log.i(TAG, "is started");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		
		Log.i(TAG, "is destroyed");
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	// 创建广播接收器,服务中接受到广播后,启动ActivityChecker线程
	public class ServiceToMainBroadcastReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(
					context,
					"MonitorService中接受的Action为：" + intent.getAction()
							+ "\n消息内容是：" + intent.getStringExtra("msg"),
					Toast.LENGTH_SHORT).show();
			new ActivityChecker().start();

		}
	}
	
	class ActivityChecker extends Thread{
		@Override
		public void run(){
			Log.i(TAG, "start check");
			while(true){
				ActivityManager am = (ActivityManager)MonitorService.this.getSystemService(Activity.ACTIVITY_SERVICE);
				ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
				String toActivityName = cn.getClassName();
				if (!toActivityName.equals(SharedPreferencesConfActivity.class.getName())){
					startAgain();
				}else{
					Log.i(TAG, "check finished");
					break;
				}
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void startAgain() {
		
		Intent intent = new Intent(this, SharedPreferencesConfActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

}
