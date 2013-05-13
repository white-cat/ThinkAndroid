package com.cat.activity;

import com.cat.adapter.ImageAdapter;
import com.ta.annotation.TAInjectView;

import android.os.Bundle;
import android.widget.GridView;

public class ThinkAndroidImageCacheActivtiy extends ThinkAndroidBaseActivity
{
	@TAInjectView(id = R.id.gridView)
	private GridView gridView;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_image_cache_title);

		final ImageAdapter adapter = new ImageAdapter(this, getTAApplication());
		gridView.setAdapter(adapter);

	}
}