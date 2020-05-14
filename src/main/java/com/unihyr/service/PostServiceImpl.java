package com.unihyr.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.PostDao;
import com.unihyr.domain.Post;

@Service
@Transactional
public class PostServiceImpl implements PostService 
{
	@Autowired private PostDao postDao; 
	
	@Override
	public long addPost(Post post) 
	{
		return this.postDao.addPost(post);
	}

	@Override
	public void updatePost(Post post) 
	{
		this.postDao.updatePost(post);
	}

	@Override
	public Post getPost(long postId) 
	{
		return this.postDao.getPost(postId);
	}

	@Override
	public List<Post> getPosts() 
	{
		return this.postDao.getPosts();
	}

	@Override
	public long countPosts()
	{
		return this.postDao.countPosts();
	}
	@Override
	public List<Post> getPosts(int first, int max) 
	{
		return this.postDao.getPosts(first, max);
	}
	@Override
	public List<Post> getActivePostsByClient(String userid)
	{
		return this.postDao.getActivePostsByClient(userid);
	}
	
	@Override
	public List<Post> getActivePostsByClient(String userid, int first, int max,String sortParam,String filterBy)
	{
		return this.postDao.getActivePostsByClient(userid, first, max,sortParam,filterBy);
	}
	
	@Override
	public long countActivePostByClient(String userid,String filterBy)
	{
		return this.postDao.countActivePostByClient(userid,filterBy);
	}
	
	@Override
	public List<Post> getAllPostsByClient(String userid, int first, int max,String sortParam)
	{
		return this.postDao.getAllPostsByClient(userid, first, max,sortParam);
	}
	
	@Override
	public long countAllPostByClient(String userid)
	{
		return this.postDao.countAllPostByClient(userid);
	}
	
	@Override
	public List<Post> getPublishedPostsByClient(String userid, int first, int max,String sortParam)
	{
		return this.postDao.getPublishedPostsByClient(userid, first, max,sortParam);
	}
	
	@Override
	public long countPublishedPostByClient(String userid)
	{
		return this.postDao.countPublishedPostByClient(userid);
	}

	
	@Override
	public List<Post> getClosedPostsByClient(String userid, int first, int max,String sortParam)
	{
		return this.postDao.getClosedPostsByClient(userid, first, max,sortParam);
	}
	
	@Override
	public long countClosedPostByClient(String userid)
	{
		return this.postDao.countClosedPostByClient(userid);
	}
	
	public List<Post> getSavedPostsByClient(String clientId, int first, int max, String sortParam)
	{
		return this.postDao.getSavedPostsByClient(clientId, first, max, sortParam);
	}
	
	public long countSavedPostByClient(String clientId)
	{
		return this.postDao.countSavedPostByClient(clientId);
	}
	
	
	@Override
	public List<Post> getPostsByIndustryUsingConsultantId(String consultantId, int first, int max,String sortParam)
	{
		
		return this.postDao.getPostsByIndustryUsingConsultantId(consultantId, first, max,sortParam);
	}

	@Override
	public long countPostsByIndustryUsingConsultantId(String name)
	{
		
		return this.postDao.countPostsByIndustryUsingConsultantId(name);
	}
	
	@Override
	public List<Post> getPostsByIndustryId(int industryId, int first, int max,String sortParam)
	{
		return this.postDao.getPostsByIndustryId(industryId, first, max,sortParam);
	}

	@Override
	public long countPostsByIndustryId(int industryId)
	{
		return this.postDao.countPostsByIndustryId(industryId);
	}

	
	@Override
	public List<Post> getPostsBySubmittedProfilesByConsultantId(String consultantId, int first, int max,String sortParam)
	{
		return this.postDao.getPostsBySubmittedProfilesByConsultantId(consultantId, first, max,sortParam);
	}

	@Override
	public long countPostsBySubmittedProfilesByConsultantId(String consultantId)
	{
		return this.postDao.countPostsBySubmittedProfilesByConsultantId(consultantId);
	}

	@Override
	public List<Post> getAllPostsBySubmittedProfilesByConsultantId(String consultantId, int first, int max,String sortParam)
	{
		return this.postDao.getAllPostsBySubmittedProfilesByConsultantId(consultantId, first, max,sortParam);
	}

	@Override
	public long countAllPostsBySubmittedProfilesByConsultantId(String consultantId)
	{
		return this.postDao.countAllPostsBySubmittedProfilesByConsultantId(consultantId);
	}

	@Override
	public List<Post> getInactivePostsBySubmittedProfilesByConsultantId(String consultantId, int first, int max,String sortParam)
	{
		return this.postDao.getInactivePostsBySubmittedProfilesByConsultantId(consultantId, first, max,sortParam);
	}

	@Override
	public long countInactivePostsBySubmittedProfilesByConsultantId(String consultantId)
	{
		return this.postDao.countInactivePostsBySubmittedProfilesByConsultantId(consultantId);
	}

	@Override
	public List<Post> getPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId, int first, int max,String sortParam)
	{
		return this.postDao.getPostsBySubmittedProfilesByConsultantId(consultantId, clientId, first, max,sortParam);
	}

	@Override
	public long countPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId)
	{
		return this.postDao.countPostsBySubmittedProfilesByConsultantId(consultantId, clientId);
	}

	@Override
	public List<Post> getAllPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId, int first, int max,String sortParam)
	{
		return this.postDao.getAllPostsBySubmittedProfilesByConsultantId(consultantId, clientId, first, max,sortParam);
	}

	@Override
	public long countAllPostsBySubmittedProfilesByConsultantId(String consultantId, String clientId)
	{
		return this.postDao.countAllPostsBySubmittedProfilesByConsultantId(consultantId, clientId);
	}

	@Override
	public List<Post> getInactivePostsBySubmittedProfilesByConsultantId(String consultantId, String clientId, int first, int max,String sortParam)
	{
		return this.postDao.getInactivePostsBySubmittedProfilesByConsultantId(consultantId, clientId, first, max,sortParam);
	}

	@Override
	public long countInactivePostsBySubmittedProfilesByConsultantId(String consultantId, String clientId)
	{
		return this.postDao.countInactivePostsBySubmittedProfilesByConsultantId(consultantId, clientId);
	}

	@Override
	public void deletePost(Post post)
	{
			this.postDao.deletePost(post);
	}

	@Override
	public List<Post> getAllPostBetweenDates(Date sDate, Date eDate, int first, int max)
	{
		return this.postDao.getAllPostBetweenDates(sDate, eDate, first, max);
	}
	
	
	
	public List<Post> getPostsFilteredForConsultant(String consultantId, String clientId, String status, String location, int first, int max, String sortParam)
	{
		return this.postDao.getPostsFilteredForConsultant(consultantId, clientId, status, location, first, max, sortParam);
	}
	
	public long countPostsFilteredForConsultant(String consultantId, String clientId, String status, String location)
	{
		return this.postDao.countPostsFilteredForConsultant(consultantId, clientId, status, location);
	}
	
	public List<String> getLocationsByConsultant(String consultantId)
	{
		return this.postDao.getLocationsByConsultant(consultantId);
	}

	@Override
	public List<Post> getAllVerifiedPostsByClient(String loggedinUser, int i, int j, String string)
	{
		// TODO Auto-generated method stub

		return this.postDao.getAllVerifiedPostsByClient(loggedinUser,  i,  j,  string);
	}
	@Override
	public List<Post> getAllVerifiedPostsByClientArchive(String loggedinUser, int i, int j, String string)
	{
		// TODO Auto-generated method stub

		return this.postDao.getAllVerifiedPostsByClientArchive(loggedinUser,  i,  j,  string);
	}

	@Override
	public long countAllVerifiedPostByClient(String userid)
	{
		// TODO Auto-generated method stub
		return this.postDao.countAllVerifiedPostByClient(userid);
	}

	@Override
	public long countActiveVerifiedPostByClient(String userid)
	{
		// TODO Auto-generated method stub
		return this.postDao.countActiveVerifiedPostByClient(userid);
	}
	
	@Override
	public List<Post> getAllActivePosts(){
		return this.postDao.getAllActivePosts();
	}
}
