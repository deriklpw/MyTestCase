package com.derik.demo.b_second;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.derik.demo.R;

public class WebViewTest extends Activity implements OnClickListener{
	
	EditText urlEditText;
	WebView webView;
	Button clickButton;
	private String urlStr = "http://www.hao123.com";
	
	@SuppressLint("SetJavaScriptEnabled") 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		urlEditText = (EditText)findViewById(R.id.url);
		webView = (WebView)findViewById(R.id.webview1);
		webView.getSettings().setJavaScriptEnabled(true);

		
		clickButton = (Button)findViewById(R.id.click);
		clickButton.setOnClickListener(this);

//		webView.setWebViewClient(new WebViewClient() {
//			// 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return true;
//			}
//			// 重写此方法可以让webview处理https请求
//			@Override
//			public void onReceivedSslError(WebView view,
//										   SslErrorHandler handler, android.net.http.SslError error) {
//				handler.proceed();
//			}
//		});
	
	}
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event){
//		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
//			String urlStr = urlEditText.getText().toString().trim();
//			showView.loadUrl(urlStr);
//			return true;
//		}
//		return false;
//	}
	
	
	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.click:

			String str = urlEditText.getText().toString().trim();
			urlStr = (str.equals("") || str == null) ? urlStr : str;
			System.out.println(urlStr);

			webView.loadUrl(urlStr);


			break;

		default:
			break;
		}
		
	}
}
