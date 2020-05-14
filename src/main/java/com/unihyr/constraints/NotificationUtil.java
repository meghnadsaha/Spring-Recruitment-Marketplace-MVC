package com.unihyr.constraints;

public class NotificationUtil
{

	public static String POST_NAME_DELEMETER="_Position_Name_";
	public static String POST_JD_DELEMETER="_Job_Id_";
	public static String POST_CLIENT_NAME="_Client_Name_";
	public static String POSITION_QUICK_UPDATE="Quick Update : "+POST_NAME_DELEMETER+" ("+POST_JD_DELEMETER+") has a update by "+POST_CLIENT_NAME;
	public static String POSITINO_EDIT_JD="JD Edit : "+POST_NAME_DELEMETER+" ("+POST_JD_DELEMETER+") has been edited by "+POST_CLIENT_NAME;
	public static String POSITION_DEACTIVATE="Position Deactivated:  "+POST_NAME_DELEMETER+" ("+POST_JD_DELEMETER+") has been deactivated by "+POST_CLIENT_NAME;
	public static String POSITION_ACTIVATE="Position Activated:  : "+POST_NAME_DELEMETER+" ("+POST_JD_DELEMETER+") has been activated by "+POST_CLIENT_NAME;
	public static String POSITION_DELETE="Position Deleted:  "+POST_NAME_DELEMETER+" ("+POST_JD_DELEMETER+") has been deleted by "+POST_CLIENT_NAME;
}
