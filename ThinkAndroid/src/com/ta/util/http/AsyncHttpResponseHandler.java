package com.ta.util.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.apache.http.Header;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class AsyncHttpResponseHandler
{
	protected static final int SUCCESS_MESSAGE = 4;
	protected static final int FAILURE_MESSAGE = 1;
	protected static final int START_MESSAGE = 2;
	protected static final int FINISH_MESSAGE = 3;
	protected static final int PROGRESS_MESSAGE = 0;
	private Handler handler;

	public AsyncHttpResponseHandler()
	{
		if (Looper.myLooper() != null)
		{
			handler = new Handler()
			{
				@Override
				public void handleMessage(Message msg)
				{
					AsyncHttpResponseHandler.this.handleMessage(msg);
				}
			};
		}
	}

	public void onStart()
	{
	}

	public void onFinish()
	{
	}

	public void onSuccess(String content)
	{

	}

	public void onProgress(long totalSize, long currentSize, long speed)
	{

	}

	public void onSuccess(int statusCode, Header[] headers, String content)
	{
		onSuccess(statusCode, content);
	}

	public void onSuccess(int statusCode, String content)
	{
		onSuccess(content);
	}

	public void onFailure(Throwable error)
	{

	}

	public void onFailure(Throwable error, String content)
	{

		onFailure(error);
	}

	protected void sendSuccessMessage(int statusCode, Header[] headers,
			String responseBody)
	{
		sendMessage(obtainMessage(SUCCESS_MESSAGE, new Object[]
		{ new Integer(statusCode), headers, responseBody }));
	}

	protected void sendFailureMessage(Throwable e, String responseBody)
	{
		sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]
		{ e, responseBody }));
	}

	protected void sendFailureMessage(Throwable e, byte[] responseBody)
	{
		sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]
		{ e, responseBody }));
	}

	protected void sendStartMessage()
	{
		sendMessage(obtainMessage(START_MESSAGE, null));
	}

	protected void sendFinishMessage()
	{
		sendMessage(obtainMessage(FINISH_MESSAGE, null));
	}

	protected void handleSuccessMessage(int statusCode, Header[] headers,
			String responseBody)
	{
		onSuccess(statusCode, headers, responseBody);
	}

	protected void handleFailureMessage(Throwable e, String responseBody)
	{
		onFailure(e, responseBody);
	}

	protected void handleProgressMessage(long totalSize, long currentSize,
			long speed)
	{
		onProgress(totalSize, currentSize, speed);
	}

	protected void handleMessage(Message msg)
	{
		Object[] response;

		switch (msg.what)
		{
		case PROGRESS_MESSAGE:
			response = (Object[]) msg.obj;
			handleProgressMessage((Long) response[0], (Long) response[1],
					(Long) response[2]);
			break;
		case SUCCESS_MESSAGE:
			response = (Object[]) msg.obj;
			handleSuccessMessage(((Integer) response[0]).intValue(),
					(Header[]) response[1], (String) response[2]);
			break;
		case FAILURE_MESSAGE:
			response = (Object[]) msg.obj;
			handleFailureMessage((Throwable) response[0], (String) response[1]);
			break;
		case START_MESSAGE:
			onStart();
			break;
		case FINISH_MESSAGE:
			onFinish();
			break;

		}
	}

	protected void sendMessage(Message msg)
	{
		if (handler != null)
		{
			handler.sendMessage(msg);
		} else
		{
			handleMessage(msg);
		}
	}

	protected Message obtainMessage(int responseMessage, Object response)
	{
		Message msg = null;
		if (handler != null)
		{
			msg = this.handler.obtainMessage(responseMessage, response);
		} else
		{
			msg = Message.obtain();
			msg.what = responseMessage;
			msg.obj = response;
		}
		return msg;
	}

	protected void sendResponseMessage(HttpResponse response)
	{
		StatusLine status = response.getStatusLine();
		String responseBody = null;
		try
		{
			HttpEntity entity = null;
			HttpEntity temp = response.getEntity();
			if (temp != null)
			{
				entity = new BufferedHttpEntity(temp);
				responseBody = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e)
		{
			sendFailureMessage(e, (String) null);
		}

		if (status.getStatusCode() >= 300)
		{
			sendFailureMessage(new HttpResponseException(
					status.getStatusCode(), status.getReasonPhrase()),
					responseBody);
		} else
		{
			sendSuccessMessage(status.getStatusCode(),
					response.getAllHeaders(), responseBody);
		}
	}
}
