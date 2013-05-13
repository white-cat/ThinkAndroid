package com.cat.entity;

import java.io.Serializable;
import java.util.Date;
import com.ta.annotation.TACompareAnnotation;
import com.ta.util.db.annotation.TAColumn;
import com.ta.util.db.annotation.TAPrimaryKey;
import com.ta.util.db.annotation.TATransient;

public class TestDataEntity implements Serializable
{

	@TATransient
	private static final long serialVersionUID = -7995717179024306707L;
	@TAPrimaryKey(autoIncrement = true)
	int auto;
	@TAColumn(name = "username")
	String name;
	Date date;
	char c;
	@TACompareAnnotation(sortFlag = 0)
	int i;
	Boolean b;
	float f;
	double d;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public char getC()
	{
		return c;
	}

	public void setC(char c)
	{
		this.c = c;
	}

	public int getI()
	{
		return i;
	}

	public void setI(int i)
	{
		this.i = i;
	}

	public Boolean getB()
	{
		return b;
	}

	public void setB(Boolean b)
	{
		this.b = b;
	}

	public float getF()
	{
		return f;
	}

	public void setF(float f)
	{
		this.f = f;
	}

	public double getD()
	{
		return d;
	}

	public void setD(double d)
	{
		this.d = d;
	}

	public int getAuto()
	{
		return auto;
	}

	public void setAuto(int auto)
	{
		this.auto = auto;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "getName" + getName() + "--getC--" + getC() + "--getD--"
				+ getD() + "--getI--" + getI() + "--getF--" + getF()
				+ "--getB--" + getB() + "--getDate--" + getDate() + "auto"
				+ getAuto();
	}
}
