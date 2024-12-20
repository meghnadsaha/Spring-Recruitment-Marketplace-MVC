package com.unihyr.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Post;
import com.unihyr.domain.Registration;

@Repository
@SuppressWarnings("unchecked")
public class ProfileDaoImpl implements ProfileDao
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long uploadProfile(CandidateProfile profile)
	{
		// TODO Auto-generated method stub
		try
		{
			sessionFactory.getCurrentSession().save(profile);
			this.sessionFactory.getCurrentSession().flush();
			return profile.getProfileId();
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean updateProfile(CandidateProfile profile)
	{
		// TODO Auto-generated method stub
		try
		{
			sessionFactory.getCurrentSession().update(profile);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public CandidateProfile getProfile(long id)
	{
		// TODO Auto-generated method stub
		List<CandidateProfile> list = (List<CandidateProfile>) this.sessionFactory.getCurrentSession()
				.createCriteria(CandidateProfile.class).add(Restrictions.eq("profileId", id))
				.setFetchMode("postionList", FetchMode.JOIN).list();
		if (!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Post> getPostListByConsultantIdInRange(String consultantId, int first, int max)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT distinct post.*  "
				+ "FROM post INNER JOIN postprofile ON "
				+ "post.postId=postprofile.postId  where postprofile.profileId IN(select candidateprofile.profileId from candidateprofile where candidateprofile.consultantId=:consultantId)");
		// query.setInteger("age", 32);
		query.setString("consultantId", consultantId);
		query.addEntity(Post.class);
		System.out.println();

		return (List<Post>) query.list();
	}

	@Override
	public List<Registration> getDistinctClientListByConsultantId(String consultantId)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT distinct registration.*  "
				+ "FROM registration INNER JOIN post ON " + "post.clientId=registration.userid  where post.postId "
				+ "IN(select postprofile.postId from candidateprofile "
				+ " INNER JOIN postprofile ON candidateprofile.profileId=postprofile.profileId where candidateProfile.consultantId=:consultantId )");
		// query.setInteger("age", 32);
		query.setString("consultantId", consultantId);
		query.addEntity(Registration.class);
		System.out.println();

		return (List<Registration>) query.list();
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdInRange(String consultantId, int first, int max)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT distinct candidateprofile.* "
				+ "FROM candidateprofile  where candidateprofile.consultantId=:consultantId");
		// query.setInteger("age", 32);
		query.setString("consultantId", consultantId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		System.out.println();

		return (List<CandidateProfile>) query.list();
	}

	@Override
	public List<Post> getPostListByConsultantIdAndClientInRange(String consultantId, String clientId, int first,
			int max)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT DISTINCT post . * " + "FROM post "
						+ "INNER JOIN postprofile ON post.postId = postprofile.postId "
						+ "WHERE postprofile.profileId " + "IN ( " + "SELECT candidateprofile.profileId "
						+ "FROM candidateprofile " + "WHERE candidateprofile.consultantId = :consultantId" + ") "
						+ "AND post.clientId = :clientId");
		// query.setInteger("age", 32);
		query.setString("consultantId", consultantId);
		query.setString("clientId", clientId);
		query.addEntity(Post.class);
		System.out.println();

		return (List<Post>) query.list();
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdAndClientInRange(String consultantId, String clientId,
			int first, int max)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT DISTINCT candidateprofile . * "
						+ "FROM candidateprofile INNER JOIN postprofile "
						+ "ON candidateprofile.profileId = postprofile.profileId " + "WHERE postprofile.postId IN "
						+ "( SELECT post.postId FROM post " + "WHERE post.clientId = :clientId) "
						+ "AND candidateprofile.consultantId = :consultantId");
		// query.setInteger("age", 32);
		query.setString("clientId", clientId);
		query.setString("consultantId", consultantId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		System.out.println();
		return (List<CandidateProfile>) query.list();
	}

	@Override
	public List<CandidateProfile> getProfileList(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(CandidateProfile.class)
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdAndPostIdInRange(String consultantId, String postId,
			int i, int j)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT DISTINCT candidateprofile . * "
						+ "FROM candidateprofile INNER JOIN postprofile "
						+ "ON candidateprofile.profileId = postprofile.profileId " + "WHERE postprofile.postId IN "
						+ "( SELECT post.postId FROM post " + "WHERE post.postId = :postId) "
						+ "AND candidateprofile.consultantId = :consultantId");
		// query.setInteger("age", 32);
		query.setString("post_id", postId);
		query.setString("consultantId", consultantId);
		query.addEntity(CandidateProfile.class).setFirstResult(i).setMaxResults(j);
		System.out.println();
		return (List<CandidateProfile>) query.list();
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdAndClientAndPostIdInRange(String consultantId,
			String clientId, String postId, int i, int j)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT candidateprofile . * "
				+ "FROM candidateprofile INNER JOIN postprofile "
				+ "ON candidateprofile.profileId = postprofile.profileId " + "WHERE postprofile.postId IN "
				+ "( SELECT post.postId FROM post " + "WHERE post.clientId = :clientId and post.postId=:postId) "
				+ "AND candidateprofile.consultantId = :consultantId");
		// query.setInteger("age", 32);
		query.setString("clientId", clientId);
		query.setString("post_id", postId);
		query.setString("consultantId", consultantId);
		query.addEntity(CandidateProfile.class).setFirstResult(i).setMaxResults(j);
		System.out.println();
		return (List<CandidateProfile>) query.list();
	}

	@Override
	public List<CandidateProfile> getProfilesByPost(String clientId)
	{
		String hql = "select pr.* from candidateProfile pr LEFT JOIN postprofile pp ON pr.profileId = pp.profileId AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setParameter("clientId", clientId);
		query.addEntity(CandidateProfile.class);
		List<CandidateProfile> list = (List<CandidateProfile>) query.list();
		return list;
	}

	@Override
	public List<CandidateProfile> getProfilesByPost(String clientId, int first, int max)
	{
		String hql = "select pr.* from candidateProfile pr LEFT JOIN postprofile pp ON pr.profileId = pp.profileId AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setParameter("clientId", clientId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		List<CandidateProfile> list = (List<CandidateProfile>) query.list();
		return list;
	}

	@Override
	public long countProfilesByPost(String clientId)
	{
		String sql = "select count(*) from candidateProfile pr LEFT JOIN postprofile pp ON pr.profileId = pp.profileId AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("clientId", clientId);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public List<CandidateProfile> getProfilesByPost(long postId, int first, int max)
	{
		String hql = "select pr.* from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pp.postId = :postId";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setParameter("post_id", postId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		List<CandidateProfile> list = (List<CandidateProfile>) query.list();
		return list;
	}

	@Override
	public long countProfilesByPost(long postId)
	{
		String sql = "select count(*) from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pp.postId = :postId";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("post_id", postId);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public List<CandidateProfile> getProfilesByPostAndConsultant(String clientId, String consultid, long postId,
			int first, int max)
	{
		String hql = "select pr.* from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.consultantId=:consultid AND pp.postId = :postId AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setParameter("post_id", postId);
		query.setParameter("consultid", consultid);
		query.setParameter("clientId", clientId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		List<CandidateProfile> list = (List<CandidateProfile>) query.list();
		return list;
	}

	@Override
	public long countProfilesByPostAndConsultant(String clientId, String consultid, long postId)
	{
		String sql = "select count(*) from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.consultantId=:consultid AND pp.postId = :postId AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("post_id", postId);
		query.setParameter("consultid", consultid);
		query.setParameter("clientId", clientId);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public List<CandidateProfile> getProfilesByConsultant(String clientId, String consultid, int first, int max)
	{
		String hql = "select pr.* from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.consultantId=:consultid AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setParameter("consultid", consultid);
		query.setParameter("clientId", clientId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		List<CandidateProfile> list = (List<CandidateProfile>) query.list();
		return list;
	}

	@Override
	public long countProfilesByConsultant(String clientId, String consultid)
	{
		String sql = "select count(*) from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.consultantId=:consultid AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("consultid", consultid);
		query.setParameter("clientId", clientId);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	
	@Override
	public List<CandidateProfile> getAllActiveProfilesByPost(String clientId, int first, int max)
	{
		String sql = "select DISTINCT pr.* from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.deleteDate IS NULL AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);          
		query.setParameter("clientId",clientId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		List<CandidateProfile> list = (List<CandidateProfile>)query.list();
		return list;
	}
	
	@Override
	public long countAllActiveProfilesByPost(String clientId)
	{
		String sql = "select count(DISTINCT pr.profileId) from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.deleteDate IS NULL AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);          
		query.setParameter("clientId",clientId);
		BigInteger count = (BigInteger)query.uniqueResult();
		return count.longValue();
	}
	
	@Override
	public List<CandidateProfile> getAllInactiveProfilesByPost(String clientId, int first, int max)
	{
		String sql = "select DISTINCT pr.* from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.deleteDate IS NOT NULL AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);          
		query.setParameter("clientId",clientId);
		query.addEntity(CandidateProfile.class).setFirstResult(first).setMaxResults(max);
		List<CandidateProfile> list = (List<CandidateProfile>)query.list();
		return list;
	}
	
	@Override
	public long countAllInactiveProfilesByPost(String clientId)
	{
		String sql = "select count(DISTINCT pr.profileId) from candidateProfile pr INNER JOIN postprofile pp ON pr.profileId = pp.profileId AND pr.deleteDate IS NOT NULL AND pp.postId in(SELECT po.postId FROM post po LEFT JOIN registration reg ON reg.userid = po.clientId AND reg.userid LIKE :clientId)";
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);          
		query.setParameter("clientId",clientId);
		BigInteger count = (BigInteger)query.uniqueResult();
		return count.longValue();
	}
	
	@Override
	public long countProfileListByConsultantIdInRange(String consultantId)
	{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CandidateProfile.class);
		criteria.add(Restrictions.isNull("deleteDate"));
		criteria.add(Restrictions.isNotNull("published"));
		criteria.add(Restrictions.eq("registration.userid",consultantId))
        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.setProjection(Projections.rowCount());
		
		return (Long)criteria.uniqueResult();
	}
	
	public long countAllProfileListByConsultantIdInRange(String consultantId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CandidateProfile.class);
		criteria.add(Restrictions.eq("registration.userid",consultantId))
        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.setProjection(Projections.rowCount());
		
		return (Long)criteria.uniqueResult();
	}

	@Override
	public long countProfileListByConsultantIdAndClientAndPostIdInRange(String consultantId, String clientId,
			String postId)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT count(*) " + "FROM candidateprofile INNER JOIN postprofile "
						+ "ON candidateprofile.profileId = postprofile.profileId " + "WHERE postprofile.postId IN "
						+ "( SELECT post.postId FROM post "
						+ "WHERE post.clientId = :clientId and post.postId=:postId) "
						+ "AND candidateprofile.consultantId = :consultantId");
		// query.setInteger("age", 32);
		query.setString("clientId", clientId);
		query.setString("post_id", postId);
		query.setString("consultantId", consultantId);
		System.out.println();
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public long countProfileListByConsultantIdAndClientInRange(String consultantId, String clientId)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT count(*) " + "FROM candidateprofile INNER JOIN postprofile "
						+ "ON candidateprofile.profileId = postprofile.profileId " + "WHERE postprofile.postId IN "
						+ "( SELECT post.postId FROM post " + "WHERE post.clientId = :clientId) "
						+ "AND candidateprofile.consultantId = :consultantId");
		// query.setInteger("age", 32);
		query.setString("clientId", clientId);
		query.setString("consultantId", consultantId);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public long countProfileListByConsultantIdAndPostIdInRange(String consultantId, String postId)
	{
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT count(*) " + "FROM candidateprofile INNER JOIN postprofile "
						+ "ON candidateprofile.profileId = postprofile.profileId " + "WHERE postprofile.postId IN "
						+ "( SELECT post.postId FROM post " + "WHERE post.postId = :postId) "
						+ "AND candidateprofile.consultantId = :consultantId");
		// query.setInteger("age", 32);
		query.setString("post_id", postId);
		query.setString("consultantId", consultantId);
		System.out.println();
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	
	public long countProfileList()
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CandidateProfile.class);
		criteria.add(Restrictions.isNull("deleteDate"))
        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.setProjection(Projections.rowCount());
		
		return (Long)criteria.uniqueResult();
	}
}
