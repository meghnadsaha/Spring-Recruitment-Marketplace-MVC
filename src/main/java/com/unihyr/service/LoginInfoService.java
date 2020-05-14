package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.LoginInfo;
import com.unihyr.model.RegistrationForm;

public interface LoginInfoService 
{
	public LoginInfo findUserById(String userid);
	
	public void addLoginInfo(LoginInfo login, RegistrationForm form);
	
	public void updateLoginInfo(LoginInfo loginInfo);
	
	public boolean checkUser(String userid, String password);
	
	public boolean updatePassword(String userid, String oldPassword, String password);
	
	public List<LoginInfo> getLoggedInUsers();

	public void deleteGeneralUser(LoginInfo li);
}
