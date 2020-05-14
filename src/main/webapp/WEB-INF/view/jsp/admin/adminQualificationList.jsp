
<%@page import="com.unihyr.domain.Qualification"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="java.util.List"%>
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
           List Of Qualifications
<!--             <small>add new </small> -->
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
<!--             <li><a href="#"><i class="fa fa-dashboard"></i> Industries</a></li> -->
            <li class="active">Qualifications</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-6">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">Qualifications</h3>
		
		             <div class="box-tools pull-right">
		               <a href="adminAddQualification" class="btn text-white" style="color: #fff;">New Qualification</a>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		           	<table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Qualification</th>
									<th>Type</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
			           		 <%
		   	           		 	List<Qualification> qListUg= (List) request.getAttribute("qListUg");
		   	           		 	if (qListUg != null && !qListUg.isEmpty()) 
		   	           		 	{
		   	           		 		for (Qualification qu : qListUg) 
		   	           		 		{
		     	           		 			
		     	           		 		%>
											<tr>
												<td><%= qu.getqTitle() %></td>
												<td><%= qu.getqType() %></td>
												<td class="text-center">
												<a href="adminEditQualification?qid=<%= qu.getqId()%>"><button class="btn btn-sm btn-success"> Edit </button></a>
												<a href="javascript:void(0)"><button class="btn btn-sm delete_qua" id="<%= qu.getqId()%>"> Delete </button></a>
												</td>
											</tr>
		     	           		 		
		       		 					<%
			           		 		}
			           		 	}
			           		 %>
			           		 <%
		   	           		 	List<Qualification> qListPg= (List) request.getAttribute("qListPg");
		   	           		 	if (qListPg != null && !qListPg.isEmpty()) 
		   	           		 	{
		   	           		 		for (Qualification qu : qListPg) 
		   	           		 		{
		     	           		 			
		     	           		 		%>
											<tr>
												<td><%= qu.getqTitle() %></td>
												<td><%= qu.getqType() %></td>
												<td class="text-center">
												<a href="adminEditQualification?qid=<%= qu.getqId()%>"><button class="btn btn-sm btn-success"> Edit </button></a>
												<a href="javascript:void(0)"><button class="btn btn-sm delete_qua" id="<%= qu.getqId()%>"> Delete </button></a>
												</td>
											</tr>
		     	           		 		
		       		 					<%
			           		 		}
			           		 	}
			           		 %>
							</tbody>
						</table>
		                
		           		
		           		
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
<script src="js/admin/jQuery-2.1.4.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
	$('.overlay').hide();
	
	$(document.body).on('click', '.posts .delete_qua' ,function(){
		var qid = $(this).attr("id");
		var row = $(this).parent().parent().parent();
		alertify.confirm("Are you sure you want to delete this qualification ?", function (e, str) {
			if (e) {
				$.ajax({
					type : "GET",
					url : "adminDeleteQualification",
					data : {'qid':qid},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.success)
						{
							row.remove();
						}
// 						alert(data);
					}
				});
			}
		});
	});
	
	
	
	
});
</script>    
  </body>
</html>