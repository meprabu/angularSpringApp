import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BcReportsComponent } from './components/bc-reports/bc-reports.component';
import { BcRootComponent } from './components/bc-root/bc-root.component';
import {AppRoutingModule} from './app-routing.module';
import {ReportService} from './services/report.service'
import {TokenService} from './services/token.service'
import {CSULicenseService} from './services/csuLicense.service'
import { HttpModule } from '@angular/http';
import { BcLoginComponent } from './components/bc-login/bc-login.component';
import { FormsModule } from '@angular/forms';
import { CsuLicenseComponent } from './components/csu-license/csu-license.component';
@NgModule({
  declarations: [AppComponent, BcReportsComponent, BcRootComponent, BcLoginComponent, CsuLicenseComponent],
  imports: [BrowserModule,AppRoutingModule,HttpModule,FormsModule],
  providers: [ReportService,TokenService, CSULicenseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
