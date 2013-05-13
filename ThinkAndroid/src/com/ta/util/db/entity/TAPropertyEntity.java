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

/**
 * @Title TADBProperty
 * @Package com.ta.core.util.sqlite
 * @Description TADBProperty 数据库的字段
 * @author 白猫
 * @date 2013-1-24 10:52
 * @version V1.0
 */
public class TAPropertyEntity
{
	protected String name;
	protected String columnName;
	protected Class<?> type;
	protected Object defaultValue;
	protected boolean isAllowNull = true;
	protected int index; // 暂时不写
	protected boolean primaryKey = false;
	protected boolean autoIncrement = false;

	public TAPropertyEntity()
	{

	}

	public TAPropertyEntity(String name, Class<?> type, Object defaultValue,
			boolean primaryKey, boolean isAllowNull, boolean autoIncrement,
			String columnName)
	{
		this.name = name;
		this.type = type;
		this.defaultValue = defaultValue;
		this.primaryKey = primaryKey;
		this.isAllowNull = isAllowNull;
		this.autoIncrement = autoIncrement;
		this.columnName = columnName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Class<?> getType()
	{
		return type;
	}

	public void setType(Class<?> type)
	{
		this.type = type;
	}

	public Object getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public boolean isPrimaryKey()
	{
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	public boolean isAllowNull()
	{
		return isAllowNull;
	}

	public void setAllowNull(boolean isAllowNull)
	{
		this.isAllowNull = isAllowNull;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public boolean isAutoIncrement()
	{
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement)
	{
		this.autoIncrement = autoIncrement;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
}
