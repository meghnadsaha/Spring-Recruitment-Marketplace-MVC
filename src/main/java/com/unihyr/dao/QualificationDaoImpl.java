package com.unihyr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.node.LongNode;
import com.unihyr.domain.Qualification;

@Repository
public class QualificationDaoImpl implements QualificationDao
{
	
	@Autowired SessionFactory sessionFactory;

	@Override
	public Boolean addQualification(Qualification qualification)
	{
		try
		{
			
			this.sessionFactory.getCurrentSession().save(qualification);
				return true;
		} catch (HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Boolean editQualification(Qualification qualification)
	{
		try{
		this.sessionFactory.getCurrentSession().update(qualification);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteQualification(Qualification qualification)
	{
		try{
		this.sessionFactory.getCurrentSession().delete(qualification);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		}

	@Override
	public Qualification getQualificationByQid(long qid)
	{
		return (Qualification)this.sessionFactory.getCurrentSession().get(Qualification.class, qid);
	}
	
	@Override
	public List<Qualification> getAllQualification()
	{
		try{
		Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(Qualification.class);
		return (List<Qualification>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<Qualification>();
		}
		}

	@Override
	public List<Qualification> getAllUGQualification()
	{
		try{
		Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(Qualification.class);
		criteria.add(Restrictions.eq("qType", "UG"))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Qualification>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<Qualification>();
		}
		}

	@Override
	public List<Qualification> getAllPGQualification()
	{
		try{
		Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(Qualification.class);
		criteria.add(Restrictions.eq("qType", "PG")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		
		return (List<Qualification>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<Qualification>();
		}
		}

}
