package com.unihyr.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.domain.BillingDetails;

@Service
@Transactional
public class BillingServiceImpl implements BillingService
{

	@Autowired
	com.unihyr.dao.BillingDao BillingDao;

	@Override
	public BillingDetails getBillingDetailsById(int id)
	{
		// TODO Auto-generated method stub
		return this.BillingDao.getBillingDetailsById(id);
	}

	@Override
	public long addBillingDetails(BillingDetails billingDetails)
	{
		// TODO Auto-generated method stub
		return this.BillingDao.addBillingDetails(billingDetails);
	}

	@Override
	public List<BillingDetails> getBillingDetailsByClientList(String userid,String sortParam)
	{
		// TODO Auto-generated method stub
		return this.BillingDao.getBillingDetailsByClientList(userid,sortParam);
	}

	@Override
	public long updateBillingDetails(BillingDetails billingDetails)
	{
		// TODO Auto-generated method stub
		return this.BillingDao.updateBillingDetails(billingDetails);
	}

	@Override
	public List<BillingDetails> getBillingDetailsByConsList(String userid, String sortParam)
	{
		// TODO Auto-generated method stub
				return this.BillingDao.getBillingDetailsByConsList(userid,sortParam);
			}

	@Override
	public BillingDetails getBillingDetailsById(long ppid)
	{
		// TODO Auto-generated method stub
		return this.BillingDao.getBillingDetailsById(ppid);
		
	}

	@Override
	public List<BillingDetails> getAllDetailsUnverified()
	{
		// TODO Auto-generated method stub
		return this.BillingDao.getAllDetailsUnverified();
	}

	@Override
	public  List<BillingDetails> getAllDetails()
	{
		// TODO Auto-generated method stub
		return this.BillingDao.getAllDetails();
	}

	
}
