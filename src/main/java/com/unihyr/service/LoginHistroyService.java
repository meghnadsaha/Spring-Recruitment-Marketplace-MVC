package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.Industry;
import com.unihyr.domain.LoginHistory;

public interface LoginHistroyService 
{
	public int addLoginHistory(LoginHistory loginHistory);
	
	public List<LoginHistory> getLoginHistory();
	

}
