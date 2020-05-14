
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
		.error{color: red;}
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Edit Qualification
            <small>Update</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="adminQualificationList"><i class="fa fa-dashboard"></i> Qualifications</a></li>
            <li class="active">Edit Qualification</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-6">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">Update Location</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="javascript:location.reload()"><i class="fa fa-fw fa-refresh"></i></button>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
	           		 <form:form method="POST"  class="form-horizontal" action="adminEditQualification" commandName="qualificationForm" onsubmit=" return validateForm()">
		                  <div class="box-body">
		                  		<div class="form-group">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Qualification</label>
			                      <div class="col-sm-8">
			                        <form:input path="qTitle" class="form-control" />
			                        <form:hidden path="qId" class="form-control" />
			                        <span class="error"> ${qTitle_error}</span>
			                      </div>
			                    </div>
		                  		<div class="form-group">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Qualification Type</label>
			                      <div class="col-sm-8">
			                        <form:input path="qType" class="form-control" />
			                        <span class="error"> ${qType_error}</span>
			                      </div>
			                    </div>
			                    
		                  </div>
		                  <div class="box-footer">
			                <button class="btn btn-primary pull-right" type="submit">Update</button>
			              </div>
		              </form:form>
		                
		           		
		           		
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
     
<script type="text/javascript">
jQuery(document).ready(function() {
	$('.overlay').hide();
	
	
	
	
	
});
</script>    
  </body>
</html>