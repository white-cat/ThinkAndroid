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
package com.ta.util.extend.app;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @Title SIMCardInfoUtil
 * @Package com.ta.util.extend.app
 * @Description 用户返回客户端SIM卡的一些信息 需要开启 <uses-permission
 *              android:name="android.permission.READ_PHONE_STATE"/>
 * @author 白猫
 * @date 2013-1-10 下午 13:53
 * @version V1.0
 */
public class SIMCardInfoUtil
{
	/**
	 * 返回本地手机号码，这个号码不一定能获取到
	 * 
	 * @param context
	 * @return
	 */
	public static String getNativePhoneNumber(Context context)
	{
		TelephonyManager telephonyManager;
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String NativePhoneNumber = null;
		NativePhoneNumber = telephonyManager.getLine1Number();
		return NativePhoneNumber;
	}

	/**
	 * 返回手机服务商名字
	 * 
	 * @param context
	 * @return
	 */
	public static String getProvidersName(Context context)
	{
		String ProvidersName = null;
		// 返回唯一的用户ID;就是这张卡的编号神马的
		String IMSI = getIMSI(context);
		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		System.out.println(IMSI);
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002"))
		{
			ProvidersName = "中国移动";
		} else if (IMSI.startsWith("46001"))
		{
			ProvidersName = "中国联通";
		} else if (IMSI.startsWith("46003"))
		{
			ProvidersName = "中国电信";
		} else
		{
			ProvidersName = "其他服务商";
		}
		return ProvidersName;
	}

	/**
	 * 返回手机IMSI号码
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context)
	{
		TelephonyManager telephonyManager;
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// 返回唯一的用户ID;就是这张卡的IMSI编号
		return telephonyManager.getSubscriberId();
	}
}