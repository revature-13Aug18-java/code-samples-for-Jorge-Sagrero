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

  checks:boolean;
  
  constructor(private _router: Router) {
    _router.events.forEach((event) => {
      if(event instanceof NavigationStart) {
        this.router = event.url !== "/";
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
