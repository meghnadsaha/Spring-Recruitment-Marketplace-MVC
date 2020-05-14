<!DOCTYPE html>
<%@page import="com.unihyr.constraints.NumberUtils"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="com.unihyr.domain.PostConsultant"%>
<%@page import="com.unihyr.domain.PostProfile"%>
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
<!-- 	<script type="text/javascript" src="js/bootstrap-slider.js"></script> -->
<style type="text/css">
	.error{color: red;}
	#ex1Slider .slider-selection {
	background: #BABABA;
}
</style>

</head>
<body class="loading">
	<%
		Registration reg = (Registration)request.getAttribute("registration");
		long totalCount = (Long)request.getAttribute("totalCount");
       	List<Post> postList = (List)request.getAttribute("postList");
		int pn = (Integer) request.getAttribute("pn");
		int rpp = (Integer) request.getAttribute("rpp");
		int tp = 0;
		String cc = "";
		if(totalCount  == 0)
		{
			cc = "0 - 0";
		}
		else if(totalCount % rpp == 0)
		{
			tp = (int)totalCount/rpp;
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
       <div class="filter">
        <div class="col-md-4 pagi_summary">
	      	 <%-- 	<sec:authorize access="hasRole('ROLE_CON_MANAGER')">
	      	 	<button id="show_interest" class="profile_status_buttonGen">Add to Active Positions</button>
	      	 	</sec:authorize> --%>
	      	 	<sec:authorize access="hasRole('ROLE_CON_USER')">
	      	 		<span >New Posts</span>
	      	 	</sec:authorize>
	      	 </div>
       
      </div> 
      <div class="positions_tab">
        	<table class="table no-margin" style="border: 1px solid gray;">
	        	<thead>
	        		<tr>
	       			<%-- 	<sec:authorize access="hasRole('ROLE_CON_MANAGER')">
	       					<th align="left"><input id="sel_allnewPost" type="checkbox"></th>
	       				</sec:authorize> --%>
	       				<th align="left">Position</th>
						<!-- <th align="left">Org</th> -->
	       				<th align="left">Industry</th>
	       				<th align="left">Posted Date</th>
	       				<th align="left">Location</th>
	       				<th align="left" style="width:140px;">Engagement Level <span title="<%=GeneralConfig.FaqEngagementLevel %>" class="questionMark"><a>?</a></span>
	       				</th>
	       				<th align="left">Exp. Range (in Yr.)</th>
	       				<th align="left">Comp. Range (in Lacs)</th>
	       				<th align="left">Fee Range(%)</th>
	       				<th align="left" style="width: 195px;" >Offered Fee(%) <span title="<%=GeneralConfig.FaqOfferedFeeNewPosition %>" class="questionMark"><a>?</a></span>
	       				</th>
	       			</tr>
       			</thead>
       			<tbody>
       				<%
		             	if(postList != null && !postList.isEmpty())
		             	{
		             		for(Post post : postList)
		             		{
		             			
		             		 	Registration regis=post.getClient();
		             			Set<Industry> industries=regis.getIndustries();
		             			Iterator<Industry> in=industries.iterator();
		             			Industry inds=null;
		             			while(in.hasNext()){
		             				inds=in.next();
		             			} 
		             			Iterator<PostConsultant> it = post.getPostConsultants().iterator();
	                  			
	                  			PostConsultant pocl = null;
	                  			int total=0;
	                  			while(it.hasNext())
	                  			{
	                  				PostConsultant pc = it.next();
	                  				if(pc.getConsultant().getUserid().equals(reg.getUserid()) || (reg.getAdmin() != null && pc.getConsultant().getUserid().equals(reg.getAdmin().getUserid())  ) )
	                  				{
	                  					pocl = pc;
	                  				}
	                  				total++;
	                  			}
	                  			
	                  			if(pocl != null)
	                  			{
	                  			}else{
		             			%>
		             				<tr>
			             				<%-- <sec:authorize access="hasRole('ROLE_CON_MANAGER')">
			             					<td><input class="sel_newposts" type="checkbox" name="newpostselector[]" value="<%=post.getPostId() %>" ></td>
						       			</sec:authorize>		 --%>
		             					<td>
		             						<div class="pre_check" style="float: none;padding: 0;">
						                	<a  href="consviewjd?pid=<%= post.getPostId() %>"  class="view_post " title="Click to view post detail"><%= post.getTitle() %></a></div>
					                	</td>
		             					
										<%-- <td><a href="consviewuser?uid=<%= post.getClient().getUserid()%>"><%= post.getClient().getOrganizationName() %></a></td> --%>
		             					<td ><%= inds.getIndustry() %></td> 
		             					
		             					<td><%= DateFormats.ddMMMMyyyy.format(post.getPublished()) %></td>
		             					<td style="word-wrap: break-word;max-width:100px;"><%= post.getLocation()%></td>
		             					<td><%
		             					if(total>=0&&total<5){
		             						%><div style="color:white;width: 29%;background: green;padding:5px;">Low</div>
		             						<%
		             						
		             					}else if(total>=5 && total<10){
		             						%><div style="color: #fff;width:50%;background: #f8b910;padding:5px;">Medium</div><%
				             						}else{
				             							%><div style="color:white;width:75%;background: red;padding:5px; ">High</div><%
				    		             						
				             						}
		             					
		             					%></td>
		             					<td><%= post.getExp_min()%> to <%= post.getExp_max()  %>  </td>
		             					<td><%= post.getCtc_min()%> to <%= post.getCtc_max()  %>  </td>
		             					<%--  <td class="text-center">
		             						<div class="pre_check" style="float: none;padding: 0;">
						                		<a id="<%= post.getPostId() %>" class="view_post " title="Click to view post detail">
						                			<img width="35px" alt="View" src="images/view-icon.png">
					                			</a>
						                	</div>
		             					</td>  --%>
		             					<td >
		             					<%
		             					if(post.getFeePercent()>0){ 
		             					%>
		             					<%=NumberUtils.round(post.getFeePercent()/2,2) %> - <%= NumberUtils.round(post.getFeePercent(),2) %>
		             					<%}else{
		             					%>
		             					<%=NumberUtils.round(post.getFeePercent(),2) %> - <%= NumberUtils.round(post.getFeePercent(),2) %>
		             					<%
		             					}
		             					%>
		             					</td>
		             					<td >
		             					<input type="hidden" value="<%=NumberUtils.round(post.getFeePercent(),2)%>" id="postfee<%=post.getPostId() %>" name="postfee"/>
		             					<input type="number" min="<%=NumberUtils.round(post.getFeePercent()/2,2)%>" max="<%=post.getFeePercent() %>" step="0.01" style="width: 55px;padding-right: 1px;" placeholder="0.00" class="number_only" onchange="restrictOneDigit(this.id,2)"  id="<%=post.getPostId()%>" value="0.0">
		             					<button disabled="disabled" id="addbutton<%=post.getPostId() %>" class="profile_status_buttonGen" style="background: #ececec;"
		             							           	 onclick="consShowInterest('<%=post.getPostId() %>')" >Add to active posts</button>
		             					
		             					</td>
		             				</tr>
		             				<%
						                  			
						                  			}
						                  			
						                  				
		             		}
		             	}
		             	
             			
           			%>
       			</tbody>
       		</table>
        
      </div>
      <%-- <div class="block tab_btm">
        <div class="pagination">
          <ul class="pagi">
          	<%
          		if(pn > 1)
          		{
          			%>
			            <li><a onclick="consnewposts('1')">First</a></li>
			            <li><a onclick="consnewposts('<%= pn-1 %>')" ><i class="fa fa-fw fa-angle-double-left"></i></a></li>
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
		            <li class="active current_page"><a><%= pn %></a></li>
          		<%
	          	if(pn < tp)
	      		{
	      			%>
	      				<li ><a onclick="consnewposts('<%= pn+1 %>')"><i class="fa fa-fw fa-angle-double-right"></i></a></li>
			            <li><a onclick="consnewposts('<%=tp %>')">Last</a></li>
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
        <div class="sort_by"> <span>Sort by</span>
          <select id="sortParam" onchange="consnewposts('1')">
            <option value="published">Recent Posts</option>
            <option value="location">Location(A-Z)</option>
            <option value="title">Job Post(A-Z)</option>
          </select>
        </div>
        <%
        String sortParam=(String)request.getAttribute("sortParam");
        %>
        <script type="text/javascript">
        <%if(sortParam!=null){%>
        $("#sortParam").val('<%=sortParam%>');
        $("#sortParam option[value='<%=sortParam%>']").attr('selected','selected');
        <%}%>
        </script>
      </div> --%>
   
<script type="text/javascript">
function isFloat(n){
    return Number(n) === n && n % 1 !== 0;
}

$(".number_only").keydown(function (e) {
	
// 	if(e.id){
		
// 	}
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A, Command+A
        (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});
$(".number_only").on("change",function(e){
	var pid=this.id;
	var prange=$('#'+pid).val();
	var fee=$('#postfee'+pid).val();
	//alert(isFloat(prange));
//	if(isFloat(prange)){
// 	alert(Number(prange));
// 	alert(Number(fee));
// 	alert((Number(fee)/2));
	if(Number(fee)>0){
		if(Number(prange)<(Number(fee)/2)){
			alertify.error("<%=GeneralConfig.ErrorMsgWrongOfferedFee%>");
			$('#addbutton'+pid).prop('disabled', true);
			$('#addbutton'+pid).css('background','#ececec');
		//	$('#'+pid).val(Number(fee));	
		}
		else if(Number(prange)>(Number(fee))){
			alertify.error("<%=GeneralConfig.ErrorMsgWrongOfferedFee%>");
			$('#addbutton'+pid).prop('disabled', true);
			$('#addbutton'+pid).css('background','#ececec');
		//	$('#'+pid).val(Number(fee));	
		}else{
			$('#addbutton'+pid).prop('disabled', false);
			$('#addbutton'+pid).css('background','#f8b910');
		}
	}else{
	//	alert(fee);
		alertify.error("<%=GeneralConfig.ErrorMsgWrongOfferedFee%>");
		$('#addbutton'+pid).prop('disabled', true);
		$('#addbutton'+pid).css('background','#ececec');
	//	$('#'+pid).val(Number(fee));
	}
	//}else{
	//	alert(fee+"n");
	//	alertify.error("wrong fee percent range entered. Changed to default value");
	//	$('#'+pid).val(Number(fee));
	//}
});


</script>
</body>
</html>
