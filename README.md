该项目已经暂停维护：需要 Java web快速开发框架可以关注我的们的项目
[JEEWEB快速开发平台](https://github.com/white-cat/jeeweb)






















#ThinkAndroid 交流平台
* QQ群：169415162(交流群1) 
* 网址：[http://www.thinkandroid.cn](http://www.thinkandroid.cn)

----

#  ThinkAndroid简介 
ThinkAndroid是一个免费的开源的、简易的、遵循Apache2开源协议发布的Android开发框架，其开发宗旨是简单、快速的进行
Android应用程序的开发，包含Android mvc、简易sqlite orm、ioc模块、封装Android httpclitent的http模块,
具有快速构建文件缓存功能，无需考虑缓存文件的格式，都可以非常轻松的实现缓存，它还基于文件缓存模块实现了图片缓存功能，
在android中加载的图片的时候，对oom的问题，和对加载图片错位的问题都轻易解决。他还包括了一个手机开发中经常应用的实用工具类，
如日志管理，配置文件管理，android下载器模块，网络切换检测等等工具。


##目前ThinkAndroid主要有以下模块：

* MVC模块：实现视图与模型的分离。

* ioc模块：android中的ioc模块，完全注解方式就可以进行UI绑定、res中的资源的读取、以及对象的初始化。

* 数据库模块：android中的orm框架，使用了线程池对sqlite进行操作。
* 
* http模块：通过httpclient进行封装http数据请求，支持异步及同步方式加载。

* 缓存模块：通过简单的配置及设计可以很好的实现缓存，对缓存可以随意的配置

* 图片缓存模块：imageview加载图片的时候无需考虑图片加载过程中出现的oom和android容器快速滑动时候出现的图片错位等现象。

* 配置器模块：可以对简易的实现配对配置的操作，目前配置文件可以支持Preference、Properties对配置进行存取。

* 日志打印模块：可以较快的轻易的是实现日志打印，支持日志打印的扩展，目前支持对sdcard写入本地打印、以及控制台打印

* 下载器模块:可以简单的实现多线程下载、后台下载、断点续传、对下载进行控制、如开始、暂停、删除等等。

* 网络状态检测模块：当网络状态改变时，对其进行检测。


---
## 使用ThinkAndroid快速开发框架需要有以下权限：

```xml
<!-- 访问互联网权限 根据需要添加-->
<uses-permission android:name="android.permission.INTERNET" />
<!-- SDCARD读写权限 根据需要添加 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 网络状态检测权限  根据需要添加-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     配置：
     <receiver android:name="com.ta.util.netstate.TANetworkStateReceiver" >
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>
 <application
        android:name="com.ta.TAApplication" >
      application必须配置以上格式
```
Activity必须继承TAActivity
----
##ThinkAndroid使用方法：
关于ThinkAndroid的更多介绍，请点击[这里](http://www.thinkandroid.cn/forum.php?mod=forumdisplay&fid=36)
##MVC模块
* MVC模块：实现视图与模型的分离。

```java
  getTAApplication().registerCommand(R.string.comand,
        TAIdentityCommand.class);
		getTAApplication().registerCommand(R.string.comand,
				TAIdentityCommand.class);
		TALogger.addLogger(new TAPrintToFileLogger());
		TARequest request = new TARequest();
		doCommand(R.string.comand, request, new TAIResponseListener()
		{

			@Override
			public void onStart(TAResponse response)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(TAResponse response)
			{
				// TODO Auto-generated method stub

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

			}

		}, false, true, true);
    
    
    
  
package com.ta.mvc.command;

import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;

public class TAIdentityCommand extends TACommand
{
  @Override
	protected void executeCommand()
	{
		// TODO Auto-generated method stub
		TARequest request = getRequest();
		TAResponse response = new TAResponse();
		response.setTag(request.getTag());
		response.setData(request.getData());
		response.setActivityKey((String) request.getActivityKey());
		response.setActivityKeyResID(request.getActivityKeyResID());
		setResponse(response);
		notifyListener(true);
	}

	protected void notifyListener(boolean success)
	{
		TAIResponseListener responseListener = getResponseListener();
		if (responseListener != null)
		{
			onComandUpdate(command_success);
		}
	}
}

```

----

##ioc模块使用方法：
* 完全注解方式就可以进行UI绑定、res中的资源的读取、以及对象的初始化。

```java
public class ThinkAndroidDemoActivity extends TAActivity {

     
	@TAInject 
	Entity entity; //目前只能对无参构造函数进行初始化
	@@TAInject(id=R.string.app_name)
	String appNameString;
	@TAInjectResource(id=R.attr.test)
	int[] test; 
	@TAInjectView(id=R.id.add);
	Button addButton;
}

```

##数据库模块
* android中的orm框架，使用了线程池对sqlite进行操作。

```java
public class ThinkAndroidDemoActivity extends TAActivity {

   TASQLiteDatabasePool sqlitePool = getTAApplication()
				.getSQLiteDatabasePool();
		TASQLiteDatabase sqliteDatabase=sqlitePool.getSQLiteDatabase();
		//使用中
		sqliteDatabase.insert(entity);
		sqlitePool.returnSQLiteDatabase(sqliteDatabase); 
 
}
```

##Http模块使用方法：
###异步get方法
```java
  AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://www.thinkandroid.cn/", new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(String content)
			{
				// TODO Auto-generated method stub
				super.onSuccess(content);
				TALogger.d(LoginActivity.this, content);
			}
			
			@Override
			public void onStart()
			{
				// TODO Auto-generated method stub
				super.onStart();
			}
			
			@Override
			public void onFailure(Throwable error)
			{
				// TODO Auto-generated method stub
				super.onFailure(error);
			}
			
			@Override
			public void onFinish()
			{
				// TODO Auto-generated method stub
				super.onFinish();
			}

		});
```
##Http模块使用方法：
###同步get方法

```java
  TASyncHttpClient client = new TASyncHttpClient();
  	client.get("http://www.thinkandroid.cn/", new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(String content)
			{
				// TODO Auto-generated method stub
				super.onSuccess(content);
				TALogger.d(LoginActivity.this, content);
			}
			
			@Override
			public void onStart()
			{
				// TODO Auto-generated method stub
				super.onStart();
			}
			
			@Override
			public void onFailure(Throwable error)
			{
				// TODO Auto-generated method stub
				super.onFailure(error);
			}
			
			@Override
			public void onFinish()
			{
				// TODO Auto-generated method stub
				super.onFinish();
			}

		});
```
### 使用http模块上传文件 或者 提交数据 到服务器（post方法）

```java
RequestParams params = new RequestParams();
  	  params.put("username", "white_cat");
		  params.put("password", "123456");
		  params.put("email", "2640017581@qq.com");
		  params.put("profile_picture", new File("/mnt/sdcard/testpic.jpg")); // 上传文件
		  params.put("profile_picture2", inputStream); // 上传数据流
		  params.put("profile_picture3", new ByteArrayInputStream(bytes)); // 提交字节流
		client.post("http://www.thinkandroid.cn/", new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(String content)
			{
				// TODO Auto-generated method stub
				super.onSuccess(content);
				TALogger.d(LoginActivity.this, content);
			}
			
			@Override
			public void onStart()
			{
				// TODO Auto-generated method stub
				super.onStart();
			}
			
			@Override
			public void onFailure(Throwable error)
			{
				// TODO Auto-generated method stub
				super.onFailure(error);
			}
			
			@Override
			public void onFinish()
			{
				// TODO Auto-generated method stub
				super.onFinish();
			}

		});
	}
```


----

###使用http下载文件：
* 支持断点续传，随时停止下载任务 或者 开始任务

```java
    AsyncHttpClient syncHttpClient = new AsyncHttpClient();
  	   FileHttpResponseHandler fHandler = new FileHttpResponseHandler(
					TAExternalOverFroyoUtils.getDiskCacheDir(TestActivity.this,
					 "sdfsdfsdf").getAbsolutePath())
		   {
			         
					
					 @Override 
					 public void onProgress(String speed, String progress) {
						 //TODO Auto-generated method stub 
						 super.onProgress(speed, progress);
					 TALogger.v(TestActivity.this, progress + "--------" + speed); }
					 
					 @Override 
					 public void onFailure(Throwable error) { 
						 // TODOAuto-generated method stub 
						 super.onFailure(error); }
				 
				 @Override
				 public void onSuccess(byte[] binaryData) { 
					 // TODOAuto-generated method stub 
					 super.onSuccess(binaryData);
				 TALogger.d(TestActivity.this, "kaishi8了"); } };
		 syncHttpClient .download(
				"http://static.qiyi.com/ext/common/iQIYI/QIYImedia_4_01.exe",
				 fHandler);
         //停止
        fHandler.setInterrupt(interrupt);
        
```


##图片模块 方法 
* imageview加载图片的时候无需考虑图片加载过程中出现的oom和android容器快速滑动时候出现的图片错位等现象。

```java
package com.test;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TADownloadBitmapHandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Adapter extends BaseAdapter
{
  TABitmapCacheWork imageFetcher;
	Context mContext;

	public Adapter(Context context, TAApplication application)
	{
		TADownloadBitmapHandler downloadBitmapFetcher = new TADownloadBitmapHandler(
				context, 100);
		imageFetcher = new TABitmapCacheWork(context);
		imageFetcher.setProcessDataHandler(downloadBitmapFetcher);
		imageFetcher.setFileCache(application.getFileCache());
		this.mContext = context;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return Images.imageThumbUrls.length;
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return Images.imageThumbUrls[position];
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View baseView = inflater.inflate(R.layout.login_adapter, null);
		final ImageView imageView = (ImageView) baseView
				.findViewById(R.id.imageView);
     //加载图片
		imageFetcher.loadFormCache(getItem(position), imageView);
		return baseView;
	}
}



使用 
  @TAInjectView(id = R.id.gridView)
	GridView  gridView;
	Adapter adapter = new Adapter(this, getTAApplication());
	gridView.setAdapter(adapter);
```

##缓存模块 方法 
* 很简单的实现缓存，以一个简单的文件缓存为例
* 下载处理类

```java
package com.test.file;

import com.ta.util.cache.TAProcessDataHandler;
//下载处理类
public class TAProcessStringHandler extends TAProcessDataHandler
{
  @Override
	public byte[] processData(Object data)
	{
		// TODO Auto-generated method stub
		String mynameString="white_cat";
		//这里对数据进行处理，如下载东西等等，转换为byte[]以供缓存存储使用
		return mynameString.getBytes();
	}
}
```

* 缓存结果返回操作类

```java
package com.test.file;

import android.widget.TextView;

import com.ta.util.cache.TACallBackHandler;

public class TAStringCallBackHandler extends TACallBackHandler<TextView>
{
  @Override
	public void onStart(TextView t, Object data)
	{
		// TODO Auto-generated method stub
		super.onStart(t, data);
	}

	@Override
	public void onSuccess(TextView t, Object data, byte[] buffer)
	{
		// TODO Auto-generated method stub
		super.onSuccess(t, data, buffer);
	}

	@Override
	public void onFailure(TextView t, Object data)
	{
		// TODO Auto-generated method stub
		super.onFailure(t, data);
	}
}
```
* 程序调用

```java
TAFileCacheWork<TextView> taFileCacheWork=new TAFileCacheWork<TextView>();
  	taFileCacheWork.setFileCache(getTAApplication().getFileCache());
		taFileCacheWork.setCallBackHandler(new TAStringCallBackHandler());
		taFileCacheWork.setProcessDataHandler(new TAProcessStringHandler());
		taFileCacheWork.loadFormCache("http://www.baidu.com", textView);
```

##打印模块使用方法
可以较快的轻易的是实现日志打印，支持日志打印的扩展，目前支持对sdcard写入本地打印、以及控制台打印
添加打印器
TALogger.addLogger(new TAPrintToFileLogger());
调用打印
TALogger.addLogger(new TAPrintToFileLogger());
TALogger.d(TestActivity.this, "test");



##下载器模块
可以简单的实现多线程下载、后台下载、断点续传、对下载进行控制、如开始、暂停、删除等等。
```java
private DownloadManager downloadManager;
downloadManager = DownloadManager.getDownloadManager();
		downloadManager.setDownLoadCallback(new DownLoadCallback()
		{
			@Override
			public void onSuccess(String url)
			{
			
			}

			@Override
			public void onLoading(String url, String speed, String progress)
			{
				// TODO Auto-generated method stub

			
			}
		});
		//添加
		downloadManager.addHandler(url);
		//继续
		downloadManager.continueHandler(url);
		//暂停
		downloadManager.pauseHandler(url);
		//删除
		downloadManager.deleteHandler(url);
		//
```


* 后台下载

```java
	private IDownloadService downloadService;
	ServiceConnection serviceConnection=new ServiceConnection()
	{
		
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			// TODO Auto-generated method stub
			downloadService=(IDownloadService)service;
		}
	};
	
	//添加任务
	downloadService.addTask(url)
	//暂停任务
 	downloadService.pauseTask(url)
 	//继续任务
 	downloadService.continueTask(url)
 	//删除任务
	downloadService.deleteTask(url)
```


## 配置器模
可以对简易的实现配对配置的操作，目前配置文件可以支持Preference、Properties对配置进行存取

```java
	TAIConfig config = application
				.getConfig(TAApplication.PROPERTIESCONFIG);
		Entity entity = new Entity();
		entity.setB(false);
		entity.setD(10);
		entity.setI(1);
		entity.setF(1f);
		config.setConfig(entity);
		Entity cEntity = config.getConfig(Entity.class);
		textView.setText(cEntity.toString());
```


## 网络状态监测模块
当网络状态改变时，对其进行监测。

```java
 TANetworkStateReceiver.registerObserver(new TANetChangeObserver()
		{
			@Override
			public void onConnect(netType type)
			{
				// TODO Auto-generated method stub
				super.onConnect(type);
				Toast.makeText(TestActivity.this, "onConnect",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onDisConnect()
			{
				// TODO Auto-generated method stub
				super.onDisConnect();
				Toast.makeText(TestActivity.this, "onDisConnect",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		
		需要开启权限
		  <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    
    配置：
     <receiver android:name="com.ta.util.netstate.TANetworkStateReceiver" >
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>
```


#关于作者(白猫)
* ThinkAndroid交流网站：[http://www.thinkandroid.cn](http://www.thinkandroid.cn)
* ThinkAndroid交流QQ群 ： 169415162(交流群1已满)
* ThinkAndroid交流QQ群 ： 230206891(交流群2)
