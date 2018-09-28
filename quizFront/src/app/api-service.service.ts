import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { IPosts } from './models/IPosts';
import { Category } from './models/category';
import { newAccount } from './models/newAccount';
import { LoginCredentials } from './models/loginCredentials';
import { Quiz } from './models/quiz';
import { CreateQuestion } from './models/create-question';

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {
  userId: number = 555;
  landingPage: boolean;

  urlamount = 'amount=';
  urlbase= 'https://opentdb.com/api.php?';
  urlcategory='&category=';
  url2 = 'https://opentdb.com/api_category.php';
  // newAccUrl = 'http://localhost:8080/Quiz_System_P2/createUser';
  // urlLogin = 'http://localhost:8080/Quiz_System_P2/login';
  // urlSubmitQuiz = 'http://localhost:8080/Quiz_System_P2/createQuiz';
  // quizExistsUrl = 'http://localhost:8080/Quiz_System_P2/quizExists';
  // newQuestion = 'http://localhost:8080/Quiz_System_P2/addQuestion';
  // quizListUrl = 'http://localhost:8080/Quiz_System_P2/quizList';	// Or whatever you used for port number
  // startQuizUrl = 'http://localhost:8080/Quiz_System_P2/QuizSession';
  // urlUpdateUser = 'http://localhost:8080/Quiz_System_P2/updateUser';


  newAccUrl = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/createUser';
  urlLogin = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/login';
  urlSubmitQuiz = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/createQuiz';
  quizExistsUrl = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/quizExists';
  newQuestion = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/addQuestion';
  quizListUrl = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/quizList';	// Or whatever you used for port number
  startQuizUrl = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/QuizSession';
  urlUpdateUser = 'http://ec2-34-235-120-197.compute-1.amazonaws.com:8080/Quiz_System_P2/updateUser';
  questionList: CreateQuestion[];
  currentQuestion: CreateQuestion;
  isRight: String[];
  quizBegan: boolean;


  public getQuizList() {
    return this.http.get<Quiz[]>(this.quizListUrl);
  }

  getQuestions(id): Observable<CreateQuestion[]> {
    return this.http.get<CreateQuestion[]>(this.startQuizUrl + '/' + id);
  }

  constructor(private http: HttpClient) {
    this.landingPage = true;
   }

   public updateUser(user) {
     return this.http.post<Account>(this.urlUpdateUser, user);
   }

   public quizExists(newQuizName) {
    return this.http.post(this.quizExistsUrl , newQuizName);
    }
    setValue(val) {
    this.userId = val;
    }

    getValue() {
    return this.userId ;
    }

    // i am not sure if the below method is used , -Jorge S.
  public updateInfo(login) {
    return this.http.post<newAccount>(this.newAccUrl + this.userId, login, { observe:'response'});
  }

  public addQuestion(question) {
    return this.http.post<Number>(this.newQuestion, question);
  }
  
  public createQuiz(quizzie) {
    return this.http.post(this.urlSubmitQuiz, quizzie);
  }
  public createUser(login) {
    //return this.http.post<newAccount>(this.newAccUrl, login);
    console.log("inside http createUser");
      console.log(login.username);
      console.log(login.password);
      console.log(login.displayName);
        return this.http.post<LoginCredentials>(this.newAccUrl, { username: login.username, password : login.password, displayName : login.displayName  });

  }


  public verifyCredentials(login) {
    // return this.http.post<LoginCredentials>(this.urlLogin, login);
    // console.log (login);
     return this.http.post(this.urlLogin, { username: login.username, password : login.password });
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
