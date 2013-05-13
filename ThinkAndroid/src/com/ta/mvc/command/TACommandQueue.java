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

import java.util.concurrent.LinkedBlockingQueue;

import com.ta.util.TALogger;

/**
 * @Title TACommandQueue
 * @package com.ta.core.mvc.command
 * @Description TACommandQueue维护一个Command
 * @author 白猫
 * @date 2013-1-16 下午 16:51
 * @version V1.0
 */
public class TACommandQueue
{
	private LinkedBlockingQueue<TAICommand> theQueue = new LinkedBlockingQueue<TAICommand>();

	public TACommandQueue()
	{
		TALogger.i(TACommandQueue.this, "初始化Command队列");
	}

	public void enqueue(TAICommand cmd)
	{
		TALogger.i(TACommandQueue.this, "添加Command到队列");
		theQueue.add(cmd);
	}

	public synchronized TAICommand getNextCommand()
	{
		TALogger.i(TACommandQueue.this, "获取Command");
		TAICommand cmd = null;
		try
		{
			TALogger.i(TACommandQueue.this, "CommandQueue::to-take");
			cmd = theQueue.take();
			TALogger.i(TACommandQueue.this, "CommandQueue::taken");
		} catch (InterruptedException e)
		{
			TALogger.i(TACommandQueue.this, "没有获取到Command");
			e.printStackTrace();
		}
		TALogger.i(TACommandQueue.this, "返回Command" + cmd);
		return cmd;
	}

	public synchronized void clear()
	{
		TALogger.i(TACommandQueue.this, "清空所有Command");
		theQueue.clear();
	}
}
