<!DOCTYPE html>
<%@page import="com.unihyr.domain.Registration"%>
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
function  loadclientdashboardposts(pn)
{
	pleaseWait();
	var db_post_status = $('#db_post_status').val();
	var sortParam=$('#sortParam').val();
	if(typeof sortParam != 'undefined'){}
	else
		sortParam='published';
	$.ajax({
		type : "GET",
		url : "clientDashboardList",
		data : {'pn':pn,'db_post_status':db_post_status,'sortParam':sortParam},
		contentType : "application/json",
		success : function(data) {
			$('.client_db_posts').html(data);
		pleaseDontWait();
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;
}
function  publishPost(pid)
{
	pleaseWait();
	$.ajax({
		type : "GET",
		url : "clientPublishPost",
		data : {'pid':pid},
		contentType : "application/json",
		success : function(data) {
			loadclientdashboardposts('1');
		pleaseDontWait();
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;
}
</script>
<script type="text/javascript">
jQuery(document).ready(function() {
	$(document.body).on('change', '.sel_posts' ,function(){
	  var val = [];
	  if($('.sel_posts:checkbox').length > $('.sel_posts:checkbox:checked').length)
	  {
		  $('#sel_all').prop("checked",false);
	  }
	  else
	  {
	  	$('#sel_all').prop("checked",true);
	  }

    });
	
	$(document.body).on('change', '#sel_all' ,function(){
		if($("input[name='sel_all']").is(':checked'))
		{
			$('.sel_posts:checkbox').prop("checked",true);
		}
		else
		{
			$('.sel_posts:checkbox').prop("checked",false);
		}
	});
  });
	
</script>
<style type="text/css">
.report_sum {
	float: left;
	margin-right: 15px;
}
</style>
</head>
<body class="loading" onload="loadclientdashboardposts('1')">
<div class="mid_wrapper">
  <div class="container">
  	<div id="positions_info">
		  	<div style="padding-bottom: 0" class="rightside_in new_table">
		  		<%--  <sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
				        <div class="bottom-padding" style="padding-bottom: 42px !important;">
				        	<div class="report_sum" >
					        	<div   class="report_sum"  >
					        		<img src="images/active.png"  width="17px">
					        	</div>
					        	<div  class="report_sum"   >
					        	${totalposts} Published
					        	</div>
				        	</div>
				        	<div   class="report_sum"   >
					        	<div   class="report_sum"  >
					        	<img src="images/inactive.png" width="17px">
					        		
					        	</div>
					        	<div   class="report_sum"  >
					        		${totalActive} Active Positions
					        	</div>
				        	</div>
				        	<div   class="report_sum"  >
					        	<div   class="report_sum"  >
					        	<img src="images/profiles.png" width="17px">
					        		
					        	</div>
					        	<div  class="report_sum"   >
					        		${totalInActive } Inactive Positions
					        	</div>
				        	</div>
				        	<div   class="report_sum"  >
					        	<div   class="report_sum"  >
					        		<img src="images/check-cloud.png"  width="17px">
					        	</div>
					        	<div   class="report_sum"  >
									${totalPending} Pending Verification
					        	</div>
				        	</div>
				        	<div   class="report_sum"   >
					        	<div   class="report_sum"  >
					        		<img src="images/check-cloud.png"  width="17px">
					        		
					        	</div>
					        	<div   class="report_sum"  >
									${totalprofiles } Profiles Received
					        	</div>
				        	</div>
				        	<div   class="report_sum"  >
					        	<div   class="report_sum"  >
					        		<img src="images/check-cloud.png"  width="17px">
					        	</div>
					        	<div   class="report_sum"  >
									${totalPending} Pending Verification
					        	</div>
				        	</div>
				        	<div   class="report_sum"   >
					        	<div   class="report_sum"  >
					        		<img src="images/check-cloud.png"  width="17px">
					        		
					        	</div>
					        	<div   class="report_sum"  >
									${totalprofiles } Profiles Received
					        	</div>
				        	</div>
<!-- 				        	<div class="col-md-2 report_sum" > -->
<!-- 					        	<div class="col-md-10"> -->
<!-- 					        		<img src="images/check-cloud.png"  width="20px"> -->
<!-- 					        		No of Partners -->
<!-- 					        	</div> -->
<!-- 					        	<div class="col-md-3"> -->
					        		${totalpartner } 
<!-- 					        	</div> -->
<!-- 				        	</div> -->
				        	
				        </div>
			    </sec:authorize>  --%>
		        <div class="block consulting">
		          <div style="    float: left;">
		            <select id="db_post_status">
		               <option value="all">All(${totalposts})</option>
					   <option value="isActive" selected="selected">Active(${totalActive})</option>
					   <option value="isNotActive" >Inactive(${totalInActive })</option>
					   <option value="pending">Pending Verification(${totalPending})</option>
					   <option value="saved">Saved(${totalSaved})</option>
					   <option value="closeDate">Closed(${totalClosed})</option>
					</select>
		          </div>
		        <!--  
			        <div  class="sort_by"> <span>Sort by</span>
		          	<select id="sortParam" onchange="loadclientdashboardposts('1')">
		            <option value="published">Recent Posts</option>
		            <option value="location">Location(A-Z)</option>
		            <option value="title">Job Post(A-Z)</option>
		          	</select>
		        	</div> 
	        	-->
		        <%
		        String sortParam=(String)request.getAttribute("sortParam");
		        %>
	        	<script type="text/javascript">
			        <%if(sortParam!=null){%>
				        $("#sortParam").val('<%=sortParam%>');
				        $("#sortParam option[value='<%=sortParam%>']").attr('selected','selected');
			        <%}%>
			        <%if(request.getParameter("qUpdate")!=null){%>
			        alertify.success("Quick update has been saved successfully");
			        <%}%>
			        
	        	</script>
		        </div>
		    </div>
	  	<div class="client_db_posts" >
		    
	    </div>
   	</div>
    <div id="post_detail" style="padding: 15px">
    </div>
  </div>
</div>
</body>
</html>
