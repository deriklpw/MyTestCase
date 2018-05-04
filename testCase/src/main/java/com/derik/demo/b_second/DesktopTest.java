package com.derik.demo.b_second;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.derik.demo.MainActivity;
import com.derik.demo.R;

/**
 * Created by derik on 16-8-27.
 */
public class DesktopTest extends Activity {

    private Intent intent;
    private LinearLayout layout;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        layout = new LinearLayout(this);
        setContentView(layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button click = new Button(this);
        click.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT));
        click.setText("click");

        layout.addView(click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                Parcelable icon = Intent.ShortcutIconResource.fromContext(DesktopTest.this, R.drawable.ic_launcher);

                // 这里只能启动Launcher的Activity
                Intent myIntent = new Intent(DesktopTest.this, MainActivity.class);

                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Liping");
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
                sendBroadcast(intent);
                Log.i("DesktopTest", "clicked");
            }
        });


    }
}
