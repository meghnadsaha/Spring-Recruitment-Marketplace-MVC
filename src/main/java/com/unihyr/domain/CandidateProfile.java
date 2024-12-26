package com.unihyr.domain;

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
import javax.persistence.Table;

@Entity
@Table(name = "candidateprofile")
public class CandidateProfile
{
	@Id
	@Column(name = "profileId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long profileId;

	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String currentRole;

	@Column(nullable = false)
	private String willingToRelocate;

	@Column
	private int noticePeriod;

	@Column(nullable = false)
	private String contact;
	@Column
	private String countryCode;
	
	@Column(nullable = false)
	private String currentOrganization;

	@Column
	private Double  currentCTC;
	@Column
	private Double expectedCTC;
	@Column
	private Double experience;

	@Column
	private Date date;
	
	@Column
	private Date deleteDate;
	
	@Column
	private Date published;

	@Column
	private String currentLocation;
	@Column
	private String preferredLocation;

	@Column
	private String resumePath;
	@Column
	private String ctcComments;

	@Column
	private String dateofbirth;
	@Column
	private String qualification_ug;
	@Column
	private String qualification_pg;
	
	
	
	public String getPreferredLocation()
	{
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation)
	{
		this.preferredLocation = preferredLocation;
	}

	public Double getExperience()
	{
		return experience;
	}

	public void setExperience(Double experience)
	{
		this.experience = experience;
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
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

	

	public String getDateofbirth()
	{
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth)
	{
		this.dateofbirth = dateofbirth;
	}

	public String getCtcComments()
	{
		return ctcComments;
	}

	public void setCtcComments(String ctcComments)
	{
		this.ctcComments = ctcComments;
	}

	public String getCurrentLocation()
	{
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation)
	{
		this.currentLocation = currentLocation;
	}

	@ManyToOne(cascade =
	{ CascadeType.ALL })
	@JoinColumn(name = "consultantId", referencedColumnName = "userid")
	private Registration registration;

	public int getNoticePeriod()
	{
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod)
	{
		this.noticePeriod = noticePeriod;
	}

	public Double getCurrentCTC()
	{
		return currentCTC;
	}

	public void setCurrentCTC(Double currentCTC)
	{
		this.currentCTC = currentCTC;
	}

	public Double getExpectedCTC()
	{
		return expectedCTC;
	}

	public void setExpectedCTC(Double expectedCTC)
	{
		this.expectedCTC = expectedCTC;
	}

	public String getWillingToRelocate()
	{
		return willingToRelocate;
	}

	public void setWillingToRelocate(String willingToRelocate)
	{
		this.willingToRelocate = willingToRelocate;
	}

	public long getProfileId()
	{
		return profileId;
	}

	public void setProfileId(long profileId)
	{
		this.profileId = profileId;
	}

	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCurrentRole()
	{
		return currentRole;
	}

	public void setCurrentRole(String currentRole)
	{
		this.currentRole = currentRole;
	}

	
	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public String getCurrentOrganization()
	{
		return currentOrganization;
	}

	public void setCurrentOrganization(String currentOrganization)
	{
		this.currentOrganization = currentOrganization;
	}


	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Date getDeleteDate()
	{
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate)
	{
		this.deleteDate = deleteDate;
	}

	public Date getPublished()
	{
		return published;
	}

	public void setPublished(Date published)
	{
		this.published = published;
	}

	
	public String getResumePath()
	{
		return resumePath;
	}

	public void setResumePath(String resumePath)
	{
		this.resumePath = resumePath;
	}

	public Registration getRegistration()
	{
		return registration;
	}

	public void setRegistration(Registration registration)
	{
		this.registration = registration;
	}

	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)  
    private Set<PostProfile> postProfile;

	public Set<PostProfile> getPostProfile()
	{
		return postProfile;
	}

	public void setPostProfile(Set<PostProfile> postProfile)
	{
		this.postProfile = postProfile;
	}
	
	@Column
	@Lob
	private String screeningNote;
	@Column
	@Lob
	private String resumeText;

	public String getResumeText()
	{
		return resumeText;
	}

	public void setResumeText(String resumeText)
	{
		this.resumeText = resumeText;
	}

	public String getScreeningNote()
	{
		return screeningNote;
	}

	public void setScreeningNote(String screeningNote)
	{
		this.screeningNote = screeningNote;
	}
	
	
	
	
	
	
}
