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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

import com.ta.common.TAReflectUtils;
import android.content.Context;

/**
 * @Title TAPropertiesConfig
 * @Package com.ta.util.config
 * @Description TAPropertiesConfig是ThinkAndroid的Properties类型配置文件操作类
 * @author 白猫
 * @date 2013-1-7 上午 10:35
 * @version V1.0
 */
public class TAPropertiesConfig implements TAIConfig
{
	/** assets中配置信息文件 */
	private String assetsPath = "/assets/tacdonfig.properties";
	/** 软件Files文件夹中配置信息文件 */
	private String filesPath = "tacdonfig.properties";
	private static TAPropertiesConfig mPropertiesConfig;
	private static final String LOADFLAG = "assetsload";
	private Context mContext;
	private Properties mProperties;

	private TAPropertiesConfig(Context context)
	{
		this.mContext = context;
	}

	/**
	 * 获得系统资源类
	 * 
	 * @param context
	 * @return
	 */
	public static TAPropertiesConfig getPropertiesConfig(Context context)
	{
		if (mPropertiesConfig == null)
		{
			mPropertiesConfig = new TAPropertiesConfig(context);
		}
		return mPropertiesConfig;
	}

	@Override
	public void loadConfig()
	{
		// TODO Auto-generated method stub
		Properties props = new Properties();
		InputStream in = TAPropertiesConfig.class
				.getResourceAsStream(assetsPath);
		try
		{
			if (in != null)
			{
				props.load(in);
				Enumeration<?> e = props.propertyNames();
				if (e.hasMoreElements())
				{
					while (e.hasMoreElements())
					{
						String s = (String) e.nextElement();
						props.setProperty(s, props.getProperty(s));
					}
				}
			}
			setBoolean(LOADFLAG, true);
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Boolean isLoadConfig()
	{
		return getBoolean(LOADFLAG, false);
	}

	public void setConfig(String key, String value)
	{

		if (value != null)
		{
			Properties props = getProperties();
			props.setProperty(key, value);
			setProperties(props);
		}

	}

	public String getAssetsPath()
	{
		return assetsPath;
	}

	public void setAssetsPath(String assetsPath)
	{
		this.assetsPath = assetsPath;
	}

	public String getFilesPath()
	{
		return filesPath;
	}

	public void setFilesPath(String filesPath)
	{
		this.filesPath = filesPath;
	}

	/**
	 * 返回配置关于配置实体
	 * 
	 * @return 返回配置实体
	 */
	private Properties getProperties()
	{
		if (mProperties == null)
		{
			mProperties = getPro();
		}
		return mProperties;
	}

	private Properties getPro()
	{
		Properties props = new Properties();
		try
		{
			InputStream in = mContext.openFileInput(filesPath);
			props.load(in);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return props;
	}

	private void setProperties(Properties p)
	{
		OutputStream out;
		try
		{
			out = mContext.openFileOutput(filesPath, Context.MODE_PRIVATE);
			p.store(out, null);
			out.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isClosed()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setString(String key, String value)
	{
		// TODO Auto-generated method stub
		setConfig(key, value);
	}

	@Override
	public void setInt(String key, int value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	@Override
	public void setBoolean(String key, Boolean value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	@Override
	public void setByte(String key, byte[] value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	@Override
	public void setShort(String key, short value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	@Override
	public void setLong(String key, long value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	@Override
	public void setFloat(String key, float value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	@Override
	public void setDouble(String key, double value)
	{
		// TODO Auto-generated method stub
		setString(key, String.valueOf(value));
	}

	public String getConfig(String key, String defaultValue)
	{
		// TODO Auto-generated method stub
		return getProperties().getProperty(key, defaultValue);
	}

	@Override
	public String getString(String key, String defaultValue)
	{
		// TODO Auto-generated method stub
		return getConfig(key, defaultValue);
	}

	@Override
	public int getInt(String key, int defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return Integer.valueOf(getString(key, ""));
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;

	}

	@Override
	public boolean getBoolean(String key, Boolean defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return Boolean.valueOf(getString(key, ""));
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;
	}

	@Override
	public byte[] getByte(String key, byte[] defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return getString(key, "").getBytes();
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;
	}

	@Override
	public short getShort(String key, Short defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return Short.valueOf(getString(key, ""));
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;
	}

	@Override
	public long getLong(String key, Long defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return Long.valueOf(getString(key, ""));
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;
	}

	@Override
	public float getFloat(String key, Float defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return Float.valueOf(getString(key, ""));
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;
	}

	@Override
	public double getDouble(String key, Double defaultValue)
	{
		// TODO Auto-generated method stub
		try
		{
			return Double.valueOf(getString(key, ""));
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return defaultValue;
	}

	@Override
	public String getString(int resID, String defaultValue)
	{
		// TODO Auto-generated method stub
		return getString(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public int getInt(int resID, int defaultValue)
	{
		// TODO Auto-generated method stub
		return getInt(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public boolean getBoolean(int resID, Boolean defaultValue)
	{
		// TODO Auto-generated method stub
		return getBoolean(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public byte[] getByte(int resID, byte[] defaultValue)
	{
		// TODO Auto-generated method stub
		return getByte(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public short getShort(int resID, Short defaultValue)
	{
		// TODO Auto-generated method stub
		return getShort(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public long getLong(int resID, Long defaultValue)
	{
		// TODO Auto-generated method stub
		return getLong(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public float getFloat(int resID, Float defaultValue)
	{
		// TODO Auto-generated method stub
		return getFloat(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public double getDouble(int resID, Double defaultValue)
	{
		// TODO Auto-generated method stub
		return getDouble(this.mContext.getString(resID), defaultValue);
	}

	@Override
	public void setString(int resID, String value)
	{
		// TODO Auto-generated method stub
		setString(this.mContext.getString(resID), value);
	}

	@Override
	public void setInt(int resID, int value)
	{
		// TODO Auto-generated method stub
		setInt(this.mContext.getString(resID), value);
	}

	@Override
	public void setBoolean(int resID, Boolean value)
	{
		// TODO Auto-generated method stub
		setBoolean(this.mContext.getString(resID), value);
	}

	@Override
	public void setByte(int resID, byte[] value)
	{
		// TODO Auto-generated method stub
		setByte(this.mContext.getString(resID), value);
	}

	@Override
	public void setShort(int resID, short value)
	{
		// TODO Auto-generated method stub
		setShort(this.mContext.getString(resID), value);
	}

	@Override
	public void setLong(int resID, long value)
	{
		// TODO Auto-generated method stub
		setLong(this.mContext.getString(resID), value);
	}

	@Override
	public void setFloat(int resID, float value)
	{
		// TODO Auto-generated method stub
		setFloat(this.mContext.getString(resID), value);
	}

	@Override
	public void setDouble(int resID, double value)
	{
		// TODO Auto-generated method stub
		setDouble(this.mContext.getString(resID), value);
	}

	@Override
	public void setConfig(Object entity)
	{
		// TODO Auto-generated method stub
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{

			if (!TAReflectUtils.isTransient(field))
			{
				if (TAReflectUtils.isBaseDateType(field))
				{
					String columnName = TAReflectUtils.getFieldName(field);
					field.setAccessible(true);
					setValue(field, columnName, entity);
				}
			}
		}
	}

	private void setValue(Field field, String columnName, Object entity)
	{
		try
		{
			Class<?> clazz = field.getType();
			if (clazz.equals(String.class))
			{
				setString(columnName, (String) field.get(entity));
			} else if (clazz.equals(Integer.class) || clazz.equals(int.class))
			{
				setInt(columnName, (Integer) field.get(entity));
			} else if (clazz.equals(Float.class) || clazz.equals(float.class))
			{
				setFloat(columnName, (Float) field.get(entity));
			} else if (clazz.equals(Double.class) || clazz.equals(double.class))
			{
				setDouble(columnName, (Double) field.get(entity));
			} else if (clazz.equals(Short.class) || clazz.equals(Short.class))
			{
				setShort(columnName, (Short) field.get(entity));
			} else if (clazz.equals(Long.class) || clazz.equals(long.class))
			{
				setLong(columnName, (Long) field.get(entity));
			} else if (clazz.equals(Boolean.class))
			{
				setBoolean(columnName, (Boolean) field.get(entity));
			}
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public <T> T getConfig(Class<T> clazz)
	{
		// TODO Auto-generated method stub
		Field[] fields = clazz.getDeclaredFields();
		T entity = null;
		try
		{
			entity = (T) clazz.newInstance();
			for (Field field : fields)
			{
				field.setAccessible(true);
				if (!TAReflectUtils.isTransient(field))
				{
					if (TAReflectUtils.isBaseDateType(field))
					{

						String columnName = TAReflectUtils.getFieldName(field);
						field.setAccessible(true);
						getValue(field, columnName, entity);
					}
				}

			}
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return entity;
	}

	private <T> void getValue(Field field, String columnName, T entity)
	{
		try
		{
			Class<?> clazz = field.getType();
			if (clazz.equals(String.class))
			{
				field.set(entity, getString(columnName, ""));
			} else if (clazz.equals(Integer.class) || clazz.equals(int.class))
			{
				field.set(entity, getInt(columnName, 0));
			} else if (clazz.equals(Float.class) || clazz.equals(float.class))
			{
				field.set(entity, getFloat(columnName, 0f));
			} else if (clazz.equals(Double.class) || clazz.equals(double.class))
			{
				field.set(entity, getDouble(columnName, 0.0));
			} else if (clazz.equals(Short.class) || clazz.equals(Short.class))
			{
				field.set(entity, getShort(columnName, (short) 0));
			} else if (clazz.equals(Long.class) || clazz.equals(long.class))
			{
				field.set(entity, getLong(columnName, 0l));
			} else if (clazz.equals(Byte.class) || clazz.equals(byte.class))
			{
				field.set(entity, getByte(columnName, new byte[8]));
			} else if (clazz.equals(Boolean.class))
			{
				field.set(entity, getBoolean(columnName, false));
			}
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void remove(String key)
	{
		// TODO Auto-generated method stub
		Properties props = getProperties();
		props.remove(key);
		setProperties(props);
	}

	@Override
	public void remove(String... keys)
	{
		// TODO Auto-generated method stub
		Properties props = getProperties();
		for (String key : keys)
		{
			props.remove(key);
		}
		setProperties(props);
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		Properties props = getProperties();
		props.clear();
		setProperties(props);
	}

	@Override
	public void open()
	{
		// TODO Auto-generated method stub

	}
}
