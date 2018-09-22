import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {ApiServiceService} from './api-service.service';
import { AppComponent } from './app.component';
import { GetterComponent } from './components/getter/getter.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SettingsComponent } from './components/settings/settings.component';
import { QuestionformComponent } from './components/questionform/questionform.component';
import { routes } from './app-routing/app-routing.module';
import { RoutercompComponent } from './components/routercomp/routercomp.component';
import { RouterModule } from '@angular/router';
import { QuizzieComponent } from './components/quizzie/quizzie.component';
import { CreateUserComponent } from './components/create-user/create-user.component';

@NgModule({
  declarations: [
    AppComponent,
    GetterComponent,
    LoginComponent,
    DashboardComponent,
    SettingsComponent,
    QuestionformComponent,
    RoutercompComponent,
    QuizzieComponent,
    CreateUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [ApiServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
