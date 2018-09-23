import { Component, OnInit } from '@angular/core';
import { CreateQuestion } from '../../models/create-question'
import { Quiz } from '../../models/quiz';
import {ApiServiceService } from '../../api-service.service';
import { Misc } from '../../models/misc';


@Component({
  selector: 'app-questionform',
  templateUrl: './questionform.component.html',
  styleUrls: ['./questionform.component.css']
})
export class QuestionformComponent implements OnInit {

  model = new CreateQuestion();
  quizzie = new Quiz();
  amounts: number = 0;
  count: number = 0;
  check: boolean = true;

  constructor(private apiService: ApiServiceService) { }

  submit() {
    this.quizzie.questions[this.count] = this.model;
    this.model = new CreateQuestion();
    this.count = this.count +1;
    console.log("amounts = " + this.amounts +" count = " + this.count);
    if (this.count == this.amounts) {
      this.count = 0;
      this.amounts = 0;
      console.log(this.quizzie);
      this.apiService.createQuiz(this.quizzie).subscribe(data => {
        alert("Quiz Saved!");
      });
      this.quizzie = new Quiz();
      this.check = true;
    }
  }
  
  submitAmounts() {
    this.check = false;
    console.log(this.quizzie)
  }

  get diagnostic() {return JSON.stringify(this.model);}
  

  ngOnInit() {
  }

}
