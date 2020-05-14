package com.unihyr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.SocialSharing;
import com.unihyr.domain.UserRole;
@Repository
public class SocialSharingDaoImpl implements SocialSharingDao
{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void load(SocialSharing socialSharing)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(SocialSharing socialSharing)
	{
		this.sessionFactory.getCurrentSession().save(socialSharing);
	}

	@Override
	public SocialSharing getSocialSharing(String userid)
	{
		List<SocialSharing> list = this.sessionFactory.getCurrentSession().createCriteria(SocialSharing.class)
				.add(Restrictions.eq("userid", userid)).list();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SocialSharing> getAllSocialSharing()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String  userid)
	{
		  String deleteQuery = "delete from SocialSharing where userid= :userid";
	      Query query = this.sessionFactory.getCurrentSession().createQuery(deleteQuery);
	      query.setString("userid", userid); //convert date from string
	      query.executeUpdate();
	      
	      
		
		
	}

}
