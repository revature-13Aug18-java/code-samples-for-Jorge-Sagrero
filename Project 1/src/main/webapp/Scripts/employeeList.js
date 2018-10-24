/**
 * 
 */
let employeeListUrl = "http://localhost:8082/p1-Jorge-Sagrero-Perez/employeeList";

function sendAjaxGet(url, func){
	console.log("inside employeeListscript!");
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}


sendAjaxGet(employeeListUrl, populateUser);

function populateUser(xhr){
	console.log(xhr.responseText);
	let response = JSON.parse(xhr.response);
	
	if(response.userLog != 0) {
	responseArr = response.userLog;
	
	let table = document.getElementById("table");
	
	for(i in responseArr){
		let newRow = document.createElement("tr");
		loc = `<td> ${responseArr[i].firstName} </td>
		<td> ${responseArr[i].lastName} </td>
		<td> ${responseArr[i].accountNumber[0].accountNumber} </td>
		<td> ${responseArr[i].email} </td>
		<td> ${responseArr[i].phoneNumber} </td>
		<td> ${responseArr[i].address} </td>
		`;
		if((responseArr[i].manager)== 1) {
			loc = loc + "<td>MANAGER</td>";
		}
		else{
			loc = loc +"<td>EMPLOYEE</td>"
		}
		
		newRow.innerHTML = loc;
		
		table.appendChild(newRow);
	}
	

	}
}
