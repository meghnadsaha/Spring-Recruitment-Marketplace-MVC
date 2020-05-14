<!DOCTYPE html>

<%@page import="com.unihyr.domain.Registration"%>
<html dir="ltr" lang="en-US">
<head>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
<!-- <link href="css/fonts.css" type="text/css" media="all" rel="stylesheet" /> -->
<!-- 	<link rel="stylesheet" href="css/fonts.css" media="screen"   /> -->
<!-- <link href="css/main.css" type="text/css" media="all" rel="stylesheet" /> -->
<!-- <link href="css/media.css" type="text/css" media="all" rel="stylesheet" /> -->
<style type="text/css">
.logo {
    width: auto;
    float: left;
    margin-top: 10px;
}
.header {
    float: left;
    width: 100%;
    padding: 5px 20px 20px;

}
.header_right {
    float: right;
}
.address_info {
    float: left;
    width: 350px;
}
.address_info {
    clear: both;
    padding-top: 5px;
    text-align: right;
}
.container {
    width: 94%;
    margin-left: auto;
    margin-right: auto;
    background-color: #fff;
}
a {
    text-decoration: none;
    outline: none;
    color: darkblue;
    }
    p {
    margin: 0px;
    padding: 0px;
}
</style>


<script>
		function getLogOut(){
			if (XMLHttpRequest)
			{
				x=new XMLHttpRequest();
			}
			else
			{
				x=new ActiveXObject("Microsoft.XMLHTTP");
			}
		     x.onreadystatechange=function()
			{
		    	 if(x.readyState==4 && x.status==200)
					{
		    		 var res = x.responseText;
		    		 window.location.href="${pageContext.request.contextPath}/j_spring_security_logout";
		    		}
			}
			x.open("GET", "${pageContext.request.contextPath}/insertLogOut",true);
			x.send();
			return true;
		}
</script>
</head>
<body class="loading"  >
	<tilesx:useAttribute name="currentpage" />
<%
	Registration reg = (Registration)request.getAttribute("registration");
	
%>
<header style="z-index: 99;">
    <div class="container">
		<div class="header">
	      <div class="logo">
	      	<a href="consdashboard"><img src="images/logo.png" alt="Logo"></a> 
	      	
	      </div>
	      
	      <div class="header_right">
	        
	      <div class="address_info">
	        	<p><span><a href="consultantaccount"><%= reg.getConsultName() %> </a>| <a href="consfaq">FAQ </a> | </span><span style="color: red;cursor: pointer;" onclick="getLogOut()">Logout </span></p>
	        </div>
	        
	      </div>
	    </div>
  	
		<%-- <nav class="nav">
				<a href="javascript:void(0);"
					onClick="$('.toggle_menu').slideToggle();" class="toggle-icon"></a>
				<ul class="toggle_menu">
					<li class="${currentpage == 'consdashboard' ? 'active' : ''}"><a href="consdashboard">Home</a></li>
					<li class="${currentpage == 'consnewposts' ? 'active' : ''}" class="active"><a href="consnewposts">New Positions</a></li>
					<li class="${currentpage == 'cons_your_positions' ? 'active' : ''}"><a href="cons_your_positions">Manage Positions</a></li>
					<li class="${currentpage == 'consBillingDetails' ? 'active' : ''}"><a href="consBillingDetails">Billing Details</a></li>
					<li class="${currentpage == 'consprofilecenter' ? 'active' : ''}"><a href="consprofilecenter">Profiles Center</a></li>
				</ul>
		</nav> --%>
		<div  style="width: 100%;text-align: right;">
	  <button class="profile_status_buttonGen"  onclick="javascript:history.go(-1)"  style="    margin-right: 17px;" >Back</button>
</div>
	</div>
</header>




</body>
</html>
