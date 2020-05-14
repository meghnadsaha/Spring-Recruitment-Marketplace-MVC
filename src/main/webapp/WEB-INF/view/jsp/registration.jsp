<!DOCTYPE html>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.model.ClientRegistrationModel"%>
<%@page import="com.unihyr.domain.Location"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		input[type="text"], input[type="number"], input[type="password"], input[type="tel"], input[type="search"], input[type="email"], textarea, select 
		{
			margin-top: 10px;
		}
	</style>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/client_js.js"></script>
	<script type="text/javascript" src="js/common_js.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$(".btn_submit").click(function() {
			var valid = true;
			var org = $('#organizationName').val();
			var designation = $('#designation').val();
			var userid = $('#userid').val();
			var industry = $('#industry').val();
			var noofpeoples = $('#noofpeoples').val();
			var contact = $('#contact').val();
			var officeLocations = $('#officeLocations').val();
			var websiteUrl = $('#websiteUrl').val();
			var nooflocation = $('#hoAddress').val();
			var about = $('#about').val();
			var officeAddress = $("#officeAddress").val();
			var name = $("#name").val();
			//	$('.error').html('&nbsp;');
			if(org == "")
			{
				$('.org_error').html('Please enter organization name ')
				valid = false;
			}
			if(userid == "" || !isEmail(userid))
			{
				$('.userid_error').html("Please enter a valid email");
				valid = false;
			}
			if(industry == "" || industry =='0')
			{
				$('.industry_error').html("Please select an industry");
				valid = false;
			}
			if(contact == "" || contact.length != 10 )
			{
				$('.contact_error').html("Please enter a valid phone number");
				valid = false;
			}
			if(officeAddress == "")
			{
				$('.officeAddress_error').html("Please enter office address");
				valid = false;
			}
			/* if(noofpeoples == "")
			{
				$('.noofpeoples_error').html("Please enter no of employees");
				valid = false;
			}
			if(designation == "")
			{
				$('.designation_error').html("Please enter designation");
				valid = false;
			}
			
			if(officeLocations == null || officeLocations == "")
			{
				$('.officeLocations_error').html("Please select city");
				valid = false;
			}
			if(websiteUrl == "" || !isUrlValid(websiteUrl))
			{
				$('.websiteUrl_error').html("Please enter a valid url");
				valid = false;
			}
			if(nooflocation == "" || !jQuery.isNumeric( nooflocation ))
			{
				$('#hoAddress').val('0');
				$('.hoAddress_error').html("Please enter a valid no of locations");
				valid = false;
			}
			
			if(name == "")
			{
				$('.name_error').html("Please enter Contact Person Name");
				valid = false;
			} */
			if(!valid)
			{
				return false;
			}
		});
	});
	
	function isEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
	function isUrlValid(url) {
	    return /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(url);
	}
	function checkUserExistance()
	{
		$('.userid_error').html("&nbsp;");
		var userid = $('#userid').val();
		if(!isEmail(userid))
		{
			$('.userid_error').html("Please enter a valid email.");
			$('#userid').focus();
			return false;
		}
		$.ajax({
			type : "GET",
			url : "checkUserExistance",
			data : {'userid':userid},
			contentType : "application/json",
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if(obj.uidexist)
				{
					$('.userid_error').html("This email already registered.");
					$('#userid').focus();
				}
				
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
	function checkConsultantNameExistance()
	{
		$('.org_error').html("&nbsp;");
		var organizationName = $('#organizationName').val();
		if(organizationName=="")
		{
			$('.org_error').html("Please enter organization name.");
			$('#organizationName').focus();
			return false;
		}
		$.ajax({
			type : "GET",
			url : "checkUserNameExistance",
			data : {'userName':organizationName},
			contentType : "application/json",
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if(obj.uNameexist)
				{
					$('.org_error').html("This organization name already registered.");
					$('#org_error').focus();
				}
				
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
	</script>
	<style type="text/css">
	.req{color: red;}
	</style>
</head>
<body style="background: rgb(236, 236, 236) none repeat scroll 0% 0%;">
	<%
	String uidex = (String)request.getAttribute("uidex");
	String usermsg = ""; 
	
	if(uidex != null && uidex.equals("exist"))
	{
		usermsg = "user aleady registered !";
	}
	String uNamedex = (String)request.getAttribute("uNamedex");
	String orgmsg = ""; 

	System.out.println(uNamedex);
	if(uNamedex != null && uNamedex.equals("exist"))
	{
		orgmsg = "Organization name aleady registered !";
	}
	ClientRegistrationModel model = (ClientRegistrationModel) request.getAttribute("regForm");
	
	%>


	<section>
		<div class="container reg_page" style="margin: 50px auto;">
			<a href="home"><span class="close" title="Home Page"><img style="    height: 40px;" src="images/close.png" /></span></a>
			<div class="reg-form">
				<form:form method="POST" action="clientregistration" commandName="regForm">
					<div class="reg-header bottom-padding" >
						<a href="home"><img alt="" src="images/logo.png"></a>
						<h2 style="float: right;">Registration for Employer</h2>
					</div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Contact Person Name<span class="req">*</span></label>
							<form:input path="name" />
							<span class="error name_error">&nbsp;<form:errors path="name" /></span>
						</div>
					</div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Phone.<span class="req">*</span></label>
							<form:input path="contact" cssStyle="padding-left:35px;" cssClass="number_only" maxlength="10" minlength="10"  />
							<span style="position: relative; padding: 5px; border-right: 1px solid rgb(212, 212, 212); float: left; margin-top: -27px;font-size: 12px;"> +91 </span>
							<span class="error contact_error">&nbsp;<form:errors path="contact" /></span>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Email id<span class="req">*</span></label>
							<form:input path="userid" type="email"  onchange="checkUserExistance()" />
							<span class="error userid_error">&nbsp;<form:errors path="userid" /> <%= usermsg %></span>
						</div>
					</div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Designation<span class="req">*</span></label>
							<form:input path="designation"  />
							<span class="error designation_error">&nbsp;<form:errors path="designation" /> </span>
						</div>
					</div>
					<div class="clearfix"></div>
					<%-- <div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Pan No<span class="req">*</span></label>
							<form:input path="panno"  />
							<span class="error panno_error">&nbsp;<form:errors path="panno" /> </span>
						</div>
					</div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Service Tax No<span class="req">*</span></label>
							<form:input path="stno"  />
							<span class="error stno_error">&nbsp;<form:errors path="stno" /> </span>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>IFSC/RTGS Code<span class="req">*</span></label>
							<form:input path="ifscCode"  />
							<span class="error ifscCode_error">&nbsp;<form:errors path="ifscCode" /> </span>
						</div>
					</div> --%>
					<%-- <div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Account No<span class="req">*</span></label>
							<form:input path="accountNo"  />
							<span class="error accountNo_error">&nbsp;<form:errors path="accountNo" /> </span>
						</div>
					</div> --%>
					
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Organization Name<span class="req">*</span></label>
							<form:input path="organizationName"   onchange="checkConsultantNameExistance()"/>
							<span class="error org_error">&nbsp;<form:errors path="organizationName" /> <%= orgmsg %></span>
						</div>
					</div>
					
				<%-- 	<div class="clearfix"></div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Password <span class="req">*</span></label>
							<form:password path="password"   />
							<span style="font-size: 9px;">(Password must contain at least six characters and alphanumeric!) </span><br>
							<span class="error password_error">&nbsp;<form:errors path="password" /></span>
						</div>
					</div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Re-Password<span class="req">*</span></label>
							<form:password path="repassword"   />
							<span class="error repassword_error">&nbsp;<form:errors path="repassword" /></span>
						</div>
					</div> --%>
					
					<div class="reg-wrap" style="display: none;">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Revenue<span class="req">*</span></label>
							<form:input path="revenue" cssClass="number_only" maxlength="10"   style="padding-right: 150px" value="10"/>
							<span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;">  INR (Millions)</span>
							<span class="error revenue_error">&nbsp;<form:errors path="revenue" /></span>
						</div>
					</div>
							<%
							List<Location> locList = (List) request.getAttribute("locList");
							List<Industry> industryList = (List) request.getAttribute("industryList");
							String[] aa = (String[]) request.getAttribute("sel_inds");
							List<String> sel_inds = null;
							if (aa != null) {
								sel_inds = Arrays.asList(aa);
							}
							System.out.println("<<<<<<<<<<<<<<< : " + sel_inds);
					%>
					<div class="reg-wrap">
						<div>
							<label>Industry<span class="req">*</span><span
								style="font-size: 9px;"></span></label>
								 <select name="industries" id="industries" >
								 	<option value="0" selected="selected">Select Industry</option>
								<%
									if (industryList != null && !industryList.isEmpty()) {
											for (Industry ind : industryList) {
												if (sel_inds != null && sel_inds.contains(String.valueOf(ind.getId()))) {
								%>
								<option value="<%=ind.getId()%>" selected="selected"><%=ind.getIndustry()%></option>
								<%
									} else {
								%>
								<option value="<%=ind.getId()%>"><%=ind.getIndustry()%></option>
								<%
									}
									}
									}
								%>
							</select> <span class="error industry_error">&nbsp; ${industry_req }</span>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>No. of employees<span class="req">*</span></label>
							<form:input path="noofpeoples" cssClass="number_only" maxlength="10"  />
							<span class="error noofpeoples_error">&nbsp;<form:errors path="noofpeoples" /></span>
						</div>
					</div>
					
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Website URL<span style="font-size: 9px;"></span><span class="req">*</span></label>
							<form:input path="websiteUrl" placeholder='http://www.example.com' />
							<span class="error websiteUrl_error">&nbsp;<form:errors path="websiteUrl" /></span>
						</div>
					</div>
					<div class="reg-wrap" style="display: none;">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>No. of locations<span class="req">*</span></label>
							<form:input path="hoAddress" class="number_only number_pasitive"/>
							<span class="error hoAddress_error">&nbsp;<form:errors path="hoAddress" /></span>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>Corporate Office<span class="req">*</span></label>
							<form:textarea path="officeAddress" />
							<span class="error officeAddress_error">&nbsp;<form:errors path="officeAddress" /></span>
						</div>
					</div>
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>About Company</label>
							<form:textarea path="about"  rows="4"/>
							<span class="error about_error">&nbsp;<form:errors path="about" /></span>
						</div>
					</div>
					<div class="clearfix"></div>
					
					<div class="reg-wrap">
						<div style="padding-bottom: 10px;" class='clearfix'>
							<label>City<span class="req">*</span></label>
							<form:select path="officeLocations" >
								<form:option value="">Select City</form:option>
								<%
	              				List<String> locList1=GeneralConfig.topLocations;
	              				for(String loc:locList1){
	              					%>
						   				<form:option value="<%=loc %>"><%=loc %></form:option>
	              					<%
	              				}
	              				%>
								
			            		<%
			            			if(locList != null && !locList.isEmpty())
			            			{
			            				for(Location loc : locList)
			            				{
			            					if(model.getOfficeLocations() != null && model.getOfficeLocations().contains(loc.getLocation()) )
			            					{
			            						if(!locList1.contains(loc.getLocation())){
			            						%>
			            							<form:option value="<%= loc.getLocation() %>" selected = "selected"><%= loc.getLocation() %></form:option>
			            						<%
			            					}}
			            					else
			            					{
			            						if(!locList1.contains(loc.getLocation())){
			            						%>
			            							<form:option value="<%= loc.getLocation() %>"><%= loc.getLocation() %></form:option>
			            						<%
			            						}
			            					}
			            				}
			            			}
			            		%>
			            	</form:select>
							
<%-- 							<form:input path="officeLocations"   /> --%>
							<span class="error officeLocations_error">&nbsp;<form:errors path="officeLocations"  /></span>
						</div>
					</div>
					
					
					<div class="clearfix"></div>
					<div class="login-footer bottom-padding clearfix">
						<div class="form_submt bottom-padding10" class='clearfix'>
					        <button type="submit" class="btn_submit btn-login btn yelo_btn" >Sign up</button>
					    </div>
<!-- 				        <a href="login"> Already have an account ?</a> -->
					</div>
				</form:form>
			</div>
		</div>
	
	</section>

<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>