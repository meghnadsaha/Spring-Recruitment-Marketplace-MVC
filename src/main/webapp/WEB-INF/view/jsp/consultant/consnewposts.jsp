<!DOCTYPE html>
<%@page import="com.unihyr.domain.Industry"%>
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
jQuery(document).ready(function() {
	$(document.body).on('change', '.sel_newposts' ,function(){
	  var val = [];
	  if($('.sel_newposts:checkbox').length > $('.sel_newposts:checkbox:checked').length)
	  {
		  $('#sel_allnewPost').removeAttr("checked");
	  }
	  else
	  {
	  	$('#sel_allnewPost').attr("checked","checked");
	  }

    });
	
	$(document.body).on('change', '#sel_allnewPost' ,function(){
		if($('#sel_allnewPost').attr('checked'))
		{
			$('.sel_newposts:checkbox').attr('checked','checked')
		}
		else
		{
			$('.sel_newposts:checkbox').removeAttr('checked')
		}
		
	});
	
	
	
  });
	
</script>

<script type="text/javascript">
	function  consnewposts(pn)
	{
		var sortParam=$('#sortParam').val();
		var sel_industry = $('#cons_db_sel_industry').val();
		pleaseWait();
		$.ajax({
			type : "GET",
			url : "consnewpostslist",
			data : {'pn':pn,'sel_industry':sel_industry,'sortParam':sortParam},
			contentType : "application/json",
			success : function(data) {
				$('.cons_new_posts').html(data);
				pleaseDontWait();
			},
			error: function (xhr, ajaxOptions, thrownError) {
				pleaseDontWait();
		      }
	    }) ;
	}
	
	
</script>

</head>
<body class="loading" onload="consnewposts('1')">

<div class="mid_wrapper">
  <div class="container">
	  	<div id="positions_info">
		  	<div>
				<div class="rightside_in new_table" style="padding: 10px 15px 0px;">
				  	<div class="block consulting" style="float: left; width: auto;">
				        <div >
							<select id="cons_db_sel_industry"  onchange="consnewposts('1')">
				        		<option value="0">All Industries</option>
				        		<c:forEach var="item" items="${indList}">
								   <option value="${item.id}"  >${item.industry}</option>
								</c:forEach>
				        	</select>
				        	
						</div>
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
			   </div>
		  </div>
	    <div  class="positions_info cons_new_posts">
	      <div class="positions_tab">
	        	<table class="table no-margin" style="border: 1px solid gray;">
		        	<thead>
		        		<tr>
	       				<th></th>
	       				<th align="left">Position</th>
	       				<th align="left">Industry</th>
	       				<th align="left">Job Id</th>
	       				<th align="left">Posted Date</th>
<!-- 	       				<th align="left">Org</th> -->
	       				<th align="left">Location</th>
	       				<th align="left">Exp. Range</th>
	       				<th align="left">Comp. Range</th>
		       			</tr>
	       			</thead>
	       			<tbody>
	       				
	       			</tbody>
	       		</table>
	        
	      </div>
	    </div>
    </div>
    <div id="post_detail"  style="padding: 25px 20px;">
	    
    </div>
  </div>
</div>

</body>
</html>
