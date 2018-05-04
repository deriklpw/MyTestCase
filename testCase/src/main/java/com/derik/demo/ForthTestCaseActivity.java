package com.derik.demo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.derik.demo.c_third.RenameTestActivity;

public class ForthTestCaseActivity extends Activity {

    private ListView listView;
    private String[] text1;
    private String[] text2;
    private Intent intent;

//	private LayoutInflater layoutInflater ;
//	private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        initView();
    }

    protected void initView() {
        text1 = new String[]{"rename"};
        text2 = new String[]{"重命名"};

        //layoutInflater = getLayoutInflater();
        //linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.activity_listview, null);

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

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();
                        intent = new Intent (ForthTestCaseActivity.this, RenameTestActivity.class);
                        startActivity(intent);

                        break;
                    case 1:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 2:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 3:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 4:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 5:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 6:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 7:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 8:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 9:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 10:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 11:

                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 12:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 13:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 14:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 15:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

                        break;
                    case 16:
                        Toast.makeText(ForthTestCaseActivity.this, "" + position + " id:" + id, Toast.LENGTH_LONG).show();

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
            LinearLayout layout = new LinearLayout(ForthTestCaseActivity.this);
            LinearLayout layout2 = new LinearLayout(ForthTestCaseActivity.this);
            layout2.setOrientation(LinearLayout.VERTICAL);  //1
            layout.setOrientation(LinearLayout.HORIZONTAL);  //0

            ImageView image1 = new ImageView(ForthTestCaseActivity.this);
            image1.setImageResource(R.drawable.ic_launcher);
            TextView textView1 = new TextView(ForthTestCaseActivity.this);
            TextView textView2 = new TextView(ForthTestCaseActivity.this);
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
