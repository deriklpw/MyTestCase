package com.derik.demo.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by derik on 16-12-14.
 */

public final class MsgToast {
    private static Toast mToast = null;

    public static void show(Context ctx, String msg){
        mToast = (mToast == null) ? Toast.makeText(ctx, msg, Toast.LENGTH_LONG) : mToast;
        mToast.setText(msg);
        mToast.show();
    }

}
