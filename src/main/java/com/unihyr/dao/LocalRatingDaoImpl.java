package com.unihyr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.LocalRating;


@Repository
public class LocalRatingDaoImpl implements LocalRatingDao{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public LocalRating getLocalRatingById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long addLocalRating(LocalRating rating) {

		try
		{
			this.sessionFactory.getCurrentSession().save(rating);
			this.sessionFactory.getCurrentSession().flush();
			return rating.getSn();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<LocalRating> getLocalRatingList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalRating> getLocalRatingListByRangeConsIndustry(int noOfRows,int consId,int industryId) {
		Session session=this.sessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(LocalRating.class);
		Criterion crn=Restrictions.eq("industryId", industryId);
		Criterion crn1=Restrictions.eq("consultantId", consId);
		LogicalExpression lg=Restrictions.and(crn, crn1);
		cr.add(lg);
		cr.addOrder(Order.desc("createDate"));
		cr.setMaxResults(noOfRows);
		return cr.list();
	}
}
