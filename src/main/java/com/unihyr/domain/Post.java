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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post implements Serializable
{
	private static final long serialVersionUID = 7878507665232547622L;

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long postId;
	
	@Column
	private String jobCode;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String location;
	
	@Column(nullable=false)
	private String function;
	
	@Column(nullable=false)
	private double exp_min;
	
	@Column(nullable=false)
	private double exp_max;
	
	@Column(nullable=false)
	private double ctc_min;

	@Column(nullable=false)
	private double ctc_max;

	@Column
	private double feePercent;
	
	@Column
	private String criteria;
	
	@Column
	@Lob
	private String comment;

	@Column
	private String uploadjd;
	@Column
	private String uploadjdaudio;
	
	@Column
	private String posterId;
	
	@Column
	@Lob
	private String additionDetail;
	
	@Column
	private Date published;
	
	@Column(nullable=false)
	private boolean isActive;

	@Column
	private Date closeDate;
	@Column
	private Date joinCloseDate;
	@Column
	private Date openAgainDate;

	@Column(nullable=false)
	private int noOfPosts;

	@Column
	private int noOfPostsFilled;
	@Column
	private int noOfPostsJoined;
	
/*	@Column(nullable=false)
	private String role;

	@Column(nullable=false)
	private String designation;*/

	@Column
	private String qualification_ug;
	@Column
	private String qualification_pg;
	@Column
	private String closeRequestClient;
	@Column
	private String closeRequestConsultant;

	@Column(nullable=false)
	private int profileParDay;

	@Column
	private String workHourStartHour;
	@Column
	private String workHourEndHour;
	@Column
	private String workHourStartMin;
	@Column
	private String workHourEndMin;
	@Column
	private String updateInfo;

	@Column
	@Lob
	private String editSummary;
	@Column
	@Lob
	private String variablePayComment;
	

//	@Column
//	private int inProcess;
//
//	public int getInProcess()
//	{
//		return inProcess;
//	}
//
//	public void setInProcess(int inProcess)
//	{
//		this.inProcess = inProcess;
//	}

	public Date getOpenAgainDate()
	{
		return openAgainDate;
	}

	public Date getJoinCloseDate()
	{
		return joinCloseDate;
	}

	public void setJoinCloseDate(Date joinCloseDate)
	{
		this.joinCloseDate = joinCloseDate;
	}

	public int getNoOfPostsJoined()
	{
		return noOfPostsJoined;
	}

	public void setNoOfPostsJoined(int noOfPostsJoined)
	{
		this.noOfPostsJoined = noOfPostsJoined;
	}

	public String getUploadjdaudio()
	{
		return uploadjdaudio;
	}

	public void setUploadjdaudio(String uploadjdaudio)
	{
		this.uploadjdaudio = uploadjdaudio;
	}

	public void setOpenAgainDate(Date openAgainDate)
	{
		this.openAgainDate = openAgainDate;
	}

	public String getQualification_ug()
	{
		return qualification_ug;
	}

	public void setQualification_ug(String qualification_ug)
	{
		this.qualification_ug = qualification_ug;
	}

	public String getQualification_pg()
	{
		return qualification_pg;
	}

	public void setQualification_pg(String qualification_pg)
	{
		this.qualification_pg = qualification_pg;
	}

	public String getVariablePayComment()
	{
		return variablePayComment;
	}

	public void setVariablePayComment(String variablePayComment)
	{
		this.variablePayComment = variablePayComment;
	}

	public double getFeePercent()
	{
		return feePercent;
	}

	public void setFeePercent(double feePercent)
	{
		this.feePercent = feePercent;
	}

	public String getPosterId()
	{
		return posterId;
	}

	public void setPosterId(String posterId)
	{
		this.posterId = posterId;
	}

	public String getUpdateInfo()
	{
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo)
	{
		this.updateInfo = updateInfo;
	}

	public int getNoOfPostsFilled()
	{
		return noOfPostsFilled;
	}

	public void setNoOfPostsFilled(int noOfPostsFilled)
	{
		this.noOfPostsFilled = noOfPostsFilled;
	}

	public String getEditSummary()
	{
		return editSummary;
	}

	public void setEditSummary(String editSummary)
	{
		this.editSummary = editSummary;
	}

	public String getWorkHourStartHour()
	{
		return workHourStartHour;
	}

	public void setWorkHourStartHour(String workHourStartHour)
	{
		this.workHourStartHour = workHourStartHour;
	}

	public String getWorkHourEndHour()
	{
		return workHourEndHour;
	}

	public void setWorkHourEndHour(String workHourEndHour)
	{
		this.workHourEndHour = workHourEndHour;
	}

	public String getWorkHourStartMin()
	{
		return workHourStartMin;
	}

	public void setWorkHourStartMin(String workHourStartMin)
	{
		this.workHourStartMin = workHourStartMin;
	}

	public String getWorkHourEndMin()
	{
		return workHourEndMin;
	}

	public void setWorkHourEndMin(String workHourEndMin)
	{
		this.workHourEndMin = workHourEndMin;
	}

	public String getCloseRequestClient()
	{
		return closeRequestClient;
	}

	public void setCloseRequestClient(String closeRequestClient)
	{
		this.closeRequestClient = closeRequestClient;
	}

	public String getCloseRequestConsultant()
	{
		return closeRequestConsultant;
	}

	public void setCloseRequestConsultant(String closeRequestConsultant)
	{
		this.closeRequestConsultant = closeRequestConsultant;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getJobCode()
	{
		return jobCode;
	}

	public void setJobCode(String jobCode)
	{
		this.jobCode = jobCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public double getExp_min() {
		return exp_min;
	}

	public void setExp_min(double exp_min) {
		this.exp_min = exp_min;
	}

	public double getExp_max() {
		return exp_max;
	}

	public void setExp_max(double exp_max) {
		this.exp_max = exp_max;
	}

	public double getCtc_min() {
		return ctc_min;
	}

	public void setCtc_min(double ctc_min) {
		this.ctc_min = ctc_min;
	}

	public double getCtc_max() {
		return ctc_max;
	}

	public void setCtc_max(double ctc_max) {
		this.ctc_max = ctc_max;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUploadjd() {
		return uploadjd;
	}

	public void setUploadjd(String uploadjd) {
		this.uploadjd = uploadjd;
	}

	public String getAdditionDetail() {
		return additionDetail;
	}

	public void setAdditionDetail(String additionDetail) {
		this.additionDetail = additionDetail;
	}

	
	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}



	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "clientId", referencedColumnName = "userid", nullable = false)
	private Registration client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastModifier", referencedColumnName = "userid")
	private Registration lastModifier;
	
	
	@Column
	private Date createDate;
	
	@Column
	private Date modifyDate;
	
	@Column
	private Date deleteDate;

	@Column
	private Date verifyDate;
	

	
	public int getNoOfPosts()
	{
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts)
	{
		this.noOfPosts = noOfPosts;
	}

/*	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}*/

	public Registration getClient()
	{
		return client;
	}

	public void setClient(Registration client)
	{
		this.client = client;
	}

	public Registration getLastModifier()
	{
		return lastModifier;
	}

	public void setLastModifier(Registration lastModifier)
	{
		this.lastModifier = lastModifier;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	
	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public Date getCloseDate()
	{
		return closeDate;
	}

	public void setCloseDate(Date closeDate)
	{
		this.closeDate = closeDate;
	}

	public int getProfileParDay()
	{
		return profileParDay;
	}

	public void setProfileParDay(int profileParDay)
	{
		this.profileParDay = profileParDay;
	}



	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<PostProfile> postProfile = new HashSet<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<PostConsultant> postConsultants = new HashSet<>();


	public Set<PostProfile> getPostProfile()
	{
		return postProfile;
	}

	public void setPostProfile(Set<PostProfile> postProfile)
	{
		this.postProfile = postProfile;
	}

	
//	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
//	private Set<PostConsultant> postConsultants;


	public Set<PostConsultant> getPostConsultants()
	{
		return postConsultants;
	}

	public void setPostConsultants(Set<PostConsultant> postConsultants)
	{
		this.postConsultants = postConsultants;
	}

	public Date getVerifyDate()
	{
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate)
	{
		this.verifyDate = verifyDate;
	}

	
	
}
