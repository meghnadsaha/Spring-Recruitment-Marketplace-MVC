package com.unihyr.service;

import com.unihyr.domain.UserRole;

/**
 * Interface to define User Role related functions
 * @author silvereye
 *
 */
public interface UserRoleService 
{
	public UserRole getRoleByUserId(String userid);
	
	public int addUserRole(UserRole userrole, String role);
}
