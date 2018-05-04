package com.derik.demo.others;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.derik.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JavaCodeDefViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		super.setContentView(layout);
		layout.setOrientation(LinearLayout.VERTICAL);

		final TextView showText = new TextView(this);
		Button bn = new Button(this);
		Button finish = new Button(this);
		GridView listView = new GridView(this);
		MyView myView = new MyView(this, null);

		bn.setText("SimpleDateFormat");
		finish.setText("finish");
		finish.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		bn.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		listView.setNumColumns(2);
		layout.addView(showText);
		layout.addView(bn);

		String[] str = { "GirdView", "View", "Test", "2x2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				JavaCodeDefViewActivity.this, R.layout.activity_girdview_adapter,
				str);
		listView.setAdapter(adapter);
		layout.addView(listView);
		layout.addView(finish);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(JavaCodeDefViewActivity.this,
						"test " + position, Toast.LENGTH_SHORT).show();
			}
		});

		bn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日, hh时mm分ss秒");
				Date date = new Date();
				showText.setText("Hello, Android SimpleDateFormat, " + sdf.format(date));
			}
		});

		finish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

}
