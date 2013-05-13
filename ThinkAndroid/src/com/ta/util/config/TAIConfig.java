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
package com.ta.util.config;

/**
 * @Title TAIConfig
 * @Package com.ta.util.config
 * @Description 配置器的接口
 * @author 白猫
 * @date 2013-4-3 上午 9:35
 * @version V1.0
 */
public interface TAIConfig
{
	/**
	 * 加载配置器
	 * 
	 */
	void loadConfig();

	/**
	 * 判断是否已经加载配置器
	 * 
	 * @return 返回是否加载的信息，false代表没有加载，true代表加载
	 */
	Boolean isLoadConfig();

	/**
	 * 开启控制器
	 */
	void open();

	/**
	 * 关闭配置器
	 */
	void close();

	/**
	 * 判断配置器是否关闭
	 * 
	 * @return 如果为true则关闭，如果为false则开启
	 */
	boolean isClosed();

	/**
	 * 设置String类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setString(String key, String value);

	/**
	 * 设置int类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setInt(String key, int value);

	/**
	 * 设置Boolean类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setBoolean(String key, Boolean value);

	/**
	 * 设置Byte类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setByte(String key, byte[] value);

	/**
	 * 设置Short类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setShort(String key, short value);

	/**
	 * 设置Long类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setLong(String key, long value);

	/**
	 * 设置Float类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setFloat(String key, float value);

	/**
	 * 设置Double类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @param value
	 *            配置值
	 */
	void setDouble(String key, double value);

	// 以资源设置
	/**
	 * 设置String类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setString(int resID, String value);

	/**
	 * 设置Int类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setInt(int resID, int value);

	/**
	 * 设置Boolean类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setBoolean(int resID, Boolean value);

	/**
	 * 设置Byte类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setByte(int resID, byte[] value);

	/**
	 * 设置Short类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setShort(int resID, short value);

	/**
	 * 设置Long类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setLong(int resID, long value);

	/**
	 * 设置Float类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setFloat(int resID, float value);

	/**
	 * 设置Double类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @param value
	 *            配置值
	 */
	void setDouble(int resID, double value);

	/**
	 * 以对象类型来配置，一组配置值
	 * 
	 * @param entity
	 *            传入的实体
	 * 
	 */
	void setConfig(Object entity);

	// 以下为get方法

	/**
	 * 返回String类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	String getString(String key, String defaultValue);

	/**
	 * 返回int类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	int getInt(String key, int defaultValue);

	/**
	 * 返回Boolean类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	boolean getBoolean(String key, Boolean defaultValue);

	/**
	 * 返回Byte类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	byte[] getByte(String key, byte[] defaultValue);

	/**
	 * 返回Short类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	short getShort(String key, Short defaultValue);

	/**
	 * 返回Long类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	long getLong(String key, Long defaultValue);

	/**
	 * 返回Float类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	float getFloat(String key, Float defaultValue);

	/**
	 * 返回Double类型的配置值
	 * 
	 * @param key
	 *            配置名
	 * @return 返回配置值配置值
	 */
	double getDouble(String key, Double defaultValue);

	// 资源型获取以下为get方法
	/**
	 * 返回String类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	String getString(int resID, String defaultValue);

	/**
	 * 返回int类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	int getInt(int resID, int defaultValue);

	/**
	 * 返回Boolean类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	boolean getBoolean(int resID, Boolean defaultValue);

	/**
	 * 返回Byte类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	byte[] getByte(int resID, byte[] defaultValue);

	/**
	 * 返回Short类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	short getShort(int resID, Short defaultValue);

	/**
	 * 返回Long类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	long getLong(int resID, Long defaultValue);

	/**
	 * 返回Float类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	float getFloat(int resID, Float defaultValue);

	/**
	 * 返回Double类型的配置值
	 * 
	 * @param resID
	 *            资源ID
	 * @return 返回配置值配置值
	 */
	double getDouble(int resID, Double defaultValue);

	/**
	 * 返回Double类型的配置值
	 * 
	 * @param clazz
	 *            返回资源的类型
	 * @return 返回带配置值的对象
	 */
	<T extends Object> T getConfig(Class<T> clazz);

	/**
	 * 删除配置值
	 * 
	 * @param key
	 */
	void remove(String key);

	/**
	 * 删除一组配置值
	 * 
	 * @param key
	 */
	void remove(String... key);

	/**
	 * 清除所有配置
	 */
	void clear();
}
