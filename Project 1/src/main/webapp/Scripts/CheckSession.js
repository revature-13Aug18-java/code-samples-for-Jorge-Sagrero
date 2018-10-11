/**
 * 
 */
let checkSessionUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/session";

function sendAjaxGet(url, func){
	console.log("inside checksessions!");
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet(checkSessionUrl, populateUser);

function populateUser(xhr){
	console.log(xhr.response);
	let response = JSON.parse(xhr.response);
	if(response.accountNumber != 0){
		document.getElementById("user").innerHTML = "You are logged in as: "+ response.fName + "<br>EmployeeId: " + response.accountNumber;
	} else {
		window.location = "http://localhost:8082/p1-Jorge-Sagrero-Perez/login";
	}
}