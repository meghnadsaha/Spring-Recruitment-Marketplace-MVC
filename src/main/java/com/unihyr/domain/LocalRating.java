package com.unihyr.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localrating")
public class LocalRating
{
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sn;

	@ManyToOne(cascade =	{ CascadeType.ALL })
	@JoinColumn(name = "ratingParamId", referencedColumnName = "id")
	private RatingParameter ratingParameter;

	@ManyToOne(cascade =	{ CascadeType.ALL })
	@JoinColumn(name = "postId", referencedColumnName = "postId")
	private Post post;

	@Column(nullable = false)
	private int industryId;

	@ManyToOne(cascade ={ CascadeType.ALL })
	@JoinColumn(name = "consultantId", referencedColumnName = "userId")
	private Registration registration;

	@Column(nullable = false)
	private boolean status;

	@Column(nullable = false)
	private String ratingParamValue;

	@Column(nullable = false)
	private Date createDate;


	private Date modifyDate;

	private Date deleteDate;
	

	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
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

	public String getRatingParamValue()
	{
		return ratingParamValue;
	}

	public void setRatingParamValue(String ratingParamValue)
	{
		this.ratingParamValue = ratingParamValue;
	}

	public long getSn()
	{
		return sn;
	}

	public void setSn(long sn)
	{
		this.sn = sn;
	}

	public RatingParameter getRatingParameter()
	{
		return ratingParameter;
	}

	public void setRatingParameter(RatingParameter ratingParameter)
	{
		this.ratingParameter = ratingParameter;
	}

	public Post getPost()
	{
		return post;
	}

	public void setPost(Post post)
	{
		this.post = post;
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
