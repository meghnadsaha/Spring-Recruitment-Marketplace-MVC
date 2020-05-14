package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notifications")
public class Notifications
{

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int notificationId;
	
	@Column
	private String  notification;

	@Column
	private boolean readStatus;

	@Column
	private String userid;
	
	@Column
	private Date date;
	
	


	public boolean isReadStatus()
	{
		return readStatus;
	}

	public void setReadStatus(boolean readStatus)
	{
		this.readStatus = readStatus;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public int getNotificationId()
	{
		return notificationId;
	}

	public void setNotificationId(int notificationId)
	{
		this.notificationId = notificationId;
	}

	public String getNotification()
	{
		return notification;
	}

	public void setNotification(String notification)
	{
		this.notification = notification;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	
}
