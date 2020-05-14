<!DOCTYPE html>
<%@page import="com.unihyr.domain.Registration"%>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<title>Uni Hyr</title>
<style type="text/css">

.help-desk{    position: fixed;
    top: 20%;
    right: -28px;
    -ms-transform: rotate(270deg);
    -webkit-transform: rotate(270deg);
    transform: rotate(270deg);}
.help-desk .desk-header{
	padding: 10px 20px; 
	color: #000; 
	background-color: #F8B910;
	cursor: pointer;
}
.content-field{margin-top: 10px;}
.help-desk .desk-content{display: none;background-color: #CCCCCC;padding: 10px 20px;width: 325px;}
.help-desk .active {display: block;}
.desk-content{
-ms-transform: rotate(7deg);
    -webkit-transform: rotate(7deg);
    transform: rotate(90deg);
    border: 1px solid #f8b910;
}
.contant-body{
padding-right: 45px;
}
</style>
</head>
<body class="loading">
<footer class="clearfix">
  <div class="footer_botm Align_cent Light12 grey3">
			<div class="container" style="background-color: #1f1e1e;font-size: 12px;width: 400px;">
				<div style="float: left;line-height: 44px;">
				<a href="termsOfService">Terms of Use</a> | <a href="privacyPolicy">Privacy Policy</a> 
				
			<!-- 			|	<a	href="">Sitemap</a> | <a href="">Work with Us</a> -->
			</div>
			<div style="float: left;line-height:52px;">
						<a  style="margin:8px;" href="https://www.facebook.com/UniHyr-491301114398011/" target="_blank"> 
						<img style="width:8px;" src="images/fb.png" title="facebook" /></a> 
						<a  style="margin:8px;"  href="https://twitter.com/unihyr" target="_blank"> 
						<img style="width:15px;"  src="images/twitter.png" title="twitter" /></a> 
						<a  style="margin:8px;"  target="_blank" href="https://in.linkedin.com/in/unihyr-admin-5aab60122"> 
						<img style="width:14px;"  src="images/linkedin.png" title="linkedin" /></a>
			</div>
			</div> 
			</div>
</footer>

<div class="help-desk">
	<div class="desk-header " style="top: -64px;
position: absolute;
right: 21px;
width: 103px;
background: rgb(0, 0, 0) none repeat scroll 0% 0%;
color: rgb(255, 255, 255);
font-weight: bold;height: 50px">Help Desk</div>
	<div class="desk-content">
		<div class="contant-body"  >
			<div class="content-field">
				<label>Your Name</label>
				<input type="text" name="name" class="name" />
			</div>
			<!-- <div class="content-field">
				<label>Your Email</label>
				<input type="email" name="email" class="email" />
			</div> -->
			<!-- <div class="content-field">
				<label>Contact No.</label>
				<input type="number" id="contactNo" name="contactNo" class="contactNo" required />
			</div> -->
			<div class="content-field">
				<label>Subject</label>
				<input type="text" name="subject" id="subject" class="subject" />
			</div>
			<div class="content-field">
				<label>Message</label>
				<textarea name="message" id="message" class="msg"></textarea>
			</div>
			<div class="content-field">
				<button class="submit profile_status_button">Submit</button>
			</div>
		</div>
	</div>
</div>


<script src="js/alertify.min.js"></script>
<script type="text/javascript">
$(".toggle-icon").click(function(){
  $(".toggle-icon").toggleClass("active");
});
</script>
<script type="text/javascript">
$(".help-desk .desk-header").click(function(event){
	$(".help-desk .desk-content").toggleClass("active");
	
	  
	  event.stopPropagation();
});

$(".help-desk .desk-content").click(function(event){
	event.stopPropagation();
});

$(".help-desk .submit").click(function(event){
	var name = $('.help-desk .name').val();
	var email ='';
	//var contactNo=$('#contactNo').val();
	var subject=$('#subject').val();
	var msg = $('.help-desk .msg').val();
	$(this).html("Sending...");
	pleaseWait();
	$.ajax({
		type : "GET",
		url : "helpDeskMessage",
		data : {'name':name,'email':email,'message':msg,'subject':subject},
		contentType : "application/json",
		success : function(data) {
			
			var obj = jQuery.parseJSON(data);
			if(obj.status)
			{
				$('.help-desk .name').val("");
			//	$('.help-desk .email').val("");
				$('.help-desk .msg').val("");
			//	$('#contactNo').val('');
				alertify.success("Message Sent Successfully !");
				$(".help-desk .desk-content").removeClass("active");
			}
			$(".help-desk .submit").html("Submit");
			pleaseDontWait();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			pleaseDontWait();
		}
	}) ;
	
	
	event.stopPropagation();
});
</script>

<div class="bodyCoverWait" style="display: none;text-align: center; ">
<img style="position: relative;top: 250px;" alt="Please wait..." src="images/pwait.gif" />
</div>

<div class="firstTimeLoginPopup profileClosed1" style="top:35%;border:1px solid #f8b910;display: none;height: auto;position: fixed;max-height: 300px;" >
				<div class="login-header" style="padding: 3px 3px 3px 2px;
line-height: 30px;
height: 90px;">
					<a href="index">
					<img alt="" src="images/logo.png" style="margin-left: 48px;">
					</a>
					<a href="javascript:void(0)"><span style="padding: 0px 9px;
    top: -16px;" class="close" title="Close" onclick="$('.bodyCover').css('display','none');$('.firstTimeLoginPopup').css('display','none');"><img style="height: 40px;" src="images/close.png" /></span></a>
				</div>
			
				
				
				
				<div class="login-wrap" style="padding: 10px;" id="profileClosed">
				
				</div>
			</div>

<div class="bodyCover profileClosed1" style="display: none;position: fixed;">

</div>
<script type="text/javascript">

function getClosedCandidates(postId){
	pleaseWait();
	$.ajax({
		type : "GET",
		url : "profileClosures",
		data : {'postId':postId},
		contentType : "application/json",
		success : function(data) {
			pleaseDontWait();
			$('#profileClosed').html(data);
			$('.profileClosed1').css('display','block');
			$('.profileClosed1').css('display','block');
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;	

}

function pleaseWait(){

	$('.bodyCoverWait').css('display','block');
}
function pleaseDontWait(){

	$('.bodyCoverWait').css('display','none');
}
</script>
<% 
String message="";
System.out.println(request.getParameter("message"));
if(request.getParameter("message")!=null){
	message=request.getParameter("message");
	message="<div style='text:center;'>"+message+"<div>";
	if(!request.getParameter("message").equals("saved")){
%>
<script type="text/javascript">
			$('#profileClosed').html("<%=message%>");
			$('.profileClosed1').css('display','block');
			$('.profileClosed1').css('display','block');
</script>
<%}else{ %>

<script type="text/javascript">
			alertify.success("Post Saved successfully");
</script>
<%} %>
<%
}
%>

<%
Registration reg = (Registration)request.getAttribute("registration");

System.out.println(reg+" first time login ");
if(reg!=null&&(reg.getFirstTime()==null||!reg.getFirstTime())){
%>

<div class="firstTimeLoginPopup" style="border:1px solid #f8b910;">
				<div class="login-header" style="padding: 3px 3px 3px 2px;
					line-height: 44px;
					height: 90px;">
					<a href="index">
					<img alt="" src="images/logo.png" style="margin-left: 48px;">
					</a>
					<span class="close" title="close" style="top: -24px;"  onclick="$('.bodyCover').css('display','none');$('.firstTimeLoginPopup').css('display','none');setFirstTimeFalse('<%=reg.getUserid() %>')">
					<img style="    height: 40px;" src="images/close.png"></span>
				</div>
				<div class="login-wrap" style="padding: 10px;">
				<%if(reg.getOrganizationName()!=null){ %>
				Congratulations on signing up
			with UniHyr. Now you can access our partner network to fulfill your
			hiring mandates. Start by posting a new position from the Post a New
			Job tab. Our user interface is intuitive and easy to use. In case of
			any issues, please feel free to reach out to your Account Manager or
			our Help Desk
		
		<div style="text-align: center;padding: 10px;" class="login-wrap">
			<h2 style="color: #f8b910;font-weight: bold;">HAPPY HIRING!</h2>
			<input style="margin-top: 15px;color: #fff;font-size: 14px;font-weight: bold;" type="button" value="Get started with UniHyr" class="profile_status_buttonGen" onclick="$('.bodyCover').css('display','none');$('.firstTimeLoginPopup').css('display','none');setFirstTimeFalse('<%=reg.getUserid() %>')"  /> 
			</div>
			<%}else{ %>
			Congratulations on signing up with UniHyr. Now you can start working on new mandates across client.
           Start by adding new mandates under new positions tab into active state.
			Our user interface is intuitive and easy to use. In case of any issues, please feel free to reach out to your Account Manager or our Help Desk.
			<div style="text-align: center;padding: 10px;" class="login-wrap">
			<input style="margin-top: 15px;color: #fff;font-size: 14px;font-weight: bold;" type="button" value="Get started with UniHyr" class="profile_status_buttonGen" onclick="$('.bodyCover').css('display','none');$('.firstTimeLoginPopup').css('display','none');setFirstTimeFalse('<%=reg.getUserid() %>')"  /> 
			</div>
			<%} %>
			</div>
			
			</div>

<div class="bodyCover">

</div>


<%} %>



</body>
</html>
