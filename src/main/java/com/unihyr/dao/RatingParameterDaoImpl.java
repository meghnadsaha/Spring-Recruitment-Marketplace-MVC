package com.unihyr.dao;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.RatingParameter;

@Repository
public class RatingParameterDaoImpl implements RatingParameterDao {


	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public int addRatingParameter(RatingParameter RatingParameter) {


		try
		{
			this.sessionFactory.getCurrentSession().save(RatingParameter);
			this.sessionFactory.getCurrentSession().flush();
			return RatingParameter.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	
	}

	@Override
	public void updateRatingParameter(RatingParameter RatingParameter) {
		// TODO Auto-generated method stub

	}

	@Override
	public RatingParameter getRatingParameter(int id) {
		// TODO Auto-generated method stub
		return (RatingParameter) this.sessionFactory.getCurrentSession().get(RatingParameter.class, id);
	}

	@Override
	public List<RatingParameter> getRatingParameterList() {
		// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				List<RatingParameter> list = (List<RatingParameter>) this.sessionFactory.getCurrentSession()
						.createCriteria(RatingParameter.class)
						.list();
				if (!list.isEmpty())
				{
					return list;
				}
		return null;
	}

}
