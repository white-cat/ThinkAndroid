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
package com.ta.util;

import java.lang.reflect.Field;

import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectResource;
import com.ta.annotation.TAInjectView;

import android.app.Activity;
import android.content.res.Resources;

public class TAInjector
{
	private static TAInjector instance;

	private TAInjector()
	{

	}

	public static TAInjector getInstance()
	{
		if (instance == null)
		{
			instance = new TAInjector();
		}
		return instance;
	}

	public void inJectAll(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(TAInjectView.class))
				{
					injectView(activity, field);
				} else if (field.isAnnotationPresent(TAInjectResource.class))
				{
					injectResource(activity, field);
				} else if (field.isAnnotationPresent(TAInject.class))
				{
					inject(activity, field);
				}
			}
		}
	}

	private void inject(Activity activity, Field field)
	{
		// TODO Auto-generated method stub
		try
		{
			field.setAccessible(true);
			field.set(activity, field.getType().newInstance());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void injectView(Activity activity, Field field)
	{
		// TODO Auto-generated method stub
		if (field.isAnnotationPresent(TAInjectView.class))
		{
			TAInjectView viewInject = field.getAnnotation(TAInjectView.class);
			int viewId = viewInject.id();
			try
			{
				field.setAccessible(true);
				field.set(activity, activity.findViewById(viewId));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void injectResource(Activity activity, Field field)
	{
		// TODO Auto-generated method stub
		if (field.isAnnotationPresent(TAInjectResource.class))
		{
			TAInjectResource resourceJect = field
					.getAnnotation(TAInjectResource.class);
			int resourceID = resourceJect.id();
			try
			{
				field.setAccessible(true);
				Resources resources = activity.getResources();
				String type = resources.getResourceTypeName(resourceID);
				if (type.equalsIgnoreCase("string"))
				{
					field.set(activity,
							activity.getResources().getString(resourceID));
				} else if (type.equalsIgnoreCase("drawable"))
				{
					field.set(activity,
							activity.getResources().getDrawable(resourceID));
				} else if (type.equalsIgnoreCase("layout"))
				{
					field.set(activity,
							activity.getResources().getLayout(resourceID));
				} else if (type.equalsIgnoreCase("array"))
				{
					if (field.getType().equals(int[].class))
					{
						field.set(activity, activity.getResources()
								.getIntArray(resourceID));
					} else if (field.getType().equals(String[].class))
					{
						field.set(activity, activity.getResources()
								.getStringArray(resourceID));
					} else
					{
						field.set(activity, activity.getResources()
								.getStringArray(resourceID));
					}

				} else if (type.equalsIgnoreCase("color"))
				{
					if (field.getType().equals(Integer.TYPE))
					{
						field.set(activity,
								activity.getResources().getColor(resourceID));
					} else
					{
						field.set(activity, activity.getResources()
								.getColorStateList(resourceID));
					}

				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void inject(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(TAInject.class))
				{
					inject(activity, field);
				}
			}
		}
	}

	public void injectView(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(TAInjectView.class))
				{
					injectView(activity, field);
				}
			}
		}
	}

	public void injectResource(Activity activity)
	{
		// TODO Auto-generated method stub
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				if (field.isAnnotationPresent(TAInjectResource.class))
				{
					injectResource(activity, field);
				}
			}
		}
	}

}
