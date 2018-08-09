package com.derik.demo.b_second.socket;


import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public class ServerThread implements Runnable {

    Socket socket = null;
    BufferedReader br = null;

    public ServerThread(Socket socket) throws IOException {
        // TODO Auto-generated constructor stub
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
    }

    @Override
    public void run() {
        try {
            String content;
            while ((content = readFromClient()) != null) {

                for (Iterator<Socket> it = MyServer.socketList.iterator(); it.hasNext(); ) {
                    Socket s = it.next();
                    try {
                        OutputStream os = s.getOutputStream();
                        Log.i("data from client", content);
                        os.write(("Server reply, " + content + "\n").getBytes("utf-8"));

                    } catch (SocketException e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        it.remove();
                        System.out.println(MyServer.socketList);
                    }
                }

            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private String readFromClient() {
        try {

            return br.readLine();

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            MyServer.socketList.remove(socket);
        }
        return null;
    }

}
