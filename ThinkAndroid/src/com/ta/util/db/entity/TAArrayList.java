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
package com.ta.util.db.entity;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.ta.common.TAStringUtils;

/**
 * @Title TAArrayList NameValuePair数组
 * @Package com.ta.util.db.entity
 * @Description NameValuePair数组
 * @author 白猫
 * @date 2013-1-20
 * @version V1.0
 */
public class TAArrayList extends ArrayList<NameValuePair>
{

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(NameValuePair nameValuePair)
	{
		if (!TAStringUtils.isEmpty(nameValuePair.getValue()))
		{
			return super.add(nameValuePair);
		} else
		{
			return false;
		}
	}

	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, String value)
	{
		return add(new BasicNameValuePair(key, value));
	}

}
