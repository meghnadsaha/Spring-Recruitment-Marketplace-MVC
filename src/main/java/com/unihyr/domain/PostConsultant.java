package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="postconsultant", uniqueConstraints={ @UniqueConstraint( columnNames = { "post_id", "lid" } ) } )
public class PostConsultant
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long pcid;
	
	@ManyToOne  
//    @JoinColumn(name = "post_id" , nullable= false)
	@JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
	private Post post;
	
	@ManyToOne  
    @JoinColumn(name = "lid")
	private Registration consultant;
	
	private Date createDate;

	

	@Column
	private double percentile;
	@Column
	private double feePercent;


	

	public double getFeePercent()
	{
		return feePercent;
	}

	public void setFeePercent(double feePercent)
	{
		this.feePercent = feePercent;
	}

	public double getPercentile()
	{
		return percentile;
	}

	public void setPercentile(double percentile)
	{
		this.percentile = percentile;
	}

	public long getPcid()
	{
		return pcid;
	}

	public void setPcid(long pcid)
	{
		this.pcid = pcid;
	}

	public Post getPost()
	{
		return post;
	}

	public void setPost(Post post)
	{
		this.post = post;
	}

	public Registration getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Registration consultant)
	{
		this.consultant = consultant;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
}
