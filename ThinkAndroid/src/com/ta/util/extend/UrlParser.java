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

import java.net.URL;

/**
 * @Title UrlParser
 * @Package com.ta.util
 * @Description url转换工具
 * @author 白猫
 * @date 2013-1-22
 * @version V1.0
 */
public class UrlParser
{
	/**
	 * url转换工具,确保url为绝对路径
	 * 
	 * @param baseUrl
	 *            url的根域名
	 * @param url
	 *            需要转换的url(绝对路径，或相对路径)
	 * @return 返回绝对路径url
	 */
	public static String urlParse(String baseUrl, String url)
	{
		String returnUrl = "";
		try
		{
			URL absoluteUrl = new URL(baseUrl);
			URL parseUrl = new URL(absoluteUrl, url);
			returnUrl = parseUrl.toString();
		} catch (Exception e)
		{
			// TODO: handle exception
			e.getStackTrace();
		}
		return returnUrl;

	}

}
