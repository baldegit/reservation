//console.log("E1ç");
//$(document).ready(function() {
//	console.log("E0");
//	// SUBMIT FORM
//    $("#roleForm").submit(function(event) {
//		// Prevent the form from submitting via the browser.
//		event.preventDefault();
//		ajaxPost();
//	});
//    
//    function ajaxPost(){
//    	
//    	var formData = {
//    		id: $("#id").val(),
//    		role: $("#role").val()
//    	}
//    	console.log("E1");
//    	$.ajax({
//    		type : "POST",
//    		contentType : "application/json",
//    		url: "http://localhost:8080/roles/",
//    		data : JSON.stringify(formData),
//    		dataType : 'json'
//    	}).then(function(result){
//    		
//    		if(result.status == "CREATED"){
//    			$("#postResult").html("<p> Id : "+result.data.id+" role : "+result.data.role+"</p>");
//    		}else{
//    			$("#postResult").html("<p> Error : "+result.message+" STATUS : "+result.satuts+"</p>");
//    		}
//    		
//    		resetData();
//    		
//    	}).fail(function(errors){
//    		alert("Error ! in the form");
//    		console.log("ERROR",errors.responseText);
//    		$("#postResult").html("<p> Error : "+errors.responseText+"</p>");
//    	})
//    	// Reset FormData after Posting
//    	
//    }
//    
//    function resetData(){
//    	$("#id").val("");
//    	$("#role").val("");
//    }
//    
//});