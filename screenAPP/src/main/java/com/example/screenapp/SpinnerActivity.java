package com.example.screenapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SpinnerActivity extends Activity implements OnClickListener {

	private static final String TAG = SpinnerActivity.class.getSimpleName();
	private Button button1;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		spinner = (Spinner) findViewById(R.id.spinner2);

		String[] arrayStr = { "足球", "篮球", "羽毛球" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				SpinnerActivity.this, android.R.layout.simple_spinner_item,
				arrayStr);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinner.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1: // finish Button
			finish();
			break;
		default:
			break;
		}
	}
}
