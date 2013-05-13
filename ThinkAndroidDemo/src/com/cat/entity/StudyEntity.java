package com.cat.entity;

public class StudyEntity
{
	String name;
	int age;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "name:" + name + "," + "age:" + age + "\n";
	}
}
