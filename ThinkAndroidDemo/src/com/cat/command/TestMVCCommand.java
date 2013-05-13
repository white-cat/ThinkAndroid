package com.cat.command;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cat.entity.StudyEntity;
import com.ta.mvc.command.TACommand;
import com.ta.mvc.common.TARequest;

public class TestMVCCommand extends TACommand
{

	@Override
	protected void executeCommand()
	{
		// TODO Auto-generated method stub
		TARequest request = getRequest();
		String strJson = (String) request.getData();
		ArrayList<StudyEntity> arrayList = new ArrayList<StudyEntity>();
		try
		{
			JSONObject jo = new JSONObject(strJson);
			JSONArray jsonArray = (JSONArray) jo.get("students");
			for (int i = 0; i < jsonArray.length(); ++i)
			{
				JSONObject o = (JSONObject) jsonArray.get(i);
				StudyEntity studyEntity = new StudyEntity();
				studyEntity.setName(o.getString("name"));
				studyEntity.setAge(o.getInt("age"));
				arrayList.add(studyEntity);
				System.out.println("name:" + o.getString("name") + "," + "age:"
						+ o.getInt("age"));
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		} finally
		{
			sendSuccessMessage(arrayList);
		}

	}

}
