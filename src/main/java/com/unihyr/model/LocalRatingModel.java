package com.unihyr.model;

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

public class LocalRatingModel 
{
	private long sn;
	
	private String ratingParameterId;
	
	
	private String postId;
	
	
	private String consultantId;
	
	
	private String value;
	
	private Date dateOfRecord;
	
	private int recordCount;
	
	
	public String getRatingParameterId() {
		return ratingParameterId;
	}

	public void setRatingParameterId(String ratingParameterId) {
		this.ratingParameterId = ratingParameterId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}


	public long getSn() {
		return sn;
	}

	public void setSn(long sn) {
		this.sn = sn;
	}


	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDateOfRecord() {
		return dateOfRecord;
	}

	public void setDateOfRecord(Date dateOfRecord) {
		this.dateOfRecord = dateOfRecord;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	

	
	
	
	
}
