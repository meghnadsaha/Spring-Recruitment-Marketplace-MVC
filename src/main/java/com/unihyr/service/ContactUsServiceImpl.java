package com.unihyr.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.dao.ContactUsDao;
import com.unihyr.domain.ContactUs;

@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService
{
	@Autowired ContactUsDao contactUsDao;
	
	public long addContactUsDetails(ContactUs contactUs){
		return contactUsDao.addContactUsDetails(contactUs);
	}

	@Override
	public List<ContactUs> getAllDemoRequest()
	{
		return contactUsDao.getAllDemoRequest();
	}
}
