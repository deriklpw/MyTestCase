package com.derik.demo.a_first.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by derik on 16-9-1.
 */
public class MyDetailFragment extends Fragment {

    private int clickNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fragment通过getArguments()函数从它所在Activity获取数据
        if (getArguments().containsKey("key")) {
            clickNum = getArguments().getInt("key");
        }

    }

    // 可以是.xml布局文件， 转换为View对象返回，或自定义View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout = new LinearLayout(getActivity());
        TextView textView = new TextView(getActivity());
        TextView num = new TextView(getActivity());

        textView.setText("您选中的选项是: ");
        num.setText("" + clickNum);
        num.setTextColor(Color.RED);

        layout.addView(textView);
        layout.addView(num);
        return layout;

    }

}
