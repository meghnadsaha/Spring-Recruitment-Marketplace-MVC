package com.unihyr.domain;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "globalrating")
public class GlobalRating
{
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sn;

	@Column(nullable = false)
	private int industryId;
	
	@ManyToOne(cascade =
	{ CascadeType.ALL })
	@JoinColumn(name = "consultantId", referencedColumnName = "userId")
	private Registration registration;
	
	@Column(nullable = false)
	private Date createDate;

	private Date modifyDate;

	private Date deleteDate;
	
	@Column(nullable = false)
	private double turnAround;
	
	@Column(nullable = false)
	private double shortlistRatio;


	
	@Column(nullable = false)
	private double industrycoverage;
	
	
	
	
	
	public double getTurnAround()
	{
		return turnAround;
	}

	public void setTurnAround(double turnAround)
	{
		this.turnAround = turnAround;
	}

	public double getShortlistRatio()
	{
		return shortlistRatio;
	}

	public void setShortlistRatio(double shortlistRatio)
	{
		this.shortlistRatio = shortlistRatio;
	}

	

	public double getIndustrycoverage()
	{
		return industrycoverage;
	}

	public void setIndustrycoverage(double industrycoverage)
	{
		this.industrycoverage = industrycoverage;
	}



	

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public Date getDeleteDate()
	{
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate)
	{
		this.deleteDate = deleteDate;
	}

	
	public long getSn()
	{
		return sn;
	}

	public void setSn(long sn)
	{
		this.sn = sn;
	}

	

	public Registration getRegistration()
	{
		return registration;
	}

	public void setRegistration(Registration registration)
	{
		this.registration = registration;
	}

}
