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
//"email": "JSAGREROPEREZ@GMAIL.COM",
//"pswrd": "PASSWORD123",
//"firstName": "JORGE",
//"lastName": "SAGRERO",
//"phoneNumber": 6614964009,
//"address": "1530 Cherry Street,Baking CA 93305",
//"manager": 0,
//"loggedIn": "FALSE",
//"accountNumber": [
//{
//"receipt": 1,
//"amount": 500,
//"reason": "BITCH YOU OWE ME",
//"status": 0,
//"managerId": 0,
//"accountNumber": 1
//},

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
//	<th class="thead-dark">First Name</th>
//	<th scope="row">Last Name</th>
//	<th scope="row">Employee Id</th>
//	<th scope="row">Receipt Number</th>
//	<th scope="row">Phone Number</th>
//	<th scope="row">Amount</th>
//	<th scope="row">Reason</th>
//	<th scope="row">Status</th>
//	<th scope="row">Manager ID</th>
//	<th scope="row">Account Number</th>
//	"receipt": 1,
//	"amount": 500,
//	"reason": "BITCH YOU OWE ME",
//	"status": 0,
//	"managerId": 0,
//	"accountNumber": 1
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