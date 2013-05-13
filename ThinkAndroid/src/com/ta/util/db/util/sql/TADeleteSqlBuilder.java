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
package com.ta.util.db.util.sql;

import java.lang.reflect.Field;

import com.ta.exception.TADBException;
import com.ta.util.db.entity.TAArrayList;
import com.ta.util.db.util.TADBUtils;

/**
 * @Title TASqlBuilder
 * @Package com.ta.util.db.util.sql
 * @Description 删除sql语句构建器类
 * @author 白猫
 * @date 2013-1-20
 * @version V1.0
 */
public class TADeleteSqlBuilder extends TASqlBuilder
{
	@Override
	public String buildSql() throws TADBException, IllegalArgumentException,
			IllegalAccessException
	{
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder(256);
		stringBuilder.append("DELETE FROM ");
		stringBuilder.append(tableName);
		if (entity == null)
		{
			stringBuilder.append(buildConditionString());
		} else
		{
			stringBuilder.append(buildWhere(buildWhere(this.entity)));
		}

		return stringBuilder.toString();
	}

	/**
	 * 创建Where语句
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws TADBException
	 */
	public TAArrayList buildWhere(Object entity)
			throws IllegalArgumentException, IllegalAccessException,
			TADBException
	{
		Class<?> clazz = entity.getClass();
		TAArrayList whereArrayList = new TAArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			field.setAccessible(true);
			if (!TADBUtils.isTransient(field))
			{
				if (TADBUtils.isBaseDateType(field))
				{
					// 如果ID不是自动增加的
					if (!TADBUtils.isAutoIncrement(field))
					{
						String columnName = TADBUtils.getColumnByField(field);
						if (null != field.get(entity)
								&& field.get(entity).toString().length() > 0)
						{
							whereArrayList.add(
									(columnName != null && !columnName
											.equals("")) ? columnName : field
											.getName(), field.get(entity)
											.toString());
						}
					}
				}
			}
		}
		if (whereArrayList.isEmpty())
		{
			throw new TADBException("不能创建Where条件，语句");
		}
		return whereArrayList;
	}
}
