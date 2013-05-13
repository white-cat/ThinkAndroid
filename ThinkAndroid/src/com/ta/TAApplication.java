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

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Stack;
import com.ta.exception.TAAppException;
import com.ta.exception.TANoSuchCommandException;
import com.ta.mvc.command.TACommandExecutor;
import com.ta.mvc.command.TAICommand;
import com.ta.mvc.command.TAIdentityCommand;
import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;
import com.ta.mvc.controller.ActivityStackInfo;
import com.ta.mvc.controller.NavigationDirection;
import com.ta.util.TAInjector;
import com.ta.util.TALogger;
import com.ta.util.cache.TAFileCache;
import com.ta.util.cache.TAFileCache.TACacheParams;
import com.ta.util.config.TAIConfig;
import com.ta.util.config.TAPreferenceConfig;
import com.ta.util.config.TAPropertiesConfig;
import com.ta.util.db.TASQLiteDatabasePool;
import com.ta.util.layoutloader.TAILayoutLoader;
import com.ta.util.layoutloader.TALayoutLoader;
import com.ta.util.netstate.TANetChangeObserver;
import com.ta.util.netstate.TANetWorkUtil.netType;
import com.ta.util.netstate.TANetworkStateReceiver;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class TAApplication extends Application implements TAIResponseListener
{
	/** 配置器 为Preference */
	public final static int PREFERENCECONFIG = 0;
	/** 配置器 为PROPERTIESCONFIG */
	public final static int PROPERTIESCONFIG = 1;

	private final static String TAIDENTITYCOMMAND = "TAIdentityCommand";
	/** 配置器 */
	private TAIConfig mCurrentConfig;
	/** 获取布局文件ID加载器 */
	private TAILayoutLoader mLayoutLoader;
	/** 加载类注入器 */
	private TAInjector mInjector;
	/** App异常崩溃处理器 */
	private UncaughtExceptionHandler uncaughtExceptionHandler;
	private static TAApplication application;
	private TACommandExecutor mCommandExecutor;
	private TAActivity currentActivity;
	private final HashMap<String, Class<? extends TAActivity>> registeredActivities = new HashMap<String, Class<? extends TAActivity>>();
	private Stack<ActivityStackInfo> activityStack = new Stack<ActivityStackInfo>();
	private NavigationDirection currentNavigationDirection;
	/** ThinkAndroid 文件缓存 */
	private TAFileCache mFileCache;
	/** ThinkAndroid数据库链接池 */
	private TASQLiteDatabasePool mSQLiteDatabasePool;
	/** ThinkAndroid 应用程序运行Activity管理器 */
	private TAAppManager mAppManager;
	private Boolean networkAvailable = false;
	private static final String SYSTEMCACHE = "thinkandroid";
	private TANetChangeObserver taNetChangeObserver;

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		onPreCreateApplication();
		super.onCreate();
		doOncreate();
		onAfterCreateApplication();
		getAppManager();
	}

	private void doOncreate()
	{
		// TODO Auto-generated method stub
		this.application = this;
		// 注册App异常崩溃处理器
		Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler());
		mCommandExecutor = TACommandExecutor.getInstance();
		taNetChangeObserver = new TANetChangeObserver()
		{
			@Override
			public void onConnect(netType type)
			{
				// TODO Auto-generated method stub
				super.onConnect(type);
				TAApplication.this.onConnect(type);
			}

			@Override
			public void onDisConnect()
			{
				// TODO Auto-generated method stub
				super.onDisConnect();
				TAApplication.this.onDisConnect();

			}
		};
		TANetworkStateReceiver.registerObserver(taNetChangeObserver);
		// 注册activity启动控制控制器
		registerCommand(TAIDENTITYCOMMAND, TAIdentityCommand.class);
	}

	/**
	 * 当前没有网络连接
	 */
	public void onDisConnect()
	{
		networkAvailable = false;
		if (currentActivity != null)
		{
			currentActivity.onDisConnect();
		}
	}

	/**
	 * 网络连接连接时调用
	 */
	protected void onConnect(netType type)
	{
		// TODO Auto-generated method stub
		networkAvailable = true;
		if (currentActivity != null)
		{
			currentActivity.onConnect(type);
		}
	}

	/**
	 * 获取Application
	 * 
	 * @return
	 */
	public static TAApplication getApplication()
	{
		return application;
	}

	protected void onAfterCreateApplication()
	{
		// TODO Auto-generated method stub

	}

	protected void onPreCreateApplication()
	{
		// TODO Auto-generated method stub

	}

	public TAIConfig getPreferenceConfig()
	{
		return getConfig(PREFERENCECONFIG);
	}

	public TAIConfig getPropertiesConfig()
	{
		return getConfig(PROPERTIESCONFIG);
	}

	public TAIConfig getConfig(int confingType)
	{
		if (confingType == PREFERENCECONFIG)
		{
			mCurrentConfig = TAPreferenceConfig.getPreferenceConfig(this);

		} else if (confingType == PROPERTIESCONFIG)
		{
			mCurrentConfig = TAPropertiesConfig.getPropertiesConfig(this);
		} else
		{
			mCurrentConfig = TAPropertiesConfig.getPropertiesConfig(this);
		}
		if (!mCurrentConfig.isLoadConfig())
		{
			mCurrentConfig.loadConfig();
		}
		return mCurrentConfig;
	}

	public TAIConfig getCurrentConfig()
	{
		if (mCurrentConfig == null)
		{
			getPreferenceConfig();
		}
		return mCurrentConfig;
	}

	public TAILayoutLoader getLayoutLoader()
	{
		if (mLayoutLoader == null)
		{
			mLayoutLoader = TALayoutLoader.getInstance(this);
		}
		return mLayoutLoader;
	}

	public void setLayoutLoader(TAILayoutLoader layoutLoader)
	{
		this.mLayoutLoader = layoutLoader;
	}

	/**
	 * 设置 App异常崩溃处理器
	 * 
	 * @param uncaughtExceptionHandler
	 */
	public void setUncaughtExceptionHandler(
			UncaughtExceptionHandler uncaughtExceptionHandler)
	{
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
	}

	private UncaughtExceptionHandler getUncaughtExceptionHandler()
	{
		if (uncaughtExceptionHandler == null)
		{
			uncaughtExceptionHandler = TAAppException.getInstance(this);
		}
		return uncaughtExceptionHandler;
	}

	public TAInjector getInjector()
	{
		if (mInjector == null)
		{
			mInjector = TAInjector.getInstance();
		}
		return mInjector;
	}

	public void setInjector(TAInjector injector)
	{
		this.mInjector = injector;
	}

	public void onActivityCreating(TAActivity activity)
	{
		if (activityStack.size() > 0)
		{
			ActivityStackInfo info = activityStack.peek();
			if (info != null)
			{
				TAResponse response = info.getResponse();
				activity.preProcessData(response);
			}
		}
	}

	public void onActivityCreated(TAActivity activity)
	{
		if (currentActivity != null)
		{
			currentActivity.finish();
		}
		currentActivity = activity;

		int size = activityStack.size();

		if (size > 0)
		{
			ActivityStackInfo info = activityStack.peek();
			if (info != null)
			{
				TAResponse response = info.getResponse();
				activity.processData(response);

				if (size >= 2 && !info.isRecord())
				{
					activityStack.pop();
				}
			}
		}
	}

	public void doCommand(String commandKey, TARequest request,
			TAIResponseListener listener, boolean record, boolean resetStack)
	{
		if (listener != null)
		{
			try
			{
				TACommandExecutor.getInstance().enqueueCommand(commandKey,
						request, listener);

			} catch (TANoSuchCommandException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			TALogger.i(TAApplication.this, "go with cmdid=" + commandKey
					+ ", record: " + record + ",rs: " + resetStack
					+ ", request: " + request);
			if (resetStack)
			{
				activityStack.clear();
			}

			currentNavigationDirection = NavigationDirection.Forward;

			ActivityStackInfo info = new ActivityStackInfo(commandKey, request,
					record, resetStack);
			activityStack.add(info);

			Object[] newTag =
			{ request.getTag(), listener };
			request.setTag(newTag);

			TALogger.i(TAApplication.this, "Enqueue-ing command");
			try
			{
				TACommandExecutor.getInstance().enqueueCommand(commandKey,
						request, this);
			} catch (TANoSuchCommandException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TALogger.i(TAApplication.this, "Enqueued command");

		}

	}

	public void back()
	{
		TALogger.i(TAApplication.this,
				"ActivityStack Size: " + activityStack.size());
		if (activityStack != null && activityStack.size() != 0)
		{
			if (activityStack.size() >= 2)
			{
				activityStack.pop();
			}

			currentNavigationDirection = NavigationDirection.Backward;
			ActivityStackInfo info = activityStack.peek();
			try
			{
				TACommandExecutor.getInstance().enqueueCommand(
						info.getCommandKey(), info.getRequest(), this);
			} catch (TANoSuchCommandException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processResponse(Message msg)
	{
		TAResponse response = (TAResponse) msg.obj;
		ActivityStackInfo top = activityStack.peek();
		top.setResponse(response);
		if (response != null)
		{
			int targetActivityKeyResID = response.getActivityKeyResID();
			String targetActivityKey = "";
			if (targetActivityKeyResID != 0)
			{
				targetActivityKey = getString(targetActivityKeyResID);
			}
			if (targetActivityKey != null
					&& targetActivityKey.equalsIgnoreCase(""))
			{
				targetActivityKey = response.getActivityKey();
			}
			Object[] newTag = (Object[]) response.getTag();
			Object tag = newTag[0];
			response.setTag(tag);
			Class<? extends TAActivity> cls = registeredActivities
					.get(targetActivityKey);
			TALogger.i(TAApplication.this,
					"Launching new activity // else, current Direction: "
							+ currentNavigationDirection);

			int asize = activityStack.size();
			TALogger.i(TAApplication.this,
					"Current Stack Size (before processing): " + asize);

			switch (currentNavigationDirection)
			{
			case Forward:
				if (asize >= 2)
				{
					if (!top.isRecord())
					{
						activityStack.pop();
					}
				}
				break;
			case Backward:
				// Popping of the last command from the stack would have
				// happened in (back)
				// Just reset the navigation direction
				currentNavigationDirection = NavigationDirection.Forward;
				break;
			}
			TALogger.i(
					TAApplication.this,
					"Current Stack Size (after processing): "
							+ activityStack.size());

			if (cls != null)
			{
				Intent launcherIntent = new Intent(currentActivity, cls);
				currentActivity.startActivity(launcherIntent);
				currentActivity.finish();
				top.setActivityClass(cls);
			}

		}

	}

	public void registerActivity(int resID, Class<? extends TAActivity> clz)
	{
		String activityKey = getString(resID);
		registeredActivities.put(activityKey, clz);
	}

	public void registerActivity(String activityKey,
			Class<? extends TAActivity> clz)
	{
		registeredActivities.put(activityKey, clz);
	}

	public void unregisterActivity(int resID)
	{
		String activityKey = getString(resID);
		unregisterActivity(activityKey);
	}

	public void unregisterActivity(String activityKey)
	{
		registeredActivities.remove(activityKey);
	}

	public void registerCommand(int resID, Class<? extends TAICommand> command)
	{

		String commandKey = getString(resID);
		registerCommand(commandKey, command);

	}

	public void registerCommand(String commandKey,
			Class<? extends TAICommand> command)
	{
		if (command != null)
		{
			mCommandExecutor.registerCommand(commandKey, command);
		}
	}

	public void unregisterCommand(int resID)
	{
		String commandKey = getString(resID);
		unregisterCommand(commandKey);
	}

	public void unregisterCommand(String commandKey)
	{

		mCommandExecutor.unregisterCommand(commandKey);
	}

	public TAFileCache getFileCache()
	{
		if (mFileCache == null)
		{
			TACacheParams cacheParams = new TACacheParams(this, SYSTEMCACHE);
			TAFileCache fileCache = new TAFileCache(cacheParams);
			application.setFileCache(fileCache);

		}
		return mFileCache;
	}

	public void setFileCache(TAFileCache fileCache)
	{
		this.mFileCache = fileCache;
	}

	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			processResponse(msg);
		}
	};

	private void handleResponse(TAResponse response)
	{
		Message msg = new Message();
		msg.what = 0;
		msg.obj = response;
		handler.sendMessage(msg);
	}

	@Override
	public void onStart()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(TAResponse response)
	{
		// TODO Auto-generated method stub
		handleResponse(response);
	}

	@Override
	public void onRuning(TAResponse response)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailure(TAResponse response)
	{
		// TODO Auto-generated method stub
		handleResponse(response);
	}

	public TASQLiteDatabasePool getSQLiteDatabasePool()
	{
		if (mSQLiteDatabasePool == null)
		{
			mSQLiteDatabasePool = TASQLiteDatabasePool.getInstance(this);
			mSQLiteDatabasePool.createPool();
		}
		return mSQLiteDatabasePool;
	}

	public void setSQLiteDatabasePool(TASQLiteDatabasePool sqliteDatabasePool)
	{
		this.mSQLiteDatabasePool = sqliteDatabasePool;
	}

	public TAAppManager getAppManager()
	{
		if (mAppManager == null)
		{
			mAppManager = TAAppManager.getAppManager();
		}
		return mAppManager;
	}

	/**
	 * 退出应用程序
	 * 
	 * @param isBackground
	 *            是否开开启后台运行,如果为true则为后台运行
	 */
	public void exitApp(Boolean isBackground)
	{
		mAppManager.AppExit(this, isBackground);
	}

	/**
	 * 获取当前网络状态，true为网络连接成功，否则网络连接失败
	 * 
	 * @return
	 */
	public Boolean isNetworkAvailable()
	{
		return networkAvailable;
	}

	@Override
	public void onFinish()
	{
		// TODO Auto-generated method stub

	}

}
