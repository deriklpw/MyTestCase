package com.example.screenapp;

import java.io.File;
import java.io.FileDescriptor;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	
	private static final String TAG = VideoActivity.class.getSimpleName();
	private String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	private VideoView videoView;
	private MediaController mediaController;
	private ActionBar actionBar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_video);
		
		videoView = (VideoView)findViewById(R.id.video);
		actionBar = getActionBar();
		actionBar.hide();
//		File file = new File("sdcard/Movies/g4.mp4");
//		File file = new File(SDPATH,"Movies/g4_2.mp4");
		
		mediaController = new MediaController(VideoActivity.this);
		
//		if(R.raw.g4!=0){
			
//			Log.e("in", "OK "+file.getAbsolutePath());
//			videoView.setVideoPath(file.getAbsolutePath());
			videoView.setVideoURI(Uri.parse("android.resource://com.example.screenapp/"+R.raw.eye_execises));
			
			videoView.setMediaController(mediaController);
			//mediaController.setMediaPlayer(videoView);
			videoView.requestFocus();
			try {
				videoView.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
        	videoView.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.start();
				}
			});
			
//		}else {
//			Log.e("no", "Error");
//			Toast.makeText(this, "不存在视频", Toast.LENGTH_SHORT).show();
//		}
		
		

	}
}
