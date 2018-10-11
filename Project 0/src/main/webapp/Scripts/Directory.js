/**
 * 
 */


//console.log("Hello world from directory.js");

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState==4 && this.status==200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/p0-Jorge-Sagrero-Perez/userCredentials", display);

function display(xhr){
	employees = JSON.parse(xhr.responseText);
	//console.log(employees)
	employeeArr = employees.userCredentials;
	
	let table = document.getElementById("table");
	
	for(i in employeeArr){
		let newRow = document.createElement("tr");
		
		if(employeeArr[i].accountNumber){
			loc = `${employeeArr[i].accountNumber.accountBalance}, ${employeeArr[i].accountNumber.accountNumber}`;
		} else {
			loc = "n/a";
		}
		
		
		
		newRow.innerHTML = `<td>${employeeArr[i].name} </td>
		<td> ${loc} </td> `;
		
		table.appendChild(newRow);
		
	}
}