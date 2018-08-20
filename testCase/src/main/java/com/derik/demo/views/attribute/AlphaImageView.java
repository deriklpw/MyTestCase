package com.derik.demo.views.attribute;

import java.util.Timer;
import java.util.TimerTask;

import com.derik.demo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class AlphaImageView extends ImageView{
	private int alphaDelta = 0;
	private int curAlpha = 0;
	private final int SPEED = 1000;
	private int duration;
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == 0x123) {
				curAlpha += alphaDelta;
				if (curAlpha >255) {
					curAlpha = 255;
				}
				AlphaImageView.this.setAlpha(curAlpha);
			}
		}
	};
	
	public AlphaImageView(Context ctx, AttributeSet set){
		super(ctx, set);
		TypedArray typedArray = ctx.obtainStyledAttributes(set, R.styleable.AlphaImageView);

		duration = typedArray.getInt(R.styleable.AlphaImageView_duration, 0 );
		alphaDelta = 255 * SPEED / duration;
		int attributeCount = set.getAttributeCount();
		Log.i("test123", "当前属性个数为："+attributeCount+":"+set.getAttributeValue(4));
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		this.setImageAlpha(curAlpha);
		super.onDraw(canvas);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.what = 0x123;
				if(curAlpha >= 255){
					timer.cancel();
				}else {
					handler.sendMessage(msg);
				}
			}
		}, 0, SPEED);
	}
}
