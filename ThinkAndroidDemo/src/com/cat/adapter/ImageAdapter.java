package com.cat.adapter;

import com.cat.activity.R;
import com.cat.data.ImagesUrls;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TABitmapCallBackHanlder;
import com.ta.util.bitmap.TADownloadBitmapHandler;
import com.ta.util.extend.draw.DensityUtils;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{

	private final Context mContext;
	private int mNumColumns = 0;
	private TABitmapCacheWork imageFetcher;

	public ImageAdapter(Context context, TAApplication application)
	{
		super();
		TADownloadBitmapHandler downloadBitmapFetcher = new TADownloadBitmapHandler(
				context, DensityUtils.dipTopx(context, 128),
				DensityUtils.dipTopx(context, 128));
		TABitmapCallBackHanlder taBitmapCallBackHanlder = new TABitmapCallBackHanlder();
		taBitmapCallBackHanlder
				.setLoadingImage(context, R.drawable.empty_photo);
		imageFetcher = new TABitmapCacheWork(context);
		imageFetcher.setProcessDataHandler(downloadBitmapFetcher);
		imageFetcher.setCallBackHandler(taBitmapCallBackHanlder);
		imageFetcher.setFileCache(application.getFileCache());
		mContext = context;

	}

	@Override
	public int getCount()
	{
		return ImagesUrls.imageThumbUrls.length + mNumColumns;
	}

	@Override
	public Object getItem(int position)
	{
		return position < mNumColumns ? null
				: ImagesUrls.imageThumbUrls[position - mNumColumns];
	}

	@Override
	public long getItemId(int position)
	{
		return position < mNumColumns ? 0 : position - mNumColumns;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container)
	{

		ImageView imageView;
		if (convertView == null)
		{
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(100, 100));// 设置ImageView宽高
			// imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		} else
		{
			imageView = (ImageView) convertView;
		}
		imageFetcher.loadFormCache(ImagesUrls.imageThumbUrls[position
				- mNumColumns], imageView);
		return imageView;
	}

	public void setNumColumns(int numColumns)
	{
		mNumColumns = numColumns;
	}

	public int getNumColumns()
	{
		return mNumColumns;
	}
}