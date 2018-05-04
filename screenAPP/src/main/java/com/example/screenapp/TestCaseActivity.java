package com.example.screenapp;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestCaseActivity extends Activity {

	private static final String TAG = TestCaseActivity.class.getSimpleName();
	static final int NOTIFICATION_ID = 0x123;
	private ListView listView;
	String[] text1;
	String[] text2;
	private Intent intent;
	private NotificationManager nm;
	private PendingIntent pi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);

		// String[] str =
		// {"《飘》","《钢铁是怎样练成的》","《其实你不懂货币战争》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》","《偷影子的人》"};
		//
		// ArrayAdapter<String> adapter1=new
		// ArrayAdapter<String>(this,R.layout.array_item,str);
		// listView = (ListView)findViewById(R.id.listview1);
		// listView.setAdapter(adapter1);
		initView();
		afreshData();
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	protected void initView() {

		text1 = new String[] { "Spinner", "ProgressBar", "Toast", "Toast",
				"Notification", "Alert", "Menu", "icareu","canvas","fragment","xml raw assets","VideoPlayer","Property Animation","PackageManager","ContentProvider" };
		text2 = new String[] { "spinner用法示例", "ProgressBar用法示例", "普通Toast",
				"自定义Toast", "消息通知示例", "AlertDialog示例", "Menu示例", "icare图形图像","圆形进度条","fragment使用示例","xml raw assets使用示例","视频播放示例","属性动画示例","应用检测示例","ContentProvider示例" };
	}

	public void afreshData() {

		ListViewAdapter lvaAdapter = new ListViewAdapter();
		listView = (ListView) findViewById(R.id.listview1);
		listView.setAdapter(lvaAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			Toast toast;

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("clicked" + position + " id:" + id);
				switch (position) {
				case 0:
					intent = new Intent(TestCaseActivity.this,
							SpinnerActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(TestCaseActivity.this,
							ProgressBarActivity.class);
					startActivity(intent);
					break;
				case 2:
					toast = Toast.makeText(TestCaseActivity.this, "" + position
							+ " id:" + id, Toast.LENGTH_LONG);
					toast.show();
					break;
				case 3:
					toast = new Toast(TestCaseActivity.this);
					toast.setGravity(Gravity.BOTTOM, 0, 0);
					//定义新的布局
					LinearLayout toastLayout = new LinearLayout(
							TestCaseActivity.this);
					toastLayout.setOrientation(0);
					ImageView image = new ImageView(TestCaseActivity.this);
					image.setImageResource(R.drawable.ic_launcher);
					TextView textView = new TextView(TestCaseActivity.this);
					textView.setText("自定义图片提示消息");
					toastLayout.addView(image);
					toastLayout.addView(textView);
					toast.setView(toastLayout);
					toast.show();
					break;
				case 4:
					intent = new Intent(TestCaseActivity.this,
							SpinnerActivity.class);
					pi = PendingIntent.getActivity(TestCaseActivity.this, 0,
							intent, 0);

					Notification notify = new Notification.Builder(
							TestCaseActivity.this)
							.setDefaults(Notification.DEFAULT_ALL)
							.setAutoCancel(true).setTicker("有新消息")
							.setSmallIcon(R.drawable.ic_launcher)
							.setContentTitle("一条新通知")
							.setContentText("恭喜你，加薪了！")
							.setWhen(System.currentTimeMillis())
							.setContentIntent(pi).build();
					nm.notify(NOTIFICATION_ID, notify);
					break;
				case 5:
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							TestCaseActivity.this);
					
					// 可以添加自己的view布局
					dialog.setTitle("AlertDialog 应用示例");
					dialog.setIcon(R.drawable.ic_launcher);
					dialog.setMessage("dialog对话框的内容区域\n更多详情，请参考。。。");
					dialog.create().show();
					break;
				case 6:
					intent = new Intent(TestCaseActivity.this, MenuActivity.class);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(TestCaseActivity.this, IcareUActivity.class);
					startActivity(intent);
					break;
				case 8:
					intent = new Intent(TestCaseActivity.this, CircleActivity.class);
					startActivity(intent);
					break;
				case 9:
					intent = new Intent(TestCaseActivity.this, com.example.fragment.BookActivity.class);
					startActivity(intent);
					break;
				case 10:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;
				case 11:
					intent = new Intent(TestCaseActivity.this, VideoActivity.class);
					startActivity(intent);
					break;
				case 12:
					intent = new Intent(TestCaseActivity.this, AnimatorActivity.class);
					startActivity(intent);
					break;
						
				case 13:
					Toast.makeText(TestCaseActivity.this, ""+isAvilible(TestCaseActivity.this, "com.tencent.mm"), Toast.LENGTH_SHORT).show();
//					if (isAvilible(FifthActivity.this, "com.tencent.mm")) {
//						Intent i = new Intent();
////						ComponentName componentName =new ComponentName("com.mob.sharesdkdemo", "com.mob.sharesdkdemo.wxapi.WXEntryActivity");
//						i.setClassName("com.fcl.icareu", "com.fcl.icareu.SettingsActivity");
////						i.setComponent(componentName);
//						startActivity(i);
//					}
					break;
				case 14:
					intent = new Intent(TestCaseActivity.this, MyContentResolver.class);
					startActivity(intent);
					break;
				case 15:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;
				case 16:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;
				case 17:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;
				case 18:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;
				case 19:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;
				case 20:
					intent = new Intent(TestCaseActivity.this, AnimationActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});

		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("selected" + position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				System.out.println("nothing");
			}
		});

	}

	public class ListViewAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LinearLayout layout = new LinearLayout(TestCaseActivity.this);
			LinearLayout layout2 = new LinearLayout(TestCaseActivity.this);
			layout2.setOrientation(1);
			layout.setOrientation(0);
			ImageView image1 = new ImageView(TestCaseActivity.this);
			image1.setImageResource(R.drawable.ic_launcher);
			TextView textview1 = new TextView(TestCaseActivity.this);
			TextView textview2 = new TextView(TestCaseActivity.this);
			textview1.setText(text1[position]);
			textview2.setText(text2[position]);
			textview1.setTextSize(20);
			textview2.setTextSize(20);
			textview1.setTextColor(Color.RED);
			textview2.setTextColor(Color.BLUE);
			layout.addView(image1);
			layout2.addView(textview1);
			layout2.addView(textview2);
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
	
	public boolean isAvilible(Context context, String packageName){
		PackageManager packagemagemnet = context.getPackageManager();
		List<PackageInfo> pInfos = packagemagemnet.getInstalledPackages(0);
		Iterator<PackageInfo> iterator;
		for (iterator = pInfos.iterator();iterator.hasNext();) {
			
			if (iterator.next().packageName.equalsIgnoreCase(packageName)) {
				return true;
			}
		}
		
		return false;
	}

}
