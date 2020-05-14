package com.unihyr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="logininfo")
public class LoginInfo implements Serializable
{
	
	private static final long serialVersionUID = 503239969044315259L;

	private int lid;
	
	private String userid;
	
	private String password;
	
	private Date modification_date;
	
	private Date login_date;
	
	private Date logout_date;
	
	private String forgotpwdid;

	private String isactive;
	
	private Boolean isLogin;
	
	private Registration reg;
	
	private Set<UserRole> roles = new HashSet<>();

	@Column
	public Date getLogin_date()
	{
		return login_date;
	}
	public void setLogin_date(Date login_date)
	{
		this.login_date = login_date;
	}
	@Column
	public Date getLogout_date()
	{
		return logout_date;
	}
	public void setLogout_date(Date logout_date)
	{
		this.logout_date = logout_date;
	}
	@Column
	public Boolean getIsLogin()
	{
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin)
	{
		this.isLogin = isLogin;
	}
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}

//	@Column(nullable=false, unique=true)
	@Column(nullable=false, unique=true)
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column
	public Date getModification_date() {
		return modification_date;
	}
	public void setModification_date(Date modification_date) {
		this.modification_date = modification_date;
	}
	
	@Column
	public String getForgotpwdid() {
		return forgotpwdid;
	}
	public void setForgotpwdid(String forgotpwdid) {
		this.forgotpwdid = forgotpwdid;
	}
	
	@Column(nullable=false)
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "log", cascade = CascadeType.ALL)
	public Registration getReg() {
		return reg;
	}
	public void setReg(Registration reg) {
		this.reg = reg;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="userrole")  
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	
	
}
