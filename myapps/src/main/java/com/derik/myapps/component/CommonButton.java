package com.derik.myapps.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by derik on 17-4-14.
 */

public class CommonButton extends View {

    private final Paint paint = new Paint();

    public CommonButton(Context context) {
        super(context);
    }

    public CommonButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas){
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.5f);
        paint.setAntiAlias(true);
        paint.setTextSize(22);
        canvas.drawRoundRect(100,100,300,300,5,5,paint);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

}
