import { Component, OnInit } from '@angular/core';
import { SystemServiceService } from '../../system-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private sysService: SystemServiceService, private router: Router) { }
  screenName: string;

  ngOnInit() {
    this.screenName = localStorage.getItem('displayName');

    if(this.screenName  == null) {
      this.router.navigate(['/login']);

    }
  }

  logout() {
    this.router.navigate(['/login']);


  }

  moveToTaken() {

  }
  moveToFailed() {

  }
  moveToPassed() {

  }
  moveToOwned() {

  }
  createNewQuiz() {

  }
  viewAvailableQuizes() {

  }
}
