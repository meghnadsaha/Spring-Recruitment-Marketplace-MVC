package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Model class Used to store all configurations which are global to whole application and 
 * to all type of Users. These permissions can only be updated by Admin  
 * @author Rohit Tiwari
 */
@Entity
@Table(name="socialsharing")
public class SocialSharing
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String oauth_expires;
	@Column
	private String oauth_token;
	@Column
	private String api_key;
	@Column
	private String socialMediaName;
	@Column
	private String userid;
	
	
	
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getOauth_expires()
	{
		return oauth_expires;
	}
	public void setOauth_expires(String oauth_expires)
	{
		this.oauth_expires = oauth_expires;
	}
	public String getOauth_token()
	{
		return oauth_token;
	}
	public void setOauth_token(String oauth_token)
	{
		this.oauth_token = oauth_token;
	}
	public String getApi_key()
	{
		return api_key;
	}
	public void setApi_key(String api_key)
	{
		this.api_key = api_key;
	}
	public String getSocialMediaName()
	{
		return socialMediaName;
	}
	public void setSocialMediaName(String socialMediaName)
	{
		this.socialMediaName = socialMediaName;
	}
}
