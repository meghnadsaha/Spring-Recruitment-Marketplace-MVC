package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.QualificationDao;
import com.unihyr.domain.Qualification;

@Transactional 
@Service
public class QualificationServiceImpl implements QualificationService
{
	@Autowired QualificationDao qualificationDao;

	@Override
	public Boolean addQualification(Qualification qualification)
	{
		return qualificationDao.addQualification(qualification);
	}

	@Override
	public Boolean editQualification(Qualification qualification)
	{
		return qualificationDao.editQualification(qualification);
	}

	@Override
	public Boolean deleteQualification(Qualification qualification)
	{
		return qualificationDao.deleteQualification(qualification);
	}

	@Override
	public Qualification getQualificationByQid(long qid)
	{
		return qualificationDao.getQualificationByQid(qid);
	}
	
	@Override
	public List<Qualification> getAllQualification()
	{
		return qualificationDao.getAllQualification();
	}

	@Override
	public List<Qualification> getAllUGQualification()
	{
		return qualificationDao.getAllUGQualification();
	}

	@Override
	public List<Qualification> getAllPGQualification()
	{
		return qualificationDao.getAllPGQualification();
	}
	
	
}
