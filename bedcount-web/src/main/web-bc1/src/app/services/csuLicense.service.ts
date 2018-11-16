import { Injectable } from '@angular/core';
import { Http, Response,RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';


@Injectable()
export class CSULicenseService{



    private baseUrl = "http://localhost:8080/bedcountapp"
    constructor(private http : Http) { }



    private getHeaders(){
      let headers = new Headers();
      headers.append('Accept', 'application/json');
      let bearer = 'Bearer ' + localStorage.getItem('token');
      headers.append('Authorization', bearer);
      return headers;
    }


    getProviders(): Observable<any>{
      let toeknval = this.http.get(`${this.baseUrl}/api/csuLicProv`, {headers: this.getHeaders()})
                              .map((response: Response) => {
                                console.log('--------------------------------------------------------');
                                console.log(response.text());
                                return response.text();
                              });
        return toeknval;
    }

    getLocations(provider: string): Observable<any>{
      let toeknval = this.http.get(`${this.baseUrl}/api/csuLicLocations?locid=${provider}`, {headers: this.getHeaders()})
                              .map((response: Response) => {
                                console.log('--------------------------------------------------------');
                                console.log(response.text());
                                return response.text();
                              });
        return toeknval;
    }

}
