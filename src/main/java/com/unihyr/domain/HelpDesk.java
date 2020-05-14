package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="helpdesk")
public class HelpDesk
{
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long hdid;
	
	@Column 
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String message;
	
	@Column  
	private String subject;
	
	@Column
	private Date seenDate;
	
	@Column Date msgDate;

	public Date getMsgDate()
	{
		return msgDate;
	}

	public void setMsgDate(Date msgDate)
	{
		this.msgDate = msgDate;
	}

	public Date getSeenDate()
	{
		return seenDate;
	}

	public void setSeenDate(Date seenDate)
	{
		this.seenDate = seenDate;
	}

	public long getHdid()
	{
		return hdid;
	}

	public void setHdid(long hdid)
	{
		this.hdid = hdid;
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

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	
	

}
