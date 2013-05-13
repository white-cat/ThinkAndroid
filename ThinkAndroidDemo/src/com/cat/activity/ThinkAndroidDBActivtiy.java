package com.cat.activity;

import java.util.Date;
import java.util.List;

import com.cat.entity.TestDataEntity;
import com.ta.annotation.TAInjectView;
import com.ta.util.db.TASQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThinkAndroidDBActivtiy extends ThinkAndroidBaseActivity
{
	@TAInjectView(id = R.id.insert_data)
	Button insertDataButton;
	@TAInjectView(id = R.id.select_data)
	Button selectDataButton;
	@TAInjectView(id = R.id.update_data)
	Button updateDataButton;
	@TAInjectView(id = R.id.delete_data)
	Button deleteDataButton;
	@TAInjectView(id = R.id.show_data)
	TextView showDataTextView;

	private TASQLiteDatabase sqLiteDatabase;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_db_title);
		createDabaBase();
	}

	/**
	 * 这个方法只是用于测试
	 */
	private void createDabaBase()
	{
		// TODO Auto-generated method stub
		sqLiteDatabase = getTAApplication().getSQLiteDatabasePool()
				.getSQLiteDatabase();
		// 创建表的操作
		// tadbAdapter.creatTable(TestDataEntity.class);
		// tadbAdapter.hasTable(TestDataEntity.class);
		// tadbAdapter.dropTable(TestDataEntity.class);
		if (sqLiteDatabase != null)
		{
			if (sqLiteDatabase.hasTable(TestDataEntity.class))
			{
				sqLiteDatabase.dropTable(TestDataEntity.class);
			}
			sqLiteDatabase.creatTable(TestDataEntity.class);

		}
	}

	/**
	 * 释放数据链接
	 */
	private void releaseSQLiteDatabase()
	{
		getTAApplication().getSQLiteDatabasePool().releaseSQLiteDatabase(
				sqLiteDatabase);
	}

	private void insertData()
	{
		// TODO Auto-generated method stub
		TestDataEntity testDataEntity = new TestDataEntity();
		testDataEntity.setName("Think Android ADD");
		testDataEntity.setB(true);
		Character c1 = new Character('c');
		testDataEntity.setC(c1);
		testDataEntity.setD(10);
		testDataEntity.setDate(new Date());
		testDataEntity.setF(2f);
		testDataEntity.setI(123);
		sqLiteDatabase.insert(testDataEntity);
		selectData();
	}

	private void selectData()
	{
		// TODO Auto-generated method stub
		String showString = "";
		List<TestDataEntity> list = sqLiteDatabase.query(TestDataEntity.class,
				false, null, null, null, null, null);
		for (int i = 0; i < list.size(); i++)
		{
			TestDataEntity testDataEntity = list.get(i);
			showString = showString + testDataEntity.toString();
		}
		show(showString);
	}

	private void updateData()
	{
		// TODO Auto-generated method stub
		TestDataEntity testDataEntity = new TestDataEntity();
		testDataEntity.setName("Think Android you");
		testDataEntity.setB(true);
		Character c1 = new Character('c');
		testDataEntity.setC(c1);
		testDataEntity.setD(10);
		testDataEntity.setDate(new Date());
		testDataEntity.setF(2f);
		testDataEntity.setI(123);
		sqLiteDatabase.update(testDataEntity, "username="
				+ "'Think Android ADD'");
		selectData();
	}

	private void deleteData()
	{
		sqLiteDatabase.delete(TestDataEntity.class, "i=" + "123");
		selectData();
	}

	private void show(String showString)
	{
		showDataTextView.setText(showString);
	}

	@Override
	protected void onAfterSetContentView()
	{
		// TODO Auto-generated method stub
		super.onAfterSetContentView();
		OnClickListener onClickListener = new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				switch (v.getId())
				{
				case R.id.insert_data:
					insertData();
					break;
				case R.id.select_data:
					selectData();
					break;
				case R.id.update_data:
					updateData();
					break;
				case R.id.delete_data:
					deleteData();
					break;
				default:
					break;
				}
			}
		};
		insertDataButton.setOnClickListener(onClickListener);
		selectDataButton.setOnClickListener(onClickListener);
		updateDataButton.setOnClickListener(onClickListener);
		deleteDataButton.setOnClickListener(onClickListener);

	}

}