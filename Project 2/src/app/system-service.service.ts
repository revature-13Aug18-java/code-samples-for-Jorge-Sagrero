import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Account } from './models/account';

@Injectable({
  providedIn: 'root'
})
export class SystemServiceService {

  user: Account;
  constructor(private http: HttpClient) { }

  logIn(un: String, pw: String) {

  }

  getUserDetails() {

  }

  logOut() {

    this.user = null;
  }
}
