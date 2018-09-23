import { Component, OnInit } from '@angular/core';
import { LoginCredentials } from '../../models/loginCredentials';
import { ApiServiceService } from '../../api-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginCredentials = new LoginCredentials();
  user: any;

  constructor(private apiService: ApiServiceService) {

   }

  ngOnInit() {
  }

  public submit() {
    this.apiService.verifyCredentials(this.login).subscribe((data) => {
      console.log(data);
      alert("User Verified, welcome back");
    });
  }
  
}
