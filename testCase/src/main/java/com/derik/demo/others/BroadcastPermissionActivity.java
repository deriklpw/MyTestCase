package com.derik.demo.others;

import com.derik.demo.Manifest.permission;
import com.derik.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BroadcastPermissionActivity extends Activity implements OnClickListener{
	
	private Button boardcast_no_permisson;
	private Button boardcast_with_permisson;
	private Button finish;
	private Intent intent;
	MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardcast_permission);
        
        boardcast_no_permisson = (Button)findViewById(R.id.boardcast_no_permisson);
        boardcast_no_permisson.setOnClickListener(this);
        boardcast_with_permisson = (Button)findViewById(R.id.boardcast_with_permisson);
        boardcast_with_permisson.setOnClickListener(this);
        finish = (Button)findViewById(R.id.finish);
        finish.setOnClickListener(this);
        
        myView = (MyView)findViewById(R.id.myview);
        myView.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	switch(v.getId()){
    	    case R.id.boardcast_no_permisson:
    	    	intent = new Intent("android.all");
    	    	intent.putExtra("msg", "来自TestCase不带发送权限的广播");
    	    	Toast.makeText(this, "广播权限使用示例,广播至ScreenAPP,不带权限", Toast.LENGTH_LONG).show();
    	    	sendBroadcast(intent);
    	    	break;
    	    case R.id.boardcast_with_permisson:
    	    	intent = new Intent("android.all");
    	    	intent.putExtra("msg", "来自TestCase带发送权限的广播:" + permission.receiver);
    	    	Toast.makeText(this, "广播权限使用示例,广播至ScreenAPP,带权限\"com.android.mypermission.receiver\",接受方需\"<uses-permission/>\"声明使用此权限", Toast.LENGTH_LONG).show();
    	    	sendBroadcast(intent, permission.receiver);
    	    	break;
    	    case R.id.finish:
    	    	finish();
    	    	break;
    	    	
    	    case R.id.myview:
    	    	Toast.makeText(this, "自定义View", Toast.LENGTH_SHORT).show();
    	    	break;
    	    default:
    	    	break;
    	}
    }
   
}
