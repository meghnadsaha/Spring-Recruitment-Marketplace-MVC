package com.unihyr.model;

public class ContactUs
{
	private long contactusid;
	
	private String usertype;
	
	private String name;
	
	private String email;
	
	private String company;
	
	private String phone;

	public long getContactusid()
	{
		return contactusid;
	}

	public void setContactusid(long contactusid)
	{
		this.contactusid = contactusid;
	}

	public String getUsertype()
	{
		return usertype;
	}

	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
}
