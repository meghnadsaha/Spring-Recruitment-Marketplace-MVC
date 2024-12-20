package com.unihyr.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.Industry;
import com.unihyr.domain.Post;
import com.unihyr.domain.Registration;

@Repository
@SuppressWarnings("unchecked")
public class PostDaoImpl implements PostDao
{

	private static final Logger logger = LoggerFactory.getLogger(PostDaoImpl.class);
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public long addPost(Post post)
	{
		this.sessionFactory.getCurrentSession().save(post);
		this.sessionFactory.getCurrentSession().flush();
		return post.getPostId();
	}
	
	@Override
	public void updatePost(Post post)
	{
		this.sessionFactory.getCurrentSession().update(post);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public Post getPost(long postId)
	{
		List<Post> list  = this.sessionFactory.getCurrentSession().createCriteria(Post.class)
				.add(Restrictions.eq("postId", postId)).addOrder(Order.desc("createDate"))
				.setFetchMode("postProfile", FetchMode.JOIN)
				.setFetchMode("postConsultants", FetchMode.JOIN)
				.setFetchMode("industry", FetchMode.JOIN)
				.list();
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Post> getPosts()
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Post.class)
				.add(Restrictions.isNull("deleteDate")).addOrder(Order.desc("createDate"))
				.setFetchMode("postProfile", FetchMode.JOIN)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}
	
	public long countPosts()
	{
		long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
				.add(Restrictions.isNull("deleteDate"))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		return count;
	}
	
	@Override
	public List<Post> getPosts(int first, int max)
	{
		Criteria criteria =this.sessionFactory.getCurrentSession().createCriteria(Post.class)
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.desc("createDate"))
				.setFetchMode("postProfile", FetchMode.JOIN)
				.setFirstResult(first).setMaxResults(max)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
		
		return criteria.list();
	}
	
	public List<Post> getActivePostsByClient(String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		criteria.add(Restrictions.isNotNull("published"))
				.add(Restrictions.eq("isActive", true))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNotNull("verifyDate"))
				.add(Restrictions.isNull("closeDate"))
				.setFetchMode("postProfile", FetchMode.JOIN).addOrder(Order.desc("createDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	
	public List<Post> getActivePostsByClient(String clientId, int first, int max,String sortParam,String filterBy)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		
		if(filterBy.equals("isActive")){
			criteria.add(Restrictions.eq("isActive", true)).add(Restrictions.isNotNull("verifyDate")).add(Restrictions.isNull("closeDate"));
		}
		else if(filterBy.equals("isNotActive")){
			criteria.add(Restrictions.eq("isActive", false)).add(Restrictions.isNotNull("verifyDate")).add(Restrictions.isNull("closeDate"));
		}
		else if(filterBy.equals("saved")){
			criteria.add(Restrictions.isNull("published"));
			}else if(filterBy.equals("pending")){
			criteria.add(Restrictions.isNull("verifyDate")).add(Restrictions.isNotNull("published"));
			criteria.add(Restrictions.eq("isActive", true)).add(Restrictions.isNull("closeDate"));
			}
			else
			criteria.add(Restrictions.isNotNull(filterBy)).add(Restrictions.isNotNull("verifyDate"));
		//.add(Restrictions.eq("isActive", true))
		criteria.add(Restrictions.isNull("deleteDate"));
		//	.add(Restrictions.isNull("closeDate"));
		//	.add(Restrictions.isNotNull("verifyDate"));
		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }
		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList)).addOrder(Order.desc("createDate"));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
          		criteria.addOrder(Order.desc(sortParam));
          		else
          		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
//		return this.sessionFactory.getCurrentSession().createCriteria(Post.class)
//				.add(Restrictions.isNull("deleteDate")).add(Restrictions.eq("client.userid", userid))
//				.addOrder(Order.desc("createDate")).setFetchMode("postProfile", FetchMode.JOIN)
//				.setFirstResult(first).setMaxResults(max)
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//				.list();
	}
	
	
	@Override
	public long countActivePostByClient(String clientId,String filterBy)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		//criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		
		if(filterBy.equals("isActive")){
			criteria.add(Restrictions.eq("isActive", true)).add(Restrictions.isNotNull("verifyDate")).add(Restrictions.isNull("closeDate"));
		}
		else if(filterBy.equals("isNotActive")){
			criteria.add(Restrictions.eq("isActive", false)).add(Restrictions.isNotNull("verifyDate")).add(Restrictions.isNull("closeDate"));
		}
		else if(filterBy.equals("saved")){
		criteria.add(Restrictions.isNull("published"));
		}else if(filterBy.equals("pending")){
		criteria.add(Restrictions.isNull("verifyDate")).add(Restrictions.isNotNull("published"));
		criteria.add(Restrictions.eq("isActive", true)).add(Restrictions.isNull("closeDate"));
		}
		else
		criteria.add(Restrictions.isNotNull(filterBy)).add(Restrictions.isNotNull("verifyDate"));
		//.add(Restrictions.eq("isActive", true))
		criteria.add(Restrictions.isNull("deleteDate"));
		//.add(Restrictions.isNotNull("verifyDate"))
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@Override
	public List<Post> getAllPostsByClient(String userid, int first, int max,String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		//	.add(Restrictions.isNotNull("verifyDate"));
		//	criteria.add(Restrictions.eq("client.userid", userid));
		
		Criterion cn1 = Restrictions.eq("client.userid", userid);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", userid);
		criteria.add(Restrictions.or(cn1, cn2));

		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
          		criteria.addOrder(Order.desc(sortParam));
          		else
          		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}
	
	@Override
	public long countAllPostByClient(String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.add(Restrictions.isNull("deleteDate"));
	//	.add(Restrictions.isNotNull("verifyDate"));
//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		
		criteria.setProjection(Projections.rowCount()) ;
		
		return (Long)criteria.uniqueResult();
	}
	
	@Override
	public List<Post> getPublishedPostsByClient(String clientId, int first, int max,String sortParam)
	{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNotNull("published"))
		.add(Restrictions.isNull("deleteDate"))
		.add(Restrictions.isNull("closeDate"));
		//.add(Restrictions.isNotNull("verifyDate"));

//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		
		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            criteria.addOrder(Order.desc("postId"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}
	
	@Override
	public long countPublishedPostByClient(String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
	//	.add(Restrictions.isNotNull("verifyDate"));
		
		criteria.add(Restrictions.isNotNull("published"))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNull("closeDate"))

				.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}

	@Override
	public List<Post> getClosedPostsByClient(String clientId, int first, int max,String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.isNotNull("closeDate"));
		
//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
	//	.add(Restrictions.isNotNull("verifyDate"));
		
		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
          		criteria.addOrder(Order.desc(sortParam));
          		else
          		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}
	
	@Override
	public long countClosedPostByClient(String clientId)
	{
		Criteria criteria =  this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.isNotNull("closeDate"));
		//criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		//.add(Restrictions.isNotNull("verifyDate"));
		
		
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	public List<Post> getSavedPostsByClient(String clientId, int first, int max,String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("published"));
		criteria.add(Restrictions.isNull("deleteDate"));
//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
//		.add(Restrictions.isNotNull("verifyDate"));
		
		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
          		criteria.addOrder(Order.desc(sortParam));
          		else
          		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}
	
	public long countSavedPostByClient(String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.add(Restrictions.isNull("published"));
		criteria.add(Restrictions.isNull("deleteDate"));
//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
	//	.add(Restrictions.isNotNull("verifyDate"));
		
		
		criteria.setProjection(Projections.rowCount());
				
		return (Long)criteria.uniqueResult();
	}
	
	
	
	@Override
	public List<Post> getPostsByIndustryUsingConsultantId(String consultantId, int first, int max,String sortParam)
	{
		List<Integer> indList = new ArrayList<>();
		
		Criteria crt = this.sessionFactory.getCurrentSession().createCriteria(Registration.class);
				 
//		crt.add(Restrictions.eq("userid", consultantId));
		
		Criterion cn1 = Restrictions.eq("userid", consultantId);
		Criterion cn2 = Restrictions.eq("admin.userid", consultantId);
		crt.add(Restrictions.or(cn1, cn2));
			 
		List<Registration> reg = crt.list();
				
		if(reg != null && !reg.isEmpty())
		{
			Set<Industry> inds = reg.get(0).getIndustries();
			Iterator<Industry> it = inds.iterator();
			while(it.hasNext())
			{
				indList.add(it.next().getId());
			}
		}
		
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate")).add(Restrictions.isNotNull("published"));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -15); 
		criteria.createAlias("client", "clientAlias");
		criteria.createAlias("clientAlias.industries", "indAlias");
		criteria.add(Restrictions.in("indAlias.id", indList));
		criteria.add(Restrictions.eq("isActive", true))
		.add(Restrictions.isNull("closeDate"))
		.add(Restrictions.ge("verifyDate", c.getTime()))
		.add(Restrictions.isNotNull("verifyDate"));
		  if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            criteria.setFetchMode("client.industries", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
        		criteria.addOrder(Order.desc(sortParam));
        		else
        		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}

	@Override
	public long countPostsByIndustryUsingConsultantId(String consultantId)
	{
		List<Integer> indList = new ArrayList<>();
		List<Registration> reg =  this.sessionFactory.getCurrentSession().createCriteria(Registration.class).add(Restrictions.eq("userid", consultantId)).list();
		if(reg != null && !reg.isEmpty())
		{
			Set<Industry> inds = reg.get(0).getIndustries();
			Iterator<Industry> it = inds.iterator();
			while(it.hasNext())
			{
				indList.add(it.next().getId());
			}
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -15); 
		
		long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.isNull("deleteDate")).add(Restrictions.isNotNull("published"))
					.createAlias("client", "clientAlias")
					.createAlias("clientAlias.industries", "indAlias")
					.add(Restrictions.in("indAlias.id", indList))
					.add(Restrictions.eq("isActive", true))
					.add(Restrictions.isNull("closeDate"))
					.add(Restrictions.isNotNull("verifyDate"))
					.add(Restrictions.ge("verifyDate", c.getTime()))
					.setProjection(Projections.rowCount())
					.uniqueResult();
		return count;
	}
	
	public List<Post> getPostsByIndustryId(int industryId, int first, int max,String sortParam)
	{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate")).add(Restrictions.isNotNull("published"));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -15); 
		criteria.createAlias("client", "clientAlias");
		criteria.createAlias("clientAlias.industries", "indAlias");
		criteria.add(Restrictions.eq("indAlias.id", industryId));
		criteria.add(Restrictions.eq("isActive", true))
		.add(Restrictions.isNull("closeDate"))
		.add(Restrictions.ge("verifyDate", c.getTime()))
		.add(Restrictions.isNotNull("verifyDate"));
		if(sortParam.indexOf("published")>=0)
		criteria.addOrder(Order.desc(sortParam));
		else
		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            criteria.setFetchMode("client.industries", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
        		criteria.addOrder(Order.desc(sortParam));
        		else
        		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}

	public long countPostsByIndustryId(int industryId)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -15); 
		long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.isNull("deleteDate")).add(Restrictions.isNotNull("published"))
					.createAlias("client", "clientAlias")
					.createAlias("clientAlias.industries", "indAlias")
					.add(Restrictions.eq("indAlias.id", industryId))
					.add(Restrictions.eq("isActive", true))
					.add(Restrictions.isNull("closeDate"))
					.add(Restrictions.isNotNull("verifyDate"))
					.add(Restrictions.ge("verifyDate", c.getTime()))
					.setProjection(Projections.rowCount())
					.uniqueResult();
		return count;
	}

	
	public List<Post> getPostsBySubmittedProfilesByConsultantId(String consultantId, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.eq("isActive", true));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias")
		.add(Restrictions.isNotNull("verifyDate"));
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
        if(sortParam.indexOf("published")>=0)
    		criteria.addOrder(Order.desc(sortParam));
    		else
    		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			if(sortParam.indexOf("published")>=0)
				criteria.addOrder(Order.desc(sortParam));
				else
				criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}

	public long countPostsBySubmittedProfilesByConsultantId(String consultantId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.eq("isActive", true));
		
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
        criteria.addOrder(Order.desc("createDate"))
		.add(Restrictions.isNotNull("verifyDate"));
        
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.in("postId",longList))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setProjection(Projections.rowCount())
					.uniqueResult();
			return count;
			
		}
		return 0;
		
	}

	
	public List<Post> getAllPostsBySubmittedProfilesByConsultantId(String consultantId, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias")
		.add(Restrictions.isNotNull("verifyDate"));
//        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
        
        Criterion cn1 = Restrictions.eq("consAlias.userid", consultantId);
		Criterion cn2 = Restrictions.eq("consAlias.admin.userid", consultantId);
		criteria.add(Restrictions.or(cn1, cn2));
        
        if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
        
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			 if(sortParam.indexOf("published")>=0)
		      		criteria.addOrder(Order.desc(sortParam));
		      		else
		      		criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}

	public long countAllPostsBySubmittedProfilesByConsultantId(String consultantId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
        criteria.addOrder(Order.desc("createDate"))
		.add(Restrictions.isNotNull("verifyDate"));
        
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.in("postId",longList))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setProjection(Projections.rowCount())
					.uniqueResult();
			return count;
			
		}
		return 0;

	}

	public List<Post> getInactivePostsBySubmittedProfilesByConsultantId(String consultantId, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNotNull("deleteDate"));
		or.add(Restrictions.eq("isActive", false));
		criteria.add(or);
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId))
		.add(Restrictions.isNotNull("verifyDate"));
        if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
        
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			 if(sortParam.indexOf("published")>=0)
		      		criteria.addOrder(Order.desc(sortParam));
		      		else
		      		criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}

	public long countInactivePostsBySubmittedProfilesByConsultantId(String consultantId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNotNull("deleteDate"));
		or.add(Restrictions.eq("isActive", false));
		criteria.add(or);
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId))
		.add(Restrictions.isNotNull("verifyDate"))
        
        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.setProjection(Projections.rowCount());
				
		return (Long)criteria.uniqueResult();
	}

	public List<Post> getPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.eq("isActive", true));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2))
		.add(Restrictions.isNotNull("verifyDate"));
		
        if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			 if(sortParam.indexOf("published")>=0)
		      		criteria.addOrder(Order.desc(sortParam));
		      		else
		      		criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}

	public long countPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.eq("isActive", true));
		
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2))
		.add(Restrictions.isNotNull("verifyDate"));
		
        criteria.addOrder(Order.desc("createDate"));
        
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.in("postId",longList))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setProjection(Projections.rowCount())
					.uniqueResult();
			return count;
			
		}
		return 0;
		
	}

	
	public List<Post> getAllPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2))
		.add(Restrictions.isNotNull("verifyDate"));
		
        if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
        
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			 if(sortParam.indexOf("published")>=0)
		      		criteria.addOrder(Order.desc(sortParam));
		      		else
		      		criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}

	public long countAllPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2))
		.add(Restrictions.isNotNull("verifyDate"));
		
        criteria.addOrder(Order.desc("createDate"));
        
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.in("postId",longList))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setProjection(Projections.rowCount())
					.uniqueResult();
			return count;
			
		}
		return 0;

	}

	public List<Post> getInactivePostsBySubmittedProfilesByConsultantId(String consultantId, String clientId, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNotNull("deleteDate"));
		or.add(Restrictions.eq("isActive", false));
		criteria.add(or);
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2))
		.add(Restrictions.isNotNull("verifyDate"));
		
        if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
        
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			 if(sortParam.indexOf("published")>=0)
		      		criteria.addOrder(Order.desc(sortParam));
		      		else
		      		criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}

	public long countInactivePostsBySubmittedProfilesByConsultantId(String consultantId, String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNotNull("deleteDate"));
		or.add(Restrictions.eq("isActive", false));
		criteria.add(or);
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2))
		.add(Restrictions.isNotNull("verifyDate"));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.setProjection(Projections.rowCount());
				
		return (Long)criteria.uniqueResult();
	}

	@Override
	public void deletePost(Post post)
	{
		this.sessionFactory.getCurrentSession().delete(post);
		this.sessionFactory.getCurrentSession().flush();
		
	}


	public List<Post> getAllPostBetweenDates(Date sDate, Date eDate, int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Post.class)
				.add(Restrictions.ge("createDate", new java.sql.Date(sDate.getTime())))
				.add(Restrictions.le("createDate", new java.sql.Date(eDate.getTime())))
				.list();
	}

	public List<Post> getPostsFilteredForConsultant(String consultantId, String clientId, String status, String location, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId))
		.add(Restrictions.isNotNull("verifyDate"));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        if(clientId != null && clientId.trim().length() > 0)
        {
        	Criterion cn1 = Restrictions.eq("client.userid", clientId);
        	criteria.createAlias("client", "clientAlias");
        	Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
        	criteria.add(Restrictions.or(cn1, cn2));
        }
        if(status != null && status.trim().length() > 0 )
        {
        	if(status.equals("active"))
        	{
        		criteria.add(Restrictions.eq("isActive", true)).add(Restrictions.isNull("closeDate"));
        	}
        	else if(status.equals("inactive"))
        	{
        		criteria.add(Restrictions.eq("isActive", false)).add(Restrictions.isNull("closeDate"));
        	}else if(!status.equals("all")){
        		criteria.add(Restrictions.isNotNull(status));
        	}
        }
        if(location != null && location.trim().length() > 0 )
        {
        	criteria.add(Restrictions.like("location",location, MatchMode.ANYWHERE));
        }
        
        if(sortParam.indexOf("published")>=0)
      		criteria.addOrder(Order.desc(sortParam));
      		else
      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.in("postId", longList));
			criteria.setFetchMode("postProfile", FetchMode.JOIN);
			 if(sortParam.indexOf("published")>=0)
		      		criteria.addOrder(Order.desc(sortParam));
		      		else
		      		criteria.addOrder(Order.asc(sortParam));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		}
		else
		{
			return new ArrayList<Post>();
		}
		
		
       
		return criteria.list();
	}
	
	public long countPostsFilteredForConsultant(String consultantId, String clientId, String status, String location)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias");
        criteria.add(Restrictions.eq("consAlias.userid",consultantId))
		.add(Restrictions.isNotNull("verifyDate"));
//        criteria.add(Restrictions.eq("client.userid",clientId));
        if(clientId != null && clientId.trim().length() > 0)
        {
        	Criterion cn1 = Restrictions.eq("client.userid", clientId);
        	criteria.createAlias("client", "clientAlias");
        	Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
        	criteria.add(Restrictions.or(cn1, cn2));
        }
        if(status != null && status.trim().length() > 0 )
        {
        	if(status.equals("active"))
        	{
        		criteria.add(Restrictions.eq("isActive", true));
        	}
        	else if(status.equals("inactive"))
        	{
        		criteria.add(Restrictions.eq("isActive", false));
        	}
        }
        if(location != null && location.trim().length() > 0 )
        {
        	criteria.add(Restrictions.eq("location",location));
        }
        
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			long count = (Long)this.sessionFactory.getCurrentSession().createCriteria(Post.class)
					.add(Restrictions.in("postId",longList))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.setProjection(Projections.rowCount())
					.uniqueResult();
			return count;
			
		}
		return 0;
		
	}
	
	public List<String> getLocationsByConsultant(String consultantId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.property("location")))));
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.createAlias("postConsultants", "pcAlias");
		criteria.createAlias("pcAlias.consultant", "consAlias")
		.add(Restrictions.isNotNull("verifyDate"));
        criteria.add(Restrictions.eq("consAlias.userid",consultantId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return  criteria.list();
		
	}

	@Override
	public List<Post> getAllVerifiedPostsByClient(String userid, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNull("deleteDate")).add(Restrictions.isNull("joinCloseDate"))
		.add(Restrictions.isNotNull("verifyDate"));
//		criteria.add(Restrictions.eq("client.userid", userid));
		
		Criterion cn1 = Restrictions.eq("client.userid", userid);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", userid);
		criteria.add(Restrictions.or(cn1, cn2));

		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
          		criteria.addOrder(Order.desc(sortParam));
          		else
          		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}

	@Override
	public List<Post> getAllVerifiedPostsByClientArchive(String userid, int first, int max, String sortParam)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id()).add(Projections.property("postId")))));
		criteria.add(Restrictions.isNotNull("deleteDate")).add(Restrictions.isNotNull("joinCloseDate"))
		.add(Restrictions.isNotNull("verifyDate"));
//		criteria.add(Restrictions.eq("client.userid", userid));
		
		Criterion cn1 = Restrictions.eq("client.userid", userid);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", userid);
		criteria.add(Restrictions.or(cn1, cn2));

		 if(sortParam.indexOf("published")>=0)
	      		criteria.addOrder(Order.desc(sortParam));
	      		else
	      		criteria.addOrder(Order.asc(sortParam));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		
		List<Object[]> idList = criteria.list();
		//get the id's from the projection
        List<Long> longList = new ArrayList<Long>();
        for (Object[] long1 : idList) {
            Object[] record = long1;
            longList.add((Long) record[0]);
        }

		if (longList.size() > 0)
		{
			//get all the id's corresponding to the projection, 
			//then apply distinct root entity
            criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
            criteria.add(Restrictions.in("postId", longList));
            criteria.setFetchMode("postProfile", FetchMode.JOIN);
            criteria.setFetchMode("postConsultants", FetchMode.JOIN);
            if(sortParam.indexOf("published")>=0)
          		criteria.addOrder(Order.desc(sortParam));
          		else
          		criteria.addOrder(Order.asc(sortParam));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
		else
		{
		//no results, so let's ommit the second query to the DB
	         return new ArrayList<Post>();
        }

		return criteria.list();
		
	}

	@Override
	public long countAllVerifiedPostByClient(String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.add(Restrictions.isNull("deleteDate"))
		.add(Restrictions.isNotNull("verifyDate"));
//		criteria.add(Restrictions.eq("client.userid", clientId));
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
		
		criteria.setProjection(Projections.rowCount()) ;
		
		return (Long)criteria.uniqueResult();
	}

	@Override
	public long countActiveVerifiedPostByClient(String clientId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
//		criteria.add(Restrictions.eq("client.userid", clientId));
				
		Criterion cn1 = Restrictions.eq("client.userid", clientId);
		criteria.createAlias("client", "clientAlias");
		Criterion cn2 = Restrictions.eq("clientAlias.admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));
				
		criteria.add(Restrictions.isNotNull("published"))
				.add(Restrictions.eq("isActive", true))
				.add(Restrictions.isNull("deleteDate"))
				.add(Restrictions.isNull("closeDate"))
				.add(Restrictions.isNotNull("verifyDate"))
				.setProjection(Projections.rowCount());
		
		return (Long)criteria.uniqueResult();
	}
	@Override
//	public List<Post> getAllActivePosts()
//	{
//		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
//		criteria.add(Restrictions.isNull("deleteDate"))
//		.add(Restrictions.isNotNull("published"))
//		.add(Restrictions.eq("isActive", true))
//		.add(Restrictions.isNull("deleteDate"))
//		.add(Restrictions.isNull("closeDate"))
//		.add(Restrictions.isNotNull("verifyDate"));
//
//		return criteria.list();
//	}

	public List<Post> getAllActivePosts() {
		List<Post> posts = null;
		try {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Post.class);
			criteria.add(Restrictions.isNull("deleteDate"))
					.add(Restrictions.isNotNull("published"))
					.add(Restrictions.eq("isActive", true))
					.add(Restrictions.isNull("deleteDate"))
					.add(Restrictions.isNull("closeDate"))
					.add(Restrictions.isNotNull("verifyDate"));

			posts = criteria.list();  // Execute the query and get the result
		} catch (HibernateException e) {
			// Log the exception
			logger.error("Error occurred while fetching active posts", e);
			// Optionally, rethrow a custom exception or return an empty list
			throw new RuntimeException("Error fetching active posts", e);
		}
		return posts;
	}
}
