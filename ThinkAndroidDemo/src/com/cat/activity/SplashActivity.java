package com.cat.activity;

import com.cat.command.TestMVCCommand;
import com.ta.TAApplication;
import com.ta.util.cache.TAFileCache;
import com.ta.util.cache.TAFileCache.TACacheParams;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * @Title: 用户其启动界面
 * @Package com.cat.activity
 * @Description: 用户其启动界面时候的一个启动页面完成一些初始化工作
 * @author 白猫
 * @date 2013-5-6
 * @version V1.0
 */
public class SplashActivity extends ThinkAndroidBaseActivity
{
	private static final String SYSTEMCACHE = "thinkandroid";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// TANetworkStateReceiver.registerNetworkStateReceiver(this);
		final View view = View.inflate(this, R.layout.splash, null);
		setContentView(view);
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
		aa.setDuration(5000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationEnd(Animation arg0)
			{
				startMain();
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationStart(Animation animation)
			{
			}
		});
	}

	@Override
	protected void onPreOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onPreOnCreate(savedInstanceState);
		TAApplication application = (TAApplication) getApplication();
		// 配置系统的缓存,可以选择性的配置
		TACacheParams cacheParams = new TACacheParams(this, SYSTEMCACHE);
		TAFileCache fileCache = new TAFileCache(cacheParams);
		application.setFileCache(fileCache);
		// 注册activity
		getTAApplication().registerCommand(R.string.testmvccommand,
				TestMVCCommand.class);
		// 注册activity
		getTAApplication().registerActivity(R.string.thinkandroidmainactivity,
				ThinkAndroidMainActivity.class);
		// 注册activity
		getTAApplication().registerActivity(R.string.thinkandroidcacheactivtiy,
				ThinkAndroidCacheActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(R.string.thinkandroiddbactivtiy,
				ThinkAndroidDBActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(
				R.string.thinkandroidimagecacheactivtiy,
				ThinkAndroidImageCacheActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(R.string.thinkandroidmvcactivtiy,
				ThinkAndroidMvcActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(R.string.thinkandroidhttpactivtiy,
				ThinkAndroidHttpActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(
				R.string.thinkandroidsimpledwonloadactivtiy,
				ThinkAndroidSimpleDwonLoadActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(
				R.string.thinkandroidsimpletwodwonloadactivtiy,
				ThinkAndroidSimpleTwoDwonLoadActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(
				R.string.thinkandroiddwonloadactivtiy,
				ThinkAndroidDwonLoadActivtiy.class);
		// 注册activity
		getTAApplication().registerActivity(
				R.string.thinkandroidmultithreaddwonloadactivtiy,
				ThinkAndroidMultiThreadDwonLoadActivtiy.class);

		// 注册activity
		getTAApplication().registerActivity(R.string.thinkandroidotheractivtiy,
				ThinkAndroidOtherActivtiy.class);

	}

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
	}

	private void startMain()
	{
		// TODO Auto-generated method stu
		doActivity(R.string.thinkandroidmainactivity);
	}

}
