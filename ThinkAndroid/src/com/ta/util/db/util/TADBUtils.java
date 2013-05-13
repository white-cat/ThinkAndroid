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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ta.common.TAStringUtils;
import com.ta.exception.TADBException;
import com.ta.util.db.annotation.TAColumn;
import com.ta.util.db.annotation.TAPrimaryKey;
import com.ta.util.db.annotation.TATableName;
import com.ta.util.db.annotation.TATransient;
import com.ta.util.db.entity.TAHashMap;
import com.ta.util.db.entity.TAPKProperyEntity;
import com.ta.util.db.entity.TAPropertyEntity;
import com.ta.util.db.entity.TATableInfoEntity;

import android.database.Cursor;

/**
 * @Title TASqlBuilder
 * @Package com.ta.util.db.util.sql
 * @Description 数据库的一些工具
 * @author 白猫
 * @date 2013-1-20
 * @version V1.0
 */
public class TADBUtils
{
	/**
	 * 通过Cursor获取一个实体数组
	 * 
	 * @param clazz
	 *            实体类型
	 * @param cursor
	 *            数据集合
	 * @return 相应实体List数组
	 */
	public static <T> List<T> getListEntity(Class<T> clazz, Cursor cursor)
	{
		List<T> queryList = TAEntityBuilder.buildQueryList(clazz, cursor);
		return queryList;
	}

	/**
	 * 返回数据表中一行的数据
	 * 
	 * @param cursor
	 *            数据集合
	 * @return TAHashMap类型数据
	 */
	public static TAHashMap<String> getRowData(Cursor cursor)
	{
		if (cursor != null && cursor.getColumnCount() > 0)
		{
			TAHashMap<String> hashMap = new TAHashMap<String>();
			int columnCount = cursor.getColumnCount();
			for (int i = 0; i < columnCount; i++)
			{
				hashMap.put(cursor.getColumnName(i), cursor.getString(i));
			}
			return hashMap;
		}
		return null;
	}

	/**
	 * 根据实体类 获得 实体类对应的表名
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getTableName(Class<?> clazz)
	{
		TATableName table = (TATableName) clazz
				.getAnnotation(TATableName.class);
		if (table == null || TAStringUtils.isEmpty(table.name()))
		{
			// 当没有注解的时候默认用类的名称作为表名,并把点（.）替换为下划线(_)
			return clazz.getName().toLowerCase().replace('.', '_');
		}
		return table.name();

	}

	/**
	 * 返回主键字段
	 * 
	 * @param clazz
	 *            实体类型
	 * @return
	 */
	public static Field getPrimaryKeyField(Class<?> clazz)
	{
		Field primaryKeyField = null;
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null)
		{

			for (Field field : fields)
			{ // 获取ID注解
				if (field.getAnnotation(TAPrimaryKey.class) != null)
				{
					primaryKeyField = field;
					break;
				}
			}
			if (primaryKeyField == null)
			{ // 没有ID注解
				for (Field field : fields)
				{
					if ("_id".equals(field.getName()))
					{
						primaryKeyField = field;
						break;
					}
				}
				if (primaryKeyField == null)
				{ // 如果没有_id的字段
					for (Field field : fields)
					{
						if ("id".equals(field.getName()))
						{
							primaryKeyField = field;
							break;
						}
					}
				}
			}
		} else
		{
			throw new RuntimeException("this model[" + clazz + "] has no field");
		}
		return primaryKeyField;
	}

	/**
	 * 返回主键名
	 * 
	 * @param clazz
	 *            实体类型
	 * @return
	 */
	public static String getPrimaryKeyFieldName(Class<?> clazz)
	{
		Field f = getPrimaryKeyField(clazz);
		return f == null ? "id" : f.getName();
	}

	/**
	 * 返回数据库字段数组
	 * 
	 * @param clazz
	 *            实体类型
	 * @return 数据库的字段数组
	 */
	public static List<TAPropertyEntity> getPropertyList(Class<?> clazz)
	{

		List<TAPropertyEntity> plist = new ArrayList<TAPropertyEntity>();
		try
		{
			Field[] fields = clazz.getDeclaredFields();
			String primaryKeyFieldName = getPrimaryKeyFieldName(clazz);
			for (Field field : fields)
			{
				if (!TADBUtils.isTransient(field))
				{
					if (TADBUtils.isBaseDateType(field))
					{

						if (field.getName().equals(primaryKeyFieldName)) // 过滤主键
							continue;

						TAPKProperyEntity property = new TAPKProperyEntity();

						property.setColumnName(TADBUtils
								.getColumnByField(field));
						property.setName(field.getName());
						property.setType(field.getType());
						property.setDefaultValue(TADBUtils
								.getPropertyDefaultValue(field));
						plist.add(property);
					}
				}
			}
			return plist;
		} catch (Exception e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 构建创建表的sql语句
	 * 
	 * @param clazz
	 *            实体类型
	 * @return 创建表的sql语句
	 * @throws TADBException
	 */
	public static String creatTableSql(Class<?> clazz) throws TADBException
	{
		TATableInfoEntity tableInfoEntity = TATableInfofactory.getInstance()
				.getTableInfoEntity(clazz);

		TAPKProperyEntity pkProperyEntity = null;
		pkProperyEntity = tableInfoEntity.getPkProperyEntity();
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("CREATE TABLE IF NOT EXISTS ");
		strSQL.append(tableInfoEntity.getTableName());
		strSQL.append(" ( ");

		if (pkProperyEntity != null)
		{
			Class<?> primaryClazz = pkProperyEntity.getType();
			if (primaryClazz == int.class || primaryClazz == Integer.class)
				if (pkProperyEntity.isAutoIncrement())
				{
					strSQL.append("\"").append(pkProperyEntity.getColumnName())
							.append("\"    ")
							.append("INTEGER PRIMARY KEY AUTOINCREMENT,");
				} else
				{
					strSQL.append("\"").append(pkProperyEntity.getColumnName())
							.append("\"    ").append("INTEGER PRIMARY KEY,");
				}
			else
				strSQL.append("\"").append(pkProperyEntity.getColumnName())
						.append("\"    ").append("TEXT PRIMARY KEY,");
		} else
		{
			strSQL.append("\"").append("id").append("\"    ")
					.append("INTEGER PRIMARY KEY AUTOINCREMENT,");
		}

		Collection<TAPropertyEntity> propertys = tableInfoEntity
				.getPropertieArrayList();
		for (TAPropertyEntity property : propertys)
		{
			strSQL.append("\"").append(property.getColumnName());
			strSQL.append("\",");
		}
		strSQL.deleteCharAt(strSQL.length() - 1);
		strSQL.append(" )");
		return strSQL.toString();
	}

	/**
	 * 检测 字段是否已经被标注为 非数据库字段
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isTransient(Field field)
	{
		return field.getAnnotation(TATransient.class) != null;
	}

	/**
	 * 检查是否是主键
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isPrimaryKey(Field field)
	{
		return field.getAnnotation(TAPrimaryKey.class) != null;
	}

	/**
	 * 检查是否自增
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isAutoIncrement(Field field)
	{
		TAPrimaryKey primaryKey = field.getAnnotation(TAPrimaryKey.class);
		if (null != primaryKey)
		{
			return primaryKey.autoIncrement();
		}
		return false;
	}

	/**
	 * 是否为基本的数据类型
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isBaseDateType(Field field)
	{
		Class<?> clazz = field.getType();
		return clazz.equals(String.class) || clazz.equals(Integer.class)
				|| clazz.equals(Byte.class) || clazz.equals(Long.class)
				|| clazz.equals(Double.class) || clazz.equals(Float.class)
				|| clazz.equals(Character.class) || clazz.equals(Short.class)
				|| clazz.equals(Boolean.class) || clazz.equals(Date.class)
				|| clazz.equals(java.util.Date.class)
				|| clazz.equals(java.sql.Date.class) || clazz.isPrimitive();
	}

	/**
	 * 获取某个列
	 * 
	 * @param field
	 * @return
	 */
	public static String getColumnByField(Field field)
	{
		TAColumn column = field.getAnnotation(TAColumn.class);
		if (column != null && column.name().trim().length() != 0)
		{
			return column.name();
		}
		TAPrimaryKey primaryKey = field.getAnnotation(TAPrimaryKey.class);
		if (primaryKey != null && primaryKey.name().trim().length() != 0)
			return primaryKey.name();

		return field.getName();
	}

	/**
	 * 获得默认值
	 * 
	 * @param field
	 * @return
	 */
	public static String getPropertyDefaultValue(Field field)
	{
		TAColumn column = field.getAnnotation(TAColumn.class);
		if (column != null && column.defaultValue().trim().length() != 0)
		{
			return column.defaultValue();
		}
		return null;
	}
}
