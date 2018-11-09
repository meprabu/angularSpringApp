import { Component, OnInit } from '@angular/core';
import {LoginDetails} from '../../model/loginDetails';


@Component({
  selector: 'app-bc-login',
  templateUrl: './bc-login.component.html',
  styleUrls: ['./bc-login.component.css']
})
export class BcLoginComponent implements OnInit {

  constructor() { }

  loginDetails : LoginDetails;

  ngOnInit() {
    localStorage.removeItem('currentUser');
    this.loginDetails = new LoginDetails();
  }

  onSubmit(){
    console.log("hello world");
    console.log(this.loginDetails);
  }

}
