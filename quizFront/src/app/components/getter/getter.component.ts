import { Component, OnInit } from '@angular/core';
import {ApiServiceService } from '../../api-service.service';
import { IPosts } from '../../models/IPosts';
import { Category } from '../../models/category';
import {Category2} from '../../models/category2';
import { Quiz } from '../../models/quiz';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-getter',
  templateUrl: './getter.component.html',
  styleUrls: ['./getter.component.css']
})
export class GetterComponent implements OnInit {
  posts: IPosts;
  cats: Category;
 // holder = this.cats.trivia_categories;
  check = false;
  category3: any[];
  category3s: any[];
  printedOption: any;
  amounts: number = 0;

  quizList: Quiz[];

  category2: Category2[] = [
    { id: 9, name: 'General Knowledge'},
    { id: 10, name: 'Entertainment: Books'},
    { id: 11, name: 'Entertainment: Film'},
    { id: 12, name: 'Entertainment: Music'},
    { id: 13, name: 'Entertainment: Musicals & Theatres'}
  ];
  constructor(private apiService: ApiServiceService, private router: Router) { }

  screenName: string;
  ngOnInit() {
    this.screenName = localStorage.getItem('displayName');
    if(this.screenName  == null) {
      this.router.navigate(['/login']);

    }
    // this.apiService.getCats().subscribe((allCats) => { console.log(allCats); this.cats = allCats;
    // this.category3 = allCats.trivia_categories; });
      this.getQuizList();
  }



  getUsers() {
    console.log(this.amounts);
    this.apiService.getPosts(this.category3s, this.amounts).subscribe((allIPosts) => { console.log(allIPosts); this.posts = allIPosts; } );

  }

  getCategory() {
    this.check = true;
    this.printedOption = this.category3s;
    console.log(this.category3s);
    console.log(this.amounts);

    this.getUsers();
  }

  getQuizList() {
    //implement a select quiz by locked status or select quiz by userid
    this.apiService.getQuizList().subscribe((quizCollection) => {
      console.log(quizCollection);
      this.quizList = quizCollection;
     });
  }

  takeQuiz(id) {
    console.log('Selected Quiz Id: ' + id);
    this.apiService.getQuestions(id).subscribe((quests) => {
      console.log(quests);
      //gets questions
      this.apiService.questionList = quests;
      this.apiService.isRight = new Array(quests.length);
      console.log(this.apiService.isRight);
    this.apiService.currentQuestion = this.apiService.questionList[0]; });
    this.apiService.quizBegan = false;
    this.router.navigate(['/savedQuizzie']);
  }



}
