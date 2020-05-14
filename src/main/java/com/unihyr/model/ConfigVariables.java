package com.unihyr.model;

/**
 * Model class Used to store all configurations which are global to whole application.  
 * @author silvereye
 */
public class ConfigVariables
{
	private double CESS = 0.5;
	private String UploadPath = "/var/unihyr/data/";
	private double TAX = 14;
	private int NoOfRatingParams = 3;
	private int rpp = 10;
	private int rpp_cons = 10;
	private int globalRatingMaxRows1=10*NoOfRatingParams;
	private int globalRatingWeight1=50;
	private int globalRatingMaxRows2=21;
	private int globalRatingWeight2=50;
	private String admin_email = "amar@silvereye.co";
	private int filesize=1024000;
	public double getCESS()
	{
		return CESS;
	}
	public void setCESS(double cESS)
	{
		CESS = cESS;
	}
	public String getUploadPath()
	{
		return UploadPath;
	}
	public void setUploadPath(String uploadPath)
	{
		UploadPath = uploadPath;
	}
	public double getTAX()
	{
		return TAX;
	}
	public void setTAX(double tAX)
	{
		TAX = tAX;
	}
	public int getNoOfRatingParams()
	{
		return NoOfRatingParams;
	}
	public void setNoOfRatingParams(int noOfRatingParams)
	{
		NoOfRatingParams = noOfRatingParams;
	}
	public int getRpp()
	{
		return rpp;
	}
	public void setRpp(int rpp)
	{
		this.rpp = rpp;
	}
	public int getRpp_cons()
	{
		return rpp_cons;
	}
	public void setRpp_cons(int rpp_cons)
	{
		this.rpp_cons = rpp_cons;
	}
	public int getGlobalRatingMaxRows1()
	{
		return globalRatingMaxRows1;
	}
	public void setGlobalRatingMaxRows1(int globalRatingMaxRows1)
	{
		this.globalRatingMaxRows1 = globalRatingMaxRows1;
	}
	public int getGlobalRatingWeight1()
	{
		return globalRatingWeight1;
	}
	public void setGlobalRatingWeight1(int globalRatingWeight1)
	{
		this.globalRatingWeight1 = globalRatingWeight1;
	}
	public int getGlobalRatingMaxRows2()
	{
		return globalRatingMaxRows2;
	}
	public void setGlobalRatingMaxRows2(int globalRatingMaxRows2)
	{
		this.globalRatingMaxRows2 = globalRatingMaxRows2;
	}
	public int getGlobalRatingWeight2()
	{
		return globalRatingWeight2;
	}
	public void setGlobalRatingWeight2(int globalRatingWeight2)
	{
		this.globalRatingWeight2 = globalRatingWeight2;
	}
	public String getAdmin_email()
	{
		return admin_email;
	}
	public void setAdmin_email(String admin_email)
	{
		this.admin_email = admin_email;
	}
	public int getFilesize()
	{
		return filesize;
	}
	public void setFilesize(int filesize)
	{
		this.filesize = filesize;
	}
	
	

}
