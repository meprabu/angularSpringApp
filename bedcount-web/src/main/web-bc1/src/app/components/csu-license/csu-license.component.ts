import { Component, OnInit } from '@angular/core';
import {CSULicenseService} from '../../services/csuLicense.service';
@Component({
  selector: 'app-csu-license',
  templateUrl: './csu-license.component.html',
  styleUrls: ['./csu-license.component.css']
})
export class CsuLicenseComponent implements OnInit {

  selectedProvider: string;
  selectedLocation: string;
  providers: string[];
  locations: string[];
  csuLicense: string[];
  userpermit: string = localStorage.getItem('userpermit');
  constructor(private _licenseService: CSULicenseService) {
  }

  ngOnInit() {
    console.log("on the csu controller");
    var provs = this.loadProviders();
  }

  loadProviders(){
    this._licenseService.getProviders().subscribe(
      data => {
        this.providers = JSON.parse(data);
        console.log("----------this.providers[0]------------" + this.providers[0]);
      }
    );
  }

  onChangeProvider(){
    console.log("selected provider is " + this.selectedProvider);
    this._licenseService.getLocations(this.selectedProvider).subscribe(
      data => {
         this.locations = JSON.parse(data);
        console.log(this.locations);
      }
    );
  }

  onChangeLocation(){
    console.log("selected location is " + this.selectedLocation);
    if(this.selectedLocation == 'all' || this.locations.length == 1){
      this.csuLicense = this.locations;
      return;
    }
   //this.csuLicense = this.locations.filter(obj => String(obj.location_code) == this.selectedLocation);
  }

}
