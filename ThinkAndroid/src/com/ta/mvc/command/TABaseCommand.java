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
package com.ta.mvc.command;

import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;

public abstract class TABaseCommand implements TAICommand
{
	private TARequest request;
	private TAResponse response;
	private TAIResponseListener responseListener;
	private boolean terminated;

	@Override
	public TARequest getRequest()
	{
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	public void setRequest(TARequest request)
	{
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public TAResponse getResponse()
	{
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public void setResponse(TAResponse response)
	{
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public TAIResponseListener getResponseListener()
	{
		// TODO Auto-generated method stub
		return responseListener;
	}

	@Override
	public void setResponseListener(TAIResponseListener responseListener)
	{
		// TODO Auto-generated method stub
		this.responseListener = responseListener;
	}

	@Override
	public void setTerminated(boolean terminated)
	{
		// TODO Auto-generated method stub
		this.terminated = terminated;
	}

	@Override
	public boolean isTerminated()
	{
		// TODO Auto-generated method stub
		return terminated;
	}

}
