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

import java.util.Date;
import java.util.HashMap;

public class TAHashMap<T extends Object> extends HashMap<String, T>
{
	private static final long serialVersionUID = 1L;

	public T put(String key, T value)
	{
		if (hasValue(value))
		{
			return super.put(key, value);
		} else
		{
			return null;
		}
	};

	public boolean hasValue(Object value)
	{
		if (value != null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public T get(Object key)
	{
		// TODO Auto-generated method stub
		return super.get(key);
	}

	public String getString(String key)
	{
		return String.valueOf(get(key));
	}

	public int getInt(String key)
	{
		return Integer.valueOf(getString(key));
	}

	public boolean getBoolean(String key)
	{
		return Boolean.valueOf(getString(key));
	}

	public double getDouble(String key)
	{
		return Double.valueOf(getString(key));
	}

	public float getFloat(String key)
	{
		return Float.valueOf(getString(key));
	}

	public long getLong(String key)
	{
		return Long.valueOf(getString(key));
	}

	/**
	 * 获取时间
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Date getDate(String key)
	{
		return new Date(getString(key));
	}

	public char getChar(String key)
	{
		return getString(key).trim().toCharArray()[0];
	}

	public byte[] getBlob(String key)
	{
		return getString(key).getBytes();
	}

	public short getShort(String key)
	{
		return Short.valueOf(getString(key));
	}

}
