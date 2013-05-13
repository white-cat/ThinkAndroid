package com.cat.activity;

import com.ta.TAActivity;
import com.ta.annotation.TAInjectView;
import com.ta.util.TALogger;
import com.ta.util.download.DownLoadCallback;
import com.ta.util.download.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThinkAndroidSimpleTwoDwonLoadActivtiy extends TAActivity
{
	final static String DOWNLOAD_DIR = "download";
	@TAInjectView(id = R.id.textview)
	private TextView textView;
	@TAInjectView(id = R.id.start)
	private Button startButton;
	@TAInjectView(id = R.id.stop)
	private Button stopButton;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_simple_two_download_title);
		setContentView(R.layout.simple_download);
	}

	@Override
	protected void onAfterSetContentView()
	{
		// TODO Auto-generated method stub
		super.onAfterSetContentView();
		final DownloadManager mDownloadManager = DownloadManager
				.getDownloadManager();

		OnClickListener onClickListener = new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				switch (v.getId())
				{
				case R.id.start:
					startCom();
					break;
				case R.id.stop:
					stopCom();
					break;
				default:
					break;
				}

			}

			private void startCom()
			{
				// TODO Auto-generated method stub
				mDownloadManager
						.addHandler("http://img.yingyonghui.com/apk/16457/com.rovio.angrybirdsspace.ads.1332528395706.apk");
				mDownloadManager.setDownLoadCallback(new DownLoadCallback()
				{

					@Override
					public void onLoading(String url, long totalSize,
							long currentSize, long speed)
					{
						// TODO Auto-generated method stub
						super.onLoading(url, totalSize, currentSize, speed);
						long downloadPercent = currentSize * 100 / totalSize;
						textView.setText(downloadPercent + "--------" + speed+"kbps");
						TALogger.v(ThinkAndroidSimpleTwoDwonLoadActivtiy.this,
								downloadPercent + "--------" + speed+"kbps");
					}

					@Override
					public void onSuccess(String url)
					{
						// TODO Auto-generated method stub
						super.onSuccess(url);
						textView.setText("下载成功了！");
					}

					@Override
					public void onFinish(String url)
					{
						// TODO Auto-generated method stub
						super.onFinish(url);
					}

					@Override
					public void onAdd(String url, Boolean isInterrupt)
					{
						// TODO Auto-generated method stub
						super.onAdd(url, isInterrupt);
					}

				});
			}

			private void stopCom()
			{
				// TODO Auto-generated method stub
				mDownloadManager
						.pauseHandler("http://img.yingyonghui.com/apk/16457/com.rovio.angrybirdsspace.ads.1332528395706.apk");
			}
		};
		startButton.setOnClickListener(onClickListener);
		stopButton.setOnClickListener(onClickListener);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
}