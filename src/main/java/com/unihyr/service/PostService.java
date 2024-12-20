package com.unihyr.service;

import com.unihyr.domain.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    public long addPost ( Post post );

    public void updatePost ( Post post );

    public Post getPost ( long postId );

    public List<Post> getPosts ();

    public long countPosts ();

    public List<Post> getPosts ( int first , int max );

    public List<Post> getActivePostsByClient ( String userid );

    public List<Post> getActivePostsByClient ( String userid , int first , int max , String sortParam , String filterBy );

    public long countActivePostByClient ( String userid , String filterBy );


    public List<Post> getAllPostsByClient ( String userid , int first , int max , String sortParam );

    public long countAllPostByClient ( String userid );

    public List<Post> getPublishedPostsByClient ( String userid , int first , int max , String sortParam );

    public long countPublishedPostByClient ( String userid );

    public List<Post> getClosedPostsByClient ( String userid , int first , int max , String sortParam );

    public long countClosedPostByClient ( String userid );

    public List<Post> getSavedPostsByClient ( String clientId , int first , int max , String sortParam );

    public long countSavedPostByClient ( String clientId );

    public List<Post> getPostsByIndustryUsingConsultantId ( String consultantId , int first , int max , String sortParam );

    public long countPostsByIndustryUsingConsultantId ( String name );

    public List<Post> getPostsByIndustryId ( int industryId , int first , int max , String sortParam );

    public long countPostsByIndustryId ( int industryId );

    public List<Post> getPostsBySubmittedProfilesByConsultantId ( String consultantId , int first , int max , String sortParam );

    public long countPostsBySubmittedProfilesByConsultantId ( String consultantId );

    public List<Post> getAllPostsBySubmittedProfilesByConsultantId ( String consultantId , int first , int max , String sortParam );

    public long countAllPostsBySubmittedProfilesByConsultantId ( String consultantId );

    public List<Post> getInactivePostsBySubmittedProfilesByConsultantId ( String consultantId , int first , int max , String sortParam );

    public long countInactivePostsBySubmittedProfilesByConsultantId ( String consultantId );

    public List<Post> getPostsBySubmittedProfilesByConsultantId ( String consultantId , String clientId , int first , int max , String sortParam );

    public long countPostsBySubmittedProfilesByConsultantId ( String consultantId , String clientId );

    public List<Post> getAllPostsBySubmittedProfilesByConsultantId ( String consultantId , String clientId , int first , int max , String sortParam );

    public long countAllPostsBySubmittedProfilesByConsultantId ( String consultantId , String clientId );

    public List<Post> getInactivePostsBySubmittedProfilesByConsultantId ( String consultantId , String clientId , int first , int max , String sortParam );

    public long countInactivePostsBySubmittedProfilesByConsultantId ( String consultantId , String clientId );

    public void deletePost ( Post post );

    public List<Post> getAllPostBetweenDates ( Date sDate , Date eDate , int first , int max );


    public List<Post> getPostsFilteredForConsultant ( String consultantId , String clientId , String status , String location , int first , int max , String sortParam );

    public long countPostsFilteredForConsultant ( String consultantId , String clientId , String status , String location );

    public List<String> getLocationsByConsultant ( String consultantId );

    public List<Post> getAllVerifiedPostsByClient ( String loggedinUser , int i , int j , String string );

    public List<Post> getAllVerifiedPostsByClientArchive ( String loggedinUser , int i , int j , String string );

    public long countAllVerifiedPostByClient ( String userid );

    public long countActiveVerifiedPostByClient ( String userid );

    List<Post> getAllActivePosts ();


}
