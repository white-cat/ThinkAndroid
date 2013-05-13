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
package com.ta.mvc.command;

import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TAResponse;

import android.os.Handler;
import android.os.Message;

public abstract class TACommand extends TABaseCommand
{
	protected final static int command_start = 1;
	protected final static int command_runting = 2;
	protected final static int command_failure = 3;
	protected final static int command_success = 4;
	protected final static int command_finish = 5;
	private TAIResponseListener listener;
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case command_start:
				listener.onStart();
				break;
			case command_runting:
				listener.onRuning(getResponse());
				break;
			case command_success:
				listener.onSuccess(getResponse());
				break;
			case command_failure:
				listener.onFailure(getResponse());
				break;
			case command_finish:
				listener.onFinish();
				break;
			default:
				break;
			}
		};

	};

	public final void execute()
	{
		onPreExecuteCommand();
		executeCommand();
		onPostExecuteCommand();
	}

	protected abstract void executeCommand();

	protected void onPreExecuteCommand()
	{
		sendStartMessage();
	}

	protected void onPostExecuteCommand()
	{

	}

	protected void sendMessage(int state)
	{
		listener = getResponseListener();
		if (listener != null)
		{
			handler.sendEmptyMessage(state);
		}
	}

	public void sendStartMessage()
	{
		sendMessage(command_start);
	}

	public void sendSuccessMessage(Object object)
	{
		TAResponse response = new TAResponse();
		response.setData(object);
		setResponse(response);
		sendMessage(command_success);
	}

	public void sendFailureMessage(Object object)
	{
		TAResponse response = new TAResponse();
		response.setData(object);
		setResponse(response);
		sendMessage(command_failure);
	}

	public void sendRuntingMessage(Object object)
	{
		TAResponse response = new TAResponse();
		response.setData(object);
		setResponse(response);
		sendMessage(command_runting);
	}

	public void sendFinishMessage()
	{
		sendMessage(command_finish);
	}
}
