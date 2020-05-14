package com.unihyr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.LoginInfo;
import com.unihyr.model.RegistrationForm;

@Repository
public class LoginInfoDaoImpl implements LoginInfoDao 
{
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public LoginInfo findUserById(String userid)
	{
		List<LoginInfo> logList = this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class).add(Restrictions.eq("userid", userid))
				.setFetchMode("roles", FetchMode.JOIN)
				.list();
		if(!logList.isEmpty())
		{
			return logList.get(0);
		}
		return null;
	}
	@Override
	public void addLoginInfo(LoginInfo login, RegistrationForm form) {
		String rawpwd=login.getPassword();
		System.out.println("rawpwd in logindao="+rawpwd);
		String encryptedpwd=BCrypt.hashpw(rawpwd, BCrypt.gensalt());
		System.out.println("encrypted pwd in logindao="+encryptedpwd);
		login.setPassword(encryptedpwd);
		this.sessionFactory.getCurrentSession().save(login);
		System.out.println("User added successfully");
	}
	
	@Override
	public void updateLoginInfo(LoginInfo loginInfo)
	{
		this.sessionFactory.getCurrentSession().update(loginInfo);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public boolean checkUser(String userid, String password)
	{
		List<LoginInfo> logList = this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class).add(Restrictions.eq("userid", userid)).list();
		if(!logList.isEmpty())
		{
			return BCrypt.checkpw(password, logList.get(0).getPassword());
		}
		return false;
	}
	
	public boolean updatePassword(String userid, String oldPassword, String password)
	{
		List<LoginInfo> logList = this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class).add(Restrictions.eq("userid", userid)).list();
		if(!logList.isEmpty())
		{
			LoginInfo info = logList.get(0);
			
			String encryptedpwd=BCrypt.hashpw(password, BCrypt.gensalt());
			info.setPassword(encryptedpwd);
			this.sessionFactory.getCurrentSession().update(info);
			return true;
		
		}
		return false;
	}
	@Override
	public List<LoginInfo> getLoggedInUsers()
	{
		List<LoginInfo> logList = this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class)
				.add(Restrictions.eq("isLogin", Boolean.TRUE))
				.list();
		return logList;
	}
	@Override
	public void deleteGeneralUser(LoginInfo li)
	{
		Session session=this.sessionFactory.getCurrentSession();
		String hql = "DELETE FROM user_industry  "  + 
	             "WHERE lid = :employee_id";
		Query query = session.createSQLQuery(hql);
		query.setParameter("employee_id", li.getLid());
		int result = query.executeUpdate();
		 hql = "DELETE FROM Registration  "  + 
	             "WHERE log_lid = :employee_id";
		 query = session.createQuery(hql);
		query.setParameter("employee_id", li.getLid());
		 result = query.executeUpdate();
		 hql = "DELETE FROM UserRole  "  + 
	             "WHERE userid = :employee_id";
		 query = session.createQuery(hql);
		query.setParameter("employee_id", li.getUserid());
		 result = query.executeUpdate();
		 hql = "DELETE FROM LoginInfo  "  + 
	             "WHERE lid = :employee_id";
		 query = session.createQuery(hql);
		query.setParameter("employee_id", li.getLid());
		 result = query.executeUpdate();
			//this.sessionFactory.getCurrentSession().delete(li);
			this.sessionFactory.getCurrentSession().flush();
		}
	
}
