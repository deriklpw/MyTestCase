package com.media.mediaapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {

	public Handler handler1;
	@SuppressLint("HandlerLeak")
	@Override
	public void run(){
		super.run();
		Looper.prepare();
		handler1 =new Handler(){
			public void handleMessage(Message msg){
				Log.i("Looper", String.valueOf(msg.what));
			}
		};
		Message m = handler1.obtainMessage();
		m.what=0x11;
		handler1.sendMessage(m);
		Looper.loop();
	}
	
}
