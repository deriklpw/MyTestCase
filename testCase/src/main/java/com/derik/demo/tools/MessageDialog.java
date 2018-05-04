package com.derik.demo.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derik.demo.R;

/**
 * Created by derik on 16-10-13.
 */
public class MessageDialog {

    private static AlertDialog dialog;
    private LayoutInflater layoutInflater;
    //    private Button continueBtn;
    private static TextView textView;
    private static LinearLayout layout;
    private static MessageDialog messageDialog;

    public MessageDialog(Context context) {
        dialog = new AlertDialog.Builder(context).create();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) layoutInflater.inflate(R.layout.message_dialog, null);
        textView = (TextView) layout.findViewById(R.id.dialog_image);


    }

    public static void show(Context context, String msg) {

        if (messageDialog == null){
            messageDialog = new MessageDialog(context);
        }

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        textView.setText(msg);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout);

    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setOnDismissListener(View.OnClickListener listener) {
        textView.setOnClickListener(listener);

    }

}