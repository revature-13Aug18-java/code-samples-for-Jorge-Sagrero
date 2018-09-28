import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../../api-service.service';
import { CreateQuestion } from '../../models/create-question';
import { Router } from '@angular/router';
import { Account } from '../../models/account';
import { newAccount } from '../../models/newAccount';
//savedquizie router
@Component({
  selector: 'app-quizzie-session',
  templateUrl: './quizzie-session.component.html',
  styleUrls: ['./quizzie-session.component.css']
})
export class QuizzieSessionComponent implements OnInit {

  currentQuestionIndex = 0;

  currentAnswer: String;
  possibleAnswers: String[];

  constructor(private aipServe: ApiServiceService, private router: Router) { }
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

  beginQuiz() {
    this.aipServe.quizBegan = true;
    this.aipServe.currentQuestion = this.aipServe.questionList[this.currentQuestionIndex];
    // console.log(this.aipServe.currentQuestion);
    this.refreshSelection();
  }

  nextQuestion() {
    if (this.currentQuestionIndex + 1 >= this.aipServe.questionList.length) { return; }
    this.aipServe.isRight[this.currentQuestionIndex++] = this.currentAnswer;

    this.aipServe.currentQuestion = this.aipServe.questionList[this.currentQuestionIndex];
    // console.log(this.aipServe.currentQuestion);
    this.refreshSelection();
  }

  previousQuestion() {
    if (this.currentQuestionIndex == 0) { return; }
    this.aipServe.isRight[this.currentQuestionIndex--] = this.currentAnswer;

    this.aipServe.currentQuestion = this.aipServe.questionList[this.currentQuestionIndex];
    // console.log(this.aipServe.currentQuestion);
    this.refreshSelection();
  }

  refreshSelection() {

    console.log(this.aipServe.currentQuestion);

    this.possibleAnswers = this.aipServe.currentQuestion.ansSet.split(';');
    this.currentAnswer = this.aipServe.isRight[this.currentQuestionIndex];
    console.log(this.aipServe.isRight);
  }

  submit() {
    this.aipServe.isRight[this.currentQuestionIndex] = this.currentAnswer;
    let total = 0;
    let correct = 0;
    for (; total < this.aipServe.isRight.length; total++) {
      if(this.aipServe.isRight[total] === undefined) {
        alert("Answer all the Questions!");
        return;
      }
      console.log('Question ' + total);
      console.log('User Answer: ' + this.aipServe.isRight[total]);
      console.log('Real Answer: ' + this.aipServe.questionList[total].questAns);
      if (this.aipServe.isRight[total] === this.aipServe.questionList[total].questAns) {
        correct++;
      }
    }

    console.log('total: ' + total);
    console.log('correct: ' + correct);
    console.log(localStorage.getItem('quizzesTaken'));
    console.log(localStorage.getItem('quizzesPassed'))
    let quizzesTaken = parseInt(localStorage.getItem('quizzesTaken'));
    let quizzesPassed = parseInt(localStorage.getItem('quizzesPassed'));
    let pwd = localStorage.getItem('pwd');
    let displayName = localStorage.getItem('displayName');
    let username = localStorage.getItem('username');
    let userId = parseInt(localStorage.getItem('userId'));
    let taken;
    let passed;

    quizzesTaken = quizzesTaken +1;
    if (((correct)/total) < 0.75) {
      alert("Failed the exam! (at least 75% to pass)");
    }
    else {
      alert("You passed!");
      quizzesPassed = quizzesPassed + 1;
    }

    localStorage.setItem('quizzesPassed', ''+quizzesPassed);
    localStorage.setItem('quizzesTaken', ''+quizzesTaken);

    let user = new Account;
    user.displayName = displayName;
    user.quizzesPassed = quizzesPassed
    user.quizzesTaken = quizzesTaken;
    user.pwd = pwd;
    user.username = username;
    user.userId = userId;
    var returned: any;
    console.log("passed: " + quizzesPassed)
    console.log("taken: " + quizzesTaken);

    console.log(user);
   
    //change users to user after test
    this.aipServe.updateUser(user).subscribe((dat) => {
      returned = dat;
      console.log(dat);
    }
      , error => {}, () => {
        console.log('quizzies taken: '+ returned.quizzesTaken);
        console.log('quizzies passed: '+ returned.quizzesPassed);

      })


    this.router.navigate(['/takeQuiz']);


  }

}
