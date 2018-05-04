package com.derik.demo.b_second.socket;

import android.util.Log;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

	public static ArrayList<Socket> socketList = new ArrayList<>();
	public static boolean isClose = false;

	public static void startServer() {
		try {
		
			ServerSocket ss = new ServerSocket(30000);
			while (true) {
				System.out.println("ready...");
				Socket s = ss.accept();
				System.out.println("connected:");
				socketList.add(s);
				Log.i("socket size",""+socketList.size());
				new Thread(new ServerThread(s)).start();

				if (isClose) {
					s.close();
					ss.close();
					break;
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
