<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>UniHyr</title>
	
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
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
<body style="background: #EDEDED;"> <!-- style="background-image: url('images/bg-image.png')" --> 
	<section>
		<div class="container" style="background: inherit;">
			<div class="login-form">
				<div class="login-header">
					<a href="home"><img alt="" src="images/logo.png"></a>
					<a href="home"><span class="close" title="Home Page"><img style="    height: 40px;" src="images/close.png" /></span></a>
				</div>
				<div class="login-wrap">
						<%
							String regSuccess = (String) request.getParameter("regSuccess");
							if (regSuccess != null && regSuccess.equals("true"))
							{
								String org = (String) request.getParameter("orgName");
								%>
									<h3 style="color: green;">
										<b><%=org%></b> registered successfully !
									</h3>
									<br>
								<%
							}
						%>
						

						<c:if test="${param.error}">
						    <div class="error" style="height: auto;">
						        Your login attempt was not successful, try again.<br />
						        Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
						    </div>
						    <br>
						</c:if>
						
												
						<form class="form-box" method="POST" action="j_spring_security_check">
							<div style="padding-bottom: 10px;" class='clearfix'>
								<label>User Id</label>
								<input name="j_username" id="j_username" type="email" required="required">
							</div>
							<div style="padding-bottom: 10px;" class='clearfix'>
								<label>Password</label> 
								<input type="password" name="j_password" id="j_password" type="password" >
							</div>
							<div class='clearfix'>
								<input type="checkbox" style="vertical-align: middle" id="j_remember" name="_spring_security_remember_me">
								Remember me
							</div>
							<div class="form_submt alin_cnt bottom-padding10"
								class='clearfix'>
								<button type="submit" class=" btn-login btn btn-block yelo_btn" style="margin-left: 0px;">Login</button>
							</div>
<!-- 							Create an account for <a href="clientregistration">Employer</a> or <a href="consultantregistration">Consultant</a> -->
						</form>
						<a href="forgetpassword">Forgot Password ?</a> 
				</div>
			</div>
		</div>
	
	</section>

<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>