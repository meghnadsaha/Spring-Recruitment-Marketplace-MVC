package com.unihyr.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.unihyr.domain.Industry;

public class PostModel 
{
	private long postId;
	
	@NotBlank(message="{NotBlank.postForm.title}")
	private String title;
	
	@NotBlank(message="{NotBlank.postForm.location}")
	private String location;
	
	//@NotBlank(message="{NotBlank.postForm.function}")
	private String function;
	
	private double exp_min;
	
	
	private double exp_max;
	
	private double ctc_min;
	
	
	private double ctc_max;
	
	private String workHourStartHour;
	private String workHourStartMin;

	private String workHourEndHour;
	private String workHourEndMin;

	private String editSummary;
	private String variablePayComment;

//	@NotBlank(message="{NotBlank.postForm.criteria}")
//	private String criteria;
	
	@Lob
	private String comment;
	
	private String uploadjd;
	
	@Lob
	private String additionDetail;
	
	@Min(value=1, message="{Min.postForm.noOfPosts}")
	private int noOfPosts;
	
/*	
	@NotBlank(message="{NotBlank.postForm.role}")
	private String role;
	
	
	@NotBlank(message="{NotBlank.postForm.designation}")
	private String designation;*/
	
//	private int inProcess;
	
	private int profileParDay;

	private MultipartFile uploadJdfile;
	private MultipartFile uploadJdAudiofile;

	private double feePercent;

	private Date openAgainDate;

	private String qualification_ug;
	private String qualification_pg;

	private String uploadjdaudio;
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

	public MultipartFile getUploadJdAudiofile()
	{
		return uploadJdAudiofile;
	}

	public void setUploadJdAudiofile(MultipartFile uploadJdAudiofile)
	{
		this.uploadJdAudiofile = uploadJdAudiofile;
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

	public String getWorkHourStartHour()
	{
		return workHourStartHour;
	}

	public void setWorkHourStartHour(String workHourStartHour)
	{
		this.workHourStartHour = workHourStartHour;
	}

	public String getWorkHourStartMin()
	{
		return workHourStartMin;
	}

	public void setWorkHourStartMin(String workHourStartMin)
	{
		this.workHourStartMin = workHourStartMin;
	}

	public String getWorkHourEndHour()
	{
		return workHourEndHour;
	}

	public void setWorkHourEndHour(String workHourEndHour)
	{
		this.workHourEndHour = workHourEndHour;
	}

	public String getWorkHourEndMin()
	{
		return workHourEndMin;
	}

	public void setWorkHourEndMin(String workHourEndMin)
	{
		this.workHourEndMin = workHourEndMin;
	}

	public String getEditSummary()
	{
		return editSummary;
	}

	public void setEditSummary(String editSummary)
	{
		this.editSummary = editSummary;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
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

//	public String getCriteria() {
//		return criteria;
//	}
//
//	public void setCriteria(String criteria) {
//		this.criteria = criteria;
//	}

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

	public int getNoOfPosts()
	{
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts)
	{
		this.noOfPosts = noOfPosts;
	}

	/*public String getRole()
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

	public MultipartFile getUploadJdfile()
	{
		return uploadJdfile;
	}

	public void setUploadJdfile(MultipartFile uploadJdfile)
	{
		this.uploadJdfile = uploadJdfile;
	}

	public int getProfileParDay()
	{
		return profileParDay;
	}

	public void setProfileParDay(int profileParDay)
	{
		this.profileParDay = profileParDay;
	}

	
}	
