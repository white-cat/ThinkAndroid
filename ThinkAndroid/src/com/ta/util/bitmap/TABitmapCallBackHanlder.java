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
package com.ta.util.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.ta.util.cache.TACallBackHandler;

public class TABitmapCallBackHanlder extends TACallBackHandler<ImageView>
{
	private Bitmap mLoadingBitmap;

	@Override
	public void onStart(ImageView t, Object data)
	{
		// TODO Auto-generated method stub
		super.onStart(t, data);
		onSuccess(t, data, null);
	}

	@Override
	public void onSuccess(ImageView imageView, Object data, byte[] buffer)
	{
		// TODO Auto-generated method stub
		super.onSuccess(imageView, data, buffer);
		if (buffer != null && imageView != null)
		{
			Bitmap bitmap = null;
			try
			{
				if (buffer != null)
				{
					bitmap = BitmapFactory.decodeByteArray(buffer, 0,
							buffer.length);
				}
				setImageBitmap(imageView, bitmap);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			if (mLoadingBitmap != null)
			{
				setImageBitmap(imageView, mLoadingBitmap);
			}
		}
	}

	@Override
	public void onFailure(ImageView t, Object data)
	{
		// TODO Auto-generated method stub
		super.onFailure(t, data);
	}

	/**
	 * 设置默认的加载图片
	 * 
	 * @param defaultBitmap
	 */
	public void setLoadingImage(Bitmap bitmap)
	{
		this.mLoadingBitmap = bitmap;
	}

	public void setLoadingImage(Context context, int resId)
	{
		this.mLoadingBitmap = BitmapFactory.decodeResource(
				context.getResources(), resId);
	}

	/**
	 * 设置Bitmap到ImageView
	 * 
	 * @param imageView
	 * @param bitmap
	 */
	private void setImageBitmap(ImageView imageView, Bitmap bitmap)
	{
		imageView.setImageBitmap(bitmap);
	}
}
