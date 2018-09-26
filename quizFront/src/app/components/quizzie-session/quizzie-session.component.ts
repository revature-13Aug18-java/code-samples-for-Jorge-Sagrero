import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../../api-service.service';
import { CreateQuestion } from '../../models/create-question';

@Component({
  selector: 'app-quizzie-session',
  templateUrl: './quizzie-session.component.html',
  styleUrls: ['./quizzie-session.component.css']
})
export class QuizzieSessionComponent implements OnInit {

  currentQuestionIndex = 0;

  currentAnswer: String;
  possibleAnswers: String[];

  constructor(private aipServe: ApiServiceService) { }

  ngOnInit() {
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
      console.log('Question ' + total);
      console.log('User Answer: ' + this.aipServe.isRight[total]);
      console.log('Real Answer: ' + this.aipServe.questionList[total].questAns);
      if (this.aipServe.isRight[total] === this.aipServe.questionList[total].questAns) {
        correct++;
      }
    }

    console.log('total: ' + total);
    console.log('correct: ' + correct);
    // Pass Fail logic here
  }

}
