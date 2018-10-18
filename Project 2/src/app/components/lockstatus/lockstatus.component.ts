import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from  '../../api-service.service';
import { Router } from '@angular/router';
import { CreateQuestion } from '../../models/create-question';
import { Quiz } from '../../models/quiz';

@Component({
  selector: 'app-lockstatus',
  templateUrl: './lockstatus.component.html',
  styleUrls: ['./lockstatus.component.css']
})
export class LockstatusComponent implements OnInit {

  constructor(private apiService: ApiServiceService, private router: Router) { }
  
  

  quizList: Quiz[];


  
  ngOnInit() {
    var id: number = parseInt(localStorage.getItem('userId'));

    console.log(id);
    this.apiService.getQuizListByUId(id).subscribe(data => {
      this.quizList = data;

    },  error => {}, () => {
      console.log(this.quizList);
    })
    
  }
  logout() {
    this.router.navigate(['/login']);


  }

}
