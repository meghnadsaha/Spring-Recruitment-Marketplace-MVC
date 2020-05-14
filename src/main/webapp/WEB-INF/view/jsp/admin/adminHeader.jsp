<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
	<!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="css/admin/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="css/admin/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="css/admin/AdminLTE.min.css">
    <link rel="stylesheet" href="css/admin/skin-blue.min.css">
    <link rel="stylesheet" href="css/alertify.core.css" />
	<link rel="stylesheet" href="css/alertify.default.css" id="toggleCSS" />
    <script src="js/admin/jQuery-2.1.4.min.js"></script>
    <script src="js/admin.js"></script>
    <script src="js/common_js.js"></script>
<script src="js/alertify.min.js"></script>
<style type="text/css">

.bodyCoverWait{
	position: fixed;
	top: 0;
	left:0;
	height: 100%;
	width: 100%;
	z-index: 9;
	opacity:0.8;
	background: #ececec;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      <!-- Main Header -->
      <header class="main-header">
        <!-- Logo -->
        <a href="admindashboard" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img alt="Unihyr" src="images/logo-mini.png"> </span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img alt="Unihyr" src="images/logo-md.png"> </span>
        </a>
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account Menu -->
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
<!--                   <img src="images/logo.jpg" class="user-image" alt="User Image"> -->
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">UniHyr Admin</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  <li class="user-header">
                    <img src="images/logo.jpg" class="img-circle" alt="User Image">
                    <p>
                      Unihyr Admin
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      
  
  </body>
</html>