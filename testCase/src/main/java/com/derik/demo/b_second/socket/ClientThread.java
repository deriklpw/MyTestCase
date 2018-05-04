package com.derik.demo.b_second.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ClientThread implements Runnable{

	private Socket s;
	private Handler handler;
	public Handler revHandler;
	BufferedReader br = null;
	private final String ip = "192.168.0.102";
	private final int port = 30000;
	
	OutputStream os = null;
	public ClientThread(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
		
	}

	@Override
	public void run(){
		try {
//			InetAddress ipAddress;
//			ipAddress = InetAddress.getLocalHost();
//			//System.out.println(ipAddress.getHostAddress());
//			s = new Socket(ipAddress, 30000);
			s = new Socket(ip, port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			os = s.getOutputStream();
			
			new Thread(){
				@Override
				public void run(){
					String content;
					try {
						while ((content = br.readLine())!=null) {
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = content;
							Log.i("from Service", content);
							handler.sendMessage(msg);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}.start();
			
			Looper.prepare();
			revHandler = new Handler(){
				
				@Override
				public void handleMessage(Message msg){
					if (msg.what == 0x345) {
						try {

							Log.i("data will be sent", msg.obj.toString());
							os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			};
			Looper.loop();
			
			
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			System.out.println("网络链接超时");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
