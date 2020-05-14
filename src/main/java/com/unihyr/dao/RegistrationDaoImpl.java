package com.unihyr.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.constraints.Roles;
import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;
import com.unihyr.domain.Registration;

@Repository
@SuppressWarnings("unchecked")
public class RegistrationDaoImpl implements RegistrationDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Registration getRegistationByUserId(String userid)
	{
		List<Registration> logList = this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.add(Restrictions.eq("userid", userid)).setFetchMode("industries", FetchMode.JOIN)
				.setFetchMode("log", FetchMode.JOIN).setFetchMode("log.roles", FetchMode.JOIN)
				.setFetchMode("subuser", FetchMode.JOIN).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if (!logList.isEmpty())
		{
			return logList.get(0);
		}
		return null;
	}

	@Override
	public int countUsers()
	{
		Long c = (Long) this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		return c.intValue();
	}

	@Override
	public List<Registration> getRegistrations(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Registration.class).addOrder(Order.desc("lid"))
				.setFirstResult(first).setMaxResults(max).setFetchMode("log", FetchMode.JOIN)

				.list();
	}

	@Override
	public Registration getRegistrationsByName(String userName)
	{
		Criteria cr=this.sessionFactory.getCurrentSession().createCriteria(Registration.class);
		Criterion r1=Restrictions.eq("consultName", userName);
		Criterion r2=Restrictions.eq("organizationName", userName);
			cr.add(Restrictions.or(r1,r2));
			List<Registration> logList=cr.list();
			if (!logList.isEmpty())
			{
				return logList.get(0);
			}
			return null;
	}

	@Override
	public void update(Registration registration)
	{
		this.sessionFactory.getCurrentSession().update(registration);
		this.sessionFactory.getCurrentSession().flush();

}

	@Override
	public List<Registration> getConsultantsByClient(String clientId)
	{
		String hql = "select DISTINCT reg.* from registration reg INNER JOIN candidateprofile cp ON reg.userid = cp.consultantId AND cp.profileId in(SELECT pp.profileId FROM postprofile pp LEFT JOIN post po ON pp.postId = po.postId AND po.clientId LIKE :clientId)";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setParameter("clientId", clientId);
		query.addEntity(Registration.class);
		List<Registration> list = (List<Registration>) query.list();
		return list;
	}

	@Override
	public List<Registration> getConsultantsByPost(long postId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.createAlias("postConsultants", "pcAlias").createAlias("pcAlias.post", "postAlias")
				.add(Restrictions.eq("postAlias.postId", postId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)

				.setFetchMode("postConsultants", FetchMode.JOIN);
		return criteria.list();

	}

	@Override
	public List<Registration> getClientsByIndustyForConsultant(String consultantId)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PostConsultant.class)
				.createAlias("post", "postAlias").createAlias("postAlias.client", "clientAlias")
				.createAlias("consultant", "conAlias")
				.setProjection(Projections.distinct((Projections.projectionList().add(Projections.id())
						.add(Projections.property("clientAlias.lid")))))
				.add(Restrictions.isNull("clientAlias.admin")).add(Restrictions.eq("conAlias.userid", consultantId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Integer[]> idList = criteria.list();
		if (idList.size() > 0)
		{

			criteria = this.sessionFactory.getCurrentSession().createCriteria(Registration.class);
			criteria.add(Restrictions.in("lid", idList));
		} else
		{
			// no results, so let's ommit the second query to the DB
			return new ArrayList<Registration>();
		}

		return criteria.list();

	}

	@Override
	public long countConsultantList()
	{
		String sql = "select count(*) from registration reg INNER JOIN userrole ur on reg.userid= ur.userid where ur.userrole =:role or ur.userrole =:role1";

		BigInteger bi = (BigInteger) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setString("role1", Roles.ROLE_CON_USER.toString()).setString("role", Roles.ROLE_CON_MANAGER.toString())
				.uniqueResult();

		return bi.longValue();
	}

	@Override
	public long countClientsList()
	{

		String sql = "select count(*) from registration reg INNER JOIN userrole ur on reg.userid= ur.userid where ur.userrole =:role or ur.userrole =:role1";

		BigInteger bi = (BigInteger) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setString("role1", Roles.ROLE_EMP_USER.toString()).setString("role", Roles.ROLE_EMP_MANAGER.toString())
				.uniqueResult();

		return bi.longValue();

	}

	public List<Registration> getClientList(int first, int max)
	{
		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid where ur.userrole =:role";

		return (List<Registration>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class).setString("role", Roles.ROLE_EMP_MANAGER.toString()).list();

	}

	public List<Registration> getConsultantList(int first, int max)
	{
		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid where ur.userrole =:role";

		return (List<Registration>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class).setString("role", Roles.ROLE_CON_MANAGER.toString()).list();

	}
	public List<Registration> getConsultantListJoined(int first, int max)
	{
		String sql = "select reg.lid from registration reg INNER JOIN userrole ur on reg.userid= ur.userid where ur.userrole =:role";

		List<Integer> lids = (List<Integer>)  this.sessionFactory.getCurrentSession().createSQLQuery(sql)
			.setString("role", Roles.ROLE_CON_MANAGER.toString()).list();
	return this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
			.add(Restrictions.in("lid", lids)).addOrder(Order.desc("lid")).setFetchMode("userindustry", FetchMode.JOIN)
			.list();
	}
	



	public List<Registration> getCoUsersByUserid(String userid)
	{
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.createAlias("log", "info")
				.add(Restrictions.eq("admin.userid", userid))
				
				.add(Restrictions.eq("info.isactive", "true"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public List<Registration> getConsultantsByClientIndustry(String clientId)
	{
		List<Integer> indList = new ArrayList<>();

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Registration.class);

		// crt.add(Restrictions.eq("userid", consultantId));

		Criterion cn1 = Restrictions.eq("userid", clientId);
		Criterion cn2 = Restrictions.eq("admin.userid", clientId);
		criteria.add(Restrictions.or(cn1, cn2));

		List<Registration> reg = criteria.list();

		if (reg != null && !reg.isEmpty())
		{
			Set<Industry> inds = reg.get(0).getIndustries();
			Iterator<Industry> it = inds.iterator();
			while (it.hasNext())
			{
				indList.add(it.next().getId());
			}
		}

		String sql = "select reg.* from registration reg INNER JOIN userrole ur on reg.userid= ur.userid INNER JOIN user_industry ui on reg.lid = ui.lid where ur.userrole =:role and ui.id IN :indList";
		return (List<Registration>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Registration.class).setString("role", Roles.ROLE_CON_MANAGER.toString())
				.setParameterList("indList", indList).list();

	}

	public List<Registration> getClientAndConsultantAdminList(int first, int max)
	{
		String sql = "select reg.lid from registration reg INNER JOIN userrole ur on reg.userid= ur.userid where ur.userrole =:clientrole or ur.userrole =:consrole";
		List<Integer> lids = (List<Integer>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setString("clientrole", Roles.ROLE_EMP_MANAGER.toString())
				.setString("consrole", Roles.ROLE_CON_MANAGER.toString()).setFirstResult(first).setMaxResults(max)
				.list();

		return this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.add(Restrictions.in("lid", lids)).addOrder(Order.desc("lid")).setFetchMode("log", FetchMode.JOIN)
				.list();

		// return
		// (List<Registration>)this.sessionFactory.getCurrentSession().createSQLQuery(sql)
		// .addEntity(Registration.class)
		// .setFetchMode("log", FetchMode.JOIN)
		// .list();
	}

	@Override
	public void insertUserIndustryMap(int industryId, int registrationId)
	{
		String sql = "INSERT INTO `unihyr`.`user_industry` ("+
					"`lid` ,`id`) VALUES (:industryId, :registrationId)";
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger("industryId", industryId)
				.setInteger("registrationId", registrationId).executeUpdate();
		
	}

	@Override
	public void insertUserIndustryMap(String userid)
	{
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void insertUserIndustryMap(String userid)
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("update Registration set isactive=:age where name=:name");
		query.setInteger("age", 32);
		query.setString("name", "Lokesh Gupta");
		int modifications=query.executeUpdate();
		
	}*/

}
