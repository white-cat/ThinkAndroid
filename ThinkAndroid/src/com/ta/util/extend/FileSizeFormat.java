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
package com.ta.util.extend;

import java.text.DecimalFormat;

public class FileSizeFormat
{
	private static String kB_UNIT_NAME = "KB";
	private static String B_UNIT_NAME = "B";
	private static String MB_UNIT_NAME = "MB";

	public static String getSizeString(long size)
	{
		if (size < 1024)
		{
			return String.valueOf(size) + B_UNIT_NAME;
		} else
		{
			size = size / 1024;
		}
		if (size < 1024)
		{
			return String.valueOf(size) + kB_UNIT_NAME;
		} else
		{
			size = size * 100 / 1024;
		}

		return String.valueOf((size / 100)) + "."
				+ ((size % 100) < 10 ? "0" : "") + String.valueOf((size % 100))
				+ MB_UNIT_NAME;
	}

	/**
	 * 以Mb为单位保留两位小数
	 * 
	 * @param dirSize
	 * @return
	 */
	public static String getMbSize(long dirSize)
	{
		double size = 0;
		size = (dirSize + 0.0) / (1024 * 1024);
		DecimalFormat df = new DecimalFormat("0.00");// 以Mb为单位保留两位小数
		String filesize = df.format(size);
		return filesize;
	}

	/**
	 * 以Mb为单位保留两位小数
	 * 
	 * @param dirSize
	 * @return
	 */
	public static String getKBSize(long dirSize)
	{
		double size = 0;
		size = (dirSize + 0.0) / 1024;
		DecimalFormat df = new DecimalFormat("0.00");// 以KB为单位保留两位小数
		String filesize = df.format(size);
		return filesize;
	}
}
