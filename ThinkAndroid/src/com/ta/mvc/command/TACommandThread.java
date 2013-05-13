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

import com.ta.util.TALogger;

/**
 * @Title TACommandThread
 * @package com.ta.mvc.command
 * @Description TACommandThread是一个专门执行command的线程
 * @author 白猫
 * @date 2013-1-16 下午 16:53
 * @version V1.0
 */
public class TACommandThread implements Runnable
{
	private int threadId;
	private Thread thread = null;
	private boolean running = false;
	private boolean stop = false;

	public TACommandThread(int threadId)
	{
		TALogger.i(TACommandThread.this, "CommandThread::ctor");
		this.threadId = threadId;
		thread = new Thread(this);
	}

	public void run()
	{
		TALogger.i(TACommandThread.this, "CommandThread::run-enter");
		while (!stop)
		{
			TALogger.i(TACommandThread.this, "CommandThread::get-next-command");
			TAICommand cmd = TACommandQueueManager.getInstance()
					.getNextCommand();
			TALogger.i(TACommandThread.this, "CommandThread::to-execute");
			cmd.execute();
			TALogger.i(TACommandThread.this, "CommandThread::executed");
		}
		TALogger.i(TACommandThread.this, "CommandThread::run-exit");
	}

	public void start()
	{
		thread.start();
		running = true;
	}

	public void stop()
	{
		stop = true;
		running = false;
	}

	public boolean isRunning()
	{
		return running;
	}

	public int getThreadId()
	{
		return threadId;
	}
}
