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
package com.ta.util.download;

import com.ta.common.TAStringUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class DownloadService extends Service
{
	// 这样能在后台运行么？
	private DownloadManager mDownloadManager;

	@Override
	public IBinder onBind(Intent intent)
	{

		return new DownloadServiceImpl();
	}

	@Override
	public void onCreate()
	{

		super.onCreate();
		// 这样能在后台运行么？
		mDownloadManager = DownloadManager.getDownloadManager();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
	}

	private class DownloadServiceImpl extends IDownloadService.Stub
	{

		@Override
		public void startManage() throws RemoteException
		{

			mDownloadManager.startManage();
		}

		@Override
		public void addTask(String url) throws RemoteException
		{
			if (!TAStringUtils.isEmpty(url))
			{
				mDownloadManager.addHandler(url);
			}

		}

		@Override
		public void pauseTask(String url) throws RemoteException
		{
			if (!TAStringUtils.isEmpty(url))
			{
				mDownloadManager.pauseHandler(url);
			}
		}

		@Override
		public void deleteTask(String url) throws RemoteException
		{
			if (!TAStringUtils.isEmpty(url))
			{
				mDownloadManager.deleteHandler(url);
			}
		}

		@Override
		public void continueTask(String url) throws RemoteException
		{
			if (!TAStringUtils.isEmpty(url))
			{
				mDownloadManager.continueHandler(url);
			}
		}

		@Override
		public void pauseAll(String url) throws RemoteException
		{
			// TODO Auto-generated method stub
			if (!TAStringUtils.isEmpty(url))
			{
				mDownloadManager.pauseAllHandler();
			}
		}

		@Override
		public void stopManage() throws RemoteException
		{
			// TODO Auto-generated method stub
			mDownloadManager.close();
		}

	}

}
