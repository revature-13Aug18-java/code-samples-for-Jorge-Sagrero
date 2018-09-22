import { Component, OnInit } from '@angular/core';
import { CreateQuestion } from '../../models/create-question'

@Component({
  selector: 'app-questionform',
  templateUrl: './questionform.component.html',
  styleUrls: ['./questionform.component.css']
})
export class QuestionformComponent implements OnInit {

  model = new CreateQuestion('black','blah','blach','bkad', 'bach');

  submitted = false;

  onSubmit() { this.submitted = true;}

  get diagnostic() {return JSON.stringify(this.model);}
  constructor() { }

  ngOnInit() {
  }

}
