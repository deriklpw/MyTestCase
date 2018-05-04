package com.example.screenapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BindServiceTest extends Service {

	private int count;
	private boolean quit;
	
	static final int NOTIFICATION_ID = 0x123;
	private static final String TAG = BindServiceTest.class.getName();

	private Mybinder binder = new Mybinder();

	public class Mybinder extends Binder {

		public int getCount() {
			return count;
		}
		
		public StartAgain getStartAgin() {

			return new StartAgain();
		}
	}

	public class StartAgain {

		public void test() {

			Toast.makeText(BindServiceTest.this, "Start test again.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		
		Log.i(TAG, "Service is binded");
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "is created");
		
		new Thread(){
			@Override
			public void run(){
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					count++;
				}
			}
		}.start();

	}

//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		Log.i(TAG, "is started");
//
//		Notification notification = new Notification(R.drawable.ic_launcher,
//				"ok ", System.currentTimeMillis());
//		Intent notificationIntent = new Intent(this,
//				SharedPreferencesConfActivity.class);
//		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//				notificationIntent, 0);
//		notification
//				.setLatestEventInfo(this, "title", "message", pendingIntent);
//		NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//		nManager.notify(NOTIFICATION_ID, notification);
//
//		return START_STICKY;
//	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "Service is unbind");

		return true;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.quit = true;
		Log.i(TAG, "Service is destroyed");

	}

}
