package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inbox")
public class Inbox
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long inboxId;
	
	@Column(nullable=false)
	@Lob
	private String message;
	
	@Column(nullable=false)
	private Date createDate;
	
	
	@Column
	private String client;
	
	
	@Column
	private String consultant;


	public long getInboxId()
	{
		return inboxId;
	}


	public void setInboxId(long inboxId)
	{
		this.inboxId = inboxId;
	}


	public String getMessage()
	{
		return message;
	}


	public void setMessage(String message)
	{
		this.message = message;
	}


	public Date getCreateDate()
	{
		return createDate;
	}


	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}


	public String getClient()
	{
		return client;
	}


	public void setClient(String client)
	{
		this.client = client;
	}


	public String getConsultant()
	{
		return consultant;
	}


	public void setConsultant(String consultant)
	{
		this.consultant = consultant;
	}
	
	@ManyToOne  
    @JoinColumn(name = "ppid" , nullable= false)
	private PostProfile postProfile;


	public PostProfile getPostProfile()
	{
		return postProfile;
	}


	public void setPostProfile(PostProfile postProfile)
	{
		this.postProfile = postProfile;
	}
	
	@Column(nullable=false)
	private boolean isViewed;
	
	
	
	
	public boolean isViewed()
	{
		return isViewed;
	}


	public void setViewed(boolean isViewed)
	{
		this.isViewed = isViewed;
	}


	@Override
	public String toString()
	{
		return ""+isViewed;
	}
}
