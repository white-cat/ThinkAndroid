package com.cat.activity;

import com.ta.TASyncHttpClient;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThinkAndroidHttpActivtiy extends ThinkAndroidBaseActivity
{
	@TAInjectView(id = R.id.syn_get_button)
	Button synGetButton;
	@TAInjectView(id = R.id.asyn_get_button)
	Button asynGetButton;
	@TAInjectView(id = R.id.syn_post_button)
	Button synPostButton;
	@TAInjectView(id = R.id.asyn_post_button)
	Button asynPostButton;
	@TAInjectView(id = R.id.show_return)
	WebView showWebView;
	@TAInjectView(id = R.id.clear_button)
	Button clearButton;
	@TAInject
	private TASyncHttpClient syncHttpClient;
	@TAInject
	private AsyncHttpClient asyncHttpClient;
	private static final String THINKANDROIDURL = "http://www.thinkandroid.cn";

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_http_title);
	}

	@Override
	protected void onAfterSetContentView()
	{
		// TODO Auto-generated method stub
		super.onAfterSetContentView();
		OnClickListener onClickListener = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				switch (v.getId())
				{
				case R.id.syn_get_button:
					synGet();
					break;

				case R.id.asyn_get_button:
					asynGet();
					break;
				case R.id.syn_post_button:
					synPost();
					break;
				case R.id.asyn_post_button:
					asynPost();
					break;
				case R.id.clear_button:
					showWebView("");
					break;
				default:
					break;
				}
			}
		};

		synGetButton.setOnClickListener(onClickListener);

		asynGetButton.setOnClickListener(onClickListener);

		synPostButton.setOnClickListener(onClickListener);

		asynPostButton.setOnClickListener(onClickListener);

		clearButton.setOnClickListener(onClickListener);
	}

	private void synGet()
	{
		String content = syncHttpClient.get(THINKANDROIDURL);
		showWebView(content);
	}

	private void asynGet()
	{
		asyncHttpClient.get(THINKANDROIDURL, new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(String content)
			{
				// TODO Auto-generated method stub
				super.onSuccess(content);
				showWebView(content);
			}
		});

	}

	private void synPost()
	{
		RequestParams params = new RequestParams();
		params.put("username", "white_cat");
		params.put("password", "123456");
		params.put("email", "2640017581@qq.com");
		// params.put("profile_picture", new File("/mnt/sdcard/testpic.jpg"));
		// // 上传文件
		// params.put("profile_picture2", inputStream); // 上传数据流
		// params.put("profile_picture3", new ByteArrayInputStream(bytes)); //
		// 提交字节流
		makeToast("本测试参数并未真正的传送，大家根据需要写参数");
		String content = syncHttpClient.post("http://www.thinkandroid.cn/");
		showWebView(content);
	}

	private void asynPost()
	{
		RequestParams params = new RequestParams();
		params.put("username", "white_cat");
		params.put("password", "123456");
		params.put("email", "2640017581@qq.com");
		// params.put("profile_picture", new File("/mnt/sdcard/testpic.jpg"));
		// // 上传文件
		// params.put("profile_picture2", inputStream); // 上传数据流
		// params.put("profile_picture3", new ByteArrayInputStream(bytes)); //
		// 提交字节流
		makeToast("本测试参数并未真正的传送，大家根据需要写参数");
		asyncHttpClient.post(THINKANDROIDURL, new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(String content)
			{
				// TODO Auto-generated method stub
				super.onSuccess(content);
				showWebView(content);
			}
		});
	}

	private void showWebView(String content)
	{
		showWebView.getSettings().setDefaultTextEncodingName("utf-8");
		showWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8",
				null);
	}

	private void makeToast(String content)
	{
		Toast.makeText(this, content, Toast.LENGTH_LONG).show();
	}
}