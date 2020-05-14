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
<body >
	<section>
		<div class="container" style="background: inherit;">
			<div class="login-form"  style="max-width: 700px">
				<div class="login-header">
					<a href="index"><img alt="" src="images/logo.png"></a>
					<a href="home"><span class="close" title="Home Page">Home</span></a>
				</div>
				<div class="login-wrap bottom-padding" style="text-align: center;">
						<%
							String regSuccess = (String) request.getAttribute("verifySuccess");
						System.out.println(regSuccess);
							if (regSuccess != null && regSuccess.equals("true"))
							{
								%>
									<p style="font-weight: bold;">
										Thank You for Verifying billing details.
									</p>	
								<%
							}else{
								%>
								<p style="font-weight: bold;">
									Some Error Occured in billing verification.
								</p>	
							<%
							}
						%>					
				</div>
			</div>
		</div>
	
	</section>
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>