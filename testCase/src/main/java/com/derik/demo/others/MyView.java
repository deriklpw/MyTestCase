package com.derik.demo.others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
	
	public class MyView extends View{

	public MyView(Context context, AttributeSet set){
		super(context, set);

	}
	
	@SuppressLint("DrawAllocation") 
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawColor(Color.YELLOW);
		Paint paint = new Paint();
		paint.setAntiAlias(false);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
//		canvas.drawCircle(100.0f, 100.0f, 80.0f, paint);
		
		Path path = new Path();
		int viewWidth = getWidth();
		Log.e("adf", ""+viewWidth);
		path.moveTo(50, 50);
		path.lineTo(150, 150);
		path.lineTo(50, 100);
		path.close();
		canvas.drawPath(path, paint);
		
	}

}
