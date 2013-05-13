package com.cat.util.stringcache;

import com.ta.TASyncHttpClient;
import com.ta.util.cache.TAProcessDataHandler;

public class TAProcessStringHandler extends TAProcessDataHandler
{
	@Override
	public byte[] processData(Object data)
	{
		// TODO Auto-generated method stub
		TASyncHttpClient client = new TASyncHttpClient();
		String returnString = client.get(data.toString());
		if (returnString == null || returnString.equalsIgnoreCase(""))
		{
			return null;
		}
		byte[] bytes = null;
		try
		{
			bytes = returnString.getBytes("UTF-8");
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return bytes;
	}
}
