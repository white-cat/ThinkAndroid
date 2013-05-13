package com.cat.adapter;

import java.util.HashMap;

import com.cat.activity.R;
import com.ta.common.TAStringUtils;
import com.ta.util.http.FileHttpResponseHandler;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ViewHolder
{

	public static final int KEY_URL = 0;
	public static final int KEY_SPEED = 1;
	public static final int KEY_PROGRESS = 2;
	public static final int KEY_IS_PAUSED = 3;

	public TextView titleText;
	public ProgressBar progressBar;
	public TextView speedText;
	public Button pauseButton;
	public Button deleteButton;
	public Button continueButton;

	private boolean hasInited = false;

	public ViewHolder(View parentView)
	{
		if (parentView != null)
		{
			titleText = (TextView) parentView
					.findViewById(com.cat.activity.R.id.title);
			speedText = (TextView) parentView.findViewById(R.id.speed);
			progressBar = (ProgressBar) parentView
					.findViewById(R.id.progress_bar);
			pauseButton = (Button) parentView.findViewById(R.id.btn_pause);
			deleteButton = (Button) parentView.findViewById(R.id.btn_delete);
			continueButton = (Button) parentView
					.findViewById(R.id.btn_continue);
			hasInited = true;
		}
	}

	public static HashMap<Integer, String> getItemDataMap(String url,
			String speed, String progress, String isPaused)
	{
		HashMap<Integer, String> item = new HashMap<Integer, String>();
		item.put(KEY_URL, url);
		item.put(KEY_SPEED, speed);
		item.put(KEY_PROGRESS, progress);
		item.put(KEY_IS_PAUSED, isPaused);
		return item;
	}

	public void setData(HashMap<Integer, String> item)
	{
		if (hasInited)
		{
			titleText.setText(TAStringUtils.getFileNameFromUrl(item
					.get(KEY_URL)));
			speedText.setText(item.get(KEY_SPEED));
			String progress = item.get(KEY_PROGRESS);
			if (TextUtils.isEmpty(progress))
			{
				progressBar.setProgress(0);
			} else
			{
				progressBar.setProgress(Integer.parseInt(progress));
			}
			if (Boolean.parseBoolean(item.get(KEY_IS_PAUSED)))
			{
				onPause();
			}
		}
	}

	public void onPause()
	{
		if (hasInited)
		{
			pauseButton.setVisibility(View.GONE);
			continueButton.setVisibility(View.VISIBLE);
		}
	}

	public void setData(String url, String speed, String progress)
	{
		setData(url, speed, progress, false + "");
	}

	public void setData(String url, String speed, String progress,
			String isPaused)
	{
		if (hasInited)
		{
			HashMap<Integer, String> item = getItemDataMap(url, speed,
					progress, isPaused);

			titleText.setText(TAStringUtils.getFileNameFromUrl(item
					.get(KEY_URL)));
			speedText.setText(speed);
			if (TextUtils.isEmpty(progress))
			{
				progressBar.setProgress(0);
			} else
			{
				progressBar
						.setProgress(Integer.parseInt(item.get(KEY_PROGRESS)));
			}

		}
	}

}
