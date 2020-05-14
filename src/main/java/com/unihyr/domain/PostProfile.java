package com.unihyr.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="postprofile", uniqueConstraints={ @UniqueConstraint( columnNames = { "postId", "profileId" } ) } )
public class PostProfile
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ppid;
	
	@ManyToOne  
    @JoinColumn(name = "postId" , nullable= false)
	private Post post;
	
	@ManyToOne  
    @JoinColumn(name = "profileId"  , nullable= false)
	private CandidateProfile profile;
	
	@Column(nullable=false)
	private Date submitted;
	
	@Column
	private Date accepted;

	@Column
	private Date rejected;
	
	@Column
	private Date recruited;
	
	@Column
	private Date declinedDate ;
	
	@Column
	private Date offerDate;
	
	@Column
	private Date offerDropDate;
	
	@Column
	private Date joinDate;

	@Column
	private Date joinDropDate;

	@Column
	private Date withdrawDate;

	@Column
	private Date modificationDate;
	
	@Column
	private String rejectReason;

	@Column
	private String actionPerformerId;
	
	@Column
	private String processStatus;
	
	@Column
	private Boolean viewStatus;
	
	@OneToOne(mappedBy="postProfile", cascade=CascadeType.ALL)  
	private BillingDetails bill;
	
	public BillingDetails getBill()
	{
		return bill;
	}

	public void setBill(BillingDetails bill)
	{
		this.bill = bill;
	}
	
	public String getProcessStatus()
	{
		return processStatus;
	}

	public void setProcessStatus(String processStatus)
	{
		this.processStatus = processStatus;
	}

	public Date getModificationDate()
	{
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate)
	{
		this.modificationDate = modificationDate;
	}

	public Date getWithdrawDate()
	{
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate)
	{
		this.withdrawDate = withdrawDate;
	}

	public String getActionPerformerId()
	{
		return actionPerformerId;
	}

	public void setActionPerformerId(String actionPerformerId)
	{
		this.actionPerformerId = actionPerformerId;
	}

	public Boolean getViewStatus()
	{
		return viewStatus;
	}

	public void setViewStatus(Boolean viewStatus)
	{
		this.viewStatus = viewStatus;
	}

	public long getPpid()
	{
		return ppid;
	}

	public void setPpid(long ppid)
	{
		this.ppid = ppid;
	}

	public Post getPost()
	{
		return post;
	}

	public void setPost(Post post)
	{
		this.post = post;
	}

	public Date getSubmitted()
	{
		return submitted;
	}

	public void setSubmitted(Date submitted)
	{
		this.submitted = submitted;
	}

	public Date getAccepted()
	{
		return accepted;
	}

	public void setAccepted(Date accepted)
	{
		this.accepted = accepted;
	}

	public Date getRejected()
	{
		return rejected;
	}

	public void setRejected(Date rejected)
	{
		this.rejected = rejected;
	}
	public Date getRecruited()
	{
		return recruited;
	}

	public void setRecruited(Date recruited)
	{
		this.recruited = recruited;
	}

	public Date getDeclinedDate()
	{
		return declinedDate;
	}

	public void setDeclinedDate(Date declinedDate)
	{
		this.declinedDate = declinedDate;
	}
	
	public CandidateProfile getProfile()
	{
		return profile;
	}

	public void setProfile(CandidateProfile profile)
	{
		this.profile = profile;
	}
	
	public Date getOfferDate()
	{
		return offerDate;
	}

	public void setOfferDate(Date offerDate)
	{
		this.offerDate = offerDate;
	}

	public Date getOfferDropDate()
	{
		return offerDropDate;
	}

	public void setOfferDropDate(Date offerDropDate)
	{
		this.offerDropDate = offerDropDate;
	}

	public Date getJoinDate()
	{
		return joinDate;
	}

	public void setJoinDate(Date joinDate)
	{
		this.joinDate = joinDate;
	}



	public Date getJoinDropDate()
	{
		return joinDropDate;
	}

	public void setJoinDropDate(Date joinDropDate)
	{
		this.joinDropDate = joinDropDate;
	}



	public String getRejectReason()
	{
		return rejectReason;
	}

	public void setRejectReason(String rejectReason)
	{
		this.rejectReason = rejectReason;
	}



	@OneToMany(mappedBy="postProfile", cascade=CascadeType.ALL)  
	private Set<Inbox> messages;

	public Set<Inbox> getMessages()
	{
		return messages;
	}

	public void setMessages(Set<Inbox> messages)
	{
		this.messages = messages;
	}
	
	
	
}
