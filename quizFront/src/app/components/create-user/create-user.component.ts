import { Component, OnInit } from '@angular/core';
import { newAccount } from '../../models/newAccount';
import { ApiServiceService } from '../../api-service.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  constructor(private apiService: ApiServiceService) { }

  login: newAccount = new newAccount();
  
  ngOnInit() {

  }
  public submit() {
    this.apiService.createUser(this.login).subscribe(data => {
      alert("User Created Successfully");
    });
  }

}
