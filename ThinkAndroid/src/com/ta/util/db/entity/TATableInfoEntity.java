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
import java.util.List;

import com.ta.common.TABaseEntity;

public class TATableInfoEntity extends TABaseEntity
{
	private static final long serialVersionUID = 488168612576359150L;
	private String tableName = "";
	private String className = "";
	private TAPKProperyEntity pkProperyEntity = null;

	ArrayList<TAPropertyEntity> propertieArrayList = new ArrayList<TAPropertyEntity>();

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public ArrayList<TAPropertyEntity> getPropertieArrayList()
	{
		return propertieArrayList;
	}

	public void setPropertieArrayList(List<TAPropertyEntity> propertyList)
	{
		this.propertieArrayList = (ArrayList<TAPropertyEntity>) propertyList;
	}

	public TAPKProperyEntity getPkProperyEntity()
	{
		return pkProperyEntity;
	}

	public void setPkProperyEntity(TAPKProperyEntity pkProperyEntity)
	{
		this.pkProperyEntity = pkProperyEntity;
	}

}
