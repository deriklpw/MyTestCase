package com.derik.myapps.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.derik.myapps.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView userName;
    private EditText password;
    private CheckBox recordPwd;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (AutoCompleteTextView) findViewById(R.id.atv_user_login);
        password = (EditText) findViewById(R.id.et_password_login);
        recordPwd = (CheckBox) findViewById(R.id.cbx_record_pwd_login);
        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_reg_login);
        EventBus.getDefault().register(this);

    }

    public void onRegister(View v){
        startActivity(new Intent(this, RegisterActivity.class));

    }

    public void onLogin(View view){

        Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
    }

    @Subscribe
    public void onEventMainThread(MessageEvent event) {
        Log.i("onEventMainThread", "user: " + event.getMsg());
        Toast.makeText(this, "user: " + event.getMsg(), Toast.LENGTH_LONG).show();


    }

    @Subscribe
    public void onEventMyEvent(MessageEvent event){
        Log.i("onEventMyEvent", "user: " + event.getMsg());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
