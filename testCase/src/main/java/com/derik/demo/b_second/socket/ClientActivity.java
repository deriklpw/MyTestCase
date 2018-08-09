package com.derik.demo.b_second.socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derik.demo.R;
import com.derik.demo.SecondTestCaseActivity;

public class ClientActivity extends Activity {

    EditText input;
    TextView show;
    Button send;
    Handler clientHandler;
    ClientThread clientThread;
    Button start;
    Button stop;
    private Intent intent;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpip);

        input = (EditText) findViewById(R.id.message_send);
        show = (TextView) findViewById(R.id.show);
        send = (Button) findViewById(R.id.send);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);


        clientHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    Log.i("data will be showed", msg.obj.toString());
                    //show.append("\n" + msg.obj.toString());
                    show.setText("get:\n" + msg.obj.toString());
                }
            }
        };

        clientThread = new ClientThread(clientHandler);
        new Thread(clientThread).start();

        start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startService(new Intent(ClientActivity.this, MyService.class));

            }
        });

        stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent = new Intent(ClientActivity.this, MyService.class);
                stopService(intent);
            }
        });



        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = input.getText().toString();
                    clientThread.revHandler.sendMessage(msg);
                    input.setText("");
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }

}
