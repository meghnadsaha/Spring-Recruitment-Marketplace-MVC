<!DOCTYPE html>
<%@page import="com.unihyr.domain.ConfigVariables"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="com.unihyr.util.IntegerPerm"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter"%>
<%@page import="com.artofsolving.jodconverter.DocumentConverter"%>
<%@page import="java.net.ConnectException"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection"%>
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
<style type="text/css">
	.error{color: red;}
	
	<%
	Registration reg=(Registration)request.getAttribute("reg");
	ConfigVariables contract=(ConfigVariables)request.getAttribute("contract");
	String str=contract.getVarValue();
	%>
</style>
<link rel="stylesheet" type="text/css" href="css/demo.css">

<script type="text/javascript" src="js/accordion.js"></script>
</head>
<body class="loading">
<div class="mid_wrapper">
  <div class="container" >
  	<div class="new_post_info" style="margin-top: 10px;padding: 0 14px;">

		      <div class="filter">
		        <div class="col-md-7  pagi_summary"><span>Contract Agreement
		        </span></div>
		 
		      </div>
		      
		      <div class="positions_tab  bottom-margin" style="border: 1px solid gray;overflow: auto;max-height: 400px;">
		        <div class="form_cont">
			      <%=str %>
		        </div>
		     
		      	</div>
				<form action="contractagreement" method="post" onclick="">
				<label><input type="checkbox" onchange="checkAgree()">
			       On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and 
			    </label> 
			    <br> <br>
		       	<input type="hidden" name="userid" value="<%=reg.getUserid()%>">
		       	<input type="submit" value="Submit" />	  
				</form>
	</div>
</div>
</div>
</body>
</html>
