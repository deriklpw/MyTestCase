package com.derik.demo.views.serialparcel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.derik.demo.R;
import com.fcl.mylibrary.UserInfo;

public class SerialParcelActivity extends Activity {

    private TextView serialResult;
    private TextView parcelResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_parcel);

        serialResult = (TextView) findViewById(R.id.serial_result);
        parcelResult = (TextView) findViewById(R.id.parcel_result);

        Intent intent = getIntent();
        SerializableTest serializableTest = (SerializableTest)intent.getSerializableExtra("serial");
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Id: ");
        strBuilder.append(serializableTest.getId());
        strBuilder.append(", Name: ");
        strBuilder.append(serializableTest.getName());
        strBuilder.append(", Password: ");
        strBuilder.append(serializableTest.getPassword());
        serialResult.setText(strBuilder);

        Parcelable parcelable = intent.getParcelableExtra("parcel");
        if (parcelable instanceof ParcelableTest) {
            ParcelableTest parcelableTest = (ParcelableTest) parcelable;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Id: ");
            stringBuilder.append(parcelableTest.getId());
            stringBuilder.append(", Name: ");
            stringBuilder.append(parcelableTest.getName());
            stringBuilder.append(", Password: ");
            stringBuilder.append(parcelableTest.getPassword());
            parcelResult.setText(stringBuilder);
        }

        Parcelable user = intent.getParcelableExtra("user");
        if (user instanceof UserInfo) {
            UserInfo userInfo = (UserInfo) user;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UserName: ");
            stringBuilder.append(userInfo.getUserName());
            stringBuilder.append(", Age: ");
            stringBuilder.append(userInfo.getAge());
            stringBuilder.append(", Birthday: ");
            stringBuilder.append(userInfo.getBirthday());
            parcelResult.setText(stringBuilder);
        }

    }
}
