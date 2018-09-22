import { Component, OnInit } from '@angular/core';
import { SystemServiceService } from '../../system-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private sysService: SystemServiceService) { }

  ngOnInit() {
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
