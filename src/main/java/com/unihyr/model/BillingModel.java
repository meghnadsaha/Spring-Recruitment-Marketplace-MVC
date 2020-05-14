package com.unihyr.model;

import java.util.Date;

import javax.persistence.Column;

public class BillingModel
{
	private int billId;
	
	private String joiningDate;
	
	private String expectedJoiningDate;
	
	private double totalCTC;
	
	private double billableCTC;

	public int getBillId()
	{
		return billId;
	}

	public void setBillId(int billId)
	{
		this.billId = billId;
	}

	public String getJoiningDate()
	{
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate)
	{
		this.joiningDate = joiningDate;
	}

	public String getExpectedJoiningDate()
	{
		return expectedJoiningDate;
	}

	public void setExpectedJoiningDate(String expectedJoiningDate)
	{
		this.expectedJoiningDate = expectedJoiningDate;
	}

	public double getTotalCTC()
	{
		return totalCTC;
	}

	public void setTotalCTC(double totalCTC)
	{
		this.totalCTC = totalCTC;
	}

	public double getBillableCTC()
	{
		return billableCTC;
	}

	public void setBillableCTC(double billableCTC)
	{
		this.billableCTC = billableCTC;
	}
	
	
	
}
