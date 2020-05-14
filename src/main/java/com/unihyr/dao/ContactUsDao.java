package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.ContactUs;

public interface ContactUsDao
{
	public long addContactUsDetails(ContactUs contactUs);

	public List<ContactUs> getAllDemoRequest();
}
