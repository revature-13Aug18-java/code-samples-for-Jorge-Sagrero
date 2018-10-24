/**
 * 
 */
let privateReceiptsUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/privateReimbursements";
http://localhost:8082/p1-Jorge-Sagrero-Perez/privateReimbursements
function sendAjaxGet(url, func){
	console.log("inside privateReimbursements!");
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
	console.log("response = " + response)
	console.log("receipts = " + response.accountNumber);
	var receiptsArr = responseArr.accountNumber[0].accountNumber;
	
	let table = document.getElementById("table");

	for(i in responseArr.accountNumber){
		console.log("hi!");
		if (responseArr.accountNumber[i].receipt != 0) {
		let newRow = document.createElement("tr");
		loc = `<td> ${responseArr.firstName} </td>
		<td> ${responseArr.lastName} </td>
		<td> ${responseArr.accountNumber[i].accountNumber} </td>
		<td> ${responseArr.accountNumber[i].receipt} </td>
		<td> ${responseArr.phoneNumber} </td>
		<td> ${responseArr.accountNumber[i].amount} </td>
		<td> ${responseArr.accountNumber[i].reason} </td>`;
		if(responseArr.accountNumber[i].status == 1) {
			loc = loc + `<td> Approved </td>`;
		}
		else if(responseArr.accountNumber[i].status == 2) {
			loc = loc + `<td> Denied </td>`;

		}
		else {
			loc = loc + `<td> Pending </td>`;

		}
		loc = loc + `<td> ${responseArr.accountNumber[i].managerId} </td>`;
		newRow.innerHTML = loc;
		
		table.appendChild(newRow);
	}
	}
	}
}
