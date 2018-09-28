import { Component, OnInit } from '@angular/core';
import {ApiServiceService } from '../../api-service.service';
import { IPosts } from '../../models/IPosts';
import { Category } from '../../models/category';
import {Category2} from '../../models/category2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-quizzie',
  templateUrl: './user-quizzie.component.html',
  styleUrls: ['./user-quizzie.component.css']
})
export class UserQuizzieComponent implements OnInit {

  posts: IPosts;
  cats: Category;
 // holder = this.cats.trivia_categories;
  check = false;
  category3: any[];
  category3s: any[];
  printedOption: any;
  amounts: number = 0;
  userAnswer: any;
  count: number = 0;
  correctAns: number = 0;
  results: number = 0;
  alternate = false
  


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
    this.apiService.getCats().subscribe((allCats) => { console.log(allCats); this.cats = allCats;
    this.category3 = allCats.trivia_categories; });


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
  logout() {
    this.router.navigate(['/login']);


  }

  
  getAnswer(){
    if(this.userAnswer === this.posts.results[this.count].correct_answer){
      this.correctAns = this.correctAns + 1;
      console.log("Correct!")
    }
    this.alternate = !this.alternate;
    this.count = this.count + 1;
    console.log(this.userAnswer);
    if(this.count == this.amounts) {
      this.results = ((this.correctAns)/(this.amounts))*100;
      console.log("your score is " + ((this.correctAns)/(this.amounts))*100 + "%")
      this.check = false;
      this.count = 0;
      this.correctAns = 0;


    }
  
  }



}
