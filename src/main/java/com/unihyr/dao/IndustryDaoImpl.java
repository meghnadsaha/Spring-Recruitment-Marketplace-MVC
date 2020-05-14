package com.unihyr.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.criteria.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.constraints.Roles;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Location;
import com.unihyr.domain.Registration;

@Repository
public class IndustryDaoImpl implements IndustryDao
{
	@Autowired private SessionFactory sessionFactory; 
	
	@Override
	public int addIndustry(Industry industry)
	{
		
		try
		{
			this.sessionFactory.getCurrentSession().save(industry);
			this.sessionFactory.getCurrentSession().flush();
			return industry.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public void updateIndustry(Industry industry)
	{
		this.sessionFactory.getCurrentSession().update(industry);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public Industry getIndustry(int id)
	{
		return (Industry)this.sessionFactory.getCurrentSession().get(Industry.class, id);
	}
	
	@Override
	public List<Industry> getIndustryList()
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Industry.class).add(Restrictions.isNull("deleteDate")).addOrder(Order.asc("industry")).list();
	}
	
	@Override
	public List<Industry> getIndustryList(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Industry.class).add(Restrictions.isNull("deleteDate")).addOrder(Order.asc("industry")).setFirstResult(first).setMaxResults(max).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Industry>  getIndustryByName(String industry)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Industry.class)
				.add(Restrictions.eq("industry", industry).ignoreCase()).list();
	}

	@Override
	public List<Registration> getClientsByIndustry(int industryId)
	{
		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid INNER JOIN user_industry ui on reg.lid = ui.lid where ur.userrole =:role and ui.id =:industryId";
		return (List<Registration>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class)
				.setString("role", Roles.ROLE_EMP_MANAGER.toString())
				.setParameter("industryId", industryId).list();
	}

	@Override
	public List<Registration> getConsultantsByIndustry(int industryId)
	{

		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid INNER JOIN user_industry ui on reg.lid = ui.lid where ur.userrole =:role and ui.id =:industryId";
		return (List<Registration>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class)
				.setString("role", Roles.ROLE_CON_MANAGER.toString())
				.setParameter("industryId", industryId).list();
	}
	@Override
	public long countClientsByIndustry(int industryId)
	{
		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid INNER JOIN user_industry ui on reg.lid = ui.lid where ur.userrole =:role and ui.id =:industryId";
		return (long) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class)
				.setString("role", Roles.ROLE_EMP_MANAGER.toString())
				.setParameter("industryId", industryId).list().size();
	}

	@Override
	public long countConsultantsByIndustry(int industryId)
	{

		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid INNER JOIN user_industry ui on reg.lid = ui.lid where ur.userrole =:role and ui.id =:industryId";
		return (long) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class)
				.setString("role", Roles.ROLE_CON_MANAGER.toString())
				.setParameter("industryId", industryId).list().size();
	}

	@Override
	public void deleteIndustry(Industry industry)
	{
		this.sessionFactory.getCurrentSession().delete(industry);
		
	}
	
	

}
