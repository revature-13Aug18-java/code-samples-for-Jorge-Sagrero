import { Component, OnInit } from '@angular/core';
import { CreateQuestion } from '../../models/create-question'
import { Quiz } from '../../models/quiz';
import {ApiServiceService } from '../../api-service.service';
import { Misc } from '../../models/misc';
import { AnswerSet } from '../../models/answerSet';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

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
  nameCheck: any;
  answerModel = new AnswerSet();
  currentQuizId: Number = 0;

  constructor(private apiService: ApiServiceService, private router: Router) { }

  submit() {
    // this.quizzie.questions[this.count] = this.model;
    this.addQuestion();
   
  }
addQuestionDataBase(questionId: Number) {
    console.log("adding question: updated " +questionId);
    console.log("before: " + this.quizzie.questions);
    this.quizzie.questions +="" + questionId + ',';
    console.log("after: " + this.quizzie.questions);


}

  addQuestion() {
    
    this.model.ansSet = this.answerModel.answer + ';' + this.answerModel.answer2 + ';' + this.answerModel.answer3 + ';' + this.model.questAns;
    console.log(this.model);
    this.apiService.addQuestion(this.model).subscribe(data => {
      console.log("hey!");
      this.currentQuizId = data;
      console.log("hey, hey!");
      this.addQuestionDataBase(data); 
      console.log("hey, hey, hey!");
      console.log( this.quizzie);}, error => {}, () => {
        console.log("after data");
          this.model = new CreateQuestion();
    this.answerModel = new AnswerSet();
    this.count = this.count +1;
    console.log("amounts = " + this.amounts +" count = " + this.count);
    if (this.count == this.amounts) {
      this.count = 0;
      this.amounts = 0;
      console.log(this.quizzie);
      this.apiService.createQuiz(this.quizzie).subscribe((data) => {
        if(data === true) {
          alert("Quiz Saved!");

        }
        else{
          alert("Quiz not Saved!");

        }
      });
      this.quizzie = new Quiz();
      this.check = true;
    }
      } );
  }
  
  submitAmounts() {
    console.log(this.quizzie.quizName);
    this.apiService.quizExists(this.quizzie.quizName).subscribe((data )=> {
      if(data === true) {
        this.nameCheck = data;
        console.log(data);
        alert("Quiz name taken")
      }
      else{
        this.nameCheck = data;
        console.log(data);
        alert("Quiz name available")
        this.check = false;
    console.log(this.quizzie)
  }
      this.nameCheck = data;
      console.log(data);

    });
    // this.check = false;
    // console.log(this.quizzie)
  }

  get diagnostic() {return JSON.stringify(this.model);}
  

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

}
