import { Component, OnInit } from '@angular/core';
import { LoginCredentials } from '../../models/loginCredentials';
import { ApiServiceService } from '../../api-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginCredentials = new LoginCredentials();
  user: any;

  constructor(private apiService: ApiServiceService) {

   }

  ngOnInit() {
  }

//   The following snippet accesses the current domain's local Storage object and adds a data item to it using Storage.setItem().

// localStorage.setItem('myCat', 'Tom');
// The syntax for reading the localStorage item is as follows:

// var cat = localStorage.getItem('myCat');
// The syntax for removing the localStorage item is as follows:

// localStorage.removeItem('myCat');
// The syntax for removing all the localStorage items is as follows:

//  clear all items
// localStorage.clear();
  public submit() {
    this.apiService.verifyCredentials(this.login).subscribe((data) => {
      console.log(data);
      // if(data == )
      alert("User Verified, welcome back");}
      , error => {}, () => {


      });
  }
  
}
