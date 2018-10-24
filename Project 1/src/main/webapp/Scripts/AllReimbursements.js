/**
 * 
 */
let privateReceiptsUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/pendingReimbursements";
http://localhost:8082/p1-Jorge-Sagrero-Perez/privateReimbursements
function sendAjaxGet(url, func){
	console.log("inside pending Reimbursements!");
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
	
	var responseArr = response.userLog;
	console.log("response = " + response)
	console.log("receipts = " + "HELP");
	
	let table = document.getElementById("table");
	console.log(responseArr);
	console.log(responseArr[0].firstName);
	for(var b in responseArr) {
		console.log("inside outer");
	for(i in responseArr[b].accountNumber){
		if(i != 0) {
		let newRow = document.createElement("tr");
		loc = `<td> ${responseArr[b].firstName} </td>
		<td> ${responseArr[b].lastName} </td>
		<td> ${responseArr[b].accountNumber[i].accountNumber} </td>
		<td> ${responseArr[b].accountNumber[i].receipt} </td>
		<td> ${responseArr[b].phoneNumber} </td>
		<td> ${responseArr[b].accountNumber[i].amount} </td>
		<td> ${responseArr[b].accountNumber[i].reason} </td>`;
		console.log("inside for loop");

		if(responseArr[b].accountNumber[i].status == 1) {
			loc = loc + `<td> Approved </td>`;
			console.log(loc);
		}
		else if(responseArr[b].accountNumber[i].status == 2) {
			loc = loc + `<td> Denied </td>`;
			console.log(loc);

		}
		else {
			loc = loc + `<td> Pending </td>`;
			console.log(loc);

		}
		loc = loc + `<td> ${responseArr[b].accountNumber[i].managerId} </td>`;
		newRow.innerHTML = loc;
		console.log(loc);
		
		table.appendChild(newRow);
		}
	}
	}
}
