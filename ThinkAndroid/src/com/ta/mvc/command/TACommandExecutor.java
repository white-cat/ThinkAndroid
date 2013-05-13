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

import java.lang.reflect.Modifier;
import java.util.HashMap;
import com.ta.exception.TANoSuchCommandException;
import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.util.TALogger;

public class TACommandExecutor
{
	private final HashMap<String, Class<? extends TAICommand>> commands = new HashMap<String, Class<? extends TAICommand>>();

	private static final TACommandExecutor instance = new TACommandExecutor();
	private boolean initialized = false;

	public TACommandExecutor()
	{
		ensureInitialized();
	}

	public static TACommandExecutor getInstance()
	{
		return instance;
	}

	public void ensureInitialized()
	{
		if (!initialized)
		{
			initialized = true;
			TALogger.i(TACommandExecutor.this, "CommandExecutor初始化");
			TACommandQueueManager.getInstance().initialize();
			TALogger.i(TACommandExecutor.this, "CommandExecutor初始化");
		}
	}

	/**
	 * 所有命令终止或标记为结束
	 */
	public void terminateAll()
	{

	}

	/**
	 * 命令入列
	 * 
	 * @param commandKey
	 *            命令ID
	 * @param request
	 *            提交的参数
	 * @param listener
	 *            响应监听器
	 * @throws TANoSuchCommandException
	 */
	public void enqueueCommand(String commandKey, TARequest request,
			TAIResponseListener listener) throws TANoSuchCommandException
	{
		final TAICommand cmd = getCommand(commandKey);
		enqueueCommand(cmd, request, listener);
	}

	public void enqueueCommand(TAICommand command, TARequest request,
			TAIResponseListener listener) throws TANoSuchCommandException
	{
		if (command != null)
		{
			command.setRequest(request);
			command.setResponseListener(listener);
			TACommandQueueManager.getInstance().enqueue(command);
		}
	}

	public void enqueueCommand(TAICommand command, TARequest request)
			throws TANoSuchCommandException
	{
		enqueueCommand(command, null, null);
	}

	public void enqueueCommand(TAICommand command)
			throws TANoSuchCommandException
	{
		enqueueCommand(command, null);
	}

	private TAICommand getCommand(String commandKey)
			throws TANoSuchCommandException
	{
		TAICommand rv = null;

		if (commands.containsKey(commandKey))
		{
			Class<? extends TAICommand> cmd = commands.get(commandKey);
			if (cmd != null)
			{
				int modifiers = cmd.getModifiers();
				if ((modifiers & Modifier.ABSTRACT) == 0
						&& (modifiers & Modifier.INTERFACE) == 0)
				{
					try
					{
						rv = cmd.newInstance();
					} catch (Exception e)
					{
						throw new TANoSuchCommandException("没发现" + commandKey
								+ "命令");
					}
				} else
				{
					throw new TANoSuchCommandException("没发现" + commandKey
							+ "命令");
				}
			}
		}

		return rv;
	}

	public void registerCommand(String commandKey,
			Class<? extends TAICommand> command)
	{
		if (command != null)
		{
			commands.put(commandKey, command);
		}
	}

	public void unregisterCommand(String commandKey)
	{
		commands.remove(commandKey);
	}
}
