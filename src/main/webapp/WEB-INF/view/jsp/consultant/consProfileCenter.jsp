<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.BillingDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function  loadclientprofilecneter(pn)
	{
		
		$.ajax({
			type : "GET",
			url : "consprofilecenterlist",
			data : {'pn':pn},
			contentType : "application/json",
			success : function(data) {
				$('.client_pro_center').html(data);
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
</script>
</head>
<body onload="loadclientprofilecneter(1)">



<div class="mid_wrapper">
  <div class="container">
	  	<div id="positions_info">
		  	<div>
				<div class="rightside_in new_table" style="padding: 10px 20px 0px;">
				  	<div class="block consulting" style="float: left; width: auto;">
				        
				    </div>
				 
         
			   </div>
		  </div>
	    <div  class="positions_info client_pro_center">
				
	    </div>
    </div>
    
  </div>
</div>




</body>
</html>