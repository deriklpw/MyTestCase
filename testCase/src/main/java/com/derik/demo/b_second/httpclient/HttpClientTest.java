/*
package com.derik.demo.b_second.httpclient;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.derik.demo.R;

public class HttpClientTest extends Activity {

	private EditText inputUrl;
	private EditText savefile;
	private Button getBtn;
	private Button postBtn;
	private TextView response;
	private HttpClient httpClient;
	private String urlStr = "http://pic59.nipic.com/file/20150201/20303008_132951239245_2.jpg";
	private ImageView image;
	private String saveStr;
	private Bitmap bitmap;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
//				response.append(msg.obj.toString() + "\n");
				image.setImageBitmap(bitmap);
			}
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_httpclient);
		httpClient = new DefaultHttpClient();
		response = (TextView) findViewById(R.id.http_client_text);
		getBtn = (Button) findViewById(R.id.http_client_get);
		postBtn = (Button) findViewById(R.id.http_client_post);
		inputUrl = (EditText) findViewById(R.id.http_client_url);
		savefile = (EditText) findViewById(R.id.http_client_target);
		image = (ImageView) findViewById(R.id.http_client_image);

		getBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String str = inputUrl.getText().toString().trim();
				urlStr = (str.equals("") || str == null) ? urlStr : str;
				accessSecret();

			}
		});

		postBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String str = inputUrl.getText().toString().trim();
				urlStr = (str.equals("") || str == null) ? urlStr : str;

				showLogin();
			}

		});
	}

	public void accessSecret() {
		response.setText("");
		new Thread() {
			@Override
			public void run() {

				HttpGet get = new HttpGet(urlStr);
				try {
					HttpResponse httpResponse = httpClient.execute(get);
					HttpEntity entity = httpResponse.getEntity();

					if (entity != null) {

						Log.i("entity", "not null");
						bitmap = BitmapFactory.decodeStream(entity.getContent());
						handler.sendEmptyMessage(0x123);

//						BufferedReader br = new BufferedReader(
//								new InputStreamReader(entity.getContent()));
//						String line;
//						while ((line = br.readLine()) != null) {
//							Message msg = Message.obtain();
//							msg.what = 0x123;
//							msg.obj = line;
//							handler.sendMessage(msg);
//						}

					}else {
						Log.e("entity", "null");
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void showLogin() {
		final View loginDialog = getLayoutInflater().inflate(
				R.layout.activity_logindialog, null);
		new AlertDialog.Builder(this).setTitle("登陆系统").setView(loginDialog)
				.setPositiveButton("登陆", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final String name = ((EditText) loginDialog
								.findViewById(R.id.name)).getText().toString()
								.trim();
						final String pass = ((EditText) loginDialog
								.findViewById(R.id.password)).getText()
								.toString().trim();
						new Thread() {
							@Override
							public void run() {
								try {
									HttpPost post = new HttpPost(urlStr);
									List<NameValuePair> params = new ArrayList<>();
									params.add(new BasicNameValuePair("name",
											name));
									params.add(new BasicNameValuePair(
											"password", pass));

									post.setEntity(new UrlEncodedFormEntity(
											params, HTTP.UTF_8));
									HttpResponse response = httpClient
											.execute(post);

									if (response.getStatusLine()
											.getStatusCode() == 200) {
										String msg = EntityUtils
												.toString(response.getEntity());
										Looper.prepare();
										Toast.makeText(HttpClientTest.this,
												msg, Toast.LENGTH_SHORT).show();
										Looper.loop();

									}

								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
						}.start();
					}
				}).setNegativeButton("取消", null).show();
	}

}
*/
