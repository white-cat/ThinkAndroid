package com.cat.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.ta.TAActivity;
import com.ta.util.netstate.TANetWorkUtil.netType;

public class ThinkAndroidBaseActivity extends TAActivity
{
	@Override
	protected void onPreOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onPreOnCreate(savedInstanceState);
	}

	@Override
	public void onConnect(netType type)
	{
		// TODO Auto-generated method stub
		super.onConnect(type);
		Toast.makeText(this, "网络连接开启", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDisConnect()
	{
		// TODO Auto-generated method stub
		super.onDisConnect();
		Toast.makeText(this, "网络连接关闭", Toast.LENGTH_LONG).show();
	}

}
