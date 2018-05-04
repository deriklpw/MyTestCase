package com.example.screenapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AllBroadcastReceiver extends BroadcastReceiver {
	
	private static final String TAG = AllBroadcastReceiver.class.getSimpleName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(
				context,
				"AllBroadcastReceiver中接受的Action为：" + intent.getAction() + "\n消息内容是："
						+ intent.getStringExtra("msg"), Toast.LENGTH_LONG)
				.show();
//		Bundle bundle = new Bundle();
//		bundle.putString("all", "MonitorService中BroadcastReceiver存入的消息.");
//		setResultExtras(bundle);
	}
}
