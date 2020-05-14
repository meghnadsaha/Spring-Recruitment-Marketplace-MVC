package com.unihyr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Model class Used to store all configurations which are global to whole application and 
 * to all type of Users. These permissions can only be updated by Admin  
 * @author Rohit Tiwari
 */
@Entity
@Table(name="configvarialbes")
public class ConfigVariables
{
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int configId;
	@Column
	private String varName;
	@Column
	@Lob
	private String varValue;
	@Column
	private String scope;
	@Column
	private String modifiedBy;
	@Column
	private Date modificationDate;
	@Column
	private Boolean status;
	
	public Boolean getStatus()
	{
		return status;
	}
	public void setStatus(Boolean status)
	{
		this.status = status;
	}
	public String getScope()
	{
		return scope;
	}
	public void setScope(String scope)
	{
		this.scope = scope;
	}
	public String getModifiedBy()
	{
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}
	public Date getModificationDate()
	{
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate)
	{
		this.modificationDate = modificationDate;
	}
	public int getConfigId()
	{
		return configId;
	}
	public void setConfigId(int configId)
	{
		this.configId = configId;
	}
	public String getVarName()
	{
		return varName;
	}
	public void setVarName(String varName)
	{
		this.varName = varName;
	}
	public String getVarValue()
	{
		return varValue;
	}
	public void setVarValue(String varValue)
	{
		this.varValue = varValue;
	}
}
