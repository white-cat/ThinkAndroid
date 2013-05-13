package com.cat.activity;

import com.cat.adapter.StringAdapter;
import com.ta.annotation.TAInjectView;

import android.os.Bundle;
import android.widget.ListView;

public class ThinkAndroidCacheActivtiy extends ThinkAndroidBaseActivity
{
	@TAInjectView(id = R.id.listview)
	private ListView listView;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_cache_title);
		StringAdapter adapter = new StringAdapter(this, getTAApplication());
		listView.setAdapter(adapter);
	}
}