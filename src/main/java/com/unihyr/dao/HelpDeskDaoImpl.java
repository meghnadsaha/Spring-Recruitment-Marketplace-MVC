package com.unihyr.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.unihyr.domain.HelpDesk;

@Repository
public class HelpDeskDaoImpl implements HelpDeskDao
{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public long addHelpDesk(HelpDesk helpDesk)
	{
		this.sessionFactory.getCurrentSession().save(helpDesk);
		return helpDesk.getHdid();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpDesk> getAllHelpDeskList(String seenStatus)
	{
		return (List<HelpDesk>)this.sessionFactory.getCurrentSession().createCriteria(HelpDesk.class)
//				.add(Restrictions.isNotNull("seenDate"))
				.list();
	}

}
