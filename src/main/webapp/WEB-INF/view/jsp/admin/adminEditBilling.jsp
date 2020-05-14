
<%@page import="com.unihyr.domain.BillingDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
		.error{color: red;}
	</style>
	<script type="text/javascript" src="js/jquery.datepick.js"></script>
<link rel="stylesheet" href="css/jquery.datepick.css" />
</head>
<body class="hold-transition skin-blue sidebar-mini" >
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <!-- 
        <section class="content-header">
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="adminConfigurations"><i class="fa fa-dashboard"></i> Global Reating</a></li>
          </ol>
        </section>
		<div class="clearfix"></div>
         -->
        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title"> Update Billing detail</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="javascript:location.reload()"><i class="fa fa-fw fa-refresh"></i></button>
		             </div>
		           </div>
		           
		           <%
		           		BillingDetails bill = (BillingDetails)request.getAttribute("bill");
		           		if(bill != null)
		           		{
		           			%>
		           				<div class="box-body no-padding">
				           		 <form:form method="POST"  class="form" action="addminEditBillingDetails" commandName="billForm" onsubmit="">
					                  <div class="box-body">
					                  		<div class="form-group col-md-6">
							                    <label class="control-label" for="inputEmail3">Candidate Name</label>
						                        <label class="form-control" ><%= bill.getCandidatePerson() %></label>
						                        <form:hidden path="billId" />
						                        <span class="error"> </span>
						                    </div>
					                  		<div class="form-group col-md-6">
						                        <label class="control-label" for="inputEmail3">Position</label>
						                        <label class="form-control" ><%=bill.getPosition() %></label>
						                        <span class="error industryId_error"></span>
						                    </div>
						                    <div class="clearfix"></div>
						                    <div class="form-group col-md-6">
							                    <label class="control-label" for="inputEmail3">Consultant Name/ Client Name  </label>
						                        <label class="form-control" >
						                        		<%=bill.getConsultantName()%> /
														<%=bill.getClientName()%>
												</label>
						                        <span class="error percentileTr_error"> </span>
						                    </div>
						                    <div class="form-group col-md-6">
						                        <label class="control-label" for="inputEmail3">Location</label>
						                        <label class="form-control" ><%if(bill.getLocation() != null ){out.println(bill.getLocation().replaceAll(",", ", "));}%></label>
						                        <span class="error industryId_error"></span>
						                    </div>
						                    
						                    <div class="form-group col-md-6">
							                    <label class="control-label" for="inputEmail3">Total CTC</label>
						                        <form:input path="totalCTC" class="form-control number_only" />
						                        <span class="error percentileSh_error"></span>
						                    </div>
						                    
						                    <div class="form-group col-md-6">
							                    <label class="control-label" for="inputEmail3">Billable CTC</label>
						                        <form:input path="billableCTC" class="form-control number_only" />
						                        <span class="error percentileCl_error"> ${varvalue_error}</span>
						                    </div>
											<div class="clearfix"></div>
						                    <div class="form-group col-md-6">
							                    <label class="control-label" for="inputEmail3">Expected Joining Date</label>
						                        <form:input path="expectedJoiningDate" class="form-control popupDatepicker" placeholder="yyyy-MM-dd" />
						                        <span class="error percentileInC_error"> ${varvalue_error}</span>
						                    </div>
						                    <div class="form-group col-md-6">
							                    <label class="control-label" for="inputEmail3">Joining Date</label>
						                        <form:input path="joiningDate" class="form-control popupDatepicker" placeholder="yyyy-MM-dd"  />
						                        <span class="error percentileOd_error"> ${varvalue_error}</span>
						                    </div>
			
					                  </div>
					                  <div class="box-footer">
						                <button class="btn btn-primary pull-right" type="submit">Update</button>
						              </div>
					              </form:form>
					       		</div>   
		           			<%
		           		}
		           
		           %>
		              
		           <div class="overlay">
		              <i class="fa fa-refresh fa-spin"></i>
		            </div>
		           <!-- /.box-body -->
	      		</div>
	          </div>  
	        </div>  
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
<script type="text/javascript">
jQuery(document).ready(function() {
	$('.overlay').hide();
});


function validateForm()
{
		var userid = $(".form #userid").val();
		var industryId = $(".form #industryId").val();
		var percentileTr = $(".form #percentileTr").val();
		var percentileSh = $(".form #percentileSh").val();
		var percentileCl = $(".form #percentileCl").val();
		var percentileInC = $(".form #percentileInC").val();
		var percentileOd = $(".form #percentileOd").val();
		var percentile = $(".form #percentile").val();
		
		var valid = true;
		$(".form .error").html("");
		if(userid == "")
		{
			valid = false;
		}
		if(industryId == "0")
		{
			$(".form .industryId_error").html("Select industry");
			valid = false;
		}
		if(percentileTr == "")
		{
			$(".form .percentileTr_error").html("Enter percentileTr");
			valid = false;
		}
		if(percentileSh == "")
		{
			$(".form .percentileSh_error").html("Enter percentileSh");
			valid = false;
		}
		if(percentileCl == "")
		{
			valid = false;
			$(".form .percentileCl_error").html("Enter percentileCl");
		}
		if(percentileInC == "")
		{
			valid = false;
			$(".form .percentileInC_error").html("Enter percentileInC");
		}
		if(percentileOd == "")
		{
			valid = false;
			$(".form .percentileOd_error").html("Enter percentileOd");
		}
		if(percentile == "")
		{
			valid = false;
			$(".form .percentile_error").html("Enter percentile");
		}
		
		return valid;
		
}


</script>  
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
 <script>
    
    $(document).ready(function(){ 
    	$('.popupDatepicker').datepick();
    });
	</script>
  </body>
</html>