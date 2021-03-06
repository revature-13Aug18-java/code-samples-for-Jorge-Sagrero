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

  goback() {
    this.router.navigate(['/']);


  }
  constructor(private apiService: ApiServiceService, private router: Router) {
   
   }
   ionViewWillEnter(){
    localStorage.clear();
   }

  ngOnInit() {
    localStorage.clear();

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
//below are user object elements
// this.userId = userId;
// 		this.username = username;
// 		this.pwd = pwd;
// 		this.quizzesTaken = quizzesTaken;
// 		this.quizzesPassed = quizzesPassed;
// 		this.displayName = displayName;
  public submit() {
    this.apiService.verifyCredentials(this.login).subscribe((data) => {
      console.log(data);
      if(data == null ) {
        alert("Login Credentials Invalid");
       }
       else {
        this.user = data;
         console.log(this.user);
         console.log("quizzesTaken: " + this.user.quizzesTaken);
         console.log("quizzesPassed: " + this.user.quizzesPassed);

         localStorage.setItem('displayName', this.user.displayName );
         localStorage.setItem('userId', this.user.userId);
         localStorage.setItem('username',this.user.username);
         localStorage.setItem('quizzesTaken', this.user.quizzesTaken);
         localStorage.setItem('quizzesPassed', this.user.quizzesPassed);
         localStorage.setItem('pwd', this.user.pwd);

         alert("Welcome Back " + this.user.displayName);
         this.router.navigate(['/loggedIn']);

       }
      }
      , error => {}, () => {
        console.log(this.user);
        

      });
  }
  
}
