import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logged-in',
  templateUrl: './logged-in.component.html',
  styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

  screenName: string;
  constructor(private router: Router) { }

  

  ngOnInit() {
    this.screenName = localStorage.getItem('displayName');
    if(this.screenName  == null) {
      this.router.navigate(['/login']);

    }

  }

}
