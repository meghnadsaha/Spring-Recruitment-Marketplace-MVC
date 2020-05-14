<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
<style type="text/css">
	.error{color: red;}
</style>
<script type="text/javascript">
	function validateForm()
	{
		var name = $('#name').val();
		var userid = $('#userid').val();

		var phone = $('#mobileno').val();
		/* var password = $('#password').val();
		var repassword = $('#repassword').val();
		 */
		var valid = true;
		$('.error').html('&nbsp;');
		
		if(name == "")
		{
			$('.name_error').html('Please enter user name ')
			valid = false;
		}
		if(userid == "" || !isEmail(userid))
		{
			$('.userid_error').html("Please enter a valid email");
			valid = false;
		}

		if(phone == "")
		{
			$('.mobileno_error').html('Please enter mobile number ');
			valid = false;
		}
		/* if(password == "")
		{
			$('.password_error').html("please enter a valid password");
			valid = false;
		}
		
		if(repassword == "" || password != repassword)
		{
			$('.repassword_error').html("Password do not match. Please re-enter both passwords");
			valid = false;
		} */
		
		if(!valid)
		{
			return false;
		}
		
	}

	function isEmail(email) 
	{
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
	}
	
	
</script>
</head>
<body class="loading">

	<div class="mid_wrapper">
	  <div class="container">
	   
	   <div class="form_cont formContNewUser" > 
<div style="text-align: center;margin-bottom: 20px;">
	    	<h2 class="formHeading">NEW USER</h2>
	    </div>
          <form:form method="POST" action="consnewuser" commandName="cuForm"  onsubmit=" return validateForm()">
	      <div class="block">
	        <div class="form_col">
	          <dl>
	            <dt>
	              <label>Name<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <form:input path="name"  />
	              <span class='error name_error'> <form:errors path="name" /></span>
	            </dd>
	          </dl>
	          <dl>
	            <dt>
	              <label>User Id<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <form:input path="userid"  />
	              <span class='error userid_error'>${uidex }<form:errors path="userid" /></span>
	            </dd>
	          </dl>
	          
	          <dl>
	            <dt>
	              <label>Mobile no<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <form:input path="mobileno"   cssClass="number_only" maxlength="10" minlength="10"  />
	              <span class='error mobileno_error'><form:errors path="mobileno" /></span>
	            </dd>
	          </dl>
	          <%-- <dl>
	            <dt>
	              <label>Password<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <form:password path="password"  />
	              <span class='error password_error'><form:errors path="password" /></span>
	            </dd>
	          </dl>
	          <dl>
	            <dt>
	              <label>Re-Password<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <form:password path="repassword"  />
	              <span class='error repassword_error'><form:errors path="repassword" /></span>
	            </dd>
	          </dl> --%>
	          
	        </div>
	      </div>
	      
	      <div class="block form_submt alin_cnt">
	        <input type="submit" name="btn_response" value="Save User" class="btn yelo_btn">
	      </div>
	      </form:form>
	    </div>
	  </div>
	</div>
<script src="ckeditor/ckeditor.js"></script>
<script src="ckeditor/bootstrap3-wysihtml5.all.js"></script>
<script>
      $(function () {
        // Replace the <textarea id="editor1"> with a CKEditor
        // instance, using default configuration.
        CKEDITOR.replace('additionDetail');
        
      });
    </script>
</body>
</html>
