package com.derik.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.derik.demo.b_second.Async.AsyncTaskTestActivity;
import com.derik.demo.c_third.MetaDataTestActivity;
import com.derik.demo.c_third.graph.GraphTestActivity;
import com.derik.demo.c_third.jnitest.JniTestActivity;
import com.derik.demo.c_third.regular.RegularTestActivity;
import com.derik.demo.c_third.resource.MenuTestActivity;
import com.derik.demo.c_third.resource.ResourceTestActivity;
import com.derik.demo.c_third.sensor.SensorTestActivity;
import com.derik.demo.c_third.serialparcel.ParcelableTest;
import com.derik.demo.c_third.RenameTestActivity;
import com.derik.demo.c_third.serialparcel.SerialParcelActivity;
import com.derik.demo.c_third.serialparcel.SerializableTest;
import com.derik.demo.c_third.component.AutoCompleteTextViewTest;
import com.derik.demo.c_third.viewpager.TabbedTestActivity;
import com.derik.demo.c_third.viewpager.TabHostActivity;
import com.derik.demo.c_third.viewpager.ViewPagerTabStripActivity;
import com.derik.demo.tools.MsgToast;
import com.derik.demo.tools.PrefUtils;
import com.derik.demo.tools.SDCardTest;
import com.fcl.mylibrary.UserInfo;

public class ThirdTestCaseActivity extends Activity {

    private ListView listView;
    private String[] text1;
    private String[] text2;
    private Intent intent;

//	private LayoutInflater layoutInflater ;
//	private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initView();

    }

    protected void initView() {
        text1 = new String[]{"rename", "AutoCompleteTextView", "meta-data", "Serializable & Parcelable",
                "Regular Expression", "ViewPager TabStrip", "ViewPager Frag", "TabHost",
                "11111", "SensorManager", "JNI", "Resource", "Menu", "graph",
                "AsyncTask"
        };
        text2 = new String[]{"重命名", "提示编辑框", "meta-data测试", "对象序列化传输示例",
                "正则表达式示例", "ViewPager and PagerTabStrip", "ViewPager and Fragment", "TabHost",
                "11111", "传感器", "JNI测试", "Xml", "optionMenu, contextMenu", "graph anim",
                "AsyncTask"
        };

        //layoutInflater = getLayoutInflater();
        //linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.activity_listview, null);
        //listView = (ListView)linearLayout.findViewById(R.id.listview1);

        listView = (ListView) findViewById(R.id.listview1);
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("clicked" + position + " id:" + id);
                switch (position) {
                    case 0:
                        intent = new Intent (ThirdTestCaseActivity.this, RenameTestActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent (ThirdTestCaseActivity.this, AutoCompleteTextViewTest.class);
                        startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent (ThirdTestCaseActivity.this, MetaDataTestActivity.class);
                        startActivity(intent);

                        break;
                    case 3:
                        intent = new Intent (ThirdTestCaseActivity.this, SerialParcelActivity.class);
                        SerializableTest serializableTest = new SerializableTest();
                        serializableTest.setId(3);
                        serializableTest.setName("derik");
                        serializableTest.setPassword("123456");

                        ParcelableTest parcelableTest = new ParcelableTest();
                        parcelableTest.setId(5);
                        parcelableTest.setName("Lionel");
                        parcelableTest.setPassword("qazwsx");

                        //使用第三方库中的UserInfo类
                        UserInfo user = new UserInfo();
                        user.setId("1");
                        user.setUserName("Derik");
                        user.setPassword("123456");
                        user.setSex("Man");
                        user.setAge(29);
                        user.setBirthday("0106");

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("serial", serializableTest);
                        bundle.putParcelable("parcel", parcelableTest);
                        bundle.putParcelable("user", user);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case 4:
                        intent = new Intent(ThirdTestCaseActivity.this, RegularTestActivity.class);
                        startActivity(intent);

                        break;
                    case 5:
                        intent = new Intent(ThirdTestCaseActivity.this, ViewPagerTabStripActivity.class);
                        startActivity(intent);

                        break;
                    case 6:
                        intent = new Intent(ThirdTestCaseActivity.this, TabbedTestActivity.class);
                        startActivity(intent);

                        break;
                    case 7:
                        intent = new Intent(ThirdTestCaseActivity.this, TabHostActivity.class);
                        startActivity(intent);

                        break;
                    case 8:
                        MsgToast.show(ThirdTestCaseActivity.this, position + " id:" + id);

                        break;
                    case 9:
                        intent = new Intent(ThirdTestCaseActivity.this, SensorTestActivity.class);
                        startActivity(intent);

                        break;
                    case 10:
                        intent = new Intent(ThirdTestCaseActivity.this, JniTestActivity.class);
                        startActivity(intent);

                        break;
                    case 11:
                        intent = new Intent(ThirdTestCaseActivity.this, ResourceTestActivity.class);
                        startActivity(intent);

                        break;
                    case 12:
                        intent = new Intent(ThirdTestCaseActivity.this, MenuTestActivity.class);
                        startActivity(intent);

                        break;
                    case 13:
                        intent = new Intent(ThirdTestCaseActivity.this, GraphTestActivity.class);
                        startActivity(intent);

                        break;
                    case 14:
                        intent = new Intent(ThirdTestCaseActivity.this, AsyncTaskTestActivity.class);
                        startActivity(intent);

                        break;
                    case 15:
                        MsgToast.show(ThirdTestCaseActivity.this, position + " id:" + id);

                        break;
                    case 16:
                        MsgToast.show(ThirdTestCaseActivity.this, position + " id:" + id);

                        break;
                    default:
                        break;
                }
            }
        });

//		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				System.out.println("selected" + position);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				System.out.println("nothing");
//			}
//		});
    }

    public class ListViewAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            LinearLayout layout = new LinearLayout(ThirdTestCaseActivity.this);
            LinearLayout layout2 = new LinearLayout(ThirdTestCaseActivity.this);
            layout2.setOrientation(LinearLayout.VERTICAL);  //1
            layout.setOrientation(LinearLayout.HORIZONTAL);  //0

            ImageView image1 = new ImageView(ThirdTestCaseActivity.this);
            image1.setImageResource(R.drawable.ic_launcher);
            TextView textView1 = new TextView(ThirdTestCaseActivity.this);
            TextView textView2 = new TextView(ThirdTestCaseActivity.this);
            textView1.setText(text1[position]);
            textView2.setText(text2[position]);
            textView1.setTextSize(20);
            textView2.setTextSize(20);
            textView1.setTextColor(Color.RED);
            textView2.setTextColor(Color.BLUE);
            layout.addView(image1);
            layout2.addView(textView1);
            layout2.addView(textView2);
            layout.addView(layout2);
            return layout;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return text1.length;
        }
    }

}
