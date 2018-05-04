package com.derik.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.derik.demo.tools.MsgToast;

public class MainActivity extends Activity implements OnClickListener {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Intent target;
    private ToggleButton actionBarEnable;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.two);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.four);
        four.setOnClickListener(this);
        five = (Button) findViewById(R.id.five);
        five.setOnClickListener(this);
        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(this);
        actionBarEnable = (ToggleButton) findViewById(R.id.main_action_bar_toggle);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBarEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    actionBar.show();
                } else {
                    actionBar.hide();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.one:
                target = new Intent(MainActivity.this, FirstTestCaseActivity.class);
                startActivityForResult(target, 128);
                break;
            case R.id.two:
                target = new Intent(MainActivity.this, SecondTestCaseActivity.class);
                startActivity(target);
                break;
            case R.id.three:
                target = new Intent(MainActivity.this, ThirdTestCaseActivity.class);
                startActivity(target);
                break;
            case R.id.four:
                target = new Intent(MainActivity.this, ForthTestCaseActivity.class);
                startActivity(target);
                break;
            case R.id.five:
                MsgToast.show(this, "" + v.getId());
                break;
            case R.id.six:
                MsgToast.show(this, "" + v.getId());
                break;
            case R.id.seven:
                MsgToast.show(this, "" + v.getId());
                break;

            default:
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == resultCode) {
            MsgToast.show(this, "" + resultCode);
            Log.i("ActivityResult", "" + resultCode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return  true;
    }

}
