	<!DOCTYPE html>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.domain.CandidateProfile"%>
<%@page import="java.util.Set"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
<style type="text/css">
	.error{color: red;}
</style>

<script type="text/javascript">
var buttonst=$('#db_post_status').val();
	if(buttonst=="isActive"){
		//$('#inact_post').css('display','none');
		$('#act_post').css('display','none');
		}else if (buttonst=="isNotActive"){
			$('#inact_post').css('display','none');
			//$('#inact_post').css('display','block');
			}else if (buttonst=="all"){
			//	$('#act_post').css('display','block');
				//$('#inact_post').css('display','block');
				}else{
				$('#act_post').css('display','none');
				$('#inact_post').css('display','none');
				}


</script>
</head>
<body class="loading">
	<%
        Long totalCount = (Long) request.getAttribute("totalCount");       	List<Post> postList = (List)request.getAttribute("postList");
		int pn = (Integer) request.getAttribute("pn");
		int rpp = (Integer) request.getAttribute("rpp");
		int tp = 0;
		String cc = "";
		if(totalCount == 0)
		{
			cc = "0 - 0";
		}
		else if(totalCount % rpp == 0)
		{
			tp = (int) (totalCount / rpp);
			cc = ((pn-1)*rpp)+1 + " - " + ((pn)*rpp);
		}
		else
		{
			tp = (int)(totalCount/rpp)+1;
			if((pn)*rpp < totalCount)
			{
				cc = ((pn-1)*rpp)+1 + " - " + ((pn)*rpp);
			}
			else
			{
				cc = ((pn-1)*rpp)+1 + " - " + totalCount;
			}
		}
		
	%>
      
	    <div class="positions_info ">
	      <div class="filter">
	      	 <div class="col-md-4">&nbsp;
	      	 	<sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
		      	 	<button id="act_post" class="profile_status_buttonGen"><%=GeneralConfig.Label_Activate %></button> 
 		      	 	<button id="inact_post" class="profile_status_buttonGen" ><%=GeneralConfig.Label_DeActivate %></button> 
<!-- 		      	 	<button id="del_post">Delete</button> -->
<!-- 		      	 	<button id="close_post" >Close</button> -->
	      	 	</sec:authorize>
	      	 </div>
	         <div class="col-md-4 pagi_summary"><span>Showing <%= cc %> of <%= totalCount %></span></div>
	         <div class="col-md-4">
                <ul class="page_nav unselectable">
                	<%
		          		if(pn > 1)
		          		{
		          			%>
					            <li><a onclick="loadclientdashboardposts('1')">First</a></li>
					            <li><a onclick="loadclientdashboardposts('<%= pn-1 %>')" ><i class="fa fa-fw fa-angle-double-left"></i></a></li>
		          			<%
		          		}
		          		else
		          		{
		          			%>
					            <li class="disabled"><a>First</a></li>
					            <li class="disabled"><a><i class="fa fa-fw fa-angle-double-left"></i></a></li>
			      			<%
		          		}
		          		%>
				            <li class="active current_page" id="<%= pn %>"><a><%= pn %></a></li>
		          		<%
			          	if(pn < tp)
			      		{
			      			%>
			      				<li ><a onclick="loadclientdashboardposts('<%= pn+1 %>')"><i class="fa fa-fw fa-angle-double-right"></i></a></li>
					            <li><a onclick="loadclientdashboardposts('<%=tp %>')">Last</a></li>
			      			<%
			      		}
			      		else
			      		{
			      			%>
			      				<li class="disabled"><a><i class="fa fa-fw fa-angle-double-right"></i></a></li>
					            <li class="disabled"><a>Last</a></li>
			      			<%
			      		}
			      	
		          	%>
                
                </ul>
              </div>
	      </div>
	      <div class="positions_tab">
	        <div >
		        <table class="table no-margin" style="border: 1px solid gray;">
		        	<thead>
		        		<tr>
		       				<sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
		       				<th align="left" width="2%">
		       				<input id="sel_all" name="sel_all" type="checkbox"> 
		       				</th>
		       				</sec:authorize>
		       				<th style="text-align: left;">Status</th>
		       				<%-- <sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
		       				<th>Published By</th>
		       				</sec:authorize> --%>
							<!-- <th align="left"  width="5%">Status</th> -->
		       				<th align="left">Job Id</th>
		       				<th align="left" style="width: 14%;">Role</th>
		       				<th >Openings</th> 
		       				<th align="left" style="max-width: 70px;">Location</th>
		       				<th >Posted Date</th>
		       				<th >No of Partners</th> 
		       				<th style="width:80px;" align="left">Received</th>
		       				<th ><%=GeneralConfig.Shortlist %></th>
		       				<th  width="18%" style="text-align: right;">View JD</th>
		       			</tr>
	       			</thead>
	       			<tbody>
	       				<%
	       					if(postList != null && !postList.isEmpty())
	       					{
	       						int count = (pn-1)*GeneralConfig.rpp + 1 ;
	       						
	       						for(Post post : postList)
	       						{
	       							Set<Integer> cons = new HashSet(); 
	       							Set<Long> shortListed = new HashSet();
	       							Iterator<PostProfile> it = post.getPostProfile().iterator();
	       							int countRead=0;
	       							while(it.hasNext())
	       							{
	       								PostProfile pp = it.next();
	       								if(pp.getProcessStatus() != null&&(pp.getProcessStatus().equals("accepted")/* ||pp.getProcessStatus().equals(GeneralConfig.SendOfferDb) */))
	       								{
	       									shortListed.add(pp.getPpid());
	       								}
	       								if(pp.getViewStatus()==null||(!pp.getViewStatus())){
	       									countRead++;
	       								}
	       							}
	       							boolean verified=false;
	       							if(post.getVerifyDate()!=null){ 	
		       						verified=true;
		       						}else{ 
		       						} 
	       							String actStatus="inactive";
	       							if(post.isActive())
	       							{
	       								actStatus="active";	
	       							}
	       							%>
						       				<tr id="<%= post.getPostId()%>" style="color:<%=(post.getVerifyDate()!=null)?"black":"gray"%>">
											<%-- <td><%= count++ %></td> --%>
											
											<sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
						        				<td>
											<%if(verified&&post.getCloseDate()==null){ 
											
											%>
											
						        				<input class="sel_posts" type="checkbox" name="selector[]" value="<%=post.getPostId() %>,<%=actStatus%>">
						        				<%} else{
						        				%>
						        				 <input disabled="disabled" class="sel_postss" type="checkbox" name="selector[]" value="<%=post.getPostId() %>">
						        				 
						        				<%} %>
						        				</td>
						        			</sec:authorize>
						        			
					       			<%-- 		<td class="status" style="text-align: center;">
					       						<%
							                  		if(post.getPublished() != null&&verified)
							                  		{
							                  			%>
							                  				<img  src="images/check-cloud.png" width="20px"  title="Published on <%= DateFormats.ddMMMMyyyy.format(post.getVerifyDate())%>">
							                  			<%
							                  		}
							                  		else
							                  		{
							                  			%>
							                  			<sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
							                  				<%if(post.getPublished() != null) {%>
							                  				
							                  				<img style="cursor: auto;" src="images/cloud-yellow.png" width="20px" title="Verification Pending">
							                  				<%}else{
							                  					%>
							                  					<img class="st_unpublished"   src="images/cloud-gray.png" width="20px" title="Click to publish">
							                  				<%}%>
							                  			</sec:authorize>
							                  			<sec:authorize access="hasRole('ROLE_EMP_USER')">
							                  				<%if(post.getPublished() != null) {%>
							                  				
							                  				<img style="cursor: auto;" src="images/cloud-yellow.png" width="20px" title="Verification Pending">
							                  				<%}else{ %>
							                  				<img class="st_unpublished"   src="images/cloud-gray.png" width="20px" title="Click to publish">
							                  				<%}%>
							                  			</sec:authorize>
							                  			<%
							                  		}
							                  	%>
				       						</td> --%>
				       						<%-- <sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
		       				<%if(post.getPosterId()!=null){ %>
		       				<td><%=post.getPosterId() %></td>
		       				<%}else {%>
		     				<td><%=post.getClient().getUserid() %></td>
		     				<%} %>
		       				
		       				</sec:authorize> --%>
				       						
				       						
						       				<td class='act_status'>
		       									<%
		       									if(post.getCloseDate()!=null){
					       							out.println("Closed");
					       						}
		       									else if(post.getPublished()==null){
					       							out.println("Saved");
					       						}
		       									else if(post.getVerifyDate()==null){
					       							out.println("Verification Pending");
					       						}else{
					       							if(verified){
		       									%>
<!-- 		       									<select id="sel_act_inact" style="padding: 0;width: 65px;font-size: 11px;border-radius: 0;"> -->
					       						<%
					       							}else{
						       					%>
<!-- 						       					<select disabled="disabled" id="sel_act_inact" style="padding: 0;width: 65px;font-size: 11px;border-radius: 0;"> -->
						       					<%	
					       							}
					       						if(post.isActive())
					       							{
					       								%>
				       										<%-- <option value="Active" selected="selected">Active</option>
				       										<sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
				       											<option value="Inactive">Inactive</option>
				       										</sec:authorize> --%>
				       										<span>Active</span>
					       								<%
					       							}
					       							else
					       							{
					       								%>
					       									<%-- <sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
				       											<option value="Active" >Active</option>
				       										</sec:authorize>
				       										
				       										<option value="Inactive" selected="selected">Inactive</option> --%>
				       										<span>Inactive</span>
					       								<%
					       							}
					       						%>
<!-- 		       									</select> -->
		       									<%} %>
					       					</td>
					       					<td style="font-size: 9px;"><%= post.getJobCode() %></td>
					       					<td>
						       					<%
						       						if(post.getPostProfile() != null && !post.getPostProfile().isEmpty())
						       						{
						       							if(verified&&post.getDeleteDate()==null&&post.getNoOfPosts()>post.getNoOfPostsJoined()){
						       							%>
									       					<a href="clientpostapplicants?pid=<%=post.getPostId()%>">
									       						<%= post.getTitle() %> 
									       					</a>
									       					<%}else{ %>
									       						<%= post.getTitle() %> 
									       					<%} %>
						       							<%	
						       						}
						       						else
						       						{
						       							if(verified&&post.getDeleteDate()==null&&post.getNoOfPosts()>post.getNoOfPostsJoined()){
						       							%>
						       							<a title="No profile submitted!"  href="clientpostapplicants?pid=<%=post.getPostId()%>">
									       						<%= post.getTitle() %> 
									       					</a>
						       							<%}else{ %>
									       						<%= post.getTitle() %> 
						       							<%}
						       						}
						       					%>
					       					</td>
					       					<%if(verified){ %>
					       					<td style="text-align: center;cursor: pointer;" onclick="getClosedCandidates(<%=post.getPostId()%>)">
						       				<a href="javascript:void(0)" ><%= post.getNoOfPosts() %> (<%=post.getNoOfPostsFilled() %> closed)</a>
						       				</td>
					       					<%}else{ %>
					       					<td style="text-align: center;" >
						       				<%= post.getNoOfPosts() %> (<%=post.getNoOfPostsFilled() %> closed)
						       				</td>
					       					<%} %>
						       				<td style="word-wrap: break-word;max-width:100px;"><%= post.getLocation() %></td>
						       				<td style="text-align: center;"><%= DateFormats.ddMMMMyyyy.format(post.getCreateDate()) %></td>
						       				<td style="text-align: center;"><%= post.getPostConsultants().size() %></td>
											<%-- <td style="text-align: center;"><%= post.getNoOfPostsFilled() %></td> --%>
						       				
						       				<td style="text-align: left;"><%= post.getPostProfile().size() %> (<%=countRead %> new)</td>
						       				<td style="text-align: center;">
						       				<%if(post.getCloseDate()!=null) {%>
						       					--
						       					<%}else{ %>
						       					<%= shortListed.size() %>
						       					<%} %>
						       				
						       				</td>
						       				<td  style="text-align: center;">
						       					<sec:authorize access="hasRole('ROLE_EMP_USER')">
						       					<div class="pre_check" style="float:none;padding: 0px;">
								                  	<a href="clientPostDetail?pid=<%= post.getPostId() %>" ><img width="24px" alt="View Post" title="Click to view post" src="images/view-icon.png"></a>
							                  	</div>
						       					</sec:authorize>
						       					<sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
						       					<div class="pre_check" >
								                  	<a href="clientPostDetail?pid=<%= post.getPostId() %>" ><img width="24px" alt="View Post" title="Click to view post" src="images/view-icon.png"></a>
							                  	</div>
									<%
										if(post.getCloseDate()==null&&post.isActive()&&post.getVerifyDate()!=null||post.getPublished()==null) {				
											
											if (post.getPublished() == null) {
									%>
									<a href="clientsavedpost?pid=<%=post.getPostId()%>">
										
										<button  class="profile_status_buttonGen"
											pid="<%=post.getPostId()%>" title="Click to Publish post" >Edit and Publish</button>
								 	</a> 
									
									
										<button class="profile_status_buttonGen"
											onclick="clientDeletePost('<%=post.getPostId()%>','other')" title="Click to delete this post">Delete</button>
									<%
										} else {
									%>
									<a 
										href="clienteditpost?pid=<%=post.getPostId()%>">
										<button class="profile_status_buttonGen"
											pid="<%=post.getPostId()%>" title="Click to edit this post">Edit</button>
									</a>
									
										<button class="profile_status_buttonGen"
											onclick="clientDeletePost('<%=post.getPostId()%>','active')" title="Click to delete this post">Delete</button>
									<%}
										} else {
											String tooltip="";
											if(!verified){
												tooltip="Verification Pending";
												%>
												
									<a 
										href="clienteditpost?pid=<%=post.getPostId()%>">
										<button class="profile_status_buttonGen"
											pid="<%=post.getPostId()%>" title="Click to edit this post">Edit</button>
									</a>
										<button class="profile_status_buttonGen"
											onclick="clientDeletePost('<%=post.getPostId()%>','other')" title="Click to delete this post">Delete</button>
												<%
											}else if(post.getCloseDate()!=null){
												tooltip="position closed";	%>
													<button
											class="profile_status_buttonGen" style="background: #ececec;cursor: auto;"
											pid="<%=post.getPostId()%>" title="<%=tooltip%>">Edit</button>
										<button class="profile_status_buttonGen"
											onclick="clientDeletePost('<%=post.getPostId()%>','other')" title="Click to delete this post">Delete</button>
												<%
											}else if(!post.isActive()){
										%>										
										<button
											class="profile_status_buttonGen" style="background: #ececec;cursor: auto;"
											pid="<%=post.getPostId()%>" title="<%=tooltip%>">Edit</button>
												<button class="profile_status_buttonGen"
													onclick="clientDeletePost('<%=post.getPostId()%>','inactive')" title="Click to delete this post">Delete</button>
	<%											tooltip="position inactive";
											}
											
									%>
								
									<%
										}
									%>
								</sec:authorize>
						       				</td>
						        		</tr>
	       							<%
	       						}
	       					}
	       				%>
	       				
			        	</tbody>
			        </table>
	        </div>
	        
	      </div>
	    </div>
      
   

</body>
</html>
