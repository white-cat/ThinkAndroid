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
package com.ta.mvc.controller;

import com.ta.TAActivity;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;

public class ActivityStackInfo
{
	private Class<? extends TAActivity> activityClass;
	private String commandKey;
	private TARequest request;
	private boolean record;
	private boolean resetStack;
	private TAResponse response;

	public ActivityStackInfo()
	{
	}

	public ActivityStackInfo(String commandKey, TARequest request,
			boolean record, boolean resetStack)
	{
		this.commandKey = commandKey;
		this.request = request;
		this.record = record;
		this.resetStack = resetStack;
	}

	public ActivityStackInfo(Class<? extends TAActivity> activityClass,
			String commandKey, TARequest request)
	{
		this.activityClass = activityClass;
		this.commandKey = commandKey;
		this.request = request;
	}

	public ActivityStackInfo(Class<? extends TAActivity> activityClass,
			String commandKey, TARequest request, boolean record,
			boolean resetStack)
	{
		this.activityClass = activityClass;
		this.commandKey = commandKey;
		this.request = request;
		this.record = record;
		this.resetStack = resetStack;
	}

	public Class<? extends TAActivity> getActivityClass()
	{
		return activityClass;
	}

	public void setActivityClass(Class<? extends TAActivity> activityClass)
	{
		this.activityClass = activityClass;
	}

	public String getCommandKey()
	{
		return commandKey;
	}

	public void setCommandKey(String commandKey)
	{
		this.commandKey = commandKey;
	}

	public TARequest getRequest()
	{
		return request;
	}

	public void setRequest(TARequest request)
	{
		this.request = request;
	}

	public boolean isRecord()
	{
		return record;
	}

	public void setRecord(boolean record)
	{
		this.record = record;
	}

	public TAResponse getResponse()
	{
		return response;
	}

	public void setResponse(TAResponse response)
	{
		this.response = response;
	}

	public boolean isResetStack()
	{
		return resetStack;
	}

	public void setResetStack(boolean resetStack)
	{
		this.resetStack = resetStack;
	}
}
