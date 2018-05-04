package com.example.screenapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MenuActivity extends Activity implements OnClickListener {

	private static final String TAG = MenuActivity.class.getSimpleName();
	private Button button1;
	private TextView textView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		textView1 = (TextView) findViewById(R.id.menu);
		registerForContextMenu(textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		SubMenu fontMenu1 = menu.addSubMenu("字体大小");
		fontMenu1.setIcon(R.drawable.ic_launcher);
		fontMenu1.setHeaderIcon(R.drawable.p3);
		fontMenu1.setHeaderTitle("选择字体大小");
		fontMenu1.add(0, 0x111, 0, "10号字体").setCheckable(true);
		fontMenu1.add(0, 0x112, 0, "12号字体").setCheckable(true);
		fontMenu1.add(0, 0x113, 0, "14号字体").setCheckable(true);
		fontMenu1.add(0, 0x114, 0, "16号字体").setCheckable(true);
		fontMenu1.add(0, 0x115, 0, "18号字体").setCheckable(true);
		menu.add(0, 0, 0, "普通菜单项");
		SubMenu colorMenu = menu.addSubMenu("字体颜色");
		colorMenu.add(0, 0x116, 0, "红色").setCheckable(true);
		colorMenu.add(0, 0x117, 0, "绿色").setCheckable(true);
		colorMenu.add(0, 0x118, 0, "黑色").setCheckable(true);
		colorMenu.add(0, 0x119, 0, "紫色").setCheckable(true);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem mItem) {
		super.onOptionsItemSelected(mItem);

		mItem.setChecked(true);
		switch (mItem.getItemId()) {
		case 0x111:
			Toast.makeText(MenuActivity.this, "" + 0x111, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x112:
			Toast.makeText(MenuActivity.this, "" + 0x112, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x113:
			Toast.makeText(MenuActivity.this, "" + 0x113, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x114:
			Toast.makeText(MenuActivity.this, "" + 0x114, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x115:
			Toast.makeText(MenuActivity.this, "" + 0x115, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x116:
			Toast.makeText(MenuActivity.this, "" + 0x116, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x117:
			Toast.makeText(MenuActivity.this, "" + 0x117, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x118:
			Toast.makeText(MenuActivity.this, "" + 0x118, Toast.LENGTH_LONG)
					.show();
			break;
		case 0x119:
			Toast.makeText(MenuActivity.this, "" + 0x119, Toast.LENGTH_LONG)
					.show();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View source,
			ContextMenu.ContextMenuInfo menuInfo) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu, menu);
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.setHeaderTitle("请选择背景色");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.test1:
			textView1.setTextColor(Color.RED);
			Toast.makeText(MenuActivity.this, "" + item.getItemId(),
					Toast.LENGTH_LONG).show();
			break;
			
		case R.id.test2:
			Toast.makeText(MenuActivity.this, "" + item.getItemId(),
					Toast.LENGTH_LONG).show();
			break;
			
		case R.id.test3:
			Toast.makeText(MenuActivity.this, "" + item.getItemId(),
					Toast.LENGTH_LONG).show();
			break;
			
		case R.id.test4:
			Toast.makeText(MenuActivity.this, "" + item.getItemId(),
					Toast.LENGTH_LONG).show();
			break;
			
		case R.id.test5:
			Toast.makeText(MenuActivity.this, "" + item.getItemId(),
					Toast.LENGTH_LONG).show();
			break;
			
		default:
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			finish();
			break;
		default:
			break;

		}
	}
}
