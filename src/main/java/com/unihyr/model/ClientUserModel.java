package com.unihyr.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class ClientUserModel
{
	@NotBlank(message="{NotBlank.cuForm.userid}")
	private String userid;
	
	@NotBlank(message="{NotBlank.cuForm.name}")
	private String name;
	

	@Length(max=10,min=10,message="{Length.regForm.contact}")
//    @NotEmpty(message="{NotEmpty.regForm.contact}")
	@NumberFormat(style=Style.NUMBER)	
	private String mobileno;
	
//	@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{6,20}",message="{Pattern.regForm.password}")
	private String password;
	
//	@NotNull(message="{NotNull.regForm.repassword}")
	private String repassword;

	public String getMobileno()
	{
		return mobileno;
	}

	public void setMobileno(String mobileno)
	{
		this.mobileno = mobileno;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRepassword()
	{
		return repassword;
	}

	public void setRepassword(String repassword)
	{
		this.repassword = repassword;
	}

	
}
