package com.unihyr.constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scala.languageFeature.reflectiveCalls;
/**
 * Used to store configuration variables which will exist 
 * for whole application
 * @author Rohit Tiwari
 */
public class GeneralConfig 
{
	public static final double CESS = 0.5;
	public static final double KrishiKalyan = 0.5;
	public static final String Label_ForConsManagePosition="Profiles Submitted";
	public static final String MessagePostDeleted="This post has been Deleted.";
	public static final String MessagePostClosed="This post has been closed.";
	public static final String MessagePostActive="This post is active. You can submit profiles.";
	public static final String MessagePostInActive="This post has been Deactivated. You can not submit profiles.";
	public static final String ErrorMsgWrongOfferedFee="pls enter the offered fee percentage within a valid range";
	public static final boolean UnihyrRatingFlag=false;
	public static final String LabelInstructionAudio="We understand that not everything can be put on the JD. Here you can upload your audio to pass on some of your key requirement for this role.";
	public static final String Label_PaymentClause="Refund clause";
	public static final String FaqEngagementLevel="It is an indicator of the competition for this position.";
	public static final String FaqOfferedFee = "It is the best rate offered by the consultant for this position. "
			+ "The maximum fee specified specified for this position was x %. This final recruitment "
			+ "fee will be estimated basis the offered fee.";
	public static final String FaqOfferedFeeNewPosition = "Specify the lowest rate you can offer within the specified "
//			+ "range to improve the visibility of your CV�s submitted at the employers end. "
			+ "The rate you offer here will be final and in case any of your candidate(s) is selected, "
			+ "the rate you specify here will be used for the estimation of recruitment fee.";
	public static final String FaqProfileQuota="It is the maximum number of profiles you can submit for a position in a week. This quota has been specified by the employer for this position.";
	public static final String FaqProfileQuotaSetMessage="The profile quota that you set here will be maximum number of profiles a consultant can submit for a given position in a week. This will help you regulate the number of profiles that you receive per week for a given position.";
	public static final String ProfileAction1 = "Shortlist";
	public static final String ProfileAction1Reject = "Reject";
	public static final String ProfileAction2 = "To be Offered";
	public static final String ProfileAction2Reject = "Decline";
	public static final String ProfileAction3 = "Offer Accepted ";
	public static final String ProfileAction3Reject = "Reject";
	public static final String ProfileAction4 = "Joined";
	public static final String ProfileAction4Reject = "Drop";
	
	public static final String InfoNoInstructionAudio="No instruction audio";
	public static final String InfoInstructionAudio="Please check instruction audio";
	
	public static final String UploadPath = "/var/unihyr/data/";
	public static final String Label_Activate = "Activate";
	public static final String Label_DeActivate = "Deactivate";
	public static final String SubmittedOnly = "Pending";
	public static final String Withdraw = "Withdrawn";
	public static final String Shortlist = "In Process";
	public static final String ShortlistRejected = "CV Rejected";
	public static final String SendOffer = "To be offered";
	public static final String SendOfferReject = "Interview Reject";
	public static final String OfferAccept = "Offer Accepted";
	public static final String OfferAcceptReject = "Offer Declined";
	public static final String OfferJoin = "Joined";
	public static final String OfferDrop = "Dropped";

	public static final String SendOfferDb = "recruited";
	public static final String PendingDb = "submitted";
	public static final String InProcessDb = "accepted";
	
	//public static final String UniHyrUrl = "http://localhost:8082/unihyr/";
//	public static final String DataUrl = "http://54.191.37.178/";
	public static final String DataUrl = "http://45.114.143.12/";
	//public static final String UniHyrUrl = "http://54.191.37.178/";
//	public static final String UniHyrUrl = "http://54.191.37.178/unihyr/";
	public static final String UniHyrUrl = "http://45.114.143.12/";
	public static final long PostDaysOut = 15;
	public static final long PostDaysInactive = 25;
	public static final long IdleCheckInterval = 86400000;
	public static final String Add_Post_Submit_Button_Value = "Submit";
	public static final String Delimeter = ",,,,,,";
	public static final long BillDaysOut = 7;
	public static final String UnihyrAddress = "UHYR Labs Private Limited 502, Fifth Floor, " ;
//	+
//			"Spaze iTech Park, Sector 49, Gurgaon � 122001, Haryana, India";
	public static final String UnihyrServiceTaxNo= "Not Applicable";
	public static final String UnihyrPanNo= "AABCU9702H";
	public static final String OldPassErrMsg = "New password can not be same as the old password";
	public static final String PassMatchErrMsg ="Re-Password not matched as new password";
	public static final String UnihyrName = "Unihyr Labs Pvt Ltd";
	public static final double UnihyrPercent = 18;
	public static final boolean UniHyr_Mail_Flag = false;
	public static final boolean UniHyr_Notificatin_Flag = true;
	public static final String EditPostUpdateInfo = "This post has been edited recently please see edit summary for more details";
	public static final String Unihyr_IFSC = "XXXXXXXXX";
	public static final String Unihyr_AccountNo = "XXXXXXXXX";
	//public static final String UploadPath = "D:/var/unihyr/data/";
	public static double TAX = 14;
	public static int NoOfRatingStaticParams = 2;
	public static int NoOfRatingDynamicParams = 3;
	public static int rpp = 10;
	public static int rpp_cons = 10;
	public static int globalRatingMaxRows1=10;
	public static int globalRatingWeight1=50;
	public static int globalRatingMaxRows2=21;
	public static int globalRatingWeight2=50;
	public static String admin_email = "unihyr@gmail.com,";
	public static int filesize=1024000;
	public static int audiofilesize=10240000;
	public static List<String> filetype = new ArrayList<>();
	public static List<String> audiofiletype = new ArrayList<>();
	public static List<String> topLocations= new ArrayList<>();
	
	static
	{
		filetype.add("doc");
		filetype.add("docx");
		filetype.add("pdf");
		audiofiletype.add("mp3");
		audiofiletype.add("amr");
		audiofiletype.add("wav");
		audiofiletype.add("m4a");
		audiofiletype.add("mp4");
//		audiofiletype.add("wma");
		audiofiletype.add("aac");
		topLocations.add("Delhi");
		topLocations.add("Mumbai");
		topLocations.add("Hyderabad");
		topLocations.add("Pune");
		topLocations.add("Bangalore");
		topLocations.add("Chennai");
		topLocations.add("Kolkata");
		topLocations.add("Gurgaon");
		topLocations.add("Noida");
	}
	
	/**
	 * String variable to store pattern for password validation.
	 */
	public static String passwordRegEx = "(?=.*\\d)(?=.*[a-z]).{6,20}";
	
	/** to validate password having required pattern or not
	 * @param password a String variable to pass password as an argument
	 * @return return boolean value either password is valid or not.
	 */
	public static boolean checkPasswordValid(String password)
	{
		Pattern p = Pattern.compile(passwordRegEx);//. represents single character  
		Matcher m = p.matcher(password);  
		boolean b = m.matches();  
		return b;
	}

	/**generate random password
	 * @return random value of type string
	 */
	public static String generatePassword()
	{
		char[] alphNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		Random rnd = new Random();

		StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) );
		for (int i = 0; i < 6; i++)
			sb.append(alphNum[rnd.nextInt(alphNum.length)]);

		String id = sb.toString();
		return id;
	}
}
