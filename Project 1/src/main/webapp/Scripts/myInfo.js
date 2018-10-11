/**
 * 
 */
let privateReceiptsUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/editMyProfile";

function sendAjaxGet(url, func){
	console.log("inside editMyProfile script!");
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}


sendAjaxGet(privateReceiptsUrl, populateUser);

function populateUser(xhr){
	console.log(xhr.responseText);
	let response = JSON.parse(xhr.response);
	
	if(response.userLog != 0) {
	var responseArr = response.userLog;
	console.log(responseArr);
	
	var receiptsArr = responseArr.accountNumber[0].accountNumber;
	
	let table = document.getElementById("table");

////<!-- 
	////
//		<table id="table" class="table">
//		<tr>
//		<th class="thead-dark">First Name</th>
//		<th scope="row">Last Name</th>
//		<th scope="row">Employee Id</th>
//		<th scope="row">Email</th>
//		<th scope="row">Phone Number</th>
//		<th scope="row">Address</th>
////		-->
	
		let newRow = document.createElement("tr");
		loc = `<td> ${responseArr.firstName} </td>
		<td> ${responseArr.lastName} </td>
		<td> ${responseArr.accountNumber[0].accountNumber} </td>
		<td> ${responseArr.email} </td>
		<td> ${responseArr.phoneNumber} </td>
		<td> ${responseArr.address} </td>`;
		console.log(loc);
		newRow.innerHTML = loc;
		table.appendChild(newRow);
	
	}
}