package com.unihyr.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unihyr.dao.BillingDao;
import com.unihyr.domain.BillingDetails;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private BillingDao billingDao;

	@Override
	@Transactional(readOnly = true)
	public BillingDetails getBillingDetailsById(int id) {
		return billingDao.getBillingDetailsById(id);
	}

	@Override
	@Transactional
	public long addBillingDetails(BillingDetails billing) {
		return billingDao.addBillingDetails(billing);
	}

	@Override
	@Transactional
	public long updateBillingDetails(BillingDetails billing) {
		return billingDao.updateBillingDetails(billing);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BillingDetails> getBillingDetailsByClientList(String userid, String sortParam) {
		return billingDao.getBillingDetailsByClientList(userid, sortParam);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BillingDetails> getBillingDetailsByConsList(String userid, String sortParam) {
		return billingDao.getBillingDetailsByConsList(userid, sortParam);
	}

	@Override
	@Transactional(readOnly = true)
	public BillingDetails getBillingDetailsById(long ppid) {
		return billingDao.getBillingDetailsById(ppid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BillingDetails> getAllDetailsUnverified() {
		return billingDao.getAllDetailsUnverified();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BillingDetails> getAllDetails() {
		return billingDao.getAllDetails();
	}
}
