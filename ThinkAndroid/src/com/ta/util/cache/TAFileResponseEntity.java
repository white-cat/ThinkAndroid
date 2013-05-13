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
package com.ta.util.cache;

import com.ta.common.AsyncTask;
import com.ta.common.TABaseEntity;

public class TAFileResponseEntity extends TABaseEntity
{
	private static final long serialVersionUID = 5525015855679979479L;
	private AsyncTask<?, ?, ?> task;
	private Object object;

	public AsyncTask<?, ?, ?> getTask()
	{
		return task;
	}

	public void setTask(AsyncTask<?, ?, ?> task)
	{
		this.task = task;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object object)
	{
		this.object = object;
	}

}
