package com.unihyr.dao;

import com.unihyr.domain.UserRole;

public interface UserRoleDao 
{
	public int addUserRole(UserRole userRole, String role);
	public UserRole getRoleByUserId(String userid);
}
