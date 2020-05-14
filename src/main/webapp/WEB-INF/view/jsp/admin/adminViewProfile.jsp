<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body class="hold-transition skin-blue sidebar-mini" >
  <%
  PostProfile pp = (PostProfile)request.getAttribute("postProfile");
  	if(pp != null)
  	{
  		
  		%>
  
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            View Profile
            <small><%= pp.getProfile().getName() %></small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Profiles</a></li>
            <li class="active"><%= pp.getProfile().getName() %></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title"><%= pp.getProfile().getName()  %> ( <%= pp.getProfile().getCurrentRole() + " - " + pp.getProfile().getCurrentOrganization() %>)</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="javascript:location.reload()"><i class="fa fa-fw fa-refresh"></i></button>
<!-- 		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i> -->
<!-- 		               </button> -->
<!-- 		               <button data-widget="remove" class="btn btn-box-tool" type="button"><i class="fa fa-times"></i></button> -->
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		           		<div class="form-horizontal">
		                  <div class="box-body">
		                  		<div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Name</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getName()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Partner</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getPost().getClient().getOrganizationName()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getEmail()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Status</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" >
			                        	<%

										String status = "";
										if (pp.getWithdrawDate() != null) {
											status = GeneralConfig.Withdraw;
										} else if (pp.getRejected() != null) {
											status = GeneralConfig.ShortlistRejected;
										} else if (pp.getDeclinedDate() != null) {
											status = GeneralConfig.SendOfferReject;
										} else if (pp.getOfferDropDate() != null) {
											status = GeneralConfig.OfferAcceptReject;
										} else if (pp.getJoinDropDate() != null) {
											status = GeneralConfig.OfferDrop;
										} else if (pp.getJoinDate() != null) {
											status = GeneralConfig.OfferJoin;
										}  else if (pp.getOfferDate() != null) {
											status = GeneralConfig.OfferAccept;
										}else if (pp.getRecruited() != null) {
											status = GeneralConfig.SendOffer;
										}else if (pp.getAccepted() != null) {
											status = GeneralConfig.Shortlist;
										} else {
											status = GeneralConfig.SubmittedOnly;
										}
			                        		 out.println(status);
			                        	%>
		                        	</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Consultant</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getRegistration().getConsultName()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Current Role</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getCurrentRole()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Current Organization</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%=pp.getProfile().getCurrentOrganization()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Current CTC</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%=pp.getProfile().getCurrentCTC()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Expected CTC</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%=pp.getProfile().getExpectedCTC()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Willing to rotate</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getWillingToRelocate()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Location</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getCurrentLocation()%></label>
			                      </div>
			                    </div>
			                    <%if(pp.getProfile().getPreferredLocation()!=null){ %>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Preferred Location</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getPreferredLocation()%></label>
			                      </div>
			                    </div><%} %> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Notice Period</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getNoticePeriod()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <%if(pp.getProfile().getExperience()!=null){ %>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Experience</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getExperience()%> Year(s)</label>
			                      </div>
			                    </div> 
			                    <%} %>
			                    <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Phone </label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= pp.getProfile().getContact()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Uploaded On </label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= DateFormats.getTimeValue(pp.getSubmitted())%></label>
			                      </div>
			                    </div> <!-- attribute end -->
		                  </div>
		                </div>
		           		<div class="form-horizontal">
	           				<div class="box-header with-border bg-gray">
				            	<h3 class="box-title">Resume</h3>
				            </div>
	           				<div  style="padding: 10px;">
		                      <%= pp.getProfile().getResumeText()%>
		                    </div> <!-- attribute end -->
		           		</div>
		           		<div class='clearfix'></div>
		           		<%
		           			if(pp.getProfile().getScreeningNote() != null)
		           			{
		           				%>
					           		<div class="form-horizontal">
				           				<div class="box-header with-border bg-gray ">
							            	<h3 class="box-title">Screening Note</h3>
							            </div>
				           				<div>
					                      <%= pp.getProfile().getScreeningNote()%>
					                    </div> <!-- attribute end -->
					           		</div>
		           				<%
		           			}
		           		%>
		           		
		       		</div>      
		           <div class="overlay">
		              <i class="fa fa-refresh fa-spin"></i>
		            </div>
		           <!-- /.box-body -->
	      		</div>
	          </div>  
	        </div>  
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
     <%
	}
    %>
<script type="text/javascript">
jQuery(document).ready(function() {
	$('.overlay').hide();
});
</script>    
  </body>
</html>