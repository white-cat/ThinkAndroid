package com.cat.util.stringcache;

import java.io.UnsupportedEncodingException;

import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cat.activity.R;
import com.ta.common.TAStringUtils;
import com.ta.util.cache.TACallBackHandler;

public class TAStringCallBackHandler extends TACallBackHandler<TextView>
{
	@Override
	public void onStart(TextView t, Object data)
	{
		// TODO Auto-generated method stub
		super.onStart(t, data);
	}

	@Override
	public void onSuccess(TextView wv, Object data, byte[] buffer)
	{
		// TODO Auto-generated method stub
		super.onSuccess(wv, data, buffer);
		FrameLayout view = (FrameLayout) wv.getParent();
		ProgressBar progressBar = (ProgressBar) view
				.findViewById(R.id.progress);
		progressBar.setVisibility(View.GONE);
		String content = new String(buffer);
		try
		{
			content = new String(buffer, "UTF -8");
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wv.setText(TAStringUtils.delHTMLTag(content));

	}

	@Override
	public void onFailure(TextView t, Object data)
	{
		// TODO Auto-generated method stub
		super.onFailure(t, data);
	}
}
