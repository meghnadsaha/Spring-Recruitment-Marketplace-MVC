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
import com.unihyr.domain.LoginHistory;
import com.unihyr.domain.Registration;

@Service
@Transactional
public class LoginHistoryServiceImpl implements LoginHistroyService
{

	@Override
	public int addLoginHistory(LoginHistory loginHistory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LoginHistory> getLoginHistory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
