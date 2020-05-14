package com.unihyr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.unihyr.dao.NotificationDao;
import com.unihyr.domain.Notifications;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService
{

	@Autowired private NotificationDao notificationDao;
	
	
	@Override
	public long addNotification(Notifications Notifications)
	{
		// TODO Auto-generated method stub
		return notificationDao.addNotification(Notifications);
	}

	@Override
	public boolean updateNotification(Notifications Notifications)
	{
		// TODO Auto-generated method stub
		return notificationDao.updateNotification(Notifications);
	}

	@Override
	public List<Notifications> getNotificationsByPostprofile(long ppid, int first, int max)
	{
		// TODO Auto-generated method stub
		return notificationDao.getNotificationsByPostprofile(ppid, first, max);
	}

	@Override
	public List<Notifications> getNotificationByUserid(String userid, int first, int max)
	{
		// TODO Auto-generated method stub
		return notificationDao.getNotificationByUserid(userid, first, max);
	}

	@Override
	public Long countUserNotifications(String loggedinUser)
	{
		return this.notificationDao.countUserNotification(loggedinUser);
	}
}
