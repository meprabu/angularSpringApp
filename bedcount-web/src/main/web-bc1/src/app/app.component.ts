import { Component } from '@angular/core';
import { OnInit} from '@angular/core';
import {TokenService} from '../app/services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'BedCount System';
  isTokenPresent : boolean = false;
  constructor(private _tokeService: TokenService){
  }

  ngOnInit(){
      this.checkToken();
  }

  checkToken(){
    this._tokeService.getToken().subscribe(
      bool => this.isTokenPresent = bool);
  }

}
