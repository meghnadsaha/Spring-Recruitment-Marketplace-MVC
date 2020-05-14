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
@Table(name="ratingcalculation")
public class RatingCalculation 
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long sn;
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="ratingParamId", referencedColumnName="id")
	private RatingParameter ratingParameter;
	

	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="postId", referencedColumnName="postId")
	private Post post;
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="profileId", referencedColumnName="profileId")
	private CandidateProfile candidateProfile;
	
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="consultantId", referencedColumnName="userId")
	private Registration registration;


	public long getSn() {
		return sn;
	}


	public void setSn(long sn) {
		this.sn = sn;
	}


	public RatingParameter getRatingParameter() {
		return ratingParameter;
	}


	public void setRatingParameter(RatingParameter ratingParameter) {
		this.ratingParameter = ratingParameter;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}


	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}


	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}


	public Registration getRegistration() {
		return registration;
	}


	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	

	
	
	
	
}
