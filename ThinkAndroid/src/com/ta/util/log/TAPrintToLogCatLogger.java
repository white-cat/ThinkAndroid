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
package com.ta.util.log;

import android.util.Log;

/**
 * @Title TAPrintToLogCatLogger
 * @Package com.ta.core.util.log
 * @Description TAPrintToLogCatLogger是TA框架中打印到LogCat上面的日志类
 * @author 白猫
 * @date 2013-1-16 14:25
 * @version V1.0
 */
public class TAPrintToLogCatLogger implements ILogger
{
	@Override
	public void d(String tag, String message)
	{
		Log.d(tag, message);
	}

	@Override
	public void e(String tag, String message)
	{
		Log.e(tag, message);
	}

	@Override
	public void i(String tag, String message)
	{
		Log.i(tag, message);
	}

	@Override
	public void v(String tag, String message)
	{
		Log.v(tag, message);
	}

	@Override
	public void w(String tag, String message)
	{
		Log.w(tag, message);
	}

	@Override
	public void println(int priority, String tag, String message)
	{
		Log.println(priority, tag, message);
	}

	@Override
	public void open()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
}
