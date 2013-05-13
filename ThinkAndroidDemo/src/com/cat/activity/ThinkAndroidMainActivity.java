package com.cat.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.ta.annotation.TAInjectView;

public class ThinkAndroidMainActivity extends ThinkAndroidBaseActivity
{
	@TAInjectView(id = R.id.test_db)
	Button testDBButton;
	@TAInjectView(id = R.id.test_cache)
	Button testCacheButton;
	@TAInjectView(id = R.id.test_image_cache)
	Button testImageCacheButton;
	@TAInjectView(id = R.id.test_mvc)
	Button testMVCCacheButton;
	@TAInjectView(id = R.id.test_http)
	Button testHTTPCacheButton;
	@TAInjectView(id = R.id.test_download)
	Button testDownloadButton;
	@TAInjectView(id = R.id.test_other)
	Button testOtherButton;
	@TAInjectView(id = R.id.exit_app)
	Button exitAppButton;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_main_title);
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
				case R.id.test_db:
					doActivity(R.string.thinkandroiddbactivtiy);
					break;
				case R.id.test_cache:
					doActivity(R.string.thinkandroidcacheactivtiy);
					break;
				case R.id.test_image_cache:
					doActivity(R.string.thinkandroidimagecacheactivtiy);
					// TANetworkStateReceiver.unRegisterNetworkStateReceiver(ThinkAndroidMainActivity.this);
					break;
				case R.id.test_mvc:
					doActivity(R.string.thinkandroidmvcactivtiy);
					// TANetworkStateReceiver.unRegisterNetworkStateReceiver(ThinkAndroidMainActivity.this);
					break;
				case R.id.test_http:
					doActivity(R.string.thinkandroidhttpactivtiy);
					// TANetworkStateReceiver.unRegisterNetworkStateReceiver(ThinkAndroidMainActivity.this);
					break;
				case R.id.test_download:
					doActivity(R.string.thinkandroiddwonloadactivtiy);
					// TANetworkStateReceiver.unRegisterNetworkStateReceiver(ThinkAndroidMainActivity.this);
					break;
				case R.id.test_other:
					doActivity(R.string.thinkandroidotheractivtiy);
					// TANetworkStateReceiver.unRegisterNetworkStateReceiver(ThinkAndroidMainActivity.this);
					break;
				case R.id.exit_app:
					// TANetworkStateReceiver.checkNetworkState(ThinkAndroidMainActivity.this);
					ThinkAndroidMainActivity.this.exitApp();
					break;
				default:
					break;
				}
			}
		};
		testDBButton.setOnClickListener(onClickListener);
		testCacheButton.setOnClickListener(onClickListener);
		testImageCacheButton.setOnClickListener(onClickListener);
		exitAppButton.setOnClickListener(onClickListener);
		testMVCCacheButton.setOnClickListener(onClickListener);
		testHTTPCacheButton.setOnClickListener(onClickListener);
		testDownloadButton.setOnClickListener(onClickListener);
		testOtherButton.setOnClickListener(onClickListener);
	}
}