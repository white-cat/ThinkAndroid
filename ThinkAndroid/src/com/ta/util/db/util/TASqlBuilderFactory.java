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
package com.ta.util.db.util;

import com.ta.util.db.util.sql.TADeleteSqlBuilder;
import com.ta.util.db.util.sql.TAInsertSqlBuilder;
import com.ta.util.db.util.sql.TAQuerySqlBuilder;
import com.ta.util.db.util.sql.TASqlBuilder;
import com.ta.util.db.util.sql.TAUpdateSqlBuilder;

/**
 * @Title TASqlBuilder
 * @Package com.ta.util.db.util.sql
 * @Description Sql构建器工厂,生成sql语句构建器
 * @author 白猫
 * @date 2013-1-20
 * @version V1.0
 */
public class TASqlBuilderFactory
{
	private static TASqlBuilderFactory instance;
	/**
	 * 调用getSqlBuilder(int operate)返回插入sql语句构建器传入的参数
	 */
	public static final int INSERT = 0;
	/**
	 * 调用getSqlBuilder(int operate)返回查询sql语句构建器传入的参数
	 */
	public static final int SELECT = 1;
	/**
	 * 调用getSqlBuilder(int operate)返回删除sql语句构建器传入的参数
	 */
	public static final int DELETE = 2;
	/**
	 * 调用getSqlBuilder(int operate)返回更新sql语句构建器传入的参数
	 */
	public static final int UPDATE = 3;

	/**
	 * 单例模式获得Sql构建器工厂
	 * 
	 * @return sql构建器
	 */
	public static TASqlBuilderFactory getInstance()
	{
		if (instance == null)
		{
			instance = new TASqlBuilderFactory();
		}
		return instance;
	}

	/**
	 * 获得sql构建器
	 * 
	 * @param operate
	 * @return 构建器
	 */
	public synchronized TASqlBuilder getSqlBuilder(int operate)
	{
		TASqlBuilder sqlBuilder = null;
		switch (operate)
		{
		case INSERT:
			sqlBuilder = new TAInsertSqlBuilder();
			break;
		case SELECT:
			sqlBuilder = new TAQuerySqlBuilder();
			break;
		case DELETE:
			sqlBuilder = new TADeleteSqlBuilder();
			break;
		case UPDATE:
			sqlBuilder = new TAUpdateSqlBuilder();
			break;
		default:
			break;
		}
		return sqlBuilder;
	}
}
