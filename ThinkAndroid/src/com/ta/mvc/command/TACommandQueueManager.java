package com.ta.mvc.command;

import com.ta.util.TALogger;

/**
 * @Title TACommandQueueManager
 * @package com.ta.mvc.command
 * @Description TACommandQueueManager是command队列的管理者
 * @author 白猫
 * @date 2013-1-16 下午 17:51
 * @version V1.0
 */
public final class TACommandQueueManager
{
	private static TACommandQueueManager instance;
	private boolean initialized = false;
	private TAThreadPool pool;
	private TACommandQueue queue;

	private TACommandQueueManager()
	{
	}

	public static TACommandQueueManager getInstance()
	{
		if (instance == null)
		{
			instance = new TACommandQueueManager();
		}
		return instance;
	}

	public void initialize()
	{
		TALogger.i(TACommandQueueManager.this, "准备初始化！");
		if (!initialized)
		{
			TALogger.i(TACommandQueueManager.this, "正在初始化！");
			queue = new TACommandQueue();
			pool = TAThreadPool.getInstance();
			TALogger.i(TACommandQueueManager.this, "完成初始化！");

			pool.start();
			initialized = true;
		}
		TALogger.i(TACommandQueueManager.this, "初始化完成！");
	}

	/**
	 * 从队列中获取Command
	 * 
	 * @return TAICommand
	 */
	public TAICommand getNextCommand()
	{
		TALogger.i(TACommandQueueManager.this, "获取Command！");
		TAICommand cmd = queue.getNextCommand();
		TALogger.i(TACommandQueueManager.this, "获取Command" + cmd + "完成！");
		return cmd;
	}

	/**
	 * 添加Command到队列中
	 */
	public void enqueue(TAICommand cmd)
	{
		TALogger.i(TACommandQueueManager.this, "添加" + cmd + "开始");
		queue.enqueue(cmd);
		TALogger.i(TACommandQueueManager.this, "添加" + cmd + "完成");
	}

	/**
	 * 清除队列
	 */
	public void clear()
	{
		queue.clear();
	}

	/**
	 * 关闭队列
	 */
	public void shutdown()
	{
		if (initialized)
		{
			queue.clear();
			pool.shutdown();
			initialized = false;
		}
	}
}
