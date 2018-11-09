import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {BcRootComponent} from '../bc-root/bc-root.component';
import {ReportService} from '../../services/report.service';
import {Report} from '../../model/report';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-bc-reports',
  templateUrl: './bc-reports.component.html',
  styleUrls: ['./bc-reports.component.css']
})
export class BcReportsComponent implements OnInit {

  reports :Report[];
  errorMsg : String = '';
  constructor(private reportService: ReportService) {
  }

  ngOnInit() {
    console.log("app routing");
     this.reportService.getAll().subscribe(r => this.reports = r, e => this.errorMsg = e);
  }

}

  function handleError (error: any) {
  // log error
  // could be something more sofisticated
  let errorMsg = error.message || `Yikes! There was a problem with our hyperdrive device and we couldn't retrieve your data!`
  console.error(errorMsg);

  // throw an application level error
  return Observable.throw(errorMsg);
  }
