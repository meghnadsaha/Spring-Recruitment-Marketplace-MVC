<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<title>UniHyr</title>

	<link rel="stylesheet" href="css/fonts.css" media="screen"   />
<link rel="stylesheet" href="css/media.css" media="screen" />
<link rel="stylesheet" href="css/style.css" media="screen" />

<script src="js/jquery.min.js"></script>
<style>
#slider {
	position: relative;
	overflow: hidden;
	margin: 20px auto 0 auto;
	border-radius: 4px;
}

#slider ul {
	position: relative;
	margin: 0;
	padding: 0;
	height: 518px;
	list-style: none;
}

#slider ul li {
	position: relative;
	display: block;
	float: left;
	margin: 0;
	padding: 0;
	width: 1200px;
	height: 518px;
	background: #ccc;
	text-align: center;
	/* line-height: 300px; */
}

a.control_prev, a.control_next {
	position: absolute;
	top: 40%;
	z-index: 999;
	display: block;
	padding: 4% 3%;
	width: auto;
	height: auto;
	/* background: #2a2a2a; */
	color: #fff;
	text-decoration: none;
	font-weight: 600;
	font-size: 18px;
	opacity: 0.8;
	cursor: pointer;
}

a.control_prev:hover, a.control_next:hover {
	opacity: 1;
	-webkit-transition: all 0.2s ease;
}

a.control_prev {
	border-radius: 0 2px 2px 0;
}

a.control_next {
	right: 0;
	border-radius: 2px 0 0 2px;
}

.slider_option {
	position: relative;
	margin: 10px auto;
	width: 160px;
	font-size: 18px;
}

.slde_m_img img {
	height: 100%;
	width: 100%;
}
</style>
<script>
	jQuery(document).ready(function($) {

		setInterval(function() {
			moveRight();
		}, 9000);

		$('#slider ul li').css("width", $(window).width());
		$('#slider ul li').css("top", -5);

		var slideCount = $('#slider ul li').length;
		var slideWidth = $('#slider ul li').width();
		var slideHeight = $('#slider ul li').height();
		var sliderUlWidth = slideCount * slideWidth;

		$('#slider').css({
			width : slideWidth,
			height : slideHeight
		});

		$('#slider ul').css({
			width : sliderUlWidth,
			marginLeft : -slideWidth
		});

		$('#slider ul li:last-child').prependTo('#slider ul');

		function moveLeft() {
			$('#slider ul').animate({
				left : +slideWidth
			}, 200, function() {
				$('#slider ul li:last-child').prependTo('#slider ul');
				$('#slider ul').css('left', '');
			});
		}
		;

		function moveRight() {
			$('#slider ul').animate({
				left : -slideWidth
			}, 700, function() {
				$('#slider ul li:first-child').appendTo('#slider ul');
				$('#slider ul').css('left', '');
			});
		}
		;

		$('a.control_prev').click(function() {
			moveLeft();
		});

		$('a.control_next').click(function() {
			moveRight();
		});

	});
</script>
<style type="text/css">
.home-nav ul {
	float: left;
}

.home-nav ul li {
	color: #100f0f;
	float: left;
	font-size: 12px;
	margin: 0 5px;
	list-style: outside none none;
}

.home-nav ul li a {
	color: #000;
	display: inline-block;
	font-size: 14px;
	margin-left: 1px;
	padding: 7px 10px 6px;
	text-align: center;
	cursor: pointer;
}

.home-nav ul li a:hover {
	border-bottom: 5px solid #F8B910;
}
</style>




</head>
<body>

	<header id="header" style="">
		<div class="Headr_top ">
			<div class="container">
				<div class="logo">
					<a href="home"><img src="images/logo.png" alt="img" border="0"></a>
				</div>
				<div class="home-nav">
					<ul style="margin-bottom: 0px; margin-top: 27px;">
						<li ><a href="home#howitworks" style="text-decoration: none;" >How It Works </a></li>
<!-- 						<li  ><a href="home#whyunihyr" style="text-decoration: none;">Why UniHyr</a></li> -->
<!-- 						<li onclick="$('html, body').animate({ scrollTop: 1000 }, 'slow');" ><a>Whats In It for</a></li> -->
						<li  ><a href="home#aboutus" style="text-decoration: none;">About</a></li>
						<!-- <li><a style="color: #f8b910;font-weight: bold;">How It Works </a></li>
					<li><a style="color: #f8b910;font-weight: bold;">Features</a></li>
					<li><a style="color: #f8b910;font-weight: bold;">Customers</a></li>
					<li><a style="color: #f8b910;font-weight: bold;">About</a></li> -->
					</ul>
				</div>
<!-- 				<div class="Top_right Light13 Bold grey1 Link1"> -->
<!-- 					<a href="login" style="background: #000 !important;">LOGIN</a> -->
					<!--      <a href="login">Hiring Partner Sign In</a> -->
					<!-- <a href="login" style="color: #f8b910;font-weight: bold;">Employer Sign In</a>    |  
				 <a href="login" style="color: #f8b910;font-weight: bold;">Hiring Partner Sign In</a>  -->
					<!-- |  
				<a href="clientregistration">Employer Sign Up</a>    |   <a href="consultantregistration">Hiring Partner Sign Up</a> -->
<!-- 				</div> -->
			</div>
		</div>
	</header>