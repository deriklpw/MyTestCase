package com.derik.demo.c_third.graph.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by derik on 17-1-23.
 */

public class DrawArc extends View {

    public DrawArc(Context context) {
        super(context);
    }

    public DrawArc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //绘制坐标轴
        drawCoordinateSystem(canvas, paint);
        drawTextTest(canvas, paint);
    }

    /**
     * 绘制坐标轴
     * @param canvas
     * @param paint
     */
    private void drawCoordinateSystem(Canvas canvas, Paint paint){
        canvas.drawLine(50, 50, 50, 600, paint);
        canvas.drawLine(50, 600, 600, 600, paint);
    }

    /**
     *
     */

    private void drawTextTest(Canvas canvas, Paint paint){
        paint.setTextSize(30);
        paint.setStrokeWidth(1);
        Path path = new Path();
        //移动到指定Point
        path.moveTo(100, 500);
        // 画贝塞尔曲线
        path.quadTo(300, 100, 600, 500);
        // 画直线
//        path.lineTo(200,350);
        path.close();
        // 画出path
        canvas.drawPath(path, paint);
        // 沿着path写字
        canvas.drawTextOnPath("你是谁呢？一千零一个夜晚", path, 10.2f, 20.2f, paint);

        // 在指定Point写字
        canvas.drawText("你好", 100, 200, paint);

    }

    private void controlView(){
        Matrix matrix = new Matrix();
        matrix.reset();
    }

}
