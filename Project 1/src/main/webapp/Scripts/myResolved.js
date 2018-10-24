/**
 * 
 */
let privateReceiptsUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/myResolved";
http://localhost:8082/p1-Jorge-Sagrero-Perez/privateReimbursements
function sendAjaxGet(url, func){
	console.log("inside myReimbursements!");
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
	console.log(response);
	if(response.userLog != 0) {
	var responseArr = response.userLog;
	var responseArr2 = response.userLog2;
	console.log("response = " + response)
	
	let table = document.getElementById("table");



	
	
	for(i in responseArr){
		let newRow = document.createElement("tr");
		loc = `<td> ${responseArr2.firstName} </td>
		<td> ${responseArr2.lastName} </td>
		<td> ${responseArr[0].accountNumber} </td>
		<td> ${responseArr[i].receipt} </td>
		<td> ${responseArr2.phoneNumber} </td>
		<td> ${responseArr[i].amount} </td>
		<td> ${responseArr[i].reason} </td>`;
		if(responseArr[i].status == 1) {
			loc = loc + `<td> Approved </td>`;
		}
		else if(responseArr[i].status == 2) {
			loc = loc + `<td> Denied </td>`;

		}
		else {
			loc = loc + `<td> Pending </td>`;

		}
		loc = loc + `<td> ${responseArr[i].managerId} </td>`;
		newRow.innerHTML = loc;
		
		table.appendChild(newRow);
	}
	}
}
