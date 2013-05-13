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

import android.os.Handler;
import android.os.Message;

public class DownLoadCallback extends Handler
{

	protected static final int START_MESSAGE = 0;
	protected static final int ADD_MESSAGE = 1;
	protected static final int PROGRESS_MESSAGE = 2;
	protected static final int SUCCESS_MESSAGE = 3;
	protected static final int FAILURE_MESSAGE = 4;
	protected static final int FINISH_MESSAGE = 5;
	protected static final int STOP_MESSAGE = 6;

	public void onStart()
	{
	}

	public void onAdd(String url, Boolean isInterrupt)
	{
	}

	public void onLoading(String url, long totalSize, long currentSize,
			long speed)
	{

	}

	public void onSuccess(String url)
	{
	}

	public void onFailure(String url, String strMsg)
	{

	}

	public void onFinish(String url)
	{
	}

	public void onStop()
	{
	}

	@Override
	public void handleMessage(Message msg)
	{
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		Object[] response;

		switch (msg.what)
		{
		case START_MESSAGE:
			onStart();
			break;
		case ADD_MESSAGE:
			response = (Object[]) msg.obj;
			onAdd((String) response[0], (Boolean) response[1]);
			break;
		case PROGRESS_MESSAGE:
			response = (Object[]) msg.obj;
			onLoading((String) response[0], (Long) response[1],
					(Long) response[2], (Long) response[3]);
			break;
		case SUCCESS_MESSAGE:
			response = (Object[]) msg.obj;
			onSuccess((String) response[0]);
			break;
		case FAILURE_MESSAGE:
			response = (Object[]) msg.obj;
			onFailure((String) response[0], (String) response[1]);
			break;
		case FINISH_MESSAGE:
			response = (Object[]) msg.obj;
			onFinish((String) response[0]);
			break;
		case STOP_MESSAGE:
			onStop();
			break;

		}
	}

	protected void sendSuccessMessage(String url)
	{
		sendMessage(obtainMessage(SUCCESS_MESSAGE, new Object[]
		{ url }));
	}

	protected void sendLoadMessage(String url, long totalSize,
			long currentSize, long speed)
	{
		sendMessage(obtainMessage(PROGRESS_MESSAGE, new Object[]
		{ url, totalSize, currentSize, speed }));
	}

	protected void sendAddMessage(String url, Boolean isInterrupt)
	{
		sendMessage(obtainMessage(ADD_MESSAGE, new Object[]
		{ url, isInterrupt }));
	}

	protected void sendFailureMessage(String url, String strMsg)
	{
		sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]
		{ url, strMsg }));
	}

	protected void sendStartMessage()
	{
		sendMessage(obtainMessage(START_MESSAGE, null));
	}

	protected void sendStopMessage()
	{
		sendMessage(obtainMessage(STOP_MESSAGE, null));
	}

	protected void sendFinishMessage(String url)
	{
		sendMessage(obtainMessage(FINISH_MESSAGE, new Object[]
		{ url }));
	}
}
