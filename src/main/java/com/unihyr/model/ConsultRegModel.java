package com.unihyr.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.unihyr.domain.Industry;


public class ConsultRegModel
{
	@NotBlank(message="{NotBlank.regForm.userid}")
	private String userid;
	
	@NotBlank(message="{NotBlank.regForm.consultName}")
	private String consultName;
	
//	@NotBlank(message="{NotBlank.regForm.founderName}")
//	private String founderName;
//	
//	@NotBlank(message="{NotBlank.regForm.founderContact}")
//	private String founderContact;
	
//	@NotEmpty(message="{NotEmpty.regForm.password}")
//	@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{6,20}",message="{Pattern.regForm.password}")
	private String password;
	
//	@NotNull(message="{NotNull.regForm.repassword}")
	private String repassword;
	
	@NotBlank(message="{NotBlank.regForm.officeLocations}")
	private String officeLocations;
	
	private String hoAddress;
	
	@Min(value=0  ,message="{Min.regForm.noofpeoples}")
	private int noofpeoples;
	
	@Min(value=0 ,message="{Min.regForm.revenue}")
	private int revenue;
	
	@Min(value=0 ,message="{Min.regForm.revenue}")
	private int yearsInIndusrty;
	
	@Length(max=10,min=10,message="{Length.regForm.contact}")
	@NumberFormat(style=Style.NUMBER)
	private String contact;
	
//	private Set<Industry> industries = new HashSet<>();
	
	@NotBlank(message="{NotBlank.regForm.officeAddress}")
	private String officeAddress;

	private String firmType;

	private String name;
	
	private int usersRequired;

	private String panno;
	
	private String stno;

	private String contractPath;
	
	private Date contractDate;

	private String contractorIP;
	
	private String ifscCode;
	
	private String accountNo;
	
	
	public String getIfscCode()
	{
		return ifscCode;
	}

	public void setIfscCode(String ifscCode)
	{
		this.ifscCode = ifscCode;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getContractorIP()
	{
		return contractorIP;
	}

	public void setContractorIP(String contractorIP)
	{
		this.contractorIP = contractorIP;
	}

	public String getContractPath()
	{
		return contractPath;
	}

	public void setContractPath(String contractPath)
	{
		this.contractPath = contractPath;
	}

	public Date getContractDate()
	{
		return contractDate;
	}

	public void setContractDate(Date contractDate)
	{
		this.contractDate = contractDate;
	}

	public String getPanno()
	{
		return panno;
	}

	public void setPanno(String panno)
	{
		this.panno = panno;
	}

	public String getStno()
	{
		return stno;
	}

	public void setStno(String stno)
	{
		this.stno = stno;
	}

//	public Set<Industry> getIndustries()
//	{
//		return industries;
//	}
//
//
//	public void setIndustries(Set<Industry> industries)
//	{
//		this.industries = industries;
//	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getUserid()
	{
		return userid;
	}


	public void setUserid(String userid)
	{
		this.userid = userid;
	}


	public String getConsultName()
	{
		return consultName;
	}


	public void setConsultName(String consultName)
	{
		this.consultName = consultName;
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


	public String getOfficeLocations()
	{
		return officeLocations;
	}


	public void setOfficeLocations(String officeLocations)
	{
		this.officeLocations = officeLocations;
	}


	public String getHoAddress()
	{
		return hoAddress;
	}


	public void setHoAddress(String hoAddress)
	{
		this.hoAddress = hoAddress;
	}


	public int getNoofpeoples()
	{
		return noofpeoples;
	}


	public void setNoofpeoples(int noofpeoples)
	{
		this.noofpeoples = noofpeoples;
	}


	public int getRevenue()
	{
		return revenue;
	}


	public void setRevenue(int revenue)
	{
		this.revenue = revenue;
	}


	public int getYearsInIndusrty()
	{
		return yearsInIndusrty;
	}


	public void setYearsInIndusrty(int yearsInIndusrty)
	{
		this.yearsInIndusrty = yearsInIndusrty;
	}


	public String getContact()
	{
		return contact;
	}


	public void setContact(String contact)
	{
		this.contact = contact;
	}


	public int getUsersRequired()
	{
		return usersRequired;
	}


	public void setUsersRequired(int usersRequired)
	{
		this.usersRequired = usersRequired;
	}
	
	@Lob
	public String getOfficeAddress()
	{
		return officeAddress;
	}


	public void setOfficeAddress(String officeAddress)
	{
		this.officeAddress = officeAddress;
	}


	public String getFirmType()
	{
		return firmType;
	}


	public void setFirmType(String firmType)
	{
		this.firmType = firmType;
	}


	@Lob
	private String about;

	public String getAbout()
	{
		return about;
	}

	public void setAbout(String about)
	{
		this.about = about;
	}
	
	
	private boolean consultant_type;


	public boolean isConsultant_type()
	{
		return consultant_type;
	}


	public void setConsultant_type(boolean consultant_type)
	{
		this.consultant_type = consultant_type;
	}


	
	
	
	
}
