import { Injectable } from '@angular/core';
import {Report} from '../model/report';
import { Http, Response,RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class TokenService{

  private baseUrl = "http://localhost:8080/bedcountapp/getToken"
  constructor(private http : Http) { }

  getToken(): Observable<boolean>{
    let toeknval$ = this.http.get(`${this.baseUrl}`, {headers: this.getHeaders()})
                            .map((response: Response) => {
                              console.log('--------------------------------------------------------');
                              console.log(response.headers.get('Authorization').split(" ")[1]);
                              if(response.text()){
                                localStorage.setItem('currentUser', response.json().who);
                                localStorage.setItem('userpermit', response.json().permit);
                                localStorage.setItem('token', response.headers.get('Authorization').split(" ")[1]);
                                return true;
                              }else{
                                return false;
                              }
                            });
          return toeknval$;
  }

  //getAll(): Observable<Report[]>{
//    let people$ = this.http.get(`${this.baseUrl}`, {headers: this.getHeaders()})
  //    .map(mapPersons);
  //    return people$;
  //}
  private getHeaders(){
    // I included these headers because otherwise FireFox
    // will request text/html instead of application/json
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    return headers;
  }



}
