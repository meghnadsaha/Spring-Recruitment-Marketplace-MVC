function addContactUs(){
	var name=$('#name').val();
	var email=$('#email').val();
	var phone=$('#phone').val();
	var company=$('#company').val();
	var valid=true;
	if(!name){
		$('name_error').val("Please enter your name");
		valid=false;
	}
	if(!email||!isEmail(email))
	{
		$('email_error').val("Please enter valid email id");
	}
	if(!company){
		$('#company').val("Please enter company name");
		valid=false;
	}
	if(!phone||phone.length != 10||!jQuery.isNumeric(phone)){
		$('#phone_error').val("Please enter valid contact number");
		valid=false;
	}
	if(valid){
		return true;
	}
	return false;
	
}

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}