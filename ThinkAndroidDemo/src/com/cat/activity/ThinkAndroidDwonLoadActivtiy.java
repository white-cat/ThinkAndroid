package com.cat.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ta.TAActivity;
import com.ta.annotation.TAInjectView;

public class ThinkAndroidDwonLoadActivtiy extends TAActivity
{
	@TAInjectView(id = R.id.test_simple_down)
	Button testSimpleDownButton;
	@TAInjectView(id = R.id.test_simple_down_two)
	Button testSimpleTwoDownButton;
	@TAInjectView(id = R.id.test_multi_thread_down)
	Button testMultiThreadDownButton;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_download_title);
		setContentView(R.layout.thinkandroiddownload);
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
				case R.id.test_simple_down:
					doActivity(R.string.thinkandroidsimpledwonloadactivtiy);
					break;
				case R.id.test_simple_down_two:
					doActivity(R.string.thinkandroidsimpletwodwonloadactivtiy);
					break;
				case R.id.test_multi_thread_down:
					doActivity(R.string.thinkandroidmultithreaddwonloadactivtiy);
					break;
				default:
					break;
				}
			}
		};
		testSimpleDownButton.setOnClickListener(onClickListener);
		testSimpleTwoDownButton.setOnClickListener(onClickListener);
		testMultiThreadDownButton.setOnClickListener(onClickListener);
	}

	@Override
	protected void onDestroy()
	{

		super.onDestroy(); // 注意先后
	}
}