package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qualification")
public class Qualification
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long qId;
	
	@Column(nullable=false)
	private String qType;
	
	@Column(nullable=false)
	private String qTitle;
	
	@Column
	Date creationDate;

	public long getqId()
	{
		return qId;
	}

	public void setqId(long qId)
	{
		this.qId = qId;
	}

	public String getqType()
	{
		return qType;
	}

	public void setqType(String qType)
	{
		this.qType = qType;
	}

	public String getqTitle()
	{
		return qTitle;
	}

	public void setqTitle(String qTitle)
	{
		this.qTitle = qTitle;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	
	
}
