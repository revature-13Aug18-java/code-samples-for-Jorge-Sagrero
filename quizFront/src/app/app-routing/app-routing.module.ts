import { Routes } from '@angular/router'
import { LoginComponent } from '../components/login/login.component';
import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { GetterComponent } from '../components/getter/getter.component';
import { QuestionformComponent } from '../components/questionform/questionform.component';
import { QuizzieComponent } from '../components/quizzie/quizzie.component';
import { CreateUserComponent } from '../components/create-user/create-user.component';

export const routes: Routes = [ {

  path: 'login',
  component: LoginComponent
}, {
  path: 'dashboard',
  component: DashboardComponent
}, {
  path: 'get' , 
  component: GetterComponent
}, {
  path: 'questionform' ,
  component: QuestionformComponent
}, {
  path: 'quizzie' ,
  component: QuizzieComponent
},{
  path: 'createUser' ,
  component: CreateUserComponent
},
 {
  path: '**',
  pathMatch: 'full',
  redirectTo: ''
}];
//export class AppRoutingModule { }
