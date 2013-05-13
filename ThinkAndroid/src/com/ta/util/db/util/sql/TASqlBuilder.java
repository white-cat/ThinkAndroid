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

import org.apache.http.NameValuePair;

import com.ta.common.TAStringUtils;
import com.ta.exception.TADBException;
import com.ta.util.db.entity.TAArrayList;
import com.ta.util.db.util.TADBUtils;

import android.text.TextUtils;

/**
 * @Title TASqlBuilder
 * @Package com.ta.util.db.util.sql
 * @Description sql语句构建器基类
 * @author 白猫
 * @date 2013-1-20
 * @version V1.0
 */
public abstract class TASqlBuilder
{
	protected Boolean distinct;
	protected String where;
	protected String groupBy;
	protected String having;
	protected String orderBy;
	protected String limit;
	protected Class<?> clazz = null;
	protected String tableName = null;
	protected Object entity;
	protected TAArrayList updateFields;

	public TASqlBuilder(Object entity)
	{
		this.entity = entity;
		setClazz(entity.getClass());
	}

	public Object getEntity()
	{
		return entity;
	}

	public void setEntity(Object entity)
	{
		this.entity = entity;
		setClazz(entity.getClass());
	}

	public void setCondition(boolean distinct, String where, String groupBy,
			String having, String orderBy, String limit)
	{
		this.distinct = distinct;
		this.where = where;
		this.groupBy = groupBy;
		this.having = having;
		this.orderBy = orderBy;
		this.limit = limit;
	}

	public TAArrayList getUpdateFields()
	{
		return updateFields;
	}

	public void setUpdateFields(TAArrayList updateFields)
	{
		this.updateFields = updateFields;
	}

	public TASqlBuilder()
	{
	}

	public TASqlBuilder(Class<?> clazz)
	{
		setTableName(clazz);
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public void setTableName(Class<?> clazz)
	{
		this.tableName = TADBUtils.getTableName(clazz);
	}

	public String getTableName()
	{
		return tableName;
	}

	public Class<?> getClazz()
	{
		return clazz;
	}

	public void setClazz(Class<?> clazz)
	{
		setTableName(clazz);
		this.clazz = clazz;
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 * @throws TADBException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String getSqlStatement() throws TADBException,
			IllegalArgumentException, IllegalAccessException
	{
		onPreGetStatement();
		return buildSql();
	}

	/**
	 * 构建sql语句前执行方法
	 * 
	 * @return
	 * @throws TADBException
	 */
	public void onPreGetStatement() throws TADBException,
			IllegalArgumentException, IllegalAccessException
	{

	}

	/**
	 * 构建sql语句
	 * 
	 * @return
	 * @throws TADBException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public abstract String buildSql() throws TADBException,
			IllegalArgumentException, IllegalAccessException;

	/**
	 * 创建条件字句
	 * 
	 * @return 返回条件Sql
	 */
	protected String buildConditionString()
	{
		StringBuilder query = new StringBuilder(120);
		appendClause(query, " WHERE ", where);
		appendClause(query, " GROUP BY ", groupBy);
		appendClause(query, " HAVING ", having);
		appendClause(query, " ORDER BY ", orderBy);
		appendClause(query, " LIMIT ", limit);
		return query.toString();
	}

	protected void appendClause(StringBuilder s, String name, String clause)
	{
		if (!TextUtils.isEmpty(clause))
		{
			s.append(name);
			s.append(clause);
		}
	}

	/**
	 * 构建where子句
	 * 
	 * @param conditions
	 *            TAArrayList类型的where数据
	 * @return 返回where子句
	 */
	public String buildWhere(TAArrayList conditions)
	{
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder(256);
		if (conditions != null)
		{
			stringBuilder.append(" WHERE ");
			for (int i = 0; i < conditions.size(); i++)
			{
				NameValuePair nameValuePair = conditions.get(i);
				stringBuilder
						.append(nameValuePair.getName())
						.append(" = ")
						.append(TAStringUtils.isNumeric(nameValuePair
								.getValue().toString()) ? nameValuePair
								.getValue() : "'" + nameValuePair.getValue()
								+ "'");
				if (i + 1 < conditions.size())
				{
					stringBuilder.append(" AND ");
				}
			}
		}
		return stringBuilder.toString();
	}
}
