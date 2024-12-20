<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
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
	var valid=true;
	if(email == "" || !isEmail(email))
	{
		$('#email').focus();
		valid = false;
	}
	return valid;
}
</script>
<style>
.Slde_cent .grd .bx h2{
font-size: 60px;
color: #fff;
text-transform: none;
font-weight: normal;

}
.Slde_cent .grd {
    width: 100%;
    margin: 0 auto;
}
.container {
    width: 100%;
    margin-left: auto;
    margin-right: auto;
    background-color: #fff;
    }
    .Slde_cent .grd .bx p {
    color: #fff;
    font-size: 29px;
}
.title_rw h2 {
    color: #000000;
    background: url(../images/tle_botm.jpg) no-repeat center bottom;
    text-transform: none;
    padding-bottom: 10px;
    font-size: 45px;
}
.home-nav ul li a {
    color: #000;
    display: inline-block;
    font-size: 19px !important;
    margin-left: 1px;
    padding: 7px 10px 6px;
    text-align: center;
    cursor: pointer;
    }
.Top_right {
    width: auto;
    float: right;
    margin-top: 26px;
    margin-right: 10px;
}
.Scrlbr {
    width: 100%;
    float: left;
    background: rgba(0,0,0,.4);
    position: absolute;
    margin-top: -106px;
    height: 100px;
    padding-top: 24px;
    }

.howflow ul li h4 {
    font-size: 21px;
    color: #000;
    text-transform: none;
    margin-bottom: 6px;
    font-weight: bold;

}
 .howflow ul li p {
    font-size: 19px;
    color: #000000;
    line-height: 30px;
    margin-bottom: 10px;
}

.featserv > ul {
    font-size: 19px;
    line-height: 30px;
}
.featserv ul li h3 {
    font-size: 31px;
    color: #000;
    }
.normalulli{

    font-size:19px !important;
}
.featserv ul li p {
    font-size: 19px;
    color: #000000;
    line-height: 30px;
    margin-bottom: 10px;
}
.featserv ul li h4 {
    font-size: 22px;
    color: #777;
    text-transform: none;
    margin-bottom: 19px;
    font-weight: bold;
}
.Lineheight1 {
    line-height: 30px;
    font-size: 19px;
}

#contactusform{
    margin-left: 28px;
}
.Slde_cent {
    width: 100%;
    position: absolute;
    left: 0;
    top: 32%;
    z-index: 6;
}
.slde_m_img img {
    height: 100%;
    width: 100%;
    opacity: 0.45;
}
h3{
font-size: 30px !important;
}
.feature_sec {
    width: 100%;
    float: left;
    padding: 35px 0px;
    background: #fff;
}





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
	height: 715px;
	list-style: none;
}

#slider1 ul li {
	position: relative;
	display: block;
	float: left;
	margin: 0;
	padding: 0;
	width: 1200px;
	height: 715px;
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
width: 1396px !important;
}
.bx-window{
width: 1396px !important;
}
</style>
<script>
// function ticker() {
//     $('#logoticker li:first').slideUp(function() {
//         $(this).appendTo($('#logoticker')).slideDown(300);
//     });
// }
	jQuery(document).ready(function($) {

		setInterval(function() {
			moveRight();
		}, 9000);

		
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
</style>
</head>
<body>
<img alt="" src="images/home_p.jpg" style="position: fixed;top:40px;left:0px;height: 100%;width: 100%;z-index: -7;" >
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.6";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
	<header id="header" style="
    position: fixed;
    z-index: 9;">
		<div class="Headr_top ">
			<div class="container">
				<div class="logo">
					<a id="home" href="home"><img style="margin-left: 58px;" src="images/logo.png" alt="img" border="0"></a>
				</div>
				<div class="home-nav">
					<ul style="margin-bottom: 0px; margin-top: 27px;">
						<li  onclick="$('html, body').animate({ scrollTop: 540 }, 'slow');" ><a href="home#howitworks" style="text-decoration: none;" >How It Works </a></li>
						<li onclick="$('html, body').animate({ scrollTop: 890 }, 'slow');" ><a href="home#whyunihyr" style="text-decoration: none;">Why UniHyr</a></li>
<!-- 						<li onclick="$('html, body').animate({ scrollTop: 1000 }, 'slow');" ><a>Whats In It for</a></li> -->
						<li onclick="$('html, body').animate({ scrollTop: 1940 }, 'slow');" ><a href="home#aboutus" style="text-decoration: none;">About</a></li>
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
	<section class="Middle" style="margin-top: 76px;background: #fffff;">
		<!-- <div class="Slider">
		<div class="prev"><a href=""><img src="images/arw1.png" border="0" alt="img"></a></div> 
		<div class="next"><a href=""><img src="images/arw2.png" border="0" alt="img"></a></div>
		<div class="slde_m_img rw"><img src="images/banner_img.jpg" alt="img"></div>
		<div class="Slde_cent"> 
			<div class="container">
				<div class="grd">
					<div class="bx">
							<h3>Hiring Simplified | Accelerated | Improved</h3>
							<p> UniHyr is a whole new
							and exciting approach to hiring. Connecting reputed organizations
							with varied hiring requirements to an elaborate network of
							recruitment advisors that form part of our network. Helping
							hundreds of recruiters hire quality talent in an amazingly short
							time.</p>
						</div>
				</div>
			</div>
		</div>
	</div> -->



		<div id="slider1" style="margin-top: 0px;">
			<a href="javascript:void(0)" class="control_next"><img
				src="images/arw2.png" border="0" alt="img" style="height: 15px;width: 5px;"></a> <a
				href="javascript:void(0)" class="control_prev"><img
				src="images/arw1.png" border="0" alt="img" style="height: 15px;width: 5px;"></a>
			<ul>
				<li><div class="Slde_cent">
						<div class="container">
							<div class="grd">
								<div class="bx">
									<h2>Hiring Simplified | Accelerated | Improved</h2>
									<p>UniHyr is an online marketplace connecting reputed
										organizations with varied hiring requirements to an elaborate
										network of recruitment advisors that form part of our network</p>
								</div>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 100%;" class="slde_m_img rw">
						<img src="images/banner_img.jpg" alt="img">
					</div></li>
				<li><div class="Slde_cent">
						<div class="container">
							<div class="grd">
								<div class="bx">


									<h2>Get the right talent in super quick time!</h2>
									<p>We know that when it comes to hiring &ndash; quality and time
										are the most critical factors. UniHyr&lsquo;s partner network
										provides a deeper penetration in the available talent pool and
										ensures that you get quality profiles in a disruptively
										shorter time</p>
								</div>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 100%;" class="slde_m_img rw">
						<img src="images/image2.png" alt="img">
					</div></li>
				<li><div class="Slde_cent">
						<div class="container">
							<div class="grd">
								<div class="bx">
									<h2>Simple, neat, and intuitive interface for a seamless
										user experience</h2>
									<p>Our user interface is simple, intuitive and powerful to
										facilitate publishing of new requirements and receiving
										profiles on the same</p>
								</div>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 100%;" class="slde_m_img rw">
						<img src="images/image3.png" alt="img">
					</div></li>
				<li><div class="Slde_cent">
						<div class="container">
							<div class="grd">
								<div class="bx">
									<h2>Leverage the power of our competitive partner network</h2>
									<p>UniHyr's unique rating system dynamically tracks
										performance of hiring partners and displays the profiles based
										on their ratings so that you can identify the best candidates
										quickly</p>
								</div>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: 100%;" class="slde_m_img rw">
						<img src="images/image4.png" alt="img">
					</div></li>

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
					<form:input required="required"  path="phone"  class="contactUsForm number_only" placeholder="Phone Number*" /> 
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
				<div class="title_rw">
					<h2 id="howitworks" >How it works</h2>
				</div>

				<div class="howflow rw">
					<ul>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i5.png"
								alt="img"></a>
							<h4>Post your Job</h4>
							<p>Employers provide a detailed description of the
								requirements that would be made visible to the hiring partners on
								the platform</p> </li>
						<li></li>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i1.png"
								alt="img"></a>
							<h4>Receive Profiles</h4>
							<p>Based on ratings multiple hiring partners would conduct
								search and submit profiles on the platform</p> </li>

						<li></li>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i2.png"
								alt="img"></a>
							<h4>Shortlist & Interview</h4>
							<p>View profiles submitted by top partners based on their
								ratings</p> </li>
						<li></li>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i4.png"
								alt="img"></a>
							<h4>Offer</h4>
							<p>Profiles would move through various stages on the platform
								from In Process > Offered > Joined</p> </li>
						<li></li>
						<li><a href="javascript:void(0)" class="thumbnl"><img src="images/i3.png"
								alt="img"></a>
							<h4>Track Billing</h4>
							<p>On joining, automatic invoice generation and payment
								tracking as per the terms</p></li>
					</ul>
				</div>
			</div>
		</div>
		
		
		<div class="feature_sec">
		<div class="container">
			<div class="title_rw"><h2  id="whyunihyr" >Why UniHyr</h2></div>
		
			<div class="featserv rw">
				<ul>
					<li style="width: 42%;">
						<img style="height: 250px;"  src="images/whyuni1.jpg" alt="img">
					</li> 
				<li style="width: 10%;"><img style="margin-top:60px;height:50px;" src="images/arrow1.png" /></li>
					<li style="width: 42%;float: right;">
						<img style="height: 250px;"   src="images/whyuni2.jpg" alt="img">
					
						</li> 
				
				</ul>
			</div>
			
		
		</div>
	</div> 
	
		<div class="feature_sec" style="margin-top: -40px;">
		<div class="container">
			
			<div class="featserv rw">
				
				<ul >
					<li style="width: 42%;float: left;">
					<ul style="text-align: left;list-style-type: disc;">
			<li>
					
					
Multiple consultants leading to multiplicity of contracts and touch points that are difficult to manage</li>
<li>

Employers get limited to a select few consultants who may or may not be the best consultants for the skill they want to hire for</li>
<li>

Inefficient interaction over emails  leading to duplicity of work and time spent in non-value adding activities
</li>
<li>
No rigorous mechanism of driving quality from the consultants
</li>
</ul>
					
						</li> 
				<li style="width: 10%;"></li>
					<li style="width: 42%;float: right;">
						<ul style="text-align: left;list-style-type: disc;">
		<li>	
Single window to post new requirements and manage profiles received from consultants</li>
<li>
Incredibly deeper access to the talent pool through the partner network to accelerate quality hiring</li>
<li>
Easy to use interface for both employers and partners where they can exchange profiles, messages and track status
</li>
<li>

Smart features like duplicity check, profile limits, bill tracking etc.
</li>
<li>
Analytics driven rating system of consultants
</li>
</ul>
						</li> 
				
				</ul>
				
			</div>
		
		
		</div>
	</div> 
		<div class="feature_sec" style="background: transparent none repeat scroll 0% 0%;color: white;">
			<div class="container">
				<div class="title_rw">
					<h2 style="color: white;">What's In It For...</h2>
				</div>

				<div class="featserv rw">
					<ul style="color: white;">
						<li>

							<h3 style="margin-bottom: 25px;">Employers</h3>
							<div class="normalulli">
								<ul style="color: white !important;">
									<li><h4 style="color: white !important;">GET ALL UNDER ONE ROOF</h4>
										<p style="color: white !important;">One stop shop to engage with rated recruitment consulting/search firms without getting into the
													hassle for managing them</p></li>
									<li><h4 style="color: white !important;">UNIQUE RATING SYSTEM TO ENSURE YOU KNOW WHO IS THE BEST</h4>
										<p style="color: white !important;">Profiles from hiring partners with better ratings are shown first so that you get the best</p></li>

									<li><h4 style="color: white !important;">HASSLE FREE AND TRANSPARENT</h4>
										<p style="color: white !important;">No hassle of managing multiple recruitment agency contracts – clients protected by a single contract with
										UniHyr outlining simple, transparent, and universal terms and conditions</p></li>
									<li><h4 style="color: white !important;">FREE TO USE – PAY ONLY ON OUTCOME</h4>
										<p style="color: white !important;">No signing up or usage charges. Payment to be made only on the hiring outcome</p></li>
								</ul>
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>
						<li>

							<h3 style="margin-bottom: 25px;">Hiring
								Partners</h3>
							<div class="normalulli" style="border-left: 1px solid #f1b910;">
								<ul  style="color: white !important;">
									<li><h4 style="color: white !important;">MORE BUSINESS!!</h4>
										<p style="color: white !important;">Significant increase in business - get to work for new clients without having to spend time & effort in
													acquiring them</p></li>
									<li><h4 style="color: white !important;">TRANSPARENT, PERFORMNCE DRIVEN</h4>
										<p style="color: white !important;">Performance rating rewards good performance and leads to transparency</p></li>
									<li><h4 style="color: white !important;">SIMPLE TO USE AND HASSLE FREE</h4>
										<p style="color: white !important;">Easy to use interface that lets you work on multiple client requirements at the same time</p></li>
									<li><h4 style="color: white !important;">FREE TO USE – PAY ONLY ON OUTCOME</h4>
										<p style="color: white !important;">No signing up or usage charges. Payment to be made only on the hiring outcome</p></li>
								</ul>
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>

						<!-- <li>
						
						<h3>Hiring Partners</h3>
						<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo invtatis et quasi architecto beatae.</p> 
						<a href="" class="readmore">Read More</a>
					</li>  -->



					</ul>
				</div>
			</div>
		</div>
		<div class="feature_sec">
		<div class="container">
			<div class="title_rw"><h2 id="aboutus"  >About Us</h2></div>
		
			<div class="featserv rw" style="text-align: left;">
				
				<p style="padding: 20px;" class="Lineheight1">Hiring
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
	</div> 
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
							<li class="pager"
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
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li 
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li 
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li 
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li 
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks1.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks2.png"></a></li>
							<li
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks3.png"></a></li>
							<li 
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks4.png"></a></li>
							<li 
								style="width: 210px; float: left; list-style: outside none none;"><a
								href="#"><img src="images/howitworks5.png"></a></li>
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
					<form:form action="requestfordemo" method="post"  commandName="contactusform">
					<form:select  class="col_4 contactfooter" path="usertype">
				<form:option value="emp">I am Employer</form:option>
				<form:option value="cons">I am Consultant</form:option>
				</form:select>
				<br>
				<form:input required="required" path="name" type="text" class="col_4 contactfooter" placeholder="Name*" />
				<form:input required="required" path="email"  type="text" class="col_4 contactfooter" placeholder="Email*" />
				<form:input required="required" path="company" type="text" class="col_4 contactfooter" placeholder="Company*" />
				<form:input required="required" path="phone" type="text" class="col_4 contactfooter  number_only" placeholder="Phone Number*" /> 	
				
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
								<img src="images/ic_5.jpg" alt="img"> unihyr@facebook.com
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