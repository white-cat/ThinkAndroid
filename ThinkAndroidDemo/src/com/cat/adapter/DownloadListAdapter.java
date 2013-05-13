package com.cat.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import com.cat.activity.R;
import com.ta.util.TALogger;
import com.ta.util.download.DownLoadCallback;
import com.ta.util.download.DownloadManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DownloadListAdapter extends BaseAdapter
{

	private Context mContext;
	private ArrayList<HashMap<Integer, String>> dataList;
	private DownloadManager downloadManager;
	private ListView downloadList;

	public DownloadListAdapter(Context context, ListView listView)
	{
		mContext = context;
		this.downloadList = listView;
		dataList = new ArrayList<HashMap<Integer, String>>();
		downloadManager = DownloadManager.getDownloadManager();
		downloadManager.setDownLoadCallback(new DownLoadCallback()
		{
			@Override
			public void onSuccess(String url)
			{
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "下载完成", Toast.LENGTH_LONG).show();
				removeItem(url);
			}

			@Override
			public void onLoading(String url, long totalSize, long currentSize,
					long speed)
			{
				// TODO Auto-generated method stub
				super.onLoading(url, totalSize, currentSize, speed);
				long downloadPercent = currentSize * 100 / totalSize;
				View taskListItem = downloadList.findViewWithTag(url);
				ViewHolder viewHolder = new ViewHolder(taskListItem);
				viewHolder.setData(url, speed + "kbs" + "|"
						+ totalSize + "|" + currentSize,
						downloadPercent + "");
				TALogger.d(DownloadListAdapter.this, "speed" + speed + "kbps"
						+ "downloadPercent" + downloadPercent);
			}
		});
	}

	@Override
	public int getCount()
	{
		return dataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	public void addItem(String url)
	{
		addItem(url, false);
	}

	public void addItem(String url, boolean isPaused)
	{
		HashMap<Integer, String> item = new HashMap<Integer, String>();
		item.put(ViewHolder.KEY_URL, url);
		dataList.add(item);
		this.notifyDataSetChanged();
	}

	public void removeItem(String url)
	{
		String tmp;
		for (int i = 0; i < dataList.size(); i++)
		{
			tmp = dataList.get(i).get(ViewHolder.KEY_URL);
			if (tmp.equals(url))
			{
				dataList.remove(i);
				this.notifyDataSetChanged();
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.download_list_item, null);
		}

		HashMap<Integer, String> itemData = dataList.get(position);
		String url = itemData.get(ViewHolder.KEY_URL);
		convertView.setTag(url);
		ViewHolder viewHolder = new ViewHolder(convertView);
		viewHolder.setData(itemData);

		viewHolder.continueButton.setOnClickListener(new DownloadBtnListener(
				url, viewHolder));
		viewHolder.pauseButton.setOnClickListener(new DownloadBtnListener(url,
				viewHolder));
		viewHolder.deleteButton.setOnClickListener(new DownloadBtnListener(url,
				viewHolder));

		return convertView;
	}

	private class DownloadBtnListener implements View.OnClickListener
	{
		private String url;
		private ViewHolder mViewHolder;

		public DownloadBtnListener(String url, ViewHolder viewHolder)
		{
			this.url = url;
			this.mViewHolder = viewHolder;
		}

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btn_continue:

				downloadManager.continueHandler(url);
				mViewHolder.continueButton.setVisibility(View.GONE);
				mViewHolder.pauseButton.setVisibility(View.VISIBLE);

				break;
			case R.id.btn_pause:

				downloadManager.pauseHandler(url);
				mViewHolder.continueButton.setVisibility(View.VISIBLE);
				mViewHolder.pauseButton.setVisibility(View.GONE);

				break;
			case R.id.btn_delete:
				downloadManager.deleteHandler(url);
				removeItem(url);

				break;
			}
		}
	}

}