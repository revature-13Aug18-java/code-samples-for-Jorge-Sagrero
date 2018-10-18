import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../../api-service.service';
import { newAccount } from '../../models/newAccount';
import { Router } from '@angular/router';
import { Account } from '../../models/account';

@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrls: ['./update-info.component.css']
})
export class UpdateInfoComponent implements OnInit {

  constructor(private apiService: ApiServiceService, private router: Router) { }
//make sure changes are to password and displayName
  login: Account = new Account();
  
 

  screenName: string;
  ngOnInit() {
    this.screenName = localStorage.getItem('displayName');
    if(this.screenName  == null) {
      this.router.navigate(['/login']);
    }
  }

  public submit() {
    this.login.quizzesTaken = parseInt(localStorage.getItem('quizzesTaken'));
    this.login.quizzesPassed = parseInt(localStorage.getItem('quizzesPassed'));
    

    this.login.username = localStorage.getItem('username');
    this.login.userId = parseInt(localStorage.getItem('userId'));
    console.log(this.login.displayName);
    console.log(this.login.pwd);
    
    localStorage.setItem('username','' + this.login.username);

    localStorage.setItem('displayName','' + this.login.displayName);
    localStorage.setItem('pwd', ''+this.login.pwd);

    this.apiService.updateUser(this.login).subscribe(data => {
      console.log(data);
      alert("Info Updated Successfully");
    });
}
logout() {
  this.router.navigate(['/login']);


}
}