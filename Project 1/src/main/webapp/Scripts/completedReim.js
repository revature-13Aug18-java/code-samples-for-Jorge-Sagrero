/**
 * 
 */
let privateReceiptsUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/completedReimbursements";
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
	console.log(responseArr)
	
	let table = document.getElementById("table");



	
	
	for(var b in responseArr){
		if(responseArr[b].receipt != 0) {
		let newRow = document.createElement("tr");
		var check = true;
		var check2 = true;
		var holderf = "";
		var holderl = "";
		var phone = "";
		do{
			for(i in responseArr2) {
				console.log(responseArr[b]);
				console.log("id: "+responseArr2[i].accountNumber[0].accountNumber);
				if(((responseArr2[i].accountNumber[0].accountNumber == responseArr[b].accountNumber))){
					holderf = responseArr2[i].firstName;
					holderl = responseArr2[i].lastName;
					phone = responseArr2[i].phoneNumber;
					check = false;
				}
					
					
			}
		}while(check);
		check = true;
		console.log("works AGAIN!");
		
		loc = `<td> ${holderf} </td>
		<td> ${holderl} </td>
		<td> ${responseArr[b].accountNumber} </td>
		<td> ${responseArr[b].receipt} </td>
		<td> ${phone} </td>
		<td> ${responseArr[b].amount} </td>
		<td> ${responseArr[b].reason} </td>`;
		if(responseArr[b].status == 1) {
			loc = loc + `<td> Approved </td>`;
		}
		else if(responseArr[b].status == 2) {
			loc = loc + `<td> Denied </td>`;

		}
		else {
			loc = loc + `<td> Pending </td>`;

		}
		loc = loc + `<td> ${responseArr[b].managerId} </td>`;
		newRow.innerHTML = loc;
		
		table.appendChild(newRow);
	}
	}
	}
}
