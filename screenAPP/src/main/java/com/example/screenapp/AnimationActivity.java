package com.example.screenapp;

import java.io.File;
import java.io.FileDescriptor;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AnimationActivity extends Activity implements OnClickListener {

	private static final String TAG = AnimationActivity.class.getSimpleName();
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayer2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(this);
		button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(this);
		mediaPlayer = MediaPlayer.create(this, R.raw.m2);
		
		try {
			AssetFileDescriptor assetFileDescriptor = getAssets().openFd("m1.mp3");
			mediaPlayer2 =  new MediaPlayer();
			FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
			mediaPlayer2.setDataSource(fileDescriptor);
			mediaPlayer2.prepare();

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			finish();
			break;
		case R.id.button2:
			XmlResourceParser xrp = getResources().getXml(R.xml.string);
			try {
				StringBuilder strBuilder = new StringBuilder("");
				while (xrp.getEventType()!=XmlResourceParser.END_DOCUMENT) {
					
					if (xrp.getEventType() == XmlResourceParser.START_TAG) {
						
						String tagNameString = xrp.getName();
						if (tagNameString.equals("book1")) {
							strBuilder.append("价格：");
							strBuilder.append(xrp.getAttributeValue(0));
							strBuilder.append(" 出版日期：");
							strBuilder.append(xrp.getAttributeValue(1));
							strBuilder.append(" 作者：");
							strBuilder.append(xrp.getAttributeValue(2));
							
						}
						strBuilder.append("\n");
					}
					xrp.next();
				}
				EditText editText = (EditText)findViewById(R.id.edittext);
				editText.setText(strBuilder);
				
				TextView textView = (TextView)findViewById(R.id.textview1);
				textView.setText(strBuilder);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case R.id.button3:
			mediaPlayer.start();	
			break;
		case R.id.button4:				
			mediaPlayer2.start();
			break;
		case R.id.button5:
			mediaPlayer.pause();
			break;
		case R.id.button6:
			mediaPlayer2.pause();
			break;
		default:
			break;

		}
	}
}
