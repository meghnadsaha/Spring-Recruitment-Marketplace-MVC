package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.Notifications;

public interface NotificationService
{

	public long addNotification(Notifications Notifications);
	
	public boolean updateNotification(Notifications Notifications);
	
	public List<Notifications> getNotificationsByPostprofile(long ppid, int first, int max);
	
	public List<Notifications> getNotificationByUserid(String userid, int first, int max);

	public Long countUserNotifications(String loggedinUser);
	
	

}
