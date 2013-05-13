/*
 * Copyright (C) 2013  WhiteCat 白猫 (www.thinkandroid.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ta;

import com.ta.mvc.command.TAIdentityCommand;
import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;
import com.ta.util.TALogger;
import com.ta.util.netstate.TANetWorkUtil.netType;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public abstract class TAActivity extends Activity
{
	/** 模块的名字 */
	private String moduleName = "";
	/** 布局文件的名字 */
	private String layouName = "";
	private static final int DIALOG_ID_PROGRESS_DEFAULT = 0x174980;
	private static final String TAIDENTITYCOMMAND = "taidentitycommand";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		notifiyApplicationActivityCreating();
		onPreOnCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		getTAApplication().getAppManager().addActivity(this);
		initActivity();
		onAfterOnCreate(savedInstanceState);
		notifiyApplicationActivityCreated();
	}

	public TAApplication getTAApplication()
	{
		return (TAApplication) getApplication();
	}

	private void notifiyApplicationActivityCreating()
	{
		getTAApplication().onActivityCreating(this);
	}

	private void notifiyApplicationActivityCreated()
	{
		getTAApplication().onActivityCreated(this);
	}

	protected void onPreOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		getTAApplication().registerCommand(TAIDENTITYCOMMAND,
				TAIdentityCommand.class);
	}

	private void initActivity()
	{
		// 初始化模块名
		getModuleName();
		// 初始化布局名
		getLayouName();
		// 加载类注入器
		initInjector();
		// 自动加载默认布局
		loadDefautLayout();
	}

	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * 初始化注入器
	 */
	private void initInjector()
	{
		// TODO Auto-generated method stub
		getTAApplication().getInjector().injectResource(this);
		getTAApplication().getInjector().inject(this);
	}

	/**
	 * 自动加载默认布局
	 */
	private void loadDefautLayout()
	{
		try
		{
			int layoutResID = getTAApplication().getLayoutLoader().getLayoutID(
					layouName);
			setContentView(layoutResID);
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void setContentView(int layoutResID)
	{
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
		// 由于view必须在视图记载之后添加注入
		getTAApplication().getInjector().injectView(this);
		onAfterSetContentView();
	}

	public void setContentView(View view, LayoutParams params)
	{
		super.setContentView(view, params);
		// 由于view必须在视图记载之后添加注入
		getTAApplication().getInjector().injectView(this);
		onAfterSetContentView();
	}

	public void setContentView(View view)
	{
		super.setContentView(view);
		// 由于view必须在视图记载之后添加注入
		getTAApplication().getInjector().injectView(this);
		onAfterSetContentView();
	}

	protected void onAfterSetContentView()
	{
		// TODO Auto-generated method stub

	}

	/**
	 * 获取模块的名字
	 */
	public String getModuleName()
	{
		String moduleName = this.moduleName;
		if (moduleName == null || moduleName.equalsIgnoreCase(""))
		{
			moduleName = getClass().getName().substring(0,
					getClass().getName().length() - 8);
			String arrays[] = moduleName.split("\\.");
			this.moduleName = moduleName = arrays[arrays.length - 1]
					.toLowerCase();
		}
		return moduleName;
	}

	/**
	 * 设置模块的名字
	 */
	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	/**
	 * 获取布局文件名
	 * 
	 * @return布局文件名
	 */
	public String getLayouName()
	{
		String layouName = this.layouName;
		if (layouName == null || layouName.equalsIgnoreCase(""))
		{
			this.layouName = this.moduleName;
		}
		return layouName;
	}

	/**
	 * 设置布局文件名
	 */
	protected void setLayouName(String layouName)
	{
		this.layouName = layouName;
	}

	public void preProcessData(TAResponse response)
	{

	}

	public void processData(TAResponse response)
	{

	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		switch (id)
		{
		case DIALOG_ID_PROGRESS_DEFAULT:
			ProgressDialog dlg = new ProgressDialog(this);
			// dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dlg.setMessage("正在加载...");
			dlg.setCancelable(true);
			return dlg;
		default:
			return super.onCreateDialog(id);

		}
	}

	public final void doCommand(int resId, TARequest request)
	{
		String commandKey = getString(resId);
		doCommand(commandKey, request, null, true);
	}

	public final void doCommand(String commandKey, TARequest request)
	{
		doCommand(commandKey, request, null, true);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener)
	{
		String commandKey = getString(resId);
		doCommand(commandKey, request, listener, true);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener)
	{
		doCommand(commandKey, request, listener, true);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener, boolean showProgress)
	{
		String commandKey = getString(resId);
		TALogger.i(TAActivity.this, "go with cmdid=" + commandKey
				+ ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, true);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean showProgress)
	{
		TALogger.i(TAActivity.this, "go with cmdid=" + commandKey
				+ ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, true);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record)
	{
		String commandKey = getString(resId);
		TALogger.i(TAActivity.this, "go with cmdid=" + commandKey
				+ ", record: " + record + ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, record, false);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record)
	{
		TALogger.i(TAActivity.this, "go with cmdid=" + commandKey
				+ ", record: " + record + ", request: " + request);
		doCommand(commandKey, request, listener, showProgress, record, false);
	}

	public final void doCommand(int resId, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record,
			boolean resetStack)
	{
		String commandKey = getString(resId);
		doCommand(commandKey, request, listener, showProgress, record,
				resetStack);
	}

	public final void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean showProgress, boolean record,
			boolean resetStack)
	{
		if (showProgress)
		{
			showProgress();
		}
		getTAApplication().doCommand(commandKey, request, listener, record,
				resetStack);
	}

	/**
	 * 返回
	 */
	public final void back()
	{
		getTAApplication().back();
	}

	/**
	 * 需要自定义进度条，请重写
	 */
	protected void showProgress()
	{
		showDialog(DIALOG_ID_PROGRESS_DEFAULT);
	}

	/**
	 * 隐藏进度跳，需要重写的请重写
	 */
	protected void hideProgress()
	{
		try
		{
			removeDialog(DIALOG_ID_PROGRESS_DEFAULT);
		} catch (IllegalArgumentException iae)
		{
		}
	}

	/**
	 * 网络连接连接时调用
	 */
	public void onConnect(netType type)
	{

	}

	/**
	 * 当前没有网络连接
	 */
	public void onDisConnect()
	{

	}

	@Override
	public void finish()
	{
		// TODO Auto-generated method stub
		getTAApplication().getAppManager().removeActivity(this);
		super.finish();
	}

	/**
	 * 退出应用程序
	 * 
	 * @param isBackground
	 *            是否开开启后台运行,如果为true则为后台运行
	 */
	public void exitApp(Boolean isBackground)
	{
		getTAApplication().exitApp(isBackground);
	}

	/**
	 * 退出应用程序
	 * 
	 */
	public void exitApp()
	{
		getTAApplication().exitApp(false);
	}

	/**
	 * 退出应用程序，且在后台运行
	 * 
	 */
	public void exitAppToBackground()
	{
		getTAApplication().exitApp(true);
	}

	/**
	 * 运行activity
	 * 
	 * @param activityResID
	 */
	public final void doActivity(int activityResID)
	{
		doActivity(activityResID, null);
	}

	public final void doActivity(int activityResID, Bundle bundle)
	{
		TARequest request = new TARequest();
		request.setData(bundle);
		request.setActivityKeyResID(activityResID);
		// 启动activity
		doCommand(TAIDENTITYCOMMAND, request);
	}

	@Override
	public void onBackPressed()
	{
		back();
	}
}
