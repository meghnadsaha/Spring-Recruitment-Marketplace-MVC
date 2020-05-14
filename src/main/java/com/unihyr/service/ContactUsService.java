package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.ContactUs;

public interface ContactUsService
{

	long addContactUsDetails(ContactUs model);
	List<ContactUs> getAllDemoRequest();
}
