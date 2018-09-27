import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from  '../../api-service.service';

@Component({
  selector: 'app-lockstatus',
  templateUrl: './lockstatus.component.html',
  styleUrls: ['./lockstatus.component.css']
})
export class LockstatusComponent implements OnInit {

  constructor(private apiService: ApiServiceService) { }

  ngOnInit() {
    this.apiService.getQuizListByUId(1);
  }

}
