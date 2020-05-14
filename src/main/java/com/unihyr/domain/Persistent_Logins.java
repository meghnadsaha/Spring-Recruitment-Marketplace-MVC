package com.unihyr.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persistent_logins") 
public class Persistent_Logins implements Serializable{
	
	private String username;
	private String series;
	private String token;
	
	@Column(nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Id
	@Column(nullable=false)
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
	@Column(nullable=false)
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(nullable=false)
	public Date getLast_used() {
		return last_used;
	}
	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}
	private Date last_used;

}
