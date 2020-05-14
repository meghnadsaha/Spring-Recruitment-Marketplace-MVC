package com.unihyr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class LoginHistory implements Serializable
{
	private int lid;
	private String userid;
	private String password;
	private Date modification_date;
	private Registration reg;
	private Set<UserRole> roles;
	private String forgotpwdid;
	private String isactive;
	public int getLid() {
		return lid;
	}
	public String getForgotpwdid() {
		return forgotpwdid;
	}
	public void setForgotpwdid(String forgotpwdid) {
		this.forgotpwdid = forgotpwdid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getModification_date() {
		return modification_date;
	}
	public void setModification_date(Date modification_date) {
		this.modification_date = modification_date;
	}
	public Registration getReg() {
		return reg;
	}
	public void setReg(Registration reg) {
		this.reg = reg;
	}
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	
}
