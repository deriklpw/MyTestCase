package com.derik.demo.views.viewpager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.derik.demo.R;

import java.util.ArrayList;

public class ViewPagerTabStripActivity extends Activity {

    private ViewPager pager = null;
    private PagerTabStrip tabStrip = null;
    private LinearLayout layoutDot = null;
    private ArrayList<View> viewContainer = new ArrayList<View>();
    private ArrayList<String> titleContainer = new ArrayList<String>();
    private Button mPreSelectedBt;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_tabstrip);

        pager = (ViewPager) findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) findViewById(R.id.tabstrip);
        layoutDot = (LinearLayout) findViewById(R.id.viewpager_layout_dot);
        tabStrip.setBackgroundColor(getResources().getColor(R.color.white));
        // 设置底线
        tabStrip.setDrawFullUnderline(false);
        // 设置文字颜色
        tabStrip.setTabIndicatorColorResource(R.color.black);
        tabStrip.setTextSpacing(100);

        View view1 = LayoutInflater.from(this).inflate(R.layout.tab1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.tab2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.tab3, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.tab4, null);

        viewContainer.add(view1);
        viewContainer.add(view2);
        viewContainer.add(view3);
        viewContainer.add(view4);

        titleContainer.add("view1");
        titleContainer.add("view2");
        titleContainer.add("view3");
        titleContainer.add("view4");

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainer.size();
            }

            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewContainer.get(position));
                Log.e("viewPager instantiate", "" + position);
                return viewContainer.get(position);
            }

            /**
             * Remove a page for the given position.  The adapter is responsible
             * for removing the view from its container, although it only must ensure
             * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
             *
             * @param container The containing View from which the page will be removed.
             * @param position The page position to be removed.
             * @param object The same object that was returned by
             * {@link #instantiateItem(View, int)}.
             */
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewContainer.get(position));
                Log.e("viewPager destroy", "" + position);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }

            // 每次滑动时执行
            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                if (mPreSelectedBt != null) {
                    mPreSelectedBt.setBackgroundResource(R.drawable.right);
                }

                Button currentBtn = (Button) layoutDot.getChildAt(position);
                currentBtn.setBackgroundResource(R.drawable.ic_launcher);
                mPreSelectedBt = currentBtn;
            }

        });

        // 首次显示的pager
        pager.setCurrentItem(1);


//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                // 将原先选中的页面变为right
//                if (mPreSelectedBt != null) {
//                    mPreSelectedBt.setBackgroundResource(R.drawable.right);
//                }
//
//                Button currentBtn = (Button) layoutDot.getChildAt(position);
//                currentBtn.setBackgroundResource(R.drawable.ic_launcher);
//                mPreSelectedBt = currentBtn;
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.right);
        for (int i = 0; i < viewContainer.size(); i++) {
            Button bt = new Button(this);
            bt.setLayoutParams(new ViewGroup.LayoutParams(bitmap.getWidth(), bitmap.getHeight()));
            bt.setBackgroundResource(R.drawable.right);
            layoutDot.addView(bt);
        }

    }

}
