package com.unihyr.model;

import com.unihyr.domain.BillingDetails;
import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.Post;

public class ClosedProfileDetails
{
	public int sno;
	public CandidateProfile candidate;
	public Post post;
	public BillingDetails bill;

	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno = sno;
	}
	public CandidateProfile getCandidate()
	{
		return candidate;
	}
	public void setCandidate(CandidateProfile candidate)
	{
		this.candidate = candidate;
	}
	public Post getPost()
	{
		return post;
	}
	public void setPost(Post post)
	{
		this.post = post;
	}
	public BillingDetails getBill()
	{
		return bill;
	}
	public void setBill(BillingDetails bill)
	{
		this.bill = bill;
	}

}
