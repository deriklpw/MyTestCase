package com.derik.demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by derik on 17-3-13.
 */

public class ItemDividerVertical extends RecyclerView.ItemDecoration {

    private int dividerSize = 1;
    private Paint mPaint;

    public ItemDividerVertical() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
    }

    public ItemDividerVertical setDividerSize(int dividerSize) {
        this.dividerSize = dividerSize;
        return this;
    }

    public ItemDividerVertical setDividerColor(int color) {
        mPaint.setColor(color);
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = dividerSize;
        outRect.right = dividerSize;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();

        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            Logger.i("left="+view.getLeft());
            Logger.i("right="+view.getRight());
            // for left
            float left = view.getLeft() - dividerSize;
            float right = view.getLeft();
            c.drawRect(left, top, right, bottom, mPaint);
            // for right, only the last child
            if (i == childCount -1) {
                Logger.i(""+i + ", childcount=" + childCount);
                float left2 = view.getRight();
                float right2 = view.getRight() + dividerSize;
                c.drawRect(left2, top, right2, bottom, mPaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}