package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.LoginInfoDao;
import com.unihyr.domain.LoginInfo;
import com.unihyr.model.RegistrationForm;

@Service
@Transactional
public class LoginInfoServiceImpl implements LoginInfoService
{
	@Autowired private LoginInfoDao loginInfoDao;
	
	@Override
	public LoginInfo findUserById(String userid)
	{
		return loginInfoDao.findUserById(userid);
	}
	
	@Override
	public void addLoginInfo(LoginInfo login, RegistrationForm form) {
		loginInfoDao.addLoginInfo(login,form);
	}
	
	@Override
	public void updateLoginInfo(LoginInfo loginInfo)
	{
		loginInfoDao.updateLoginInfo(loginInfo);
	}
	
	@Override
	public boolean checkUser(String userid, String password)
	{
		return this.loginInfoDao.checkUser(userid, password);
	}
	
	
	@Override
	public boolean updatePassword(String userid, String oldPassword, String password)
	{
		return this.loginInfoDao.updatePassword(userid, oldPassword, password);
	}

	@Override
	public List<LoginInfo> getLoggedInUsers()
	{
		return this.loginInfoDao.getLoggedInUsers();
	}

	@Override
	public void deleteGeneralUser(LoginInfo li)
	{
		this.loginInfoDao.deleteGeneralUser(li);
	}
}
