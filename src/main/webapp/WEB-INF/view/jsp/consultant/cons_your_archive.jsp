<%@page import="com.unihyr.domain.SocialSharing"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.unihyr.domain.Inbox"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="com.unihyr.domain.PostConsultant"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="com.unihyr.domain.CandidateProfile"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String code=(String)request.getParameter("code");
String state=(String)request.getParameter("state");



%>
<script type="text/javascript" src="//platform.linkedin.com/in.js">
	api_key:78qdnibk4iyxe8
	//authorize: true
	//onLoad: onLinkedInLoad
</script>
<script type="text/javascript">


</script>

<script type="text/javascript">

  function shareContent() {
	  <%
	  SocialSharing socialSharing=(SocialSharing)request.getAttribute("socialSharing");
	  Post post=(Post)request.getAttribute("selectedPost");
	  %>
    var payload ={
    		  "comment": "Check out http://54.191.37.178/unihyr/!",
    		  "content": {
    		    "title": "Unihyr unique hiring solution",
    		    "description": "posttitle",
    		    "submitted-url": "http://54.191.37.178/unihyr/",  
    		    "submitted-image-url": "http://54.191.37.178/unihyr/images/logo.png"
    		  },
    		  "visibility": {
    		    "code": "anyone"
    		  }  
    		};
    <%
   
    if(socialSharing!=null){
    %>
    IN.ENV.auth={
    		api_key:"<%=socialSharing.getApi_key()%>",
    		oauth_expires_in:<%=socialSharing.getOauth_expires()%>,
    		oauth_token:"<%=socialSharing.getOauth_token()%>"
    };
    <%}%>
    IN.API.Raw("people/~/shares?oauth2_access_token="+IN.ENV.auth.oauth_token+"&&format=json")
    .method("POST")
    .body(JSON.stringify(payload))
    .result(onSuccess)
    .error(onError);
  }
</script>



<script type="text/javascript">

// Setup an event listener to make an API call once auth is complete
function onLinkedInLoad() {
    IN.Event.on(IN, "auth", getProfileData);
  //  alert(IN.ENV.auth.oauth_token);
}

// Handle the successful return from the API call
function onSuccess(data) {
	alert("dgs");
	alertify.success("shared successfully");
    console.log(data);
}

// Handle an error response from the API call
function onError(error) {
	alert(error);
	$.ajax({
		type : "GET",
		url : "deleteSocialSharingData",
		
		contentType : "application/json",
		success : function(data) {
		pleaseDontWait();
		
		//location.href="";
		},
		error : function(xhr, ajaxOptions, thrownError) {
		}
	});
    console.log(error);
}

myCallback = function(data) {
	  alert(data.count);
	};
function resultCallback(){
	alert("sdfj;a");
}
// Use the API call wrapper to request the member's basic profile data
function getProfileData() {

<%--  location.href="https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=<%=code%>&redirect_uri=http://localhost:8080/unihyr/cons_your_positions&client_id=78qdnibk4iyxe8&client_secret=F4G91cCRjWbEGVcJ";   --%>



	<%-- var url = "https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=<%=code%>&redirect_uri=http://localhost:8080/unihyr/cons_your_positions&client_id=78qdnibk4iyxe8&client_secret=F4G91cCRjWbEGVcJ&jsonp-callback=myCallback&format=jsonp";
 	$.getJSON(url, { dataType: "jsonp" }, function (data) {
 	    alert(data);
 	}); --%>








/* POST /oauth/v2/accessToken HTTP/1.1
Host: www.linkedin.com
Content-Type: application/x-www-form-urlencoded */
// alert("cfv");
<%--  var payload ={
		  	"data":{"grant_type": "authorization_code",
			"code":"<%=code%>",
			"redirect_uri":"http://localhost:8080/unihyr/cons_your_positions",
			"client_id":"78qdnibk4iyxe8",
			"client_secret":"F4G91cCRjWbEGVcJ",
				'format':'jsonp',
				'jsonp-callback':'resultCallback'
		  	},
		  	"Content-Type": "application/x-www-form-urlencoded"
};
IN.API.Raw().url("/oauth/v2/accessToken")
			.method("POST")
			.body(JSON.stringify(payload))
		    .result(onSuccess)
		    .error(onError); --%>
		    
		    $.ajax({
		    type: "GET",            
            url: "https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=<%=code%>&redirect_uri=http://localhost:8080/unihyr/cons_your_positions&client_id=78qdnibk4iyxe8&client_secret=F4G91cCRjWbEGVcJ&callback=jsonpcallback",
            dataType: 'jsonp',
            accept: 'application/json',
            success: function (response) {
              console.log("ACCESS_TOKEN" + data.access_token);
              resolve(data);
           },
           error: function (request, textStatus, error) {
              console.log(error);
              reject(error);
            }
		    });
		   
<%--            $.ajax({
    type: "GET",
	url : "https://www.linkedin.com/oauth/v2/accessToken",
// 	contentType : "application/x-www-form-urlencoded",
    async:true,
    dataType : 'jsonp',  
    crossDomain:true,
    accept: 'application/json',
	data : {
		'grant_type' : 'authorization_code',
		'code' : '<%=code%>',
		'redirect_uri':'http://localhost:8080/unihyr/cons_your_positions',
		'client_id':'78qdnibk4iyxe8',
		'client_secret':'F4G91cCRjWbEGVcJ',
		'format':'jsonp',
		'jsonp-callback':'resultCallback',
		    'accept': 'application/json',
	},
	contentType : "application/json",
	 success: function (data, status, error) {
	      console.log('success', data);
	    },
	    error: function (data, status, error) {
	      console.log('error', data, status, error);
	    }
});     
 --%>	<%-- $.ajax({
		type : "POST",
	    crossDomain: true,
		contentType : "application/x-www-form-urlencoded",
		url : "https://www.linkedin.com/uas/oauth2/accessToken",
		data : {
			'grant_type' : 'authorization_code',
			'code' : '<%=code%>',
			'redirect_uri':'http://localhost:8080/unihyr/cons_your_positions',
			'client_id':'78qdnibk4iyxe8',
			'client_secret':'F4G91cCRjWbEGVcJ'
		},
        async:true,
        dataType : 'jsonp',   //you may use jsonp for cross origin request
        crossDomain:true,
		success : function(data) {
			alert(data+"fg");
			console.log(data);
			pleaseWait();
			$.ajax({
				type : "GET",
				url : "addSocialSharingData",
				data : {
					'api_key' : IN.ENV.auth.api_key,
					'oauth_expires_in' : IN.ENV.auth.oauth_expires_in,
					'oauth_token':IN.ENV.auth.oauth_token
				},
				contentType : "application/json",
				success : function(data) {
				pleaseDontWait();
			//	location.href="";
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("fg");
				}
			});
			
			
		pleaseDontWait();
	//	location.href="";
		},
		error : function(xhr, ajaxOptions, thrownError) {
		//alert(thrownError+"sdgs");
		}
	}); --%>
	

}

</script>




<script src="js/model.popup.js"></script>
<script type="text/javascript">

function fillProfiles(pageNo) 
{
	var clientId = $('#selectionOfClient').val();
	var sel_con = $('#postsList > li').hasClass("active");
	var postId = "";
	var posttitle = "";
	var sortParam=$('#sortParam').val();
	if(typeof sortParam != 'undefined'){}
	else
		sortParam='all';
	if (sel_con) 
	{
		postId = $('#postsList  .active').attr("id");
		posttitle = $('#postsList  .active > a').text();
	}
	if(postId != "" && clientId != "")
	{
		pleaseWait();
		$.ajax({
			type : "GET",
			url : "profilelistbyconsidclientid",
			data : {
				'clientId' : clientId,
				'postId' : postId,
				'pageNo':pageNo,'sortParam':sortParam
			},
			contentType : "application/json",
			success : function(data) {

				$('.right_side').html(data);
			pleaseDontWait();
			},
			error : function(xhr, ajaxOptions, thrownError) {
			}
		});
	}
	
}

</script>
<script src="js/model.popup.js"></script>
<style type="text/css">
.report_sum{padding: 2px 0;}
</style>

<body onload="fillProfiles('1')">

<%
	List<Registration> clientList = (List<Registration>) request.getAttribute("clientList");
%>

<div class="mid_wrapper">
<%
Registration sel_client = (Registration)request.getAttribute("selClient");
List<PostConsultant> postConsList = (List)request.getAttribute("postConsList");
%>
	<div class="container">
		<script type="text/javascript"></script>
		<%-- <sec:authorize access="hasRole('ROLE_CON_MANAGER')">
			<div style="padding-bottom: 0" class="rightside_in new_table">
		        <div class="bottom-padding" style=" border: 2px solid gray; border-radius: 5px; margin-bottom: 10px; padding: 10px;">
			        <div class="bottom-padding">
			        	
			        	<div class="col-md-4 report_sum">
				        	<div class="col-md-9">
				        		My Active Positions
				        	</div>
				        	<div class="col-md-3">
				        		${totalActive }
				        	</div>
			        	</div>
			        	<div class="col-md-4 report_sum">
				        	<div class="col-md-9">
				        		No of Profile Submitted
				        	</div>
				        	<div class="col-md-3">
				        		${totalprofiles }
				        	</div>
			        	</div>
			        	<div class="col-md-4 report_sum">
				        	<div class="col-md-9">
				        		No of Profile Shortlisted
				        	</div>
				        	<div class="col-md-3">
				        		${totalshortlist }
				        	</div>
			        	</div>
			        	
			        	<div class="col-md-4 report_sum">
				        	<div class="col-md-9">
				        		No of Candidate Joined
				        	</div>
				        	<div class="col-md-3">
				        		${totaljoin }
				        	</div>
			        	</div>
			        	<div class="col-md-4 report_sum">
				        	<div class="col-md-9">
				        		No of Clients
				        	</div>
				        	<div class="col-md-3">
				        		${totalpartner }
				        	</div>
			        	</div>
			        	
			        </div>
		        </div>
	        </div>
	    </sec:authorize> --%>
	<%--     <%if(post!=null) {
    
    	Set<Integer> cons = new HashSet(); 
		Set<Long> shortListed = new HashSet();
		Iterator<PostProfile> it = post.getPostProfile().iterator();
		int countRead=0;
		while(it.hasNext())
		{
			PostProfile pp = it.next();
			if(pp.getAccepted() != null)
			{
				shortListed.add(pp.getPpid());
			}
			if(pp.getViewStatus()==null||(!pp.getViewStatus())){
				countRead++;
			}
		}
    
    %>
    
    
    
    	 <sec:authorize access="hasRole('ROLE_CON_MANAGER')">
    <div style="padding-bottom: 0" class="rightside_in new_table">
        <div class="bottom-padding manageposthead" >
	        <div class="bottom-padding">
	        	
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Post ID
		        	</div>
		        	<div class="col-md-7">
		        		<%=post.getJobCode() %>
		        	</div>
	        	</div>
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Openings 
		        	</div>
		        	<div class="col-md-7">
		        		<%=post.getNoOfPosts() %>
		        	</div>
	        	</div>
	        	
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Profile Received
		        	</div>
		        	<div class="col-md-7">
		        		<%=post.getPostProfile().size() %>
		        	</div>
	        	</div>
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Posted Date
		        	</div>
		        	<div class="col-md-7">
		        		<%=DateFormats.getTimeValue(post.getPublished()) %>
		        	</div>
	        	</div>
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Closed
		        	</div>
		        	<div class="col-md-7">
		        		<%=post.getNoOfPostsFilled() %>
		        	</div>
	        	</div>
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Unread Profiles
		        	</div>
		        	<div class="col-md-7">
		        		<%=countRead %>
		        	</div>
	        	</div>
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Location
		        	</div>
		        	<div class="col-md-7">
		        		<%=post.getLocation() %>
		        	</div>
	        	</div>
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		No of Partners
		        	</div>
		        	<div class="col-md-7">
		        		${totalpartner }
		        	</div>
	        	</div>
	        	
	        	<div class="col-md-4 report_sum" >
		        	<div class="col-md-5">
		        		Shortlisted
		        	</div>
		        	<div class="col-md-7">
		        		<%=shortListed.size() %>
		        	</div>
	        	</div>
	        	
	        </div>
        </div>
    </div>
    </sec:authorize> 
    <%} %>
	 --%>
<%-- 	 <div  class="pre_check" style="float: left;margin-left: -9px;">
									<a href="https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=78qdnibk4iyxe8&redirect_uri=http://localhost:8080/unihyr/cons_your_positions&state=987654321&scope=r_basicprofile"		>
										Login
									</a>	
									<a href="https://www.linkedin.com/oauth/v2/accessToken?grant_type=authorization_code&code=<%=code%>&redirect_uri=http://localhost:8080/unihyr/cons_your_positions&client_id=78qdnibk4iyxe8&client_secret=F4G91cCRjWbEGVcJ&scope=r_basicprofile"		>
										Login
									</a>		
									<%if(socialSharing==null){ %>
										<script type="in/Login"></script> 
									<%}else{ %>
										<button onclick="shareContent()" class="profile_status_buttonGen" >Share</button>
									<%} %>
	 </div>  --%>
		<div class="new_post_info" style="padding: 0 15px;">
			<div class="left_side">
				<div class="left_menu">
					<select id="selectionOfClient" >
						<option value="">Select Client</option>
						<%
							System.out.println(">>>>>>>>>>>>>>>>>>> : " + sel_client);
							for (Registration client : clientList) 
							{
								if(sel_client != null && sel_client.getUserid().equals(client.getUserid()))
								{
									%>
										<option value="<%=client.getUserid()%>" selected="selected"><%=client.getOrganizationName()%></option>
									<%
								}
								else
								{
									%>
										<option value="<%=client.getUserid()%>"><%=client.getOrganizationName()%></option>
									<%
								}
							}
						%>
					</select>
					<h2 style="background: #4e4e4e none repeat scroll 0 0; border-radius: 5px 5px 0 0;color: #fff;margin-top: 5px">Job Positions</h2>
					<div id='cons_leftside_postlist'>
						<%
							if(postConsList != null && !postConsList.isEmpty())
							{
								%>
									<ul id="postsList">
								<%
								long postSelected = post.getPostId();
								for(PostConsultant pc : postConsList)
								{
									post=pc.getPost();
									if(postSelected == pc.getPost().getPostId())
									{
										%>	<li  class="active"     id="<%=pc.getPost().getPostId() %>" >
			<a title="Click to view your positions"  style='color: <%=((post.getCloseDate()!=null)?"red":"black") %>'    >
			<%=post.getTitle()%><br>
			<i><span style="font-size: 8px;">(Job Code: <%=post.getJobCode() %>)</span></i></a>
			</li>
<%-- 											<li id="<%=pc.getPost().getPostId() %>" class="active"  > --%>
<%-- 												<a title="Click to view your positions" href="cons_your_positions?pid=<%= pc.getPost().getPostId()%>" ><%=pc.getPost().getTitle()%></a> --%>
<!-- 											</li> -->
										<%
									}
									else
									{
										%>
											<li id="<%=pc.getPost().getPostId() %>" >
			<a title="Click to view your positions"   style='color: <%=((post.getCloseDate()!=null)?"red":"black") %>'   >
			<%=post.getTitle()%><br>
			<i><span style="font-size: 8px;">(Job Code: <%=post.getJobCode() %>)</span></i></a>
			</li>
										<%
									}
									
								}
								%>
									</ul>
								<%
							}
						
						
						%>
					</div>
				</div>
			</div>
			
<div class="right_side">

<div class="candidate_profiles_def" style="margin-top: 11px;
margin-left: 13px;" >
							<div class="filter">
								<div class="col-md-7 pagi_summary">
									<span>Showing 0 - 0 of 0</span>
								</div>
								<div class="col-md-5">
					                <ul class="page_nav unselectable">
										<li class="disabled"><a>First</a></li>
										<li class="disabled"><a><i class="fa fa-fw fa-angle-double-left"></i></a></li>
										<li class="active current_page"><a>1</a></li>
										<li class="disabled"><a><i class="fa fa-fw fa-angle-double-right"></i></a></li>
										<li class="disabled"><a>Last</a></li>
									</ul>
					              </div>
							</div>
							<table class="table no-margin" style="border: 1px solid gray;">
									<thead>
										<tr>
											<th align="left">Name</th>
											<th align="left">Phone</th>
											<th align="left">Current Role</th>
											<th align="left">Organization</th>
											<th align="left">Curent Salary (In Lacs)</th>
											<th align="left">Notice Period (In Days)</th>
											<th>Submitted</th>
											<th>Status</th>
										</tr>
									</thead>
									
								</table>
							
							
							
<!-- 							--------------------           inner data ---------------------- -->
							
						</div>
			</div>
		</div>
	</div>
</div>
<div id="abcModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content" style="width: 50%">
    <span class="close">x</span>
    <div>
	    <div class="modal-body">
	    </div>
    	<div  class="model-footer">
	    	<button class="btn btn-cancel">Cancel</button>
	    	<button class="btn btn-ok">Ok</button>
    	</div>
    </div>
  </div>

</div>
<div id="rejectModal" class="modal">
	
  <!-- Modal content -->
  <div class="modal-content" style="width: 50%">
    <span class="close">x</span>
    <div>
	    <div class="modal-body">
	    	<p>Please select the reason of rejection</p>
		    <select class="sel_rej_join" style="margin-top: 10px;">
		    	<option value="">Select Reason </option>
		    	<option value="Got better opportunity - salary">Got better opportunity - salary</option>
		    	<option value="Got better opportunity - location">Got better opportunity - location</option>
		    	<option value="Got better opportunity - role">Got better opportunity - role</option>
		    	<option value="Got retained">Got retained</option>
		    	<option value="Unfavorable feedback on new organization/role">Unfavorable feedback on new organization/role</option>
		    	<option value="Personal Issues">Personal Issues</option>
		    </select>
		    <br><span style="color: red">&nbsp; </span>
		    <input type="hidden" id="reject_type" value="">
		    <input type="hidden" id="reject_for" value="">
	    </div>
    	<div  class="model-footer">
	    	<button class="btn btn-cancel">Cancel</button>
	    	<button class="btn btn-ok">Ok</button>
    	</div>
    </div>
  </div>
</div>
<script type="text/javascript">
jQuery(document).ready(function() {
	$(document.body).on('click', '.btn-open' ,function(){
		var reject_type = $(this).attr("data-type");
		var reject_for = $(this).parent().attr("id");
		$('.modal-body #reject_type').val(reject_type);
		$('.modal-body #reject_for').val(reject_for);
		$('.modal-content select').hide();
		if(reject_type == "join_reject"||reject_type == "candidate_withdraw")
		{
			$('.sel_rej_join').show();
			$('#rejectModal').show();
		}
	})
});

</script>



	<div id="offerModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content" style="width: 50%">
			<span class="close" onclick="$('#offerModal').css('display','none');">x</span>
			<div>
				<div class="modal-body">
					<p>Please fill all details</p>
					<br>
					<%if(post!=null){ %>
					<input	type="hidden" id="postIdForAccept" value="<%=post.getPostId() %>" />
					 <%}else{ %>
					<input	type="hidden" id="postIdForAccept"  />
					  
					 <%} %>
					 
					 <!-- <label>Total CTC (INR): </label><span
						style="color: green; font-weight: bold;" id="totalCTCinWords"></span>
					<br>
					<input type="text" id="totalCTC"
						onchange="getAmountInWords(this.value,'totalCTCinWords')" /> <input
						type="hidden" id="postIdForAccept" /> <br> <span
						id="errorTotalCTC" style="display: none; color: red;"></span> <label>Billable
						CTC (INR): </label> <span style="color: green; font-weight: bold;"
						id="billableCTCinWords"></span> <br> <input type="text"
						id="billableCTC"
						onchange="getAmountInWords(this.value,'billableCTCinWords')" /> <br>
					<span id="errorBillableCTC" style="display: none; color: red;"></span> -->
					<label>Joining Date : </label> <br>
					<input type="text" id="datepicker" /> <span id="errorJoiningDate"
						style="display: none; color: red;"></span>
				</div>
				<div class="model-footer">
					<button class="btn btn-cancel" onclick="$('#offerModal').css('display','none');">Cancel</button>
					<button class="btn btn-ok" id="offerjoinedpopup">Ok</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	jQuery(document).ready(function() {

	$(document.body).on('click', '.profile_status > .join_accept', function() {
		$('#datepicker').val('');
		$('#errorJoiningDate').html('');
		$('#offerModal').show();
	});
	});
	</script>
	
<!--   <script src="//code.jquery.com/jquery-1.10.2.js"></script> -->
  <script src="js/jquery-3.1.0.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<script type="text/javascript">
		jQuery(document).ready(function() {
			$(function() {
				$("#datepicker").datepicker({
					dateFormat : 'yy-mm-dd'
				});
			});
		});
	</script></body>