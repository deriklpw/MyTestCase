package com.derik.demo.b_second.socket;

import android.app.IntentService;
import android.content.Intent;

public class MyService extends IntentService{

	public MyService() {
		super("MyService");
		MyServer.isClose = false;
		System.out.println("MyService is created.");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent){
		MyServer.startServer();
	}
	
	@Override
	public void onDestroy(){
		MyServer.isClose = true;
		System.out.println("MyService is stopped.");
	}

}
