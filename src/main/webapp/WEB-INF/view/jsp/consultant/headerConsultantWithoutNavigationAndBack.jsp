<!DOCTYPE html>

<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.Registration"%>
<html dir="ltr" lang="en-US">
<head>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
<link href="css/fonts.css" type="text/css" media="all" rel="stylesheet" />
	<link rel="stylesheet" href="css/fonts.css" media="screen"   />
<link href="css/main.css" type="text/css" media="all" rel="stylesheet" />
<link href="css/media.css" type="text/css" media="all" rel="stylesheet" />
<link href="css/font-awesome.css" type="text/css" media="all"rel="stylesheet" />
<link rel="stylesheet" href="css/alertify.core.css" />
<link rel="stylesheet" href="css/alertify.default.css" id="toggleCSS" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.IE.js"></script>
<script type="text/javascript" src="js/consult_js.js"></script>
<script type="text/javascript" src="js/common_js.js"></script>
<link rel="stylesheet" href="css/model.popup.css" />


<script src="js/alertify.min.js"></script>

<script type="text/javascript">
<%if(GeneralConfig.UniHyr_Notificatin_Flag){%>
jQuery(document).ready(function() {
	var i = setInterval(function(){
		countMessage();
		countNotification();
	},555000);
	
});
<%}%>
function countMessage(){
	$.ajax({
		type : "GET",
		url : "conscountmessages",
		async: false,
		data : {},
		contentType : "application/json",
		success : function(data) {
			if(data!='0'){
				$('#messageCount').css("display","block");
				$('#messageCount').html(data);
			}
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
	}) ;
}

function countNotification(){
	$.ajax({
		type : "GET",
		url : "countUserNotifications",
		data : {},
		contentType : "application/json",
		success : function(data) {
			if(data!='0'){
				$('#notificationCount').css("display","block");
				$('#notificationCount').html(data);
			}
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;
}

function getMessages(){
	pleaseWait();
	$('.notification .noti_inner').html("");
	$('.noti_title').html("Messages");
	$.ajax({
		type : "GET",
		url : "consmessages",
		async: false,
		data : {},
		contentType : "application/json",
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			if(obj.mList.length > 0)
			{
				$('.notification .noti_inner').html("");
				$('.noti_title').html("Messages");
				$('#messageCount').css("display","none");
				$.each(obj.mList , function(i, val) {
				$('.notification .noti_inner').append("<a href='consapplicantinfo?ppid="+val.ppid+"'><div class='noti_row' title='"+val.message+"' postprofile='"+val.ppid+"'>" +
							"<span class='noti-cons'>"+val.cons+"</span> sent a message on " +
							"<span class='post-title'>"+val.ptitle+"</span>.</div></a><span style='font-size:9px;' >"+val.msgdate+"</span");
				});
			}
			else
			{
				$('#messageCount').css("display","none");
				$('.notification .noti_inner').append("<p>No message available</p>");
			}
			pleaseDontWait();
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
	}) ;
}

function getNotifications(){
	pleaseWait();
	$('.notification .noti_inner').html("");	
	$('.noti_title').html("Notifications");
	$.ajax({
		type : "GET",
		url : "getUserNotifications",
		data : {},
		contentType : "application/json",
		success : function(data) {
		var obj = jQuery.parseJSON(data);
		$('.notification .noti_inner').html("");	
		$('.noti_title').html("Notifications");
			if(obj.mList.length > 0)
			{
				$('#notificationCount').css("display","none");
				$.each(obj.mList , function(i, val) {
					$('.notification .noti_inner').append("<div class='noti_row'><p>"+val.notification+"</p></div><span style='font-size:9px;' >"+val.msgdate+"</span");
				});
			}
			else
			{
				$('#notificationCount').css("display","none");
				$('.notification .noti_inner').append("<p>No notification available</p>");
			}
			pleaseDontWait();
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;
}
</script>


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
<header>
    <div class="container">
		<div class="header">
	      <div class="logo">
	      	<a href="consdashboard"><img src="images/logo.png" alt="Logo"></a> 
	      	
	      </div>
	      
	      <div class="header_right">
	        
	      <div class="address_info">
	        	<p><span><a href="consultantaccount"><%= reg.getConsultName() %> </a>
	        	<%if(GeneralConfig.UnihyrRatingFlag){ %>
	        	| <a  href="consRating">Rating </a><%} %> 
	        	| <a href="consfaq">FAQ </a> | </span><span style="color: red;cursor: pointer;" onclick="getLogOut()">Logout </span></p>
	        </div>
	        <div class="notification">
			<div class="noti-icon more_product" onclick="getMessages()">
				<div class="icon "  >
					<img  class="messageIcon"  src="images/mailbox.png">
					
					<span id="messageCount" class="messageCount"></span>
				</div>
			</div>
			<div class="noti-icon more_product" onclick="getNotifications()">
				<div class="icon "  >
					<img style="" src="images/reminder.png">
					
					<span id="notificationCount" class="notificationCount"></span>
				</div>
			</div>
			<div class="user_noti_content arrow_box_1">
				<div class="noti_title">Messages</div>
				<div class="noti_inner ">
					<!-- <div class="noti_row" title="text message ">
						<span class="noti-cons">Uni Consultant</span> sent a message on  
						<span class="post-title">post title</span>.
					</div> -->
				</div>
			</div>
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
