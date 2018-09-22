import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { IPosts } from './models/IPosts';
import { Category } from './models/category';
@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  urlamount = 'amount=';
  urlbase= 'https://opentdb.com/api.php?';
  urlcategory='&category=' 
  url2 = 'https://opentdb.com/api_category.php';
  constructor(private http: HttpClient) { }

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
