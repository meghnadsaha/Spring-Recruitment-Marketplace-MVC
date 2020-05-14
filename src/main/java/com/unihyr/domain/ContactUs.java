package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contactus")
public class ContactUs
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long contactusid;
	
	@Column(nullable=false)
	private String usertype;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String company;
	
	@Column(nullable=false)
	private String phone;


	@Column
	private Date seenDate;
	
	@Column Date msgDate;
	
	
	public Date getSeenDate()
	{
		return seenDate;
	}

	public void setSeenDate(Date seenDate)
	{
		this.seenDate = seenDate;
	}

	public Date getMsgDate()
	{
		return msgDate;
	}

	public void setMsgDate(Date msgDate)
	{
		this.msgDate = msgDate;
	}

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
