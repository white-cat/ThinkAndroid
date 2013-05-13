package com.cat.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cat.entity.TestDataEntity;
import com.ta.TAActivity;
import com.ta.TAApplication;
import com.ta.annotation.TAInjectView;
import com.ta.util.TAComparator;
import com.ta.util.config.TAIConfig;
import com.ta.util.resoperate.TAPreferenceOperateUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThinkAndroidOtherActivtiy extends TAActivity
{
	@TAInjectView(id = R.id.test_configure)
	private Button testConfigureButton;
	@TAInjectView(id = R.id.test_resoperate)
	private Button testResoperateButton;
	@TAInjectView(id = R.id.test_comparator)
	private Button testComparatorButton;
	@TAInjectView(id = R.id.show_view)
	private TextView showViewTextView;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onAfterOnCreate(savedInstanceState);
		setTitle(R.string.thinkandroid_test_other_title);

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
				case R.id.test_configure:
					testConfig();
					break;
				case R.id.test_resoperate:
					testResoperate();
					break;
				case R.id.test_comparator:
					testComparator();
					break;

				default:
					break;
				}
			}

		};
		testConfigureButton.setOnClickListener(onClickListener);
		testResoperateButton.setOnClickListener(onClickListener);
		testComparatorButton.setOnClickListener(onClickListener);

	}

	private void testConfig()
	{
		TAIConfig config;
		// confing有两种Preference和properties
		config = getTAApplication().getConfig(TAApplication.PROPERTIESCONFIG);
		TestDataEntity testDataEntity = new TestDataEntity();
		testDataEntity.setName("Think Android ADD");
		testDataEntity.setB(true);
		Character c1 = new Character('c');
		testDataEntity.setC(c1);
		testDataEntity.setD(10);
		testDataEntity.setDate(new Date());
		testDataEntity.setF(2f);
		testDataEntity.setI(123);
		// 保存配置
		config.setConfig(testDataEntity);
		// 读取配置
		TestDataEntity dataEntity = config.getConfig(TestDataEntity.class);
		showViewTextView.setText(dataEntity.toString());
	}

	private void testResoperate()
	{
		// 这个在资源包中，TAPreferenceOperateUtils，TAPropertiesOperateUtils使用起来很简单
		TAPreferenceOperateUtils OperateUtils = new TAPreferenceOperateUtils(
				this);
		OperateUtils.setBoolean("henhao", true);
		OperateUtils.getBoolean("henhao", false);
		makeToast("没有显示具体看源代码");
	}

	private void testComparator()
	{
		// @TACompareAnnotation(sortFlag = 0) 根据实体中这个注解的字段进行排序
		// 如果要对集合对象或数组对象进行排
		TAComparator<TestDataEntity> comparator = new TAComparator<TestDataEntity>(
				TAComparator.ASC_SORT_TYPE);
		List<TestDataEntity> listTestDataEntities = new ArrayList<TestDataEntity>();
		// 对集合对象进行排序
		Collections.sort(listTestDataEntities, comparator);
		makeToast("没有显示具体看源代码");
	}

	private void makeToast(String content)
	{
		Toast.makeText(this, content, Toast.LENGTH_LONG).show();
	}
}