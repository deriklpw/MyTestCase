package com.derik.demo.views.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.derik.demo.R;

/**
 * Created by derik on 16-9-1.
 */
public class FragmentTestMain extends FragmentActivity {

    private FragmentManager fragmentManager;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);
        click = (Button) findViewById(R.id.click);
        fragmentManager = getFragmentManager();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 动态替换, 只能新建实例，且不能含有layout.xml的fragment
                MyListFragment2 myListFragment2 = new MyListFragment2();

                fragmentManager.beginTransaction().replace(R.id.fragment3, myListFragment2).commit();

            }
        });
    }

    // Activity 中的fragment通过此回调接口和Activity通信
    // 此处的通信逻辑为，ListFragment通过此接口传递Id给Activity（Activity实现的自定义接口），Activity实现接口中获取到此Id的内容，实例化MyDetailFragment
    // 这个Fragment，并将实例化后带相应数据的fragment替换显示出来
//    @Override
//    public void onItemSelected(int id) {
//
//        Bundle data = new Bundle();
//        data.putInt("key", id);
//        MyDetailFragment myDetailFragment = new MyDetailFragment();
//
//        // Activity中通过在其中的fragment的setArguments()方法传递数据给fragment
//        // setArguments()和getArguments均是Fragment对象的方法
//        myDetailFragment.setArguments(data);
//
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        transaction.replace(R.id.fragment3, myDetailFragment);
//        transaction.addToBackStack("Test");
//        transaction.commit();
//    }
}
