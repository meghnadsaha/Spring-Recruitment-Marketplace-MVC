jQuery(document).ready(function() {
	
	$(".length_check").keydown(function (e) {
		var val = $(this).val().length;
		var length = $(this).attr('data-length');
		if(val < length)
		{
			e.preventDefault();
		}
	});
	
	
	$(document.body).on('click', '.edit_post' ,function(){
		var pid = $(this).attr("pid");
		alertify.confirm("Are you sure you want to edit this post ?", function (e, str) {
			if (e) {
				
				window.location.href('clienteditpost?pid='+pid);
			}
		});
		
	});
	$(document.body).on('change', '#selected_post' ,function(){
		
		$('#candidate_profiles').hide();
		$('#candidate_profiles_def').show();
		
		$('#cons_list > li').removeClass("active");
		var pid = $(this).val();
		if(pid != "" && pid != "0")
		{
			$('#view_jd .view_post').attr('href',"clientPostDetail?pid="+pid);
//			$('#view_jd .view_post').attr('target',"_blank");
			$('#view_jd .view_post').removeClass('btn_disabled');
		}
		else
		{
			$('#view_jd .view_post').attr('href',"javascript:void(0)");
//			$('#view_jd .view_post').attr('target',"");
			$('#view_jd .view_post').addClass('btn_disabled');
		}
		
		$('#view_jd .view_consultant').attr('href',"javascript:void(0)");
//		$('#view_jd .view_consultant').attr('target',"");
		$('#view_jd .view_consultant').addClass('btn_disabled');
		location.href="clientpostapplicants?pid="+pid;
		
		
	});
	
	$(document.body).on('click', '#cons_list > li' ,function(){
		$('#cons_list > li').removeClass("active");
		var selected_post = $('#selected_post').val();
		if(selected_post != "" && selected_post != "0")
		{
			$(this).addClass("active");
			$('#view_jd .view_consultant').attr('href',"clientviewconsultant?consid="+$(this).attr("id"));
//			$('#view_jd .view_consultant').attr('target',"_blank");
			$('#view_jd .view_consultant').removeClass('btn_disabled');
			loadclientposts("1");
		}
		else
		{
			alertify.error("Select post first !");
		}
	});
	
	$(document.body).on('change', '#db_post_status ' ,function(){
		loadclientdashboardposts("1");
	});

	$(document.body).on('change', '.select_jd' ,function(){
		$(this).parent().siblings("input").val($(this).val());
		
	});
	$(document.body).on('change', '.select_jdaudio' ,function(){
		$(this).parent().siblings("input").val($(this).val());
		
	});
	
	$(document.body).on('click', '.profile_status > .accept_profile' ,function(){
		var selected = $(this).parent();
		var ppid = $(this).parent().attr("id");
		var data_view = $(this).parent().attr("data-view");
		alertify.confirm(" Are you sure you want to shortlist this profile ?", function (e, str) {
		if (e) {
			pleaseWait();
			$.ajax({
				type : "GET",
				url : "clientacceptreject",
				data : {'ppid':ppid,'ppstatus':'accept'},
				contentType : "application/json",
				success : function(data) {
					
					
					var obj = jQuery.parseJSON(data);
					if(obj.status == "accepted")
					{
						
					var pn=	$('.active current_page > a').html();
					loadclientposts(pn);	
				
					}
					else
					{
						alertify.error("Oops something wrong !");
					}
					pleaseDontWait();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					pleaseDontWait();
				}
			}) ;
		
		}
		});
	});
	
	$(document.body).on('click', '#rejectModal .btn-ok' ,function(){
		
//		$('#rejectModal').hide();
		var reject_type = $('.modal-body #reject_type').val();
		var reject_for = $('.modal-body #reject_for').val();
		var rej_reason = "";
		var ppstatus = "";
		
		if(reject_type == "reject_profile")
		{
			 ppstatus = "reject";
			 rej_reason = $('.sel_rej_profiel').val();
		}
		else if(reject_type == "reject_recruit")
		{
			 ppstatus = "reject_recruit";
			 rej_reason = $('.sel_rej_recruit').val();
		}
		else if(reject_type == "offer_reject")
		{
			 ppstatus = "offer_reject";
			 rej_reason = $('.sel_rej_offer').val();
		}
		var selected = $('.proile_row #'+reject_for);
		var data_view = selected.attr("data-view");
		if(rej_reason!=""){
		$('#rejectModal').hide();

		pleaseWait();
		$.ajax({
			type : "GET",
			url : "clientacceptreject",
			data : {'ppid':reject_for,'ppstatus':ppstatus,'rej_reason':rej_reason},
			contentType : "application/json",
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if(obj.status == "rejected")
				{
					var pn=	$('.current_page > a').html();
					loadclientposts(pn);
					pleaseDontWait();
				
				}
				else
				{
					var pn=	$('.current_page > a').html();
					loadclientposts(pn);
					pleaseDontWait();
				}
			},
			error: function (xhr, ajaxOptions, thrownError) {
				pleaseDontWait();
			}
		}) ;
		
		}else{

			    $('.sel_rej_join_error').html("Please select a reason.");
		}
		
	});
	
	$(document.body).on('click', '.profile_status > .recruit_profile' ,function(){
		var selected = $(this).parent();
		var ppid = $(this).parent().attr("id");
		var data_view = $(this).parent().attr("data-view");
		alertify.confirm("Are you sure you want to shortlist this candidate for the offer?", function (e, str) {
		if (e) {
pleaseWait();
			$.ajax({
				type : "GET",
				url : "clientacceptreject",
				data : {'ppid':ppid,'ppstatus':'recruit'},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "recruited")
					{

						var pn=	$('.current_page > a').html();
						loadclientposts(pn);
						/*
						if(data_view !="table")
						{
							selected.html("<p>Status : Offer </p><button class='offer_accept profile_status_button' title='Click to accept offer'>Send Offer</button><button class='btn-open profile_status_button' data-type='offer_reject' title='Click to reject offer'>Reject</button>");
						}
						else
						{
							selected.parent().parent().find('td:eq(7)').html("<span>Offer</span>");
							selected.html("<button class='btn-offer-open profile_status_button' data-type='offer_accept' title='Click to accept offer' onclick='$('#postIdForAccept').val('"+ppid+"')' >Offer Accept</button><button class='btn-open profile_status_button' data-type='offer_reject' title='Click to reject offer'>Reject</button>");
						}
					*///	alertify.success("Profile send offered successfully !");
					}
					else
					{
						var pn=	$('.current_page > a').html();
						loadclientposts(pn);
						
						//alertify.error("Oops something wrong !");
					}

				pleaseDontWait();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					pleaseDontWait();
				}
			}) ;
		
		}
		});
	});
	
	$(document.body).on('click', '.btn-offer-open' ,function()
	{
		$('#postIdForAccept').val($(this).parent().attr("id"));
	});
	
	$(document.body).on('click', '.profile_status > .reject_recruit' ,function(){
		var selected = $(this).parent();
		var ppid = $(this).parent().attr("id");
		var data_view = $(this).parent().attr("data-view");
		alertify.confirm("Are you sure you want to reject this profile ?", function (e, str) {
		if (e) {
			pleaseWait();
			$.ajax({
				type : "GET",
				url : "clientacceptreject",
				data : {'ppid':ppid,'ppstatus':'reject_recruit'},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);

					var pn=	$('.current_page > a').html();
					loadclientposts(pn);
					pleaseDontWait();
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
		
			}
		});
		
	});
	
	$(document.body).on('click', '#offerModal .btn-ok' ,function(){
		var ppid =$('#postIdForAccept').val();
		var totalCTC=0.0;
		var billableCTC=0.0;
		var joiningDate="";
		
		var flag=true;
			try{
				totalCTC=$('#totalCTC').val();
				if(totalCTC==""||(!$.isNumeric(totalCTC)))
					{
					flag=false;
					$('#errorTotalCTC').html('Please enter valid value');
					$('#errorTotalCTC').css('display','block');
					}else{
						$('#errorTotalCTC').html('');
						$('#errorTotalCTC').css('display','none');
					}
			}catch(e){
				flag=false;
				$('#errorTotalCTC').html('Please enter valid value');
				$('#errorTotalCTC').css('display','block');
			}
			try{
				billableCTC=$('#billableCTC').val();
				if(billableCTC==""||(!$.isNumeric(billableCTC)))
				{
					flag=false;
				$('#errorBillableCTC').html('Please enter valid value');
				$('#errorBillableCTC').css('display','block');
				}else{
					$('#errorBillableCTC').html('');
					$('#errorBillableCTC').css('display','none');
				}
			}catch(e){
				flag=false;
				$('#errorBillableCTC').html('Please enter valid value');
				$('#errorBillableCTC').css('display','block');
			}
			try{
						joiningDate=$('#datepicker').val();
			            var EnteredDate = joiningDate; // For JQuery
			            var date = EnteredDate.substring(8, 10);
			            var month = EnteredDate.substring(5, 7);
			            var year = EnteredDate.substring(0, 4);
			            var myDate = new Date(year, month - 1, date);
			            var today = new Date();
			            if (myDate > today) {
			                $('#errorJoiningDate').css('display','none');
			            }
			            else {
			            	flag=false;
			            	$('#errorJoiningDate').html("Joining date is less than offer accept's date.");
			                $('#errorJoiningDate').css('display','block');
			                $('#datepicker').val('');
			            }
			            if(Number(billableCTC)>Number(totalCTC)){
								flag=false;
								$('#errorTotalCTC').html('Billable CTC should be less than or equal to Total CTC.');
								$('#errorTotalCTC').css('display','block');
								}else{
									$('#errorTotalCTC').html('');
									$('#errorTotalCTC').css('display','none');
								}
			            var location=$('#location').val();
			          //  alert(location);
			            if(location==""){
			            	flag=false;
			            	$('#errorLocation').html("Please select job location");
			            	$('#errorLocation').css('display','block');
			            }else{
			            	$('#errorLocation').html("");
			            	$('#errorLocation').css('display','none');
			            }
			}catch(e){
				
			}
		if(flag){

			$('#offerModal').hide();
		var data_view = $(this).parent().attr("data-view");
		pleaseWait();
			
			$.ajax({
				type : "GET",
				url : "clientacceptreject",
				data : {'ppid':ppid,'ppstatus':'offer_accept','totalCTC':totalCTC,'billableCTC':billableCTC,'joiningDate':joiningDate,'location':location},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "offer_accept")
					{
						/*if(data_view != "table")
						{
							loadclientposts(1);
						}
						else
						{
							selected.parent().parent().find('td:eq(7)').html("<span>Offered</span>");
							selected.html("")
						}*/
						var pn=	$('.current_page > a').html();
						loadclientposts(pn);
						
					}
					else
					{
						alertify.error("Oops something wrong !");
					}
					pleaseDontWait();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					pleaseDontWait();
				}
			}) ;
		
		
		}
	});
	$(document.body).on('click', '.profile_status > .offer_reject' ,function(){
		var selected = $(this).parent();
		var ppid = $(this).parent().attr("id");
		var data_view = $(this).parent().attr("data-view");
		alertify.confirm("Are you sure to reject offer ?", function (e, str) {
		if (e) {
			pleaseWait();
			$.ajax({
				type : "GET",
				url : "clientacceptreject",
				data : {'ppid':ppid,'ppstatus':'offer_reject'},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);

					var pn=	$('.current_page > a').html();
					loadclientposts(pn);
					pleaseDontWait();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
				}
			}) ;
		
		}
		});
	});
	
	$(document.body).on('click', '.status > .st_published' ,function(){
		var sel_img = $(this);
		var pid = $(this).parent().parent().attr("id");
		alertify.confirm("Are you sure to unpublish ?", function (e, str) {
		if (e) {
			
			$.ajax({
				type : "GET",
				url : "clientunpublishpost",
				data : {'pid':pid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "success")
					{
						sel_img.removeClass("st_published");
						sel_img.addClass("st_unpublished");
						sel_img.attr("src","images/cloud-gray.png");
						alertify.success("Post unpublished successfilly !");
					}
					else
					{
						alertify.error("Oops something wrong !");
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
			
		}
		});
	});
	
	
	$(document.body).on('click', '.status > .st_unpublished' ,function(){
		var sel_img = $(this);
		var pid = $(this).parent().parent().attr("id");
		alertify.confirm("Are you sure you want to publish ?", function (e, str) {
		if (e) {
			pleaseWait();
			$.ajax({
				type : "GET",
				url : "clientpublishpost",
				data : {'pid':pid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "success")
					{
						sel_img.attr("src","images/check-cloud.png");
						sel_img.removeClass("st_unpublished");
//						sel_img.addClass("st_published");
						alertify.success("Hi, You posted "+obj.jobCode+" successfully !");
					pleaseDontWait();
					}
					else
					{
						alertify.error("Oops something wrong !");
					pleaseDontWait();
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
			
		}
		});
	});
	
	
	$(document.body).on('click', '.filter  #act_post' ,function(){
		
		var val = [];
		var valida=true;
		 $('.sel_posts:checkbox:checked').each(function(i){
	        val[i] = $(this).val();
	        var valsplit=val[i].split(',');
	        if(valsplit[1]=='active'){
				 valida=false;
	        }
	        val[i]=valsplit[0];
	      });
		 if(valida==false){
        	 alertify.error("Oops, you have selected already active post !");
			 return false;
		 }
		 
		 if(! val.length > 0)
		 {
			 alertify.error("Oops, please select any post !");
			 return false;
		 }

		 alertify.confirm("Are you sure you want to activate this post ?", function (e, str) {
				if (e) 
				{
					pleaseWait();
					$.ajax({
						type : "GET",
						url : "clientBulkActive",
						data : {'pids':val.toString()},
						contentType : "application/json",
						success : function(data) {
							var obj = jQuery.parseJSON(data);
							if(obj.status == "success")
							{
								loadclientdashboardposts($('.page_nav .current_page').attr("id"));
								alertify.success("Hi, post activate successfully !");
								/*$.ajax({
									type : "GET",
									url : "clientMailActive",
									data : {'pids':val.toString()},
									contentType : "application/json",
									success : function(data) {
										var obj = jQuery.parseJSON(data);
										if(!obj.status)
										{
											alertify.error("Oops, mail not send !");
										}
										pleaseDontWait();		
									//	location.href="";
									},
									error: function (xhr, ajaxOptions, thrownError) {
										alertify.error("Oops, mail not send !");
									}
								}) ;*/
							}
						},
						error: function (xhr, ajaxOptions, thrownError) {
					      }
				    }) ;
				}
		 	});
	});
	
	
	$(document.body).on('click', '.filter  #inact_post' ,function(){

		var val = [];
		var valida=true;
		 $('.sel_posts:checkbox:checked').each(function(i){
	        val[i] = $(this).val();
	        var valsplit=val[i].split(',');
	        if(valsplit[1]=='inactive'){
	        	 valida=false;
	        }

	        val[i]=valsplit[0];
	      });
		 if(valida==false){
			 alertify.error("Oops, you have selected already inactive post !");
			 return false;
		 }
		 
		 if(!(val.length > 0))
		 {
			 alertify.error("Oops, please select any post !");
			 return false;
		 }
		 
		alertify.confirm("Are you sure you want to inactivate this post ?", function (e, str) {
			if (e) 
			{
				pleaseWait();
				$.ajax({
					type : "GET",
					url : "clientBulkInactive",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, post inactivate successfully !");
							loadclientdashboardposts($('.page_nav .current_page').attr("id"));
						/*	
							$.ajax({
								type : "GET",
								url : "clientMailInactive",
								data : {'pids':val.toString()},
								contentType : "application/json",
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if(!obj.status)
									{
										alertify.error("Oops, mail not send !");
									}
									pleaseDontWait();
									//location.href="";
								},
								error: function (xhr, ajaxOptions, thrownError) {
									pleaseDontWait();
									alertify.error("Oops, mail not send !");
								}
							}) ;*/
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
	});
	
	$(document.body).on('click', '.filter  #del_post' ,function(){
		
		var val = [];
		

			var val = [];
			var valida=true;
			 $('.sel_posts:checkbox:checked').each(function(i){
		        val[i] = $(this).val();
		        var valsplit=val[i].split(',');
		        if(valsplit[1]=='inactive'){
		        	 valida=false;
		        }

		        val[i]=valsplit[0];
		      });
			 if(valida==false){
				 alertify.error("Oops, you have selected already inactive post !");
				 return false;
			 }
		 
		 
		 
		 
		 
		 
		 if(! val.length > 0)
		 {
			 alertify.error("Oops, please select any post !");
			 return false;
		 }
		 
		alertify.confirm("Are you sure you want to delete this post ?", function (e, str) {
			if (e) 
			{
				$.ajax({
					type : "GET",
					url : "clientBulkDelete",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, posts deleted successfully !");
							loadclientdashboardposts($('.page_nav .current_page').attr("id"));
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
	});
	
	
	
	
	$(document.body).on('click', '.page_nav  .post_inactivate' ,function(){
		var val = [];
		var pid = $(this).attr("id");
		val[0]=pid;
		alertify.confirm("Are you sure you want to inactivate this post ?", function (e, str) {
			if (e) 
			{
				pleaseWait();
				$.ajax({
					type : "GET",
					url : "clientBulkInactive",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, posts inactivate successfully !");
							$.ajax({
								type : "GET",
								url : "clientMailInactive",
								data : {'pids':val.toString()},
								contentType : "application/json",
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if(!obj.status)
									{
										alertify.error("Oops, mail not send !");
									}
									pleaseDontWait();
								},
								error: function (xhr, ajaxOptions, thrownError) {
									alertify.error("Oops, mail not send !");
								}
							}) ;
							location.reload();
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
	});
	
	
	$(document.body).on('click', '.page_nav  .post_activate' ,function(){
		var val = [];
		var pid = $(this).attr("id");
		val[0]=pid;
		alertify.confirm("Are you sure you want to activate this post ?", function (e, str) {
			if (e) 
			{
				$.ajax({
					type : "GET",
					url : "clientBulkActive",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, posts activated successfully !");
							/*$.ajax({
								type : "GET",
								url : "clientMailActive",
								data : {'pids':val.toString()},
								contentType : "application/json",
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if(!obj.status)
									{
										alertify.error("Oops, mail not send !");
									}
									
								},
								error: function (xhr, ajaxOptions, thrownError) {
									alertify.error("Oops, mail not send !");
								}
							}) ;*/
							location.reload();
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
	});
	
	$(document.body).on('click', '.page_nav  .post_close' ,function(){
		var val = [];
		var pid = $(this).attr("id");
		val[0]=pid;
		alertify.confirm("Are you sure you want to close this post ?", function (e, str) {
			if (e) 
			{pleaseWait();
				$.ajax({
					type : "GET",
					url : "clientBulkClose",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, posts closed successfully !");
							$.ajax({
								type : "GET",
								url : "clientMailClose",
								data : {'pids':val.toString()},
								contentType : "application/json",
								success : function(data) 
								{
									var obj = jQuery.parseJSON(data);
									if(!obj.status)
									{
										alertify.error("Oops, mail not send !");
									}
							pleaseDontWait();		
								},
								error: function (xhr, ajaxOptions, thrownError) {
									alertify.error("Oops, mail not send !");
								pleaseDontWait();
								}
							}) ;
							
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
	});
	
	$(document.body).on('click', '.page_nav  .post_delete' ,function(){
		var val = [];
		var pid = $(this).attr("id");
		val[0]=pid;
		alertify.confirm("Are you sure you want to delete this post ?", function (e, str) {
			if (e) 
			{
				$.ajax({
					type : "GET",
					url : "clientBulkDelete",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, posts delete successfully !");
							location.reload();
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
	});
	
	
	
	
	
	$(document.body).on('click', '.filter  #close_post' ,function(){
		
		var val = [];
		 $('.sel_posts:checkbox:checked').each(function(i){
	        val[i] = $(this).val();
	     
		 });
		 if(! val.length > 0)
		 {
			 alertify.error("Oops, please select any post !");
			 return false;
		 }
		 
		alertify.confirm("Are you sure you want to close this post ?", function (e, str) {
			if (e) 
			{
				$.ajax({
					type : "GET",
					url : "clientBulkClose",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							alertify.success("Hi, posts close request send successfully !");
							loadclientdashboardposts($('.page_nav .current_page').attr("id"));
						}
						else
						{
							alertify.success("Oops, something wrong !");
						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
		 
		});
		
	});
	
	
	
	$(document.body).on('change', '.act_status #sel_act_inact' ,function(){
		var pids = [];
		var pid = $(this).parent().parent().attr("id");
		pids[0]= pid; 
		var sel_val = $(this).val();
		var url = "";
		var msg = "";
		var mailurl = "";
		if(sel_val == "Active")
		{
			url = "clientBulkActive";
			msg = "Are you sure you want to activate this post ?";
			mailurl="clientMailActive";
		}
		else
		{
			url = "clientBulkInactive";
			msg = "Are you sure you want to inactive this post ?";
			mailurl="clientMailInactive";
		}
		
		alertify.confirm(msg, function (e, str) {
			if (e) 
			{
				pleaseWait();
				$.ajax({
					type : "GET",
					url : url,
					data : {'pids':pids.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
						/*	$.ajax({
								type : "GET",
								url : mailurl,
								data : {'pids':pids.toString()},
								contentType : "application/json",
								success : function(data) {
									var obj = jQuery.parseJSON(data);
									if(!obj.status)
									{
										alertify.error("Oops, Error occured in sending mail!");
									}else{
										alertify.success("Hi, Post "+sel_val+"  successfully !");
												
									}
									pleaseDontWait();
								},
								error: function (xhr, ajaxOptions, thrownError) {
									alertify.error("Oops, Error occured in sending mail !");
									pleaseDontWait();
								}
							}) ;*/

						}
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
					}
				}) ;
			}
			
		 
		});
		
	});
	
	
});

function getAmountInWords(num,idd){
	if($.isNumeric(num)){
	$.ajax({
		type : "GET",
		url : "getAmountInWords",
		data : {'amount':num},
		contentType : "application/json",
		success : function(data) {
			$('#'+idd).html(data +" only.");
		},
		error: function (xhr, ajaxOptions, thrownError) {
	      }
    }) ;	
	}else{
	}
	
	
	
}
function clientDeletePost(pid,status){
	var msg='Do you still want to delete this position?'; 
	if(status=='active'){
		 msg="Please note this position is ACTIVE. Deleting it would result in loss of all the details of this position. You may consider DEACTIVATING this position if you think you will re-start hiring for this position in the near future. Do you still want to delete this position?";
	 }
	if(status=='inactive')
		{
		msg='Please note this position is currently INACTIVE. Deleting it would result in loss of all the details of this position. Do you still want to delete this position?';
		}
	alertify.confirm(msg, function (e, str) {
		if (e) 
		{
			$.ajax({
				type : "GET",
				url : "clientBulkDelete",
				data : {'pids':pid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "success")
					{
						alertify.success("Hi, posts deleted successfully !");
						loadclientdashboardposts($('.page_nav .current_page').attr("id"));
					}
					
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
		}
	 
	});
}
