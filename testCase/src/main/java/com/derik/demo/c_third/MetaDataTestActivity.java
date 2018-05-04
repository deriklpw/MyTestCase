package com.derik.demo.c_third;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;

import com.derik.demo.R;
import com.derik.demo.b_second.AIDLService;

public class MetaDataTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_data_test);
        try{
            ApplicationInfo pi = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            int resId_pi = pi.metaData.getInt("com.derik.demo.meta_data_app");

              // Service中的应用方法
            ComponentName componentNameService = new ComponentName(this, AIDLService.class);
            ServiceInfo si = this.getPackageManager().getServiceInfo(componentNameService , PackageManager.GET_META_DATA);
            int resId_si = si.metaData.getInt("com.derik.demo.meta_data_service");

              // Receiver中的应用方法
//            ComponentName componentNameReceiver = new ComponentName(this, "Receiver.class");
//            ServiceInfo ri = this.getPackageManager().getServiceInfo(componentNameReceiver , PackageManager.GET_META_DATA);

            ActivityInfo ai = this.getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            int resId_ai = ai.metaData.getInt("com.derik.demo.meta_data_activity");

            AlertDialog dialog = new AlertDialog.Builder(MetaDataTestActivity.this)
                    .setIcon(R.drawable.ic_launcher)
                    .setTitle(R.string.app_name)
                    .setMessage(
                            resId_pi + ": " + getString(resId_pi)
                            + "\n"
                            + resId_ai + ": " + getString(resId_ai)
                                    + "\n"
                                    + resId_si + ": " +getString(resId_si))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).create();
            dialog.show();

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

    }
}
