import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { newAccount } from '../../models/newAccount';
import { Account } from '../../models/account';

@Component({
  selector: 'app-view-stats',
  templateUrl: './view-stats.component.html',
  styleUrls: ['./view-stats.component.css']
})
export class ViewStatsComponent implements OnInit {
  login: Account = new Account;
  passrate: any;
  constructor(private router: Router) { }
  screenName: string;
  ngOnInit() {
    this.screenName = localStorage.getItem('displayName');
    if(this.screenName  == null) {
      this.router.navigate(['/login']);
     
    }
    this.login.quizzesTaken = parseInt(localStorage.getItem('quizzesTaken'));
    this.login.quizzesPassed = parseInt(localStorage.getItem('quizzesPassed'));
    this.login.pwd = localStorage.getItem('password');
    this.login.displayName = localStorage.getItem('displayName');
    this.login.username = localStorage.getItem('username');
    this.login.userId = parseInt(localStorage.getItem('userId'));
    this.passrate = ((this.login.quizzesPassed/this.login.quizzesTaken)*100).toPrecision(3);
    console.log(this.login.displayName);
  }
  // ionViewWillEnter(){
  //   localStorage.clear();
  //  }
  
  logout() {
    this.router.navigate(['/login']);


  }
  

}
