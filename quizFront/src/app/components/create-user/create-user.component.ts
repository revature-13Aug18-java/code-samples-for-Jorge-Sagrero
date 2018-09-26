import { Component, OnInit } from '@angular/core';
import { newAccount } from '../../models/newAccount';
import { ApiServiceService } from '../../api-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  constructor(private apiService: ApiServiceService, private router: Router) { }
  account: any;
  login: newAccount = new newAccount();
  
  ngOnInit() {

  }
  public submit() {
    this.apiService.createUser(this.login).subscribe((data) => {
      console.log(data);
      if(data == null ) {
        alert("Login Credentials Invalid");
       }
       else {
         this.account = data;
         localStorage.setItem('displayName', this.account.displayName );
         localStorage.setItem('userId', this.account.userId);
         localStorage.setItem('username',this.account.username);
         alert("User Account " + this.account.displayName + " Created Successfully");
         this.router.navigate(['/loggedIn']);

       }
      }
      , error => {}, () => {
        console.log(this.account);
        
    });
  }

}
