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

import com.ta.common.TABaseEntity;

/**
 * @Title TADBMasterEntity 对应sqlite_master表的实体类
 * @Package com.ta.util.db.entity
 * @Description 对应sqlite_master表的实体类
 * @author 白猫
 * @date 2013-1-20
 * @version V1.0
 */
public class TADBMasterEntity extends TABaseEntity
{
	private static final long serialVersionUID = 4511697615195446516L;
	private String type;
	private String name;
	private String tbl_name;
	private String sql;
	private int rootpage;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTbl_name()
	{
		return tbl_name;
	}

	public void setTbl_name(String tbl_name)
	{
		this.tbl_name = tbl_name;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public int getRootpage()
	{
		return rootpage;
	}

	public void setRootpage(int rootpage)
	{
		this.rootpage = rootpage;
	}

}
