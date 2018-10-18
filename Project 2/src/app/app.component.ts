import { Component } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quizFront';
  router: boolean;
  userHome: boolean;
  navigate: boolean;
  checks:boolean;
  
  constructor(private _router: Router) {
    _router.events.forEach((event) => {
      if(event instanceof NavigationStart) {
        this.router = event.url !== "/";
        //if current page is not app-root, hide app-root
        // this.userHome = (event.url !== "/") &&(event.url !== "/default") &&(event.url !== "/login") &&(event.url !== "/createUser")
        // //if current page is not within logged in, hide dashboard
        // this.navigate = (event.url === "/default") ||(event.url === "/login") ||(event.url === "/createUser")
      }
    });
    
   }

  ngDestroy() {

  }
  // login() {

  //   this.location = "/random"
  // }

  // createNew() {
  //   this.location = "/random"

  // }

  // default() {
  //   this.location = "/random"

  // }
  
  
}
