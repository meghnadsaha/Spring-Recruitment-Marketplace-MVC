package com.unihyr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
/**
 * A bean class to set and get all details of User while registration.
 * @author Rohit Tiwari
 */
@Entity
@Table(name="registration")
public class Registration implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8719433485080003372L;
	/**
	 * Unique Identity of each registration
	 */
	private int lid;
	/**
	 * Name of Registering User
	 */
	private String name;
	
	private String userid;
	
	
	private Date regdate;

	
	private Date modification_date;
	
	
	private LoginInfo log;
	
	
	private Boolean firstTime;
	
//	------------------ common Data --------------
	
	
	private String officeLocations;
	
	private String hoAddress;
	
	private int noofpeoples;
	
	private int revenue;
	
	private String contact;
	
	private String logo;
	
	private String websiteUrl;
	

	private String about;

	
	private Set<Industry> industries = new HashSet<>();
	
	
//	------------------ Client Data -------------- 
	

	private String organizationName;
	private String designation;
	
	
	private int usersRequired;
	
//	@ManyToOne(cascade =
//		{ CascadeType.ALL })
//		@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
//	private CandidateProfile candidateProfile;
//	

	private String contractNo;

	private double feePercent1;
	private double feePercent2;
	private double feePercent3;
	private double feePercent4;
	private double feePercent5;
	private double feeCommission;
	/*private double ctcSlabs1Min;
	private double ctcSlabs1Max;
	private double ctcSlabs2Min;
	private double ctcSlabs2Max;
	private double ctcSlabs3Min;
	private double ctcSlabs3Max;
	private double ctcSlabs4Min;
	private double ctcSlabs4Max;
	private double ctcSlabs5Min;*/

	private String slab1;
	private String slab2;
	private String slab3;
	private String slab4;
	private String slab5;
	
	private Integer paymentDays;

	private String emptyField;

	private String panno;
	private String stno;
	private String ifscCode;
	private String accountNo;
	private String contractPath;
	private String contractorIP;
	private Date contractDate;
	
	
	

	@Column
	public String getIfscCode()
	{
		return ifscCode;
	}


	public void setIfscCode(String ifscCode)
	{
		this.ifscCode = ifscCode;
	}


	@Column
	public String getAccountNo()
	{
		return accountNo;
	}


	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}


	@Column
	public String getContractorIP()
	{
		return contractorIP;
	}


	public void setContractorIP(String contractorIP)
	{
		this.contractorIP = contractorIP;
	}


	@Column
	public String getContractPath()
	{
		return contractPath;
	}


	public void setContractPath(String contractPath)
	{
		this.contractPath = contractPath;
	}

	@Column
	public Date getContractDate()
	{
		return contractDate;
	}


	public void setContractDate(Date contractDate)
	{
		this.contractDate = contractDate;
	}


	@Column
	public String getPanno()
	{
		return panno;
	}


	public void setPanno(String panno)
	{
		this.panno = panno;
	}

	@Column
	public String getStno()
	{
		return stno;
	}


	public void setStno(String stno)
	{
		this.stno = stno;
	}


	@Column
	public String getDesignation()
	{
		return designation;
	}


	public void setDesignation(String designation)
	{
		this.designation = designation;
	}


	@Column	
	public double getFeeCommission()
	{
		return feeCommission;
	}


	public void setFeeCommission(double feeCommission)
	{
		this.feeCommission = feeCommission;
	}


	//	------------------ Consultant Data -------------- 	
	@Column
	public String getSlab1()
	{
		return slab1;
	}


	public void setSlab1(String slab1)
	{
		this.slab1 = slab1;
	}

	@Column
	public String getSlab2()
	{
		return slab2;
	}


	public void setSlab2(String slab2)
	{
		this.slab2 = slab2;
	}

	@Column
	public String getSlab3()
	{
		return slab3;
	}


	public void setSlab3(String slab3)
	{
		this.slab3 = slab3;
	}

	@Column
	public String getSlab4()
	{
		return slab4;
	}


	public void setSlab4(String slab4)
	{
		this.slab4 = slab4;
	}

	@Column
	public String getSlab5()
	{
		return slab5;
	}


	public void setSlab5(String slab5)
	{
		this.slab5 = slab5;
	}


	@Column
	public Boolean getFirstTime()
	{
		return firstTime;
	}


	public void setFirstTime(Boolean firstTime)
	{
		this.firstTime = firstTime;
	}


	


	public void setFeePercent5(double feePercent5)
	{
		this.feePercent5 = feePercent5;
	}

	
	@Column
	public double getFeePercent1()
	{
		return feePercent1;
	}

	public void setFeePercent1(double feePercent1)
	{
		this.feePercent1 = feePercent1;
	}

	@Column
	public double getFeePercent2()
	{
		return feePercent2;
	}

	public void setFeePercent2(double feePercent2)
	{
		this.feePercent2 = feePercent2;
	}

	@Column
	public double getFeePercent3()
	{
		return feePercent3;
	}

	public void setFeePercent3(double feePercent3)
	{
		this.feePercent3 = feePercent3;
	}

	@Column
	public double getFeePercent4()
	{
		return feePercent4;
	}

	public void setFeePercent4(double feePercent4)
	{
		this.feePercent4 = feePercent4;
	}

	@Column
	public double getFeePercent5()
	{
		return feePercent5;
	}


	@Column
	public String getContractNo()
	{
		return contractNo;
	}

	public void setContractNo(String contractNo)
	{
		this.contractNo = contractNo;
	}

	
	@Column
	public Integer getPaymentDays()
	{
		return paymentDays;
	}

	public void setPaymentDays(Integer paymentDays)
	{
		this.paymentDays = paymentDays;
	}

	@Column
	public String getEmptyField()
	{
		return emptyField;
	}

	public void setEmptyField(String emptyField)
	{
		this.emptyField = emptyField;
	}


	private String consultName;
	
	
	
	private int yearsInIndusrty;

	private Set<PostConsultant> postConsultants;

	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	@Column(nullable=false)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Column
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	

	@Column(nullable=false)
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Column
	public Date getModification_date() {
		return modification_date;
	}

	public void setModification_date(Date modification_date) {
		this.modification_date = modification_date;
	}

	@OneToOne(fetch = FetchType.LAZY)
	public LoginInfo getLog() {
		return log;
	}

	public void setLog(LoginInfo log) {
		this.log = log;
	}

	@Column
	public String getOfficeLocations() {
		return officeLocations;
	}

	public void setOfficeLocations(String officeLocations) {
		this.officeLocations = officeLocations;
	}

	@Column
	public String getHoAddress() {
		return hoAddress;
	}

	public void setHoAddress(String hoAddress) {
		this.hoAddress = hoAddress;
	}

	@Column(nullable=false)
	public int getNoofpeoples() {
		return noofpeoples;
	}

	public void setNoofpeoples(int noofpeoples) {
		this.noofpeoples = noofpeoples;
	}
	
	@Column(nullable=false)
	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	@Column
	public String getContact() {
		return contact;
	}

	
	public void setContact(String contact) {
		this.contact = contact;
	}

	
	
	
	@Column
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Column
	public int getUsersRequired() {
		return usersRequired;
	}

	public void setUsersRequired(int usersRequired) {
		this.usersRequired = usersRequired;
	}

	@Column
	public String getConsultName() {
		return consultName;
	}

	public void setConsultName(String consultName) {
		this.consultName = consultName;
	}

	@Column
	public int getYearsInIndusrty() {
		return yearsInIndusrty;
	}

	public void setYearsInIndusrty(int yearsInIndusrty) {
		this.yearsInIndusrty = yearsInIndusrty;
	}

	@Column
	public String getWebsiteUrl()
	{
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl)
	{
		this.websiteUrl = websiteUrl;
	}

	@Column
	public String getLogo()
	{
		return logo;
	}

	public void setLogo(String logo)
	{
		this.logo = logo;
	}

	
	
	
	@OneToMany(mappedBy="consultant", fetch = FetchType.LAZY)  
	public Set<PostConsultant> getPostConsultants()
	{
		return postConsultants;
	}

	public void setPostConsultants(Set<PostConsultant> postConsultants)
	{
		this.postConsultants = postConsultants;
	}


	
	@Column
	@Lob
	public String getAbout()
	{
		return about;
	}

	public void setAbout(String about)
	{
		this.about = about;
	}
	
	
	private Registration admin;
 
    private Set<Registration> subuser = new HashSet<Registration>();

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_admin", referencedColumnName="userid" )
    public Registration getAdmin()
	{
		return admin;
	}

	public void setAdmin(Registration admin)
	{
		this.admin = admin;
	}

	@OneToMany(mappedBy="admin")
    public Set<Registration> getSubuser()
	{
		return subuser;
	}

	public void setSubuser(Set<Registration> subuser)
	{
		this.subuser = subuser;
	}
 

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name="user_industry", 
                joinColumns={@JoinColumn(name="lid")}, 
                inverseJoinColumns={@JoinColumn(name="id")})
	public Set<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(Set<Industry> industries) {
		this.industries = industries;
	}

	@Column(nullable= false)
	private boolean consultant_type;


	public boolean isConsultant_type()
	{
		return consultant_type;
	}


	public void setConsultant_type(boolean consultant_type)
	{
		this.consultant_type = consultant_type;
	}
	
	@Column
	@Lob
	private String officeAddress;
	
	@Column
	private String firmType;
	
	
	public String getOfficeAddress()
	{
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress)
	{
		this.officeAddress = officeAddress;
	}

	public String getFirmType()
	{
		return firmType;
	}

	public void setFirmType(String firmType)
	{
		this.firmType = firmType;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		if (!(obj instanceof Registration))
		{
			return false;
		}
		
		Registration reg = (Registration) obj;
		
		boolean st = reg.getUserid().equals(this.getUserid());
		
		return st;
	}

}
