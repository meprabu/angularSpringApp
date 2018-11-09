import { Injectable } from '@angular/core';
import {Report} from '../model/report';
import { Http, Response,RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';


const REPORTS : Report[] = [
  {'locationCode':"7000","bedType":null,"orgCode":"104","orgName":null,"male":"3","female":"4","coed":"5","pendAdm":"1","pendDis":"0","locationName":"Corinth Crisis Center"},
  {"locationCode":"8000","bedType":null,"orgCode":"104","orgName":null,"male":"0","female":"0","coed":"0","pendAdm":"6","pendDis":"6","locationName":"Batesville Crisis Center"},
  {"locationCode":"0006","bedType":null,"orgCode":"106","orgName":null,"male":"1","female":"1","coed":"1","pendAdm":"1","pendDis":"1","locationName":"Bolivar County"},
  {"locationCode":"0022","bedType":null,"orgCode":"106","orgName":null,"male":"2","female":"2","coed":"2","pendAdm":"4","pendDis":"4","locationName":"Grenada County"},
  {"locationCode":"0611","bedType":null,"orgCode":"108","orgName":null,"male":"0","female":"0","coed":"0","pendAdm":"0","pendDis":"0","locationName":"Brookhaven Crisis Center"},
  {"locationCode":"0113","bedType":null,"orgCode":"113","orgName":null,"male":"0","female":"0","coed":"0","pendAdm":"6","pendDis":"6","locationName":"113 TEST"}
]

@Injectable()
export class ReportService {
  private baseUrl = "http://localhost:8080/bedcountapp/api/csureports";
  constructor(private http : Http) { }

  getAllReports() : Report[] {
    return REPORTS;
  }

  getAll(): Observable<Report[]>{
    let headers = this.getHeaders();
    console.log(headers);
    let people$ = this.http.get(`${this.baseUrl}`, {headers})
      .map(mapPersons);
      return people$;
  }


  private getHeaders(){
    // I included these headers because otherwise FireFox
    // will request text/html instead of application/json
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    let bearer = 'Bearer ' + localStorage.getItem('currentUser');
    headers.append('Authorization', bearer);
    return headers;
  }



}

  function mapPerson(response:Response): Report{
    //console.log(response);
   // toPerson looks just like in the previous example
   return toReport(response.json());
  }

  function mapPersons(response:Response): Report[]{
  // The response of the API has a results
  // property with the actual results
  console.log(response);
  return response.json().map(toReport)
}

  function toReport(r:any): Report{
  let report = <Report>({
    locationName: r.locationName,
    male : r.male,
    female: r.female,
    coed: r.coed,
    pendAdm: r.pendAdm,
    pendDis: r.pendDis
  });
  console.log('Parsed person:', report);
  return report;
  }
