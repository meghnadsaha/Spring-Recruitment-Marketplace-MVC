package com.unihyr.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.Notifications;

@Repository
public class NotificationDaoImpl implements NotificationDao
{
	@Autowired private SessionFactory sessionFactory;
	
	public long addNotification(Notifications Notifications)
	{
		this.sessionFactory.getCurrentSession().save(Notifications);
		this.sessionFactory.getCurrentSession().flush();
		return Notifications.getNotificationId();
	}
	public boolean updateNotification(Notifications Notifications)
	{
		try
		{
			this.sessionFactory.getCurrentSession().update(Notifications);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} 
		catch (Exception e)
		{
			return false;
			// TODO: handle exception
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notifications> getNotificationsByPostprofile(long ppid, int first, int max)
		{
		return this.sessionFactory.getCurrentSession().createCriteria(Notifications.class)
				.addOrder(Order.desc("date"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Notifications> getNotificationByUserid(String userid, int first, int max)
	{
			
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		
		Calendar cal2=Calendar.getInstance();

		Date startDate=cal2.getTime();
		Date endDate= cal.getTime();
		java.sql.Date start= new java.sql.Date(startDate.getTime());
		java.sql.Date end= new java.sql.Date(endDate.getTime());
		return this.sessionFactory.getCurrentSession().createCriteria(Notifications.class)
				.add(Restrictions.eq("userid",userid))
				.addOrder(Order.desc("date"))/*.add(Restrictions.between("date", start , end ))*/
				.list();
	}

	@Override
	public Long countUserNotification(String loggedinUser)
	{
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Notifications.class)
				.add(Restrictions.eq("userid",loggedinUser))
				.add(Restrictions.eq("readStatus", false))
				.setProjection(Projections.rowCount()).uniqueResult();
	}
	
}
