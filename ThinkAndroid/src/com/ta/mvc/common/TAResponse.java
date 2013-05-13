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
package com.ta.mvc.common;

import com.ta.common.TABaseEntity;

/**
 * @Title TAResponse
 * @package com.ta.mvc.common
 * @Description TAResponse是返回的数据
 * @author 白猫
 * @date 2013-1-16 下午
 * @version V1.0
 * @param Object
 */
public class TAResponse extends TABaseEntity
{
	private static final long serialVersionUID = 2715141726276497343L;
	private Object tag;
	private Object data;
	private String activityKey;
	private int activityKeyResID;

	public TAResponse()
	{

	}

	public TAResponse(Object tag, Object data)
	{
		this.tag = tag;
		this.data = data;
	}

	public Object getTag()
	{
		return tag;
	}

	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public int getActivityKeyResID()
	{
		return activityKeyResID;
	}

	public void setActivityKeyResID(int activityKeyResID)
	{
		this.activityKeyResID = activityKeyResID;
	}

	public String getActivityKey()
	{
		return activityKey;
	}

	public void setActivityKey(String activityKey)
	{
		this.activityKey = activityKey;
	}

}
