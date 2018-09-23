import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { IPosts } from './models/IPosts';
import { Category } from './models/category';
import { newAccount } from './models/newAccount';
import { LoginCredentials } from './models/loginCredentials';
@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  urlamount = 'amount=';
  urlbase= 'https://opentdb.com/api.php?';
  urlcategory='&category=' 
  url2 = 'https://opentdb.com/api_category.php';
  newAccUrl = 'http://localhost:8083/'
  urlLogin = 'http://localhost:8083/'
  urlSubmitQuiz = 'http://localhost:8083/'
  
  constructor(private http: HttpClient) { }

  
  public createQuiz(quizzie) {
    return this.http.post<newAccount>(this.urlSubmitQuiz, quizzie);
  }
  public createUser(login) {
    return this.http.post<newAccount>(this.newAccUrl, login);
  }

  public verifyCredentials(login) {
    return this.http.post<LoginCredentials>(this.urlLogin, login);
  }

  getCats(): Observable<Category> {
    return this.http
    .get<Category>(this.url2);
  }

  getPosts(category3s, amount): Observable<IPosts> {
    var newurl = this.urlbase+ this.urlamount + amount  + this.urlcategory + category3s;
    return this.http
      .get<IPosts>(newurl);
  }
}
