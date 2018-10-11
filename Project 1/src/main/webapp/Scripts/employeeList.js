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
	
//		th>First Name</th>
//		<th>Last Name</th>
//		<th>Employee Id</th>
//		<th>Email</th>
//		<th>Phone Number</th>
//		<th>Address</th>
//		<th>Employee/Manager</th>
	}
}