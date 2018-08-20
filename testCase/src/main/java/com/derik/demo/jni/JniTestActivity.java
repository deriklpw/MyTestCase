package com.derik.demo.jni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class JniTestActivity extends Activity implements View.OnClickListener {

    static{
        System.loadLibrary("JniTest");
        System.loadLibrary("MyJniClass");
    }

    private native int Add(double num1,double num2);
    private native int Sub(double num1,double num2);
    private native int Mul(double num1,double num2);
    private native int Div(double num1,double num2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(Add(54,3));
        System.out.println(Sub(63,2));
        System.out.println(Mul(45,2));
        System.out.println(Div(10,2));
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(new MyJniClass().getString("你好，世界！"));
    }

    @Override
    public void onClick(View v) {

    }
}
