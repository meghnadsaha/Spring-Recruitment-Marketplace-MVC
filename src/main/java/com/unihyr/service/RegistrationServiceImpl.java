package com.unihyr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.RegistrationDao;
import com.unihyr.domain.Registration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService
{
	
	
	@Autowired private JavaMailSender mailSender;
	@Autowired private RegistrationDao registrationDao;
	
	@Override
	public Registration getRegistationByUserId(String userid)
	{
		return registrationDao.getRegistationByUserId(userid);
	}
	
	@Override
	public void update(Registration registration)
	{
		registrationDao.update(registration);
	}
	
	@Override
	public int countUsers()
	{
		return registrationDao.countUsers();
	}

	@Override
	public List<Registration> getRegistrations(int first, int max)
	{
		return registrationDao.getRegistrations(first, max);
	}
	
	@Override
	public List<Registration> getConsultantsByClient(String clientId)
	{
		return this.registrationDao.getConsultantsByClient(clientId);
	}
	
	@Override
	public List<Registration> getConsultantsByPost(long postId)
	{
		return this.registrationDao.getConsultantsByPost(postId);
	}
	
	@Override
	public List<Registration> getClientsByIndustyForConsultant(String consultantId)
	{
		return this.registrationDao.getClientsByIndustyForConsultant(consultantId);
	}
	
	@Override
	public long countConsultantList()
	{
		return this.registrationDao.countConsultantList();
	}
	
	@Override
	public long countClientsList()
	{
		return this.registrationDao.countClientsList();
	}

	public List<Registration> getClientList(int first, int max)
	{
		return this.registrationDao.getClientList(first, max);
	}
	
	public List<Registration> getConsultantList(int first, int max)
	{
		return this.registrationDao.getConsultantList(first, max);
	}
	
	public List<Registration> getCoUsersByUserid(String userid)
	{
		return this.registrationDao.getCoUsersByUserid(userid);
	}
	
	public List<Registration> getConsultantsByClientIndustry(String clientId)
	{
		return this.registrationDao.getConsultantsByClientIndustry(clientId);
	}
	
	public List<Registration> getClientAndConsultantAdminList(int first, int max)
	{
		return this.registrationDao.getClientAndConsultantAdminList(first, max);
	}

	@Override
	public Registration getRegistrationsByName(String userName)
	{
		// TODO Auto-generated method stub
		return this.registrationDao.getRegistrationsByName(userName);
	}

	@Override
	public void insertUserIndustryMap(int industryId, int registrationId)
	{
		this.registrationDao.insertUserIndustryMap(industryId, registrationId);
		
	}

	@Override
	public void disableUser(String userid)
	{
		this.registrationDao.insertUserIndustryMap(userid);
	}

	@Override
	public List<Registration> getConsultantListJoined(int first, int max)
	{
		return this.registrationDao.getConsultantListJoined( first,  max);

	}
}
