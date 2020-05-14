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

	<header id="header">
		<div class="Headr_top ">
			<div class="container">
				<div class="logo">
					<a href="home"><img src="images/logo.png" alt="img" border="0"></a>
				</div>
				<div class="home-nav">
					<ul style="margin-bottom: 0px; margin-top: 27px;">
						<li><a>How It Works </a></li>
						<li><a>Features</a></li>
						<li><a>Customers</a></li>
						<li><a>About</a></li>
						<!-- <li><a style="color: #f8b910;font-weight: bold;">How It Works </a></li>
					<li><a style="color: #f8b910;font-weight: bold;">Features</a></li>
					<li><a style="color: #f8b910;font-weight: bold;">Customers</a></li>
					<li><a style="color: #f8b910;font-weight: bold;">About</a></li> -->
					</ul>
				</div>
				<div class="Top_right Light13 Bold grey1 Link1">
					<a href="login">Sign In</a>
					<!--      <a href="login">Hiring Partner Sign In</a> -->
					<!-- <a href="login" style="color: #f8b910;font-weight: bold;">Employer Sign In</a>    |  
				 <a href="login" style="color: #f8b910;font-weight: bold;">Hiring Partner Sign In</a>  -->
					<!-- |  
				<a href="clientregistration">Employer Sign Up</a>    |   <a href="consultantregistration">Hiring Partner Sign Up</a> -->
				</div>
			</div>
		</div>
	</header>
	<section class="Middle">
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



		<div id="slider" style="margin-top: 0px;">
			<a href="javascript:void(0)" class="control_next"><img
				src="images/arw2.png" border="0" alt="img"></a> <a
				href="javascript:void(0)" class="control_prev"><img
				src="images/arw1.png" border="0" alt="img"></a>
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
									<p>We know that when it comes to hiring – quality and time
										are the most critical factors. UniHyr's partner network
										provides a deeper penetration in the available talent pool the
										reach and ensures that you get quality profiles in a
										disruptively shorter time</p>
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
									<p>Our user interface has been designed keeping in mind the
										users to let you navigate & manage your positions effectively</p>
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

				<p class="Fl m_r_20 White font21 opnsbld sign_title">New to
					Unihire? Sign Up</p>
				<a href="clientregistration" class="signup_btn">Employer</a> <a
					href="consultantregistration" class="signup_btn">Hiring
					Partner </a>
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
					<h2>WHAT'S IN IT FOR...</h2>
				</div>

				<div class="featserv rw">
					<ul>
						<li>

							<h3 style="font-weight: bold; margin-bottom: 25px;">Employers</h3>
							<div class="normalulli">
								<ol>
									<li><h4>GET ALL UNDER ONE ROOF</h4>
										<ul>
											<li><p>One stop shop to engage with rated
													recruitment consulting/search firms without getting into
													the hassle for managing them</p></li>
											<li><p>It’s a lot less hassle than finding,
													negotiating with, and managing multiple recruitment
													agencies directly</p></li>
										</ul></li>
									<li><h4>UNIQUE RATING SYSTEM TO ENSURE YOU KNOW WHO
											IS THE BEST</h4>
										<ul>
											<li><p>Access more agencies, quickly, so you're more
													likely to find a better candidate, faster</p></li>
											<li><p>In a competitive environment, each consultant
													strives to get the best in the quickest possible time
													leading to amazing results in time to hire and quality of
													hire</p></li>
										</ul></li>

									<li><h4>HASSLE FREE AND TRANSPARENT</h4>
										<ul>
											<li><p>Dedicated Account Manager and efficient
													Helpdesk to effectively service the clients</p></li>
											<li><p>No hassle of managing multiple recruitment
													agency contracts – clients protected by a single contract
													with UniHyr outlining simple, transparent, and universal
													terms and conditions</p></li>
										</ul></li>
									<li><h4>FREE TO USE – PAY ONLY ON OUTCOME</h4>
										<ul>
											<li><p>No signing up or usage charges. Payment to be
													made only on the hiring outcome</p></li>
											<li><p>UniHyr would provide a single interface of
													payments to consultants Easy tracking of payables and
													status of candidates</p></li>
										</ul></li>
								</ol>
							</div> <!-- 						<a href="" class="readmore">Read More</a> -->
						</li>
						<li>

							<h3 style="font-weight: bold; margin-bottom: 25px;">Hiring
								Partners</h3>
							<div class="normalulli" style="border-left: 1px solid #f1b910;">
								<ol>
									<li><h4>MORE BUSINESS!!</h4>
										<ul>
											<li><p>Significant increase in business - get to
													work for new clients without having to spend time & effort
													in acquiring them.</p></li>
											<li><p>Business expansion at finger tips – basis
													capability and bandwidth take on more and more work through
													UniHyr</p></li>
										</ul></li>
									<li><h4>UNIQUE RATING SYSTEM TO ENSURE YOU KNOW WHO
											IS THE BEST</h4>
										<ul>
											<li><p>Performance rating rewards good performance
													and leads to transparency</p></li>
											<li><p>Rating based on quantitative inputs that are
													captured real time on the platform</p>
											<li><p>No judgment or qualitative inputs leading to
													transparency</p></li>
										</ul></li>

									<li><h4>SIMPLE TO USE AND HASSLE FREE</h4>
										<ul>
											<li><p>Dedicated Account Manager and efficient
													Helpdesk to effectively service the consultants</p></li>
											<li><p>No hassle of managing multiple client
													contracts –protected by a single contract with UniHyr
													outlining simple, transparent, and universal terms and
													conditions</p></li>
										</ul></li>
									<li><h4>FREE TO USE – PAY ONLY ON OUTCOME</h4>
										<ul>
											<li><p>No signing up or usage charges. Payment to be
													made only on the hiring outcome</p></li>
											<li><p>Easy tracking of receivables and status of
													candidates</p></li>
											<li><p>Zero collection hassles</p></li>
										</ul></li>







								</ol>
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
	</div> -->

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
				<div class="col_1">
					<h3>About Us</h3>
					<p class="Lineheight1">
						Vestibulum iaculis lacinia est. Proin dictum elementum velit.
						Fusce euismod consequat ante. Lorem ipsum dolor sit amet,
						consectetuer adipiscing elit. Pellentesque sed dolor. <br />
						<br />Vestibulum iaculis lacinia est. Proin dictum elementum
						velit. Fusce euismod consequat ante. Lorem ipsum dolor sit amet,
						consectetuer adipiscing elit.
					</p>

				</div>

				<div class="col_2">
					<h3>News</h3>

					<div class="news rw">
						<ul>
							<li>
								<div class="thumbnl">
									<img src="images/img4.jpg" alt="img">
								</div>
								<div class="detail Light12 grey2">
									<p class="Light14 Link2">
										<a href="">30/09/2015</a>
									</p>
									<p>Vestibulum iaculis lacinia est. Proin dictum elementum
										velit. Fusce euismod</p>
									<p class="Link2">
										<a href="">read more...</a>
									</p>
								</div>
							</li>
							<li>
								<div class="thumbnl">
									<img src="images/img5.jpg" alt="img">
								</div>
								<div class="detail Light12 grey2">
									<p class="Light14 Link2">
										<a href="">30/09/2015</a>
									</p>
									<p>Vestibulum iaculis lacinia est. Proin dictum elementum
										velit. Fusce euismod</p>
									<p class="Link2">
										<a href="">read more...</a>
									</p>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="col_3">
					<h3>Reach Us</h3>
					<div class="rw m_b_10 Light12 grey2">
						<div class="adrs_b_1 Lineheight1">
							Unit 5/62 -64 West Ave<br /> Edinburgh Parks SA 5111<br /> PO Box
							3023<br /> Elizabeth East SA 5112
						</div>

						<div class="adrs_b_2">
							<p>
								<img src="images/ic_3.jpg" alt="img"> (08) 8283 3477
							</p>
							<p>
								<img src="images/ic_4.jpg" alt="img"> (08) 8283 3577
							</p>
							<p>
								<img src="images/ic_5.jpg" alt="img"> mail@company.com
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer_botm Align_cent Light12 grey3">
			<div class="container">
				<a href="">Terms of Use</a> | <a href="">Privacy Policy</a> | <a
					href="">Sitemap</a> | <a href="">Work with Us</a>
			</div>
		</div>
	</footer>


	<script type="text/javascript" src="js/script.js"></script>




</body>
</html>