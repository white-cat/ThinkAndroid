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
package com.ta.util.download;

import java.util.ArrayList;
import java.util.List;
import com.ta.TAApplication;
import com.ta.common.TAStringUtils;

public class DownLoadConfigUtil
{
	public static final String PREFERENCE_NAME = "com.yyxu.download";
	public static final int URL_COUNT = 3;
	public static final String KEY_URL = "url";

	public static void storeURL(int index, String url)
	{
		TAApplication.getApplication().getCurrentConfig()
				.setString(KEY_URL + index, url);
	}

	public static void clearURL(int index)
	{
		TAApplication.getApplication().getCurrentConfig()
				.remove(KEY_URL + index);
	}

	public static String getURL(int index)
	{
		return TAApplication.getApplication().getCurrentConfig()
				.getString(KEY_URL + index, "");
	}

	public static List<String> getURLArray()
	{
		List<String> urlList = new ArrayList<String>();
		for (int i = 0; i < URL_COUNT; i++)
		{
			if (!TAStringUtils.isEmpty(getURL(i)))
			{
				urlList.add(getString(KEY_URL + i));
			}
		}
		return urlList;
	}

	private static String getString(String key)
	{
		// TODO Auto-generated method stub
		return TAApplication.getApplication().getCurrentConfig()
				.getString(key, "");
	}

}
