<!DOCTYPE html>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
<style type="text/css">
	.error{color: red;}
</style>
<script type="text/javascript">
	function  loadclientposts(pn)
	{
var sortParam=$('#sortParam').val();
if(typeof sortParam != 'undefined'){}
else
	sortParam='published';
		$.ajax({
			type : "GET",
			url : "loadclientposts",
			data : {'pn':pn,'sortParam':sortParam},
			contentType : "application/json",
			success : function(data) {
				$('#positions_info').html(data);
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
</script>
</head>
<body class="loading" onload="loadclientposts('1')">

<div class="mid_wrapper">
  <div class="container">
    <div class="positions_info" id="positions_info">

    </div>

    <div class="positions_info" id="post_detail" style="display: none;">
<table class="table no-margin" style="border: 1px solid gray;">
									<thead>
										<tr>
											<th align="left">Name</th>
											<th align="left">Phone</th>
											<th align="left">Current Role</th>
											<th align="left">Organization</th>
											<th align="left">Curent Salary</th>
											<th>Notice Period</th>
											<th>Submitted</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody><tr>
														<td colspan="9" style="width: auto;">
															<strong>No candidate submitted for this  role till now</strong> 
														</td>
													</tr>
									</tbody>
								</table>
    </div>
    
  </div>
</div>

</body>
</html>
