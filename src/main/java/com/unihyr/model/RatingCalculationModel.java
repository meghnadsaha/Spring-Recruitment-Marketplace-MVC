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

public class RatingCalculationModel 
{
	private long sn;
	
	private String ratingParameterId;
	

	private String postId;
	
	private String profileId;
	
	
	private String consultantId;


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


	public String getProfileId() {
		return profileId;
	}


	public void setProfileId(String profileId) {
		this.profileId = profileId;
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




	
	

	
	
	
	
}
