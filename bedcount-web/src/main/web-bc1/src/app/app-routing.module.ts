import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import  {BcReportsComponent} from './components/bc-reports/bc-reports.component';
import {BcRootComponent} from './components/bc-root/bc-root.component';
import {BcLoginComponent} from './components/bc-login/bc-login.component';
import {AppComponent} from '../app/app.component';
const routes: Routes = [
  {
    path: 'reports',
    component: BcReportsComponent
  },
  {
    path : 'root',
    component: BcRootComponent
  },
  {
    path : 'login',
    component: BcLoginComponent
  }

];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
