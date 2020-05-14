<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function  load_admin_post()
	{
		$('#load_admin_post').parent().parent().siblings(".overlay").show();
		$.ajax({
			type : "GET",
			url : "adminDashboardPostList",
			data : {},
			contentType : "application/json",
			success : function(data) {
				$('#load_admin_post').html(data);
				$('#load_admin_post').parent().parent().siblings(".overlay").hide();
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
	
	function  load_admin_profile()
	{
		$('#load_admin_profile').parent().parent().siblings(".overlay").show();
		$.ajax({
			type : "GET",
			url : "adminDashboardProfileList",
			data : {},
			contentType : "application/json",
			success : function(data) {
				$('#load_admin_profile').html(data);
				$('#load_admin_profile').parent().parent().siblings(".overlay").hide();
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
	function  load_admin_client()
	{
		$('#load_admin_client').parent().parent().siblings(".overlay").show();
		$.ajax({
			type : "GET",
			url : "adminDashboardClientList",
			data : {},
			contentType : "application/json",
			success : function(data) {
				$('#load_admin_client').html(data);
				$('#load_admin_client').parent().parent().siblings(".overlay").hide();
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
	function  load_admin_consultant()
	{
		$('#load_admin_consultant').parent().parent().siblings(".overlay").show();
		$.ajax({
			type : "GET",
			url : "adminDashboardConsultantList",
			data : {},
			contentType : "application/json",
			success : function(data) {
				$('#load_admin_consultant').html(data);
				$('#load_admin_consultant').parent().parent().siblings(".overlay").hide();
			},
			error: function (xhr, ajaxOptions, thrownError) {
		      }
	    }) ;
	}
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini" onload="load_admin_post(); load_admin_profile(); load_admin_client(); load_admin_consultant();">
  
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Admin Dashboard
            <small>Optional description</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li class="active">Here</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-aqua"><img alt="" src="images/job-icon-cabot.png" width="75"></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Profiles</span>
                  <span class="info-box-number">${totalProfiles }</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-red"><img alt="" src="images/job-post.png" width="90"> </span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Posts</span>
                  <span class="info-box-number">${totalPosts }</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>

            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Employers</span>
                  <span class="info-box-number">${totalClient }</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Consultants</span>
                  <span class="info-box-number">${totalConsultant }</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->
          </div>
          <!-- Your Page Content Here -->
			
			
		<div class="col-md-6 col-sm-12  no-padding">
			<div class="row">
				<div class="col-sm-12">
					<div class="box box-success" style="min-height: 200px">
			           <div class="box-header with-border bg-green">
			             <h3 class="box-title">Post List</h3>
			
			             <div class="box-tools pull-right">
			             	<button class="text-green" type="button" onclick="load_admin_post()"><i class="fa fa-fw fa-refresh"></i></button>
			               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
			               </button>
<!-- 			               <button data-widget="remove" class="btn btn-box-tool" type="button"><i class="fa fa-times"></i></button> -->
			             </div>
			           </div>
			           <!-- /.box-header -->
			           <div class="box-body no-padding">
			               <table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Job Code</th>
									<th>Post Title</th>
									<th>Client</th>
									<th>Date</th>
									<th>Status</th>
									<th>Verified</th>
									
								</tr>
							</thead>
							<tbody id="load_admin_post">
								
							</tbody>
						</table>
			           </div>
			           <div class="overlay">
			              <i class="fa fa-refresh fa-spin"></i>
			            </div>
			           <!-- /.box-body -->
			      </div>
				  <div class="box box-success" style="min-height: 200px">
			           <div class="box-header with-border bg-green">
			             <h3 class="box-title">List of Employers</h3>
			
			             <div class="box-tools pull-right">
			               <button class="text-green" type="button" onclick="load_admin_client()"><i class="fa fa-fw fa-refresh"></i></button>
			               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
			               </button>
<!-- 			               <button data-widget="remove" class="btn btn-box-tool" type="button"><i class="fa fa-times"></i></button> -->
			             </div>
			           </div>
		           <!-- /.box-header -->
			           <div class="box-body no-padding">
			               <table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Org Name</th>
									<th>Email</th>
									<th>Contact</th>
									<th>Registered</th>
									
								</tr>
							</thead>
							<tbody id="load_admin_client">
								
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
			
		</div>
		
		<div class="col-md-6 col-sm-12 ">
			<div class="row">
				<div class="col-sm-12">
					<div class="box box-success" style="min-height: 200px">
			           <div class="box-header with-border bg-green">
			             <h3 class="box-title">Profiles Uploaded</h3>
			
			             <div class="box-tools pull-right">
			               <button class="text-green" type="button" onclick="load_admin_profile()"><i class="fa fa-fw fa-refresh"></i></button>
			               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
			               </button>
<!-- 			               <button data-widget="remove" class="btn btn-box-tool" type="button"><i class="fa fa-times"></i></button> -->
			             </div>
			           </div>
			           <!-- /.box-header -->
			           <div class="box-body no-padding">
			               <table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Candidate Name</th>
									<th>Current Role</th>
									<th>Post Title</th>
									<th>Submitted</th>
									
								</tr>
							</thead>
							<tbody id="load_admin_profile">
								
							</tbody>
						</table>
			           </div>
			           <div class="overlay">
			              <i class="fa fa-refresh fa-spin"></i>
			            </div>
			           <!-- /.box-body -->
			      </div>
			      <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">List of Consultants</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="load_admin_consultant()"><i class="fa fa-fw fa-refresh"></i></button>
		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
		               </button>
<!-- 		               <button data-widget="remove" class="btn btn-box-tool" type="button"><i class="fa fa-times"></i></button> -->
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		             <table class="table posts">
						<thead class="bg-gray">
							<tr>
								<th>Consultant Name</th>
								<th>Email</th>
								<th>Contact</th>
								<th>Registered</th>
								
							</tr>
						</thead>
						<tbody  id="load_admin_consultant">
							
							
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
		</div>	
			
		<div class="clearfix"></div>	
		<div class="row">
			<div class="col-md-6"> 
				<!-- Donut chart -->
	              <div class="box box-primary">
	                <div class="box-header with-border">
	                  <i class="fa fa-bar-chart-o"></i>
	                  <h3 class="box-title">Donut Chart</h3>
	                  <div class="box-tools pull-right">
	                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
	                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
	                  </div>
	                </div>
	                <div class="box-body">
	                  <div id="donut-chart" style="height: 300px;"></div>
	                </div><!-- /.box-body-->
	              </div><!-- /.box -->
	          </div>    
          </div>    
              
      
              
              
              
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <!-- Control Sidebar -->
     
    </div><!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="js/admin/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="js/admin/bootstrap.js"></script>
    <!-- AdminLTE App -->
    <script src="js/admin/app.js"></script>

    <!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
         
   <!-- FastClick -->
   <script src="js/admin/fastclick.js"></script>
   <!-- FLOT CHARTS -->
    <script src="js/admin/jquery.flot.js"></script>
   <!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
    <script src="js/admin/jquery.flot.resize.js"></script>
    <!-- FLOT PIE PLUGIN - also used to draw donut charts -->
    <script src="js/admin/jquery.flot.pie.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="js/admin/demo.js"></script>
<!--     <script src="http://cdn.jsdelivr.net/jquery.flot/0.8.3/jquery.flot.min.js"></script> -->
    
    
    
    
    <!-- ChartJS 1.0.1 -->
    <script src="js/admin/Chart.js"></script>
     <!-- Sparkline -->
    <script src="js/admin/jquery.sparkline.js"></script>
    <!-- jvectormap -->
    <script src="js/admin/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="js/admin/jquery-jvectormap-world-mill-en.js"></script>
    <!-- SlimScroll 1.3.0 -->
    <script src="js/admin/jquery.slimscroll.js"></script>
<!--     <script src="js/admin/dashboard2.js"></script> -->
    
    
    
    
     <script type="text/javascript">
     $(function(){
    	 /*
          * DONUT CHART
          * -----------
          */

         var donutData = [
           {label: "Series2", data: 30, color: "#3c8dbc"},
           {label: "Series3", data: 20, color: "#0073b7"},
           {label: "Series4", data: 50, color: "#00c0ef"}
         ];
         $.plot("#donut-chart", donutData, {
           series: {
             pie: {
               show: true,
               radius: 1,
               innerRadius: 0.5,
               label: {
                 show: true,
                 radius: 2 / 3,
                 formatter: labelFormatter,
                 threshold: 0.1
               }

             }
           },
           legend: {
             show: false
           }
         });
         /*
          * END DONUT CHART
          */
     function labelFormatter(label, series) {
         return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
                 + label
                 + "<br>"
                 + Math.round(series.percent) + "%</div>";
       }
     });
     
     
     
     
     
     
     
     
     </script>    
  </body>
</html>