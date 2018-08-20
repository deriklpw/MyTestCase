package com.derik.demo.net.socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derik.demo.R;

public class UdpTestActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText serverInput;
    private TextView serverReceive;
    private Button serverSend;

    private EditText clientInput;
    private TextView clientReceive;
    private Button clientSend;
    //两个UdpServer，模拟可收发UDP消息的两个端
    private UdpServer mServerUDP;
    private UdpServer mClientUDP;

    private static final int CONSTANT_MSG_RECEIVED_SERVER = 0x001;
    private static final int CONSTANT_MSG_RECEIVED_CLIENT = 0x002;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONSTANT_MSG_RECEIVED_SERVER:
                    serverReceive.setText((String) msg.obj);
                    break;
                case CONSTANT_MSG_RECEIVED_CLIENT:
                    clientReceive.setText((String) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udp_tcp);
        bindView();
        initServerUDP();
        initClientUDP();
    }

    private void bindView() {
        serverInput = findViewById(R.id.et_server_input);
        serverReceive = findViewById(R.id.tv_server_receive);
        serverSend = findViewById(R.id.bt_server_send);
        serverSend.setOnClickListener(this);

        clientInput = findViewById(R.id.et_client_input);
        clientReceive = findViewById(R.id.tv_client_receive);
        clientSend = findViewById(R.id.bt_client_send);
        clientSend.setOnClickListener(this);
    }

    private void initServerUDP() {
        mServerUDP = new UdpServer("224.255.10.0", "224.255.20.0", 9898);
        mServerUDP.setDataListener(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                sendMessage(CONSTANT_MSG_RECEIVED_SERVER, s + ", " + "" +s2);
            }
        });
    }

    private void initClientUDP() {
        mClientUDP = new UdpServer("224.255.20.0", "224.255.10.0", 9898);
        mClientUDP.setDataListener(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                sendMessage(CONSTANT_MSG_RECEIVED_CLIENT, s + ", " + s2);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_server_send:
                clientReceive.setText("");
                String serverMsg = serverInput.getText().toString().trim();
                mServerUDP.sendMsg(serverMsg);
                break;
            case R.id.bt_client_send:
                serverReceive.setText("");
                String clientMsg = clientInput.getText().toString().trim();
                mClientUDP.sendMsg(clientMsg);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mServerUDP.startServer();
        mClientUDP.startServer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mServerUDP.stopServer();
        mClientUDP.stopServer();
    }

    public void sendMessage(int what, String msg) {
        if (mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.obj = msg;
            mHandler.sendMessage(message);
        }
    }
}
