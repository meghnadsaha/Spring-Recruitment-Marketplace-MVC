package com.unihyr.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class RegistrationForm 
{
	@NotEmpty(message="{NotEmpty.indexForm.fname}")
	@NotBlank(message="{NotBlank.indexForm.fname}")
	private String fname;
	
	private String lname;
	
	@NotEmpty(message="{NotEmpty.indexForm.userid}")
	@Email(message="{Email.indexForm.email}")
	private String userid;
	
	//@NotEmpty(message="{NotEmpty.indexForm.password}")
	//@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{6,20}",message="{Pattern.indexForm.password}")
	private String password;
	
	//@NotNull(message="{NotNull.indexForm.repassword}")
	private String repassword;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
		matchPassword();
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
		matchPassword();
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(max=10,min=10,message="{Length.indexForm.contact}")
    @NotEmpty(message="{NotEmpty.indexForm.contact}")
	@NumberFormat(style=Style.NUMBER)
	private String contact;
	
	
	@NotEmpty(message="{NotEmpty.indexForm.gender}")
	private String gender;
	
	private void matchPassword() {
	    if(this.password == null || this.repassword == null){
	        return;
	    }else if(!(this.password.equals(this.repassword))){
	    	System.out.println("in checkpassword");
	        this.repassword = null;
	    }
	}
	
	
	
}
