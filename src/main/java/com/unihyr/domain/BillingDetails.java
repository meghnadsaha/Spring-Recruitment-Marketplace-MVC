package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table(name="billingdetails")
public class BillingDetails
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int billId;
	
	@Column(nullable=false)
	private long postId;

	@Column(nullable=false)
	private String position;

	@Column(nullable=false)
	private String clientId;
	
	@Column(nullable=false)
	private String consultantId;

	@Column(nullable=false)
	private String clientName;
	
	@Column
	private String clientAddress;
	
	@Column(nullable=false)
	private String consultantName;

	@Column
	private String candidatePerson;

	@Column
	private String invoicePath;
	
	@Column
	private String consInvoicePath;
	
	@Column(nullable=false)
	private String location;

	@Column(nullable=false)
	private Date submittedDate;
	
	@Column(nullable=false)
	private Date offerAcceptedDate;

	@Column
	private Date joiningDate;

	@Column
	private Date expectedJoiningDate;
	
	@Column
	private Boolean verificationStatus;

	@Column(nullable=false)
	private double totalCTC;
	
	@Column(nullable=false)
	private double billableCTC;
	

	@Column(nullable=false)
	private double feePercentForClient;
	
	@Column(nullable=false)
	private double feePercentToAdmin;

	@Column(nullable=false)
	private double fee;
	
	@Column(nullable=false)
	private double tax;

	@Column(nullable=false)
	private double totalAmount;

	@Column(nullable=false)
	private Date createDate;

	@Column
	private Date paymentDueDateForCo;
	@Column
	private Date paymentDueDateForAd;

	@Column
	private Date deleteDate;

	@Column(nullable=false)
	private boolean adminPaidStatus;

	@Column(nullable=false)
	private boolean clientPaidStatus;
	
	@Column(nullable=false)
	private long postProfileId;

	
	

	public String getConsInvoicePath()
	{
		return consInvoicePath;
	}

	public void setConsInvoicePath(String consInvoicePath)
	{
		this.consInvoicePath = consInvoicePath;
	}

	@OneToOne  
    @JoinColumn(name = "ppid" , nullable= false)
	private PostProfile postProfile;
	
	public PostProfile getPostProfile()
	{
		return postProfile;
	}

	public void setPostProfile(PostProfile postProfile)
	{
		this.postProfile = postProfile;
	}

	@Column
	private Date paidDate;
	
	public String getInvoicePath()
	{
		return invoicePath;
	}

	public void setInvoicePath(String invoicePath)
	{
		this.invoicePath = invoicePath;
	}

	public Boolean getVerificationStatus()
	{
		return verificationStatus;
	}

	public void setVerificationStatus(Boolean verificationStatus)
	{
		this.verificationStatus = verificationStatus;
	}

	public Date getExpectedJoiningDate()
	{
		return expectedJoiningDate;
	}

	public void setExpectedJoiningDate(Date expectedJoiningDate)
	{
		this.expectedJoiningDate = expectedJoiningDate;
	}

	public String getClientAddress()
	{
		return clientAddress;
	}

	public void setClientAddress(String clientAddress)
	{
		this.clientAddress = clientAddress;
	}

	public String getCandidatePerson()
	{
		return candidatePerson;
	}

	public void setCandidatePerson(String candidatePerson)
	{
		this.candidatePerson = candidatePerson;
	}

	public long getPostProfileId()
	{
		return postProfileId;
	}

	public void setPostProfileId(long postProfileId)
	{
		this.postProfileId = postProfileId;
	}

	public double getFeePercentForClient()
	{
		return feePercentForClient;
	}

	public void setFeePercentForClient(double feePercentForClient)
	{
		this.feePercentForClient = feePercentForClient;
	}

	public double getFeePercentToAdmin()
	{
		return feePercentToAdmin;
	}

	public void setFeePercentToAdmin(double feePercentToAdmin)
	{
		this.feePercentToAdmin = feePercentToAdmin;
	}

	public String getClientName()
	{
		return clientName;
	}

	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	public String getConsultantName()
	{
		return consultantName;
	}

	public void setConsultantName(String consultantName)
	{
		this.consultantName = consultantName;
	}

	public boolean isAdminPaidStatus()
	{
		return adminPaidStatus;
	}

	public void setAdminPaidStatus(boolean adminPaidStatus)
	{
		this.adminPaidStatus = adminPaidStatus;
	}

	public boolean isClientPaidStatus()
	{
		return clientPaidStatus;
	}

	public void setClientPaidStatus(boolean clientPaidStatus)
	{
		this.clientPaidStatus = clientPaidStatus;
	}

	public double getTax()
	{
		return tax;
	}

	public void setTax(double tax)
	{
		this.tax = tax;
	}


	public Date getPaidDate()
	{
		return paidDate;
	}

	public void setPaidDate(Date paidDate)
	{
		this.paidDate = paidDate;
	}

	


	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public String getConsultantId()
	{
		return consultantId;
	}

	public void setConsultantId(String consultantId)
	{
		this.consultantId = consultantId;
	}

	public int getBillId()
	{
		return billId;
	}

	public void setBillId(int billId)
	{
		this.billId = billId;
	}

	public Long getPostId()
	{
		return postId;
	}

	public void setPostId(long l)
	{
		this.postId = l;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}



	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public Date getSubmittedDate()
	{
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate)
	{
		this.submittedDate = submittedDate;
	}

	public Date getOfferAcceptedDate()
	{
		return offerAcceptedDate;
	}

	public void setOfferAcceptedDate(Date offerAcceptedDate)
	{
		this.offerAcceptedDate = offerAcceptedDate;
	}

	public Date getJoiningDate()
	{
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate)
	{
		this.joiningDate = joiningDate;
	}

	public double getTotalCTC()
	{
		return totalCTC;
	}

	public void setTotalCTC(double totalCTC)
	{
		this.totalCTC = totalCTC;
	}

	public double getBillableCTC()
	{
		return billableCTC;
	}

	public void setBillableCTC(double billableCTC)
	{
		this.billableCTC = billableCTC;
	}

	public double getFee()
	{
		return fee;
	}

	public void setFee(double fee)
	{
		this.fee = fee;
	}

	public double getTotalAmount()
	{
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount)
	{
		this.totalAmount = totalAmount;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	

	public Date getPaymentDueDateForCo()
	{
		return paymentDueDateForCo;
	}

	public void setPaymentDueDateForCo(Date paymentDueDateForCo)
	{
		this.paymentDueDateForCo = paymentDueDateForCo;
	}

	public Date getPaymentDueDateForAd()
	{
		return paymentDueDateForAd;
	}

	public void setPaymentDueDateForAd(Date paymentDueDateForAd)
	{
		this.paymentDueDateForAd = paymentDueDateForAd;
	}

	public Date getDeleteDate()
	{
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate)
	{
		this.deleteDate = deleteDate;
	}
	
	
	
	
	
	
}
