jQuery(document).ready(function() {
	
	var modal = document.getElementById('rejectModal');
	
	//Get the button that opens the modal
//	var btn = document.getElementById("myBtn");
	
	//Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[1];
	
	//When the user clicks on the button, open the modal
//	btn.onclick = function() {
//		modal.style.display = "block";
//	}
	
	//When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}
	
	var btn_cancel = document.getElementsByClassName("btn-cancel")[1];
	btn_cancel.onclick = function() {
		modal.style.display = "none";
	}
	
	//When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
	
	
	
	
	
var modal1 = document.getElementById('offerModal');
	
	//Get the button that opens the modal
//	var btn = document.getElementById("myBtn");
	
	//Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	//When the user clicks on the button, open the modal
//	btn.onclick = function() {
//		modal.style.display = "block";
//	}
	
	//When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal1.style.display = "none";
	}
	
	var btn_cancel = document.getElementsByClassName("btn-cancel")[0];
	btn_cancel.onclick = function() {
		modal1.style.display = "none";
	}
	
	//When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal1) {
			modal1.style.display = "none";
		}
	}
	
	
});
	
	
