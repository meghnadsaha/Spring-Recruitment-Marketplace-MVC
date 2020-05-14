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
@Table(name = "globalratingpercentile")
public class GlobalRatingPercentile
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
	


	@Column
	private double percentileTr;
	@Column
	private double percentileSh;
	@Column
	private double percentileCl;
	@Column
	private double percentileInC;
	@Column
	private double percentileOd;
	@Column
	private double percentile;
	@Column
	private int offerDrop;
	@Column
	private int offerJoin;
	
	
	
	
	
	

	


	public double getPercentileTr()
	{
		return percentileTr;
	}

	public void setPercentileTr(double percentileTr)
	{
		this.percentileTr = percentileTr;
	}

	public int getOfferDrop()
	{
		return offerDrop;
	}

	public void setOfferDrop(int offerDrop)
	{
		this.offerDrop = offerDrop;
	}

	public int getOfferJoin()
	{
		return offerJoin;
	}

	public void setOfferJoin(int offerJoin)
	{
		this.offerJoin = offerJoin;
	}

	public double getPercentileSh()
	{
		return percentileSh;
	}

	public void setPercentileSh(double percentileSh)
	{
		this.percentileSh = percentileSh;
	}

	public double getPercentileCl()
	{
		return percentileCl;
	}

	public void setPercentileCl(double percentileCl)
	{
		this.percentileCl = percentileCl;
	}

	public double getPercentileInC()
	{
		return percentileInC;
	}

	public void setPercentileInC(double percentileInC)
	{
		this.percentileInC = percentileInC;
	}

	public double getPercentileOd()
	{
		return percentileOd;
	}

	public void setPercentileOd(double percentileOd)
	{
		this.percentileOd = percentileOd;
	}

	public double getPercentile()
	{
		return percentile;
	}

	public void setPercentile(double percentile)
	{
		this.percentile = percentile;
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
