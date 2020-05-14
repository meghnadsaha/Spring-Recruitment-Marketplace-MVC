package com.unihyr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addUserRole(UserRole userrole, String role) {
		userrole.setUserrole(role);
		this.sessionFactory.getCurrentSession().save(userrole);
		System.out.println("UserRole assigned");
		this.sessionFactory.getCurrentSession().flush();
		return userrole.getSn();
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserRole getRoleByUserId(String userid) {
		List<UserRole> roleList = this.sessionFactory.getCurrentSession().createCriteria(UserRole.class)
				.add(Restrictions.eq("userid", userid)).list();
		if (!roleList.isEmpty()) {
			return roleList.get(0);
		}
		return null;
	}
	
}
