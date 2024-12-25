package com.unihyr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.Industry;
import com.unihyr.domain.PostConsultant;

@Repository
public class PostConsultnatDaoImpl implements PostConsultnatDao
{
	@Autowired private SessionFactory sessionFactory;

	public List<PostConsultant> getInterestedPostForConsultantByClient(String consultantId, String clientId,String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
		criteria.createAlias("post.client", "clientAlias").createAlias("post", "postAlias")
		.add(Restrictions.isNotNull("postAlias.verifyDate"))
		.add(Restrictions.isNull("postAlias.deleteDate"));
		// criteria.add(Restrictions.eq("clientAlias.userid", clientId));
		Criterion cn1 = Restrictions.eq("clientAlias.userid", clientId);
		// criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		criteria.createAlias("consultant", "consAlias").add(Restrictions.eq("consAlias.userid", consultantId));
		if(sortParam.equals("percentile"))
		criteria.addOrder(Order.asc(sortParam));
		else{
			criteria.addOrder(Order.asc("postAlias."+sortParam));
			criteria.addOrder(Order.desc("postAlias.verifyDate"));
		
		}
		return criteria.list();
	}
//	public List<PostConsultant> getClosedInterestedPostForConsultantByClient(String consultantId, String clientId,String sortParam)
//	{
//		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
//		criteria.createAlias("post.client", "clientAlias").createAlias("post", "postAlias")
//		.add(Restrictions.isNotNull("postAlias.verifyDate")).add(Restrictions.disjunction().add(Restrictions.isNotNull("postAlias.joinCloseDate"))
//		.add(Restrictions.isNotNull("postAlias.deleteDate")));
//		// criteria.add(Restrictions.eq("clientAlias.userid", clientId));
//		Criterion cn1 = Restrictions.eq("clientAlias.userid", clientId);
//		// criteria.createAlias("client", "clientAlias");
//		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
//		criteria.add(Restrictions.or(cn1, cn2));
//		criteria.createAlias("consultant", "consAlias").add(Restrictions.eq("consAlias.userid", consultantId));
//		if(sortParam.equals("percentile"))
//		criteria.addOrder(Order.asc(sortParam));
//		else{
//			criteria.addOrder(Order.asc("postAlias."+sortParam));
//			criteria.addOrder(Order.desc("postAlias.verifyDate"));
//
//		}
//		return criteria.list();
//	}
public List<PostConsultant> getClosedInterestedPostForConsultantByClient(String consultantId, String clientId, String sortParam) {
	Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);

	// Fetch required associations eagerly
	criteria.setFetchMode("post.client", FetchMode.JOIN);
	criteria.setFetchMode("post", FetchMode.JOIN);
	criteria.setFetchMode("consultant", FetchMode.JOIN);

	criteria.createAlias("post.client", "clientAlias")
			.createAlias("post", "postAlias")
			.add(Restrictions.isNotNull("postAlias.verifyDate"))
			.add(Restrictions.disjunction()
							 .add(Restrictions.isNotNull("postAlias.joinCloseDate"))
							 .add(Restrictions.isNotNull("postAlias.deleteDate"))
			);

	Criterion cn1 = Restrictions.eq("clientAlias.userid", clientId);
	Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
	criteria.add(Restrictions.or(cn1, cn2));

	criteria.createAlias("consultant", "consAlias")
			.add(Restrictions.eq("consAlias.userid", consultantId));

	if (sortParam.equals("percentile")) {
		criteria.addOrder(Order.asc(sortParam));
	} else {
		criteria.addOrder(Order.asc("postAlias." + sortParam));
		criteria.addOrder(Order.desc("postAlias.verifyDate"));
	}

	return criteria.list();
}

	public List<PostConsultant> getInterestedPostForConsultantByClientOrdered(String consultantId, String clientId,String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
		criteria.createAlias("post.client", "clientAlias").createAlias("post", "postAlias")
		.add(Restrictions.isNotNull("postAlias.verifyDate"));
		// criteria.add(Restrictions.eq("clientAlias.userid", clientId));
		Criterion cn1 = Restrictions.eq("clientAlias.userid", clientId);
		// criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		criteria.createAlias("consultant", "consAlias").add(Restrictions.eq("consAlias.userid", consultantId));
		criteria.addOrder(Order.asc(sortParam));
		return criteria.list();
	}
	
	@Override
	public List<PostConsultant> getInterestedConsultantByPost(long postId,String sortOrder,String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
		if(sortOrder.indexOf("desc")>=0)
		criteria.addOrder(Order.desc(sortParam));
		else
		criteria.addOrder(Order.asc(sortParam));
		criteria.add(Restrictions.eq("post.postId", postId));
		return criteria.list();
	}
	@Override
	public List<PostConsultant> getInterestedConsultantSortedByPost(long postId,String sortParam,String order)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
		criteria.add(Restrictions.eq("post.postId", postId));
		criteria.addOrder(Order.asc(sortParam));
		return criteria.list();
	}

	@Override
	public List<PostConsultant> getPostConsultantsByConsultant(int lid)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
		criteria.add(Restrictions.eq("consultant.lid", lid));
		return criteria.list();
	}

	@Override
	public void updatePostConsultant(PostConsultant postConsultant)
	{
		this.sessionFactory.getCurrentSession().update(postConsultant);
		this.sessionFactory.getCurrentSession().flush();
		return;
	}

	@Override
	public List<PostConsultant> getPostConsultantsByConsIdandPostId(int lid, long postId)
	{
		List<PostConsultant> list=new  ArrayList<PostConsultant>();
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class);
		criteria.add(Restrictions.eq("post.postId", postId));
		criteria.add(Restrictions.eq("consultant.lid", lid));
		list=criteria.list();
		if(list!=null)
		return list;
		else
		return new  ArrayList<PostConsultant>();
	}
}
