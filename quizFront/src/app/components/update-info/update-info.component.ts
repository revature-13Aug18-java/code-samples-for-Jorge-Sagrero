import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../../api-service.service';
import { newAccount } from '../../models/newAccount';

@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrls: ['./update-info.component.css']
})
export class UpdateInfoComponent implements OnInit {

  constructor(private apiService: ApiServiceService) { }
//make sure changes are to password and displayName
  login: newAccount = new newAccount();
  
  ngOnInit() {

  }

  public submit() {
    this.apiService.updateInfo(this.login).subscribe(data => {
      alert("Info Updated Successfully");
    });
}
}
