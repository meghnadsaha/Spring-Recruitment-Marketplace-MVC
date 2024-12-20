<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<title>UniHyr</title>
<link rel="stylesheet" href="css/fontshome.css" media="screen"   />
<link rel="stylesheet" href="css/media.css" media="screen" />
<link rel="stylesheet" href="css/style.css" media="screen" />

<script src="js/jquery.min.js"></script>
<script src="js/contact.js"></script>
<script src="js/common_js.js"></script>

<script src="js/jquery.bxSlider.js"></script>
		<script type="text/javascript">
  		$(document).ready(function(){
     		$('#slider').bxSlider({
    		ticker: true,
    		tickerSpeed: 5000,
			tickerHover: true
  		});
  		});
		</script>
<script type="text/javascript">
function validateForm(){
	var email=$('#email').val();
	var contact=$('#phone').val();
	
	var valid=true;
	if(email == "" || !isEmail(email))
	{
		$('#email').focus();
		$('#email').css("border-color","red");
		valid = false;
	}
	if(contact == "" || contact.length != 10)
	{
		$('#phone').focus();
		$('#phone').css("border-color","red");
		valid = false;
	}
	return valid;
}

function validateForm1(){
	var email=$('.email').val();
	var contact=$('.phone').val();
	
	var valid=true;
	if(email == "" || !isEmail(email))
	{
		$('.email').focus();
		$('.email').css("border-color","red");
		valid = false;
	}
	if(contact == "" || contact.length != 10)
	{
		$('.phone').focus();
		$('.phone').css("border-color","red");
		valid = false;
	}
	return valid;
}

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}

</script>
<style>
.firstTimeLoginPopup{
	    margin: 0px auto;
    top: 25%;
    left: 17%;
    position: fixed;
    height: 244px;
    width: 914px;
    background: #fff;
    z-index: 10;
}
.bodyCover{
	position: fixed;
	top: 0;
	left:0;
	height: 100%;
	width: 100%;
	z-index: 9;
	opacity:0.8;
	background: #ececec;
}
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

#slider1 {
	position: relative;
	overflow: hidden;
	margin: 20px auto 0 auto;
	border-radius: 4px;
}
#u_0_2{
color: #fff;
}
#u_0_3{
color: #fff;
}
#slider1 ul {
	position: relative;
	margin: 0;
	padding: 0;
	height: 650px
	list-style: none;
}

#slider1 ul li {
	position: relative;
	display: block;
	float: left;
	margin: 0;
	padding: 0;
	width: 1200px;
	height: 650px
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



#slider {
	list-style:none;
	padding:0px
}

.slider-container { 
	padding:10px; 
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px; 
}

#slider img { 
	margin:0px; 
	display:inline-block  
}

#slider li {
	width:210px
}
.bx-wrapper{
width: 1076px !important;
}
.bx-window{
width: 1076px !important;
}
</style>
<script>
// function ticker() {
//     $('#logoticker li:first').slideUp(function() {
//         $(this).appendTo($('#logoticker')).slideDown(300);
//     });
// }
	jQuery(document).ready(function($) {

// 		setInterval(function() {
// 			moveRight();
// 		}, 9000);

		
	//	setInterval(ticker, 3000);
		
		$('#slider1 ul li').css("width", $(window).width());
		$('#slider1 ul li').css("top", -5);

		var slideCount = $('#slider1 ul li').length;
		var slideWidth = $('#slider1 ul li').width();
		var slideHeight = $('#slider1 ul li').height();
		var sliderUlWidth = slideCount * slideWidth;

		$('#slider1').css({
			width : slideWidth,
			height : slideHeight
		});

		$('#slider1 ul').css({
			width : sliderUlWidth,
			marginLeft : -slideWidth
		});

		$('#slider1 ul li:last-child').prependTo('#slider1 ul');

		function moveLeft() {
			$('#slider1 ul').animate({
				left : +slideWidth
			}, 200, function() {
				$('#slider1 ul li:last-child').prependTo('#slider1 ul');
				$('#slider1 ul').css('left', '');
			});
		}
		;

		function moveRight() {
			$('#slider1 ul').animate({
				left : -slideWidth
			}, 700, function() {
				$('#slider1 ul li:first-child').appendTo('#slider1 ul');
				$('#slider1 ul').css('left', '');
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
.Headr_top{ 
/*     background-color: rgba(243, 156, 18, 0.81); */
}
.feature_sec{
background: #fff !important;
}
.footer{
background-color: rgba(9, 9, 9, 0.95);
}
</style>
</head>
<body style="background-image: url('images/banner_imgcopy.jpg');
background-attachment: fixed;
-webkit-background-size: cover;
background-size: cover;
height: 650px;
position: relative;
top: 0;
bottom: 0;
left: 0;
right: 0; ">
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.6";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
	<header id="header" style="
    position: fixed;">
		<div class="Headr_top " >
			<div class="container">
				<div class="logo">
					<a id="home" href="home"><img src="images/logo.png" alt="img" border="0"></a>
				</div>
				<div class="home-nav">
					<ul style="margin-bottom: 0px; margin-top: 27px;">
						<li  onclick="$('html, body').animate({ scrollTop: 540 }, 'slow');" ><a href="home#howitworks" style="text-decoration: none;" >Why UniHyr </a></li>
<!-- 						<li onclick="$('html, body').animate({ scrollTop: 890 }, 'slow');" ><a href="home#whyunihyr" style="text-decoration: none;">Why UniHyr</a></li> -->
<!-- 						<li onclick="$('html, body').animate({ scrollTop: 1000 }, 'slow');" ><a>Whats In It for</a></li> -->
<!-- 						<li onclick="$('html, body').animate({ scrollTop: 1940 }, 'slow');" ><a href="home#aboutus" style="text-decoration: none;">About</a></li> -->
						<!-- <li><a style="color: #f8b910;font-weight: bold;">How It Works </a></li>
					<li><a style="color: #f8b910;font-weight: bold;">Features</a></li>
					<li><a style="color: #f8b910;font-weight: bold;">Customers</a></li>
					<li><a style="color: #f8b910;font-weight: bold;">About</a></li> -->
					</ul>
				</div>
				<div class="Top_right Light13 Bold grey1 Link1">
					<a href="login" style="background: #000 !important;">LOGIN</a>
					<!--      <a href="login">Hiring Partner Sign In</a> -->
					<!-- <a href="login" style="color: #f8b910;font-weight: bold;">Employer Sign In</a>    |  
				 <a href="login" style="color: #f8b910;font-weight: bold;">Hiring Partner Sign In</a>  -->
					<!-- |  
				<a href="clientregistration">Employer Sign Up</a>    |   <a href="consultantregistration">Hiring Partner Sign Up</a> -->
				</div>
			</div>
		</div>
	</header>
	<section class="Middle" >
		


		<div style="margin-top: 0px;height: 650px;  background-color: rgba(96, 89, 77, 0.42);">
			
			<ul>
				<li><div class="Slde_cent" >
						<div class="container">
							<div class="grd">
								<div class="bx">
									<h2 style="font-size: 38px;color: #f8b910;margin-bottom: 15px;">Hiring Simplified | Accelerated | Improved</h2>
									<p style="font-size: 22px;">UniHyr is an analytics driven online recruitment marketplace that connects employers to a large network of hiring partners resulting in faster, cost effective hiring of quality talent</p>
								</div>
							</div>
						</div>
					</div>
					</li>
				

	

			</ul>
		</div>

		<div class="Scrlbr">
			<div class="container" style="margin: 0px auto;">
				<form:form action="requestfordemo" method="post"  commandName="contactusform" onsubmit="return validateForm()">
					<form:select path="usertype" class="contactUsForm" >
						<option value="emp">I am Employer</option>
						<option value="cons">I am Consultant</option>
					</form:select>
					<form:input required="required" path="name"   class="contactUsForm" placeholder="Name*" />
					<form:input required="required" path="email"  class="contactUsForm" placeholder="Email*" />
					<form:input required="required" path="company"  class="contactUsForm" placeholder="Company*" />
					<form:input required="required"  path="phone" maxlength="10" minlength="10" class="contactUsForm number_only" placeholder="Phone Number*" /> 
					
					<input   class="contactUsForm" type="submit" value="Request for demo" style="background: black;color : white;border: 1px solid black;" />
				
				</form:form>
			</div>
		</div>
		<!-- <div class="Hwrk">
		<div class="container">
			<div class="title_rw">
			<h2 >How does it all work?</h2></div>
			<div class="Video rw Align_cent"><img src="images/video_img.jpg" alt="img"></div>
		</div>
	</div> -->
<div class="feature_sec">
			<div class="container">
				<div class="title_rw"><p>&nbsp;</p><p>&nbsp;</p>
					<h2 id="howitworks" >Why UniHyr</h2>
				</div>

				<div class="howflow rw">
					<ul>
<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i3.png"
								alt="img"></a>
							<h4>Super Quick</h4>
							<p>With a large network of high quality consultants working on the position in a competitive environment, the employers start receiving relevant candidate profiles in a disruptively shorter time</p></li>
						
							<li></li>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i4.png"
								alt="img"></a>
							<h4>Powerful</h4>
							<p>UniHyr's analytics driven rating system dynamically tracks performance of hiring partners as they work on various positions on the platform. Visibility of profiles from top rated partners would be higher at the employers end so that they get quality candidates in short time.</p> </li>
						<li></li>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i6.png"
								alt="img"></a>
							<h4>Cost Effective</h4>
							<p>Platform provides the flexibility to the consultants to dynamically offer lower rates if they want to improve the visibility of the profiles at the employers end</p></li>
					<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i2.png"
								alt="img"></a>
							<h4>Simple to Use</h4>
							<p>The platform has been designed by industry experts keeping in mind the key activities performed by the employers and consultants during a typical hiring process. User interface is intuitive and easy to use.</p> </li>
					
					</ul>
				</div>
			</div>
		</div>
		
	
		<div class="feature_sec">
			<div class="container">
				<div class="title_rw">
					<h2>WHAT'S IN IT FOR...</h2>
				</div>

				<div class="featserv rw"  style="margin-bottom: 50px;">
					<ul>
						<li>
							<div class="normalulli" >
								<img style="width: 100%" alt="" src="images/oway.png" />
							</div>  
						</li>
						<li>
							<div class="normalulli" >
								<img style="width: 100%" alt="" src="images/nway.png" />
							</div>  						
						</li>

						


					</ul>
				</div></div></div>
		
		<div class="feature_sec">
			<div class="container">
				<div class="title_rw">
					<h2>WHAT'S IN IT FOR...</h2>
				</div>

				<div class="featserv rw"  style="margin-bottom: 50px;">
					<ul>
						<li>

							<h3 style="font-weight: bold; margin-bottom: 25px;">Employers</h3>
							<div class="normalulli">
								<ul>
									
								<li>
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/e1.png">
								</div>
								<div style="float: left;width: 80%;">
								
									<h4>GET ALL UNDER ONE ROOF</h4>
										<p>One stop shop to engage with rated recruitment consulting/search firms without getting into the hassle for managing multiple touch points or having to attend numerous emails/calls</p>
													</div>
													</li>
													
									<li>
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/e3.png">
								</div>
								<div style="float: left;width: 80%;"><h4>REDUCED COST PER HIRE</h4>
										<p>Competitive range bound bidding on recruitment fee by consultants. Employers just need to agree on the maximum rate for the positions</p>
										</div>
										</li>
									<li>
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/e2.png">
								</div>
								<div style="float: left;width: 80%;"><h4>UNIQUE RATING SYSTEM TO ENSURE YOU KNOW WHO IS THE BEST</h4>
										<p>Unique rating system creates a competitive environment, each consultant strives to get the best within the quickest possible time.</p>
										</div>
										</li>

									<li><div style="float: left;margin-right: 10px">
									<img alt="" src="images/e4.png">
								</div>
								<div style="float: left;width: 80%;"><h4>FREE TO USE a€“ PAY ONLY ON OUTCOME</h4>
										<p>No signing up or usage charges. Payment to be made only on successful closure of the position</p>
										</div>
										</li>
								</ul>
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>
						<li>

							
							<div class="normalulli" >
								<img style="width: 100%" alt="" src="images/managepost.png" />
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>

						<!-- <li>
						
						<h3>Hiring Partners</h3>
						<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo invtatis et quasi architecto beatae.</p> 
						<a href="" class="readmore">Read More</a>
					</li>  -->



					</ul>
				</div>
				<div class="featserv rw">
					<ul>
						
						<li>

							
							<div class="normalulli" >
								<img alt="" src="images/managepost1.png"  style="width: 100%;"/>
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>

					<li>

							<h3 style="font-weight: bold; margin-bottom: 25px;">Hiring
								Partners</h3>
							<div class="normalulli" >
								<ul>
									<li style="margin-bottom: 5px;">
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/c1.png">
								</div>
								<div style="float: left;width: 80%;"><h4>MORE BUSINESS!!</h4>
										<p>Significant increase in business - get to work for new clients without having to spend time & effort in
													acquiring them</p></div></li>
									<li>
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/c2.png">
								</div>
								<div style="float: left;width: 80%;"><h4>TRANSPARENT, PERFORMNCE DRIVEN</h4>
										<p>Performance rating rewards good performance and leads to transparency</p></div></li>
									<li>
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/e3.png">
								</div>
								<div style="float: left;width: 80%;"><h4>SIMPLE TO USE AND HASSLE FREE</h4>
										<p>Easy to use interface that lets you work on multiple client requirements at the same time</p></div></li>
									<li>
									<div style="float: left;margin-right: 10px">
									<img alt="" src="images/e4.png">
								</div>
								<div style="float: left;width: 80%;"><h4>FREE TO USE a PAY ONLY ON OUTCOME</h4>
										<p>No signing up or usage charges. A small commission to be charged by the platform on the revenue you earn on the platform.</p></div></li>
								</ul>
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>


					</ul>
				</div>
			</div>
		</div>
		<!-- <div class="feature_sec">
		<div class="container">
			<div class="title_rw"><h2 id="aboutus"  >About Us</h2></div>
		
			<div class="featserv rw" style="text-align: left;">
				
				<p style="font-size: 12px;padding: 20px;" class="Lineheight1">Hiring
						continues to be one of the single biggest challenge impacting
						growth of the modern day businesses. UniHyr is an innovative
						solution that brings together several potential employers and a
						large number of hiring partners on a single platform so that they
						can work together to meet the talent acquisition goals effectively
						in a breathtakingly quick time. The unique partner rating system
						ensures that the partner compete on transparent terms and the
						competition benefits the employer as they get high quality and
						relevant profiles.</p>	
			</div>
		</div>
	</div>  -->
		<div  class="feature_sec">
			<div class="container">
				<div class="title_rw">
					<h2 id="howitworks">Our Clients</h2>
				</div>
						<ul style="width: 999999px; position: relative; left: -1371.16px;"
							id="slider">
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
<!-- 							<li class="pager" -->
<!-- 								style="width: 210px; float: left; list-style: outside none none;"><a -->
<!-- 								href="#"><img src="images/howitworks6.png"></a></li> -->
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks7.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
<!-- 							<li class="pager" -->
<!-- 								style="width: 210px; float: left; list-style: outside none none;"><a -->
<!-- 								href="#"><img src="images/howitworks6.png"></a></li> -->
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks7.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
<!-- 							<li class="pager" -->
<!-- 								style="width: 210px; float: left; list-style: outside none none;"><a -->
<!-- 								href="#"><img src="images/howitworks6.png"></a></li> -->
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks7.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
<!-- 							<li class="pager" -->
<!-- 								style="width: 210px; float: left; list-style: outside none none;"><a -->
<!-- 								href="#"><img src="images/howitworks6.png"></a></li> -->
							<li class="pager"
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks7.png"></a></li>
							
						</ul>
					</div>
				</div>
<!-- <div class="feature_sec">
		<div class="container">
			<div class="title_rw"><h2 >Featured Employers</h2></div>
			<div class="featserv rw">
				<ul>
					<li>
						<a href="" class="thumbnl"><img src="images/img1.jpg" alt="img"></a> 
						<h3>Jenny Deo</h3>
						<h4>Loremipsum</h4>
						<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo invtatis et quasi architecto beatae.</p> 
						<a href="" class="readmore">Read More</a>
					</li> 
					<li>
						<a href="" class="thumbnl"><img src="images/img2.jpg" alt="img"></a> 
						<h3>John Smith</h3>
						<h4>Loremipsum</h4>
						<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo invtatis et quasi architecto beatae.</p> 
						<a href="" class="readmore">Read More</a>
					</li> 
					<li>
						<a href="" class="thumbnl"><img src="images/img3.jpg" alt="img"></a> 
						<h3> Mark COLLIS</h3>
						<h4>Loremipsum</h4>
						<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo invtatis et quasi architecto beatae.</p> 
						<a href="" class="readmore">Read More</a>
					</li>
				</ul>
			</div>
		</div>
	</div> 
 -->
		<!-- <div class="Comnt">
		<div class="container">
			<div class="title_rw"><h2>Success Stories</h2></div>
			<div class="comnt_sec">
				<p class="Black font14 opnslight Italic Lineheight2"><img src="images/ic_1.png" align="texttop" alt="img"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. <img src="images/ic_2.png" align="absbottom" alt="img"> </p>
			</div>
		</div>
	</div>
	 -->

	</section>

	<footer class="footer rw">
		<div class="footer_top Light12 grey2">
			<div class="container">
				<!--<div class="col_1">
					<h3></h3>
					
 					<div style="margin-left: 22px; color: #fff !important;" class="fb-like" data-href="https://www.facebook.com/UniHyr-491301114398011/" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
				</div>
 -->
				 <div class="col_2" style="text-align: center;margin-left: 0px;">
					<h3>Contact Us</h3>

					<div class="news rw">
						<ul style="font-size: 12px;">
							<li>
					<form:form action="requestfordemo" method="post"  commandName="contactusform" onsubmit="return validateForm1()">
					<form:select  class="col_4 contactfooter" path="usertype">
				<form:option value="emp">I am Employer</form:option>
				<form:option value="cons">I am Consultant</form:option>
				</form:select>
				<br>
				<form:input required="required" path="name" type="text" class="col_4 contactfooter" placeholder="Name*" />
				<form:input required="required" path="email"  type="text" class="email col_4 contactfooter" placeholder="Email*" />
				<form:input required="required" path="company" type="text" class="col_4 contactfooter" placeholder="Company*" />
				<form:input required="required" path="phone" type="text" class="phone col_4 contactfooter  number_only" placeholder="Phone Number*" /> 	
				
				<br>
				<input type="submit"   class="col_4 contactfooter" value="Submit Request" style="margin-bottom: 15px;margin-left:-1px;
					margin-right: 0px !important;
					background: black none repeat scroll 0% 0%;
					color: white;
					overflow: hidden;
					font-size: 14px;border: 1px solid black;" />
				
				</form:form>
								
							</li>
						</ul>
					</div>
				</div> 
				<div class="col_3">
					
					<div class="rw m_b_10 Light12 grey2" style="font-size: 11px;">
						<!-- <div class="adrs_b_1 Lineheight1">
							Unit 5/62 -64 West Ave<br /> Edinburgh Parks SA 5111<br /> PO
							Box 3023<br /> Elizabeth East SA 5112
						</div> -->

						<div class="adrs_b_2"><h3>Reach Us</h3>
							<p>
								<img src="images/ic_3.jpg" alt="img"> 92 8283 3477
							</p>
							<p>
								<img src="images/ic_4.jpg" alt="img"> 011 283 3577
							</p>
							<p>
								<img src="images/ic_5.jpg" alt="img"> unihyr@gmail.com
							</p>
							
					</div>
				</div>
			</div>
		</div>
		<div class="footer_botm Align_cent Light12 grey3">
			<div class="container" style="background-color: #1f1e1e;font-size: 12px;width: 400px;">
				<div style="float: left;line-height: 44px;">
				<a href="termsOfService">Terms of Use</a> | <a href="privacyPolicy">Privacy Policy</a> 
				
			<!-- 			|	<a	href="">Sitemap</a> | <a href="">Work with Us</a> -->
			</div>
			<div style="float: left;line-height:52px;">
						<a  style="margin:8px;" href="https://www.facebook.com/UniHyr-491301114398011/" target="_blank"> 
						<img style="width:8px;" src="images/fb.png" title="facebook" /></a> 
						<a  style="margin:8px;"  href="https://twitter.com/unihyr" target="_blank"> 
						<img style="width:15px;"  src="images/twitter.png" title="twitter" /></a> 
						<a  style="margin:8px;"  target="_blank" href="https://in.linkedin.com/in/unihyr-admin-5aab60122"> 
						<img style="width:14px;"  src="images/linkedin.png" title="linkedin" /></a>
			</div>
			</div> 
			</div>
	</footer>
	<div
		style=" cursor: pointer;
		position:fixed;bottom:10px;right: 10px;z-index:100;"
		id="movetotop"><a href="#home">
<img style="height: 40px;" alt="Top" src="images/movetotop.png"></a>
</div>

	<script type="text/javascript" src="js/script.js"></script>

<script type="text/javascript">
$("#movetotop").click(function() {
	  $("html, body").animate({ scrollTop: 0 }, "slow");
	  return false;
	});

</script>
<div class="bodyCoverWait" style="display: none;text-align: center; ">
<img style="position: relative;top: 250px;" alt="Please wait..." src="images/pwait.gif" />
</div>

<div class="firstTimeLoginPopup profileClosed1" style="top:35%;border:1px solid #f8b910;display: none;height: auto;position: fixed;max-height: 300px;" >
				<div class="login-header" style="padding: 3px 3px 3px 2px;
line-height: 30px;
height: 72px;">
					<a href="index">
					<img alt="" src="images/logo.png" style="margin-left: 48px;">
					</a>
					<a href="javascript:void(0)"><span style="padding: 0px 9px;
    top: -16px;" class="close" title="Close" onclick="$('.bodyCover').css('display','none');$('.firstTimeLoginPopup').css('display','none');"><img style="height: 40px;" src="images/close.png" /></span></a>
				</div>
			
				
				
				
				<div class="login-wrap" style="padding: 10px;" id="profileClosed">
				
				</div>
			</div>

<div class="bodyCover profileClosed1" style="display: none;position: fixed;">

</div>
<script type="text/javascript">

function getClosedCandidates(postId){
	pleaseWait();
	$.ajax({
		type : "GET",
		url : "profileClosures",
		data : {'postId':postId},
		contentType : "application/json",
		success : function(data) {
			pleaseDontWait();
			$('#profileClosed').html(data);
			$('.profileClosed1').css('display','block');
			$('.profileClosed1').css('display','block');
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;

}

function pleaseWait(){

	$('.bodyCoverWait').css('display','block');
}
function pleaseDontWait(){

	$('.bodyCoverWait').css('display','none');
}
</script>
<% 
String message="";
System.out.println(request.getParameter("message"));
if(request.getParameter("message")!=null){
	message=request.getParameter("message");
	message="<div style='text-align:center;font-size:12px;'>"+message+"<div>";
%>
<script type="text/javascript">
			$('#profileClosed').html("<%=message%>");
			$('.profileClosed1').css('display','block');
			$('.profileClosed1').css('display','block');
			</script>
<%
}
%>



</body>
</html>