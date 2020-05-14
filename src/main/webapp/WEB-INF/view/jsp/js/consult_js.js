jQuery(document).ready(function() {
	
	$(".length_check").keydown(function (e) {
		var val = $(this).val().length;
		var length = $(this).attr('data-length');
		if(val < length)
		{
			e.preventDefault();
		}
	});
	
	
	$(document.body).on('change', '.select_jd' ,function(){
		$(this).parent().siblings("input").val($(this).val());
		
	});

	$(document.body).on('click', '#offerjoinedpopup' ,function(){
		var ppid =$('#postIdForAccept').val();
		var joiningDate=$('#datepicker').val();
        var EnteredDate = joiningDate; // For JQuery
        var date = EnteredDate.substring(8, 10);
        var month = EnteredDate.substring(5, 7);
        var year = EnteredDate.substring(0, 4);
        var myDate = new Date(year, month - 1, date);
        var today = new Date();
        var flag=true;
        if (myDate < today) {
            $('#errorJoiningDate').css('display','none');
        }
        else {
        	flag=false;
        	$('#errorJoiningDate').html("Joining date can not be greator than today's date.");
            $('#errorJoiningDate').css('display','block');
            $('#datepicker').val('');
        }
        if(flag){
		pleaseWait();
			$.ajax({
				type : "GET",
				url : "consacceptoffer",
				data : {'ppid':ppid,'ppstatus':'join_accept','joiningDate':joiningDate},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					pleaseDontWait();
					var pn=	$('.current_page > a').html();
					$('#offerModal').css('display','none');
		            $('#datepicker').val('');
					fillProfiles(pn);
//					location.reload();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					pleaseDontWait();
				}
			});
        }
	});

//	$(document.body).on('click', '#offerjoinedpopup' ,function(){
//		var ppid =$('#postIdForAccept').val();
//		var joiningDate=$('#datepicker').val();
//
//		pleaseWait();
//			$.ajax({
//				type : "GET",
//				url : "consacceptoffer",
//				data : {'ppid':ppid,'ppstatus':'join_accept','joiningDate':joiningDate},
//				contentType : "application/json",
//				success : function(data) {
//					var obj = jQuery.parseJSON(data);
//					pleaseDontWait();
//					location.href="";	
//				},
//				error: function (xhr, ajaxOptions, thrownError) {
//					pleaseDontWait();
//				}
//			});
//	});
	
	$(document.body).on('click', '#rejectModal .btn-ok' ,function(){

		
		var reject_type = $('.modal-body #reject_type').val();
		var reject_for = $('.modal-body #reject_for').val();
		
		var rej_reason = "";
		
		var ppstatus = "";
		if(reject_type == "join_reject")
		{
			 ppstatus = "join_reject";
			 rej_reason = $('.sel_rej_join').val();
		}
		if(reject_type == "candidate_withdraw")
		{
			 ppstatus = "candidate_withdraw";
			 rej_reason = $('.sel_rej_join').val();
		}
		
		var selected = $('.cons_proile_row #'+reject_for);
		var data_view = selected.attr("data-view");
		if(rej_reason==""){
		    $('.sel_rej_join_error').html("Please select a reason.");
		}else{
			$('#rejectModal').hide();
		pleaseWait();
			$.ajax({
				type : "GET",
				url : "consacceptoffer",
				data : {'ppid':reject_for,'ppstatus':ppstatus,'rej_reason':rej_reason},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "join_reject")
					{
						if(data_view != "table")
						{
							location.reload();
						}
						else
						{
							//location.reload();
							selected.parent().parent().find('td:eq(7)').html("<span>Offer Droped</span>");
							selected.html("")
						}
					}else if(obj.status == "candidate_withdraw"){
						if(data_view != "table")
						{
							location.reload();
						}
						else
						{
							//location.reload();
							selected.parent().parent().find('td:eq(7)').html("<span>Withdrawn</span>");
							selected.html("")
						}
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
	
	
	$(document.body).on('click', '.pre_check > .post_interest' ,function(){
		var row = $(this);
		var pid = $(this).attr("id");
		alertify.confirm("Are you interested for this post ?", function (e, str) {
		if (e) {
			$.ajax({
				type : "GET",
				url : "consPostInterest",
				data : {'pid':pid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "success")
					{
						row.removeClass("post_interest");
						row.html("<img src='images/int-icon.png' alt='interested'>");
						row.prop('title','You have added this post to active postions.');
						location.reload();
					}
					
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
		}
		});
	});
	

	$(document.body).on('click', '.filter  #show_interest' ,function(){
		
		var val = [];
		var comval =[];
		 $('.sel_newposts:checkbox:checked').each(function(i){
	        val[i] = $(this).val();
	        comval[i] = $('#'+val[i]).val();
	      });
	 
		 if(! val.length > 0)
		 {
			 alertify.error("Oops, please select any post !");
			 return false;
		 }

		 	alertify.confirm("By adding these positions to your active list, you agree to terms of condientiality of the client", function (e, str) {
				if (e) 
				{
					pleaseWait();
					$.ajax({
						type : "GET",
						url : "consBulkInterest",
						data : {'pids':val.toString(),'coms':comval.toString()},
						contentType : "application/json",
						success : function(data) {
							var obj = jQuery.parseJSON(data);
							if(obj.status == "success")
							{

								pleaseDontWait();
								consnewposts(1);
//								location.reload();
							}
						},
						error: function (xhr, ajaxOptions, thrownError) {
					      }
				    }) ;
				}
		 	});
	});
	

	
	$(document.body).on('click', '.filter  #close_request' ,function(){
		
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
					url : "consBulkClose",
					data : {'pids':val.toString()},
					contentType : "application/json",
					success : function(data) {
						var obj = jQuery.parseJSON(data);
						if(obj.status == "success")
						{
							
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
	
	
	
	
	$(document.body).on('click', '#postsList > li' ,function(){
		var pid=$(this).attr("id");
		$("#postsList > .active").removeClass("active");
		$(this).addClass("active");
		
		var clientId = $('#selectionOfClient').val();
		if(clientId == "")
		{
			alertify.error("Select Client first !");
			return false;
		}
		
		$('.view_id .view_post').removeClass('btn_disabled');
		$('.view_id .view_post').attr('href',"consviewjd?pid="+pid);
//		$('.view_id .view_post').attr('target','_blank');
		$('.upload_new_profile').removeClass('btn_disabled');
		
		fillProfiles("1") ;
	});
	
	$(document.body).on('change', '#cons_db_sel_client , #cons_db_sel_loc' ,function(){
		loadconsdashboardposts("1");
	});
	
	$(document.body).on('change', '#cons_db_post_status' ,function(){
		loadconsdashboardposts("1");
	});
	
	
	$(document.body).on('change', '#selectionOfClient' ,function(){
		$('#postsList > li').removeClass('active');
		$('.view_id .view_post').addClass('btn_disabled');
		$('.view_id .view_post').attr('href','javascript:void(0)');
		$('.view_id .view_post').removeAttr('target');
		$('.upload_new_profile').addClass('btn_disabled');
		fillPosts(this.value);
	});
	

	$(document.body).on('click', '.upload_new_profile' ,function(){
		var clientId = $('#selectionOfClient').val();
		var postId = $("#postsList > .active").attr("id");
		if( clientId != "" && clientId != "undefined" && postId != "" && postId != undefined)
		{
			location.href="uploadprofile?pid="+postId;
			
		}
	});
	var matched, browser;

	jQuery.uaMatch = function( ua ) {
	    ua = ua.toLowerCase();

	    var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
	        /(webkit)[ \/]([\w.]+)/.exec( ua ) ||
	        /(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
	        /(msie) ([\w.]+)/.exec( ua ) ||
	        ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
	        [];

	    return {
	        browser: match[ 1 ] || "",
	        version: match[ 2 ] || "0"
	    };
	};

	
	
	matched = jQuery.uaMatch( navigator.userAgent );
	browser = {};

	if ( matched.browser ) {
	    browser[ matched.browser ] = true;
	    browser.version = matched.version;
	}

	// Chrome is Webkit, but Webkit is also Safari.
	if ( browser.chrome ) {
	    browser.webkit = true;
	} else if ( browser.webkit ) {
	    browser.safari = true;
	}

//	jQuery.browser = browser;
//	 window.availableTags = [ '91','11' ];
//	$("#countryCode").autocomplete({source: availableTags});
});


function fillPosts(clientId) 
{

	var arcflag=$('#arcflag').val();
	$('.candidate_profiles_def').show();
	$('.candidate_profiles_for_cons').html("");
	if(clientId != "")
	{
		$.ajax({
			type : "GET",
			url : "cons_leftside_postlist",
			data : {
				'clientId' : clientId,"arcflag":arcflag
			},
			contentType : "application/json",
			success : function(data) {
				$('#cons_leftside_postlist').html(data);
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
			}
		});
		$.ajax({
			type : "GET",
			url : "cons_your_positions_blankpage",
			contentType : "application/json",
			success : function(data) {
				$('.right_side').html(data);
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
			}
		});
	}
	else
	{
		$('#postsList').html("");
	}
	
}

function consCloseRequest(pids){
	

	alertify.confirm("Are you sure you want to close this post ?", function (e, str) {
		if (e) 
		{
			$.ajax({
				type : "GET",
				url : "consBulkClose",
				data : {'pids':pids},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "success")
					{
						alertify.success("Hi, posts close request send successfully !");
						
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
	
}
function consShowInterest(pids){

 	alertify.confirm("By adding these positions to your active list, you agree to terms of condientiality of the client", function (e, str) {
		if (e) 
		{
			pleaseWait();
			$.ajax({
				type : "GET",
				url : "consBulkInterest",
				data : {'pids':pids,'coms':$('#'+pids).val()},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.status == "success")
					{
						pleaseDontWait();
							//alertify.success("Hi, Submitted Interest Successfully !");

//						consnewposts(1);
						location.reload();
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
			      }
		    }) ;
		}
 	});
}


function setCandidatureWithdraw(ppid){
//	var ppid =$('#postIdForAccept').val();
	alertify.confirm("Are you sure you want to withdraw your candidature from this position ?", function (e, str) {
		if (e) 
		{
	pleaseWait();
		$.ajax({
			type : "GET",
			url : "consacceptoffer",
			data : {'ppid':ppid,'ppstatus':'candidate_withdraw','joiningDate':''},
			contentType : "application/json",
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				pleaseDontWait();
				location.reload();
			},
			error: function (xhr, ajaxOptions, thrownError) {
				pleaseDontWait();
			}
		});
		}
	});
}