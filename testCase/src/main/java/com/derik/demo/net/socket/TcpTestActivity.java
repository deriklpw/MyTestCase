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

public class TcpTestActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText serverInput;
    private TextView serverReceive;
    private Button serverSend;

    private EditText clientInput;
    private TextView clientReceive;
    private Button clientSend;
    private TcpServer mTcpServer;
    private TcpClient mTcpClient;

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
        initServerTCP();
    }

    private void bindView() {
        serverInput = findViewById(R.id.et_server_input);
        serverInput.setVisibility(View.GONE);
        serverReceive = findViewById(R.id.tv_server_receive);
        serverSend = findViewById(R.id.bt_server_send);
        serverSend.setVisibility(View.GONE);
        serverSend.setOnClickListener(this);

        clientInput = findViewById(R.id.et_client_input);
        clientReceive = findViewById(R.id.tv_client_receive);
        clientSend = findViewById(R.id.bt_client_send);
        clientSend.setOnClickListener(this);
    }

    private void initServerTCP() {
        mTcpServer = TcpServer.getInstance();
        mTcpServer.setDataListener(new Consumer<String>() {
            @Override
            public void accept(String s) {
                //success
                sendMessage(CONSTANT_MSG_RECEIVED_SERVER, s);
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String s) {
                //error
                sendMessage(CONSTANT_MSG_RECEIVED_SERVER, s);
            }
        });

    }

    private void initClientTCP(String host, int ip) {
        mTcpClient = new TcpClient(host, ip);
        mTcpClient.setDataListener(new Consumer<String>() {
            @Override
            public void accept(String s) {
                sendMessage(CONSTANT_MSG_RECEIVED_CLIENT, s);
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String s) {
                sendMessage(CONSTANT_MSG_RECEIVED_CLIENT, s);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_server_send:
                clientReceive.setText("");
                break;
            case R.id.bt_client_send:
                serverReceive.setText("");
                String clientMsg = clientInput.getText().toString().trim();
                mTcpClient.sendMsg(clientMsg);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTcpServer.startServer(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                if (mTcpClient == null) {
                    initClientTCP(s, integer);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTcpServer.stopServer();
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
