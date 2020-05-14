<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>UniHyr</title>
	
	<link rel="stylesheet" href="css/fonts.css" media="screen"   />
	<link rel="stylesheet" href="css/media.css" media="screen" />
    <link rel="stylesheet" href="css/style.css" media="screen" />
	<link rel="stylesheet" href="css/font-awesome.css" media="screen"   />
	<link href="css/main.css" type="text/css" media="all" rel="stylesheet" />
	<style type="text/css">
		input[type="text"], input[type="password"], input[type="tel"], input[type="search"], input[type="email"], textarea, select 
		{
			margin-top: 10px;
		}
	</style>
</head>
<body>
	<section>
		<div class="container" style="background: inherit;">
			<div class="login-form"  style="max-width: 700px">
				<div class="login-header" style="padding: 4px;">
					<a href="index">
					<img alt="" src="images/logo.png" style="margin-left: 48px;">
					</a>
					<a href="home"><span class="close" title="Home Page" style="top: -24px;">
					<img style="    height: 40px;" src="images/close.png" /></span></a>
				</div>
				<div class="login-wrap bottom-padding">
						
						<%
						String regSuccess = (String) request.getAttribute("forgetpasswordres");
						//System.out.println("asdfasdfasd"+regSuccess);
							if (regSuccess != null && regSuccess.equals("true"))
						{%>
							<p>
									You new password has been sent to your mail id. 
								</p>
						<%}
							 regSuccess = (String) request.getAttribute("contractagree");
							//System.out.println("asdfasdfasd"+regSuccess);
								if (regSuccess != null && regSuccess.equals("true"))
							{%><p>
								You've completed your registration process. Your userid and password has been sent to your mail id. 
							</p>
								
							<%}else if(regSuccess != null && regSuccess.equals("exist")){
								%><p>
								You've already completed your registration process. Thank you 
							</p>
								
							<%
							}
								 regSuccess = (String) request.getParameter("regSuccess");
	 							if (regSuccess != null && regSuccess.equals("true"))
								{
								String org = (String) request.getParameter("orgName");
								%>
									<form class="form-box bottom-padding" method="POST" action="j_spring_security_check" style="padding-bottom: 0px !important;">
									<p>
										Thank You for showing interest in UniHyr. Our representative will get in touch with you within 24 business hours . For any other information, please write to register@unihyr.com
									</p>	
									</form>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<a href="adminuserlist" class="btn" style="background-color: green;border: 1px solid gray; padding:5px 10px;color: #fff;">Back To Users List</a>
									</sec:authorize>
								<%
							}
						%>					
				</div>
				<div style="text-align: center;">
				 <a style='position: relative;
						    bottom: 14px;
						    background: rgb(249, 185, 16) none repeat scroll 0% 0%;
						    border-radius: 3px;
						    font-size: 17px;
						    color: rgb(255, 255, 255);
						    padding: 3px 14px;
						    text-align: center;' href='index' >OK</a>
				</div>
			</div>
		</div>
	
	</section>


	











	<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>