package com.derik.demo.tools;

import android.view.View;

/**
 * Created by derik on 17-3-1.
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    @Override
    public void onClick(View v){
        Long currentClickTime = System.currentTimeMillis();

        if ((currentClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME){
            lastClickTime = currentClickTime;
            OnNoDoubleClick(v);
        }

    }
    public void OnNoDoubleClick(View v){}
}
