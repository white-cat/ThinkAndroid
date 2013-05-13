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
package com.ta;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpRequest;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import android.content.Context;
import android.os.Message;

/**
 * @Title TASyncHttpClient
 * @Package com.ta
 * @Description TASyncHttpClient是网络请求的一个类,可以支持异步,也可以不开启异步来处理程序
 * @author 白猫
 * @date 2013-1-16 14:20
 * @version V1.0
 */
public class TASyncHttpClient extends AsyncHttpClient
{
	private int responseCode;
	protected String result;
	protected AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler()
	{

		@Override
		protected void sendResponseMessage(org.apache.http.HttpResponse response)
		{
			responseCode = response.getStatusLine().getStatusCode();
			super.sendResponseMessage(response);
		}

		@Override
		protected void sendMessage(Message msg)
		{
			handleMessage(msg);
		}

		@Override
		public void onSuccess(String content)
		{
			result = content;
		}

		@Override
		public void onFailure(Throwable error, String content)
		{
			result = onRequestFailed(error, content);
		}
	};

	public int getResponseCode()
	{
		return responseCode;
	}

	@Override
	protected void sendRequest(DefaultHttpClient client,
			HttpContext httpContext, HttpUriRequest uriRequest,
			String contentType, AsyncHttpResponseHandler responseHandler,
			Context context)
	{
		if (contentType != null)
		{
			uriRequest.addHeader("Content-Type", contentType);
		}
		new AsyncHttpRequest(client, httpContext, uriRequest, responseHandler)
				.run();
	}

	public String onRequestFailed(Throwable error, String content)
	{
		return "";
	}

	public void delete(String url, RequestParams queryParams,
			AsyncHttpResponseHandler responseHandler)
	{
		delete(url, responseHandler);
	}

	public String get(String url, RequestParams params)
	{
		this.get(url, params, responseHandler);
		return result;
	}

	public String get(String url)
	{
		this.get(url, null, responseHandler);
		return result;
	}

	public String put(String url, RequestParams params)
	{
		this.put(url, params, responseHandler);
		return result;
	}

	public String put(String url)
	{
		this.put(url, null, responseHandler);
		return result;
	}

	public String post(String url, RequestParams params)
	{
		this.post(url, params, responseHandler);
		return result;
	}

	public String post(String url)
	{
		this.post(url, null, responseHandler);
		return result;
	}

	public String delete(String url, RequestParams params)
	{
		this.delete(url, params, responseHandler);
		return result;
	}

	public String delete(String url)
	{
		this.delete(url, null, responseHandler);
		return result;
	}

}
