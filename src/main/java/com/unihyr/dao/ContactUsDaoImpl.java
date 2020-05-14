package com.unihyr.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.ContactUs;
import com.unihyr.domain.HelpDesk;
@Repository
public class ContactUsDaoImpl implements ContactUsDao
{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public long addContactUsDetails(ContactUs contactUs)
	{
		this.sessionFactory.getCurrentSession().save(contactUs);
		return contactUs.getContactusid();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactUs> getAllDemoRequest()
	{
		return (List<ContactUs>)this.sessionFactory.getCurrentSession().createCriteria(ContactUs.class)
				.list();
	}

}
