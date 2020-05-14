package com.unihyr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.UserRoleDao;
import com.unihyr.domain.UserRole;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService 
{
	@Autowired private UserRoleDao userRoleDao;
	public UserRole getRoleByUserId(String userid)
	{
		return userRoleDao.getRoleByUserId(userid);
	}
	
	@Override
	public int addUserRole(UserRole userrole, String role) 
	{
		return userRoleDao.addUserRole(userrole, role);
	}
}
